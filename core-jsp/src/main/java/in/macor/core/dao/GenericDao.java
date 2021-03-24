/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.core.dao;

import in.macor.core.dao.iface.IGenericDao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author macorin
 */
public abstract class GenericDao<T, PK> implements IGenericDao<T, PK> {

    @SuppressWarnings("unchecked")
    private Class<T> objectClass = (Class<T>) ((ParameterizedType) getClass()
                                               .getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public void save(T vo) {
        getSession().saveOrUpdate(vo);
    }

    @Override
    public void delete(T vo) {
        getSession().delete(vo);
    }

    @Override
    public void deleteAll() {
        Query query = getSession().createQuery(
                "DELETE FROM " + objectClass.getSimpleName());
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(PK id) {
        Criteria criteria = getSession().createCriteria(objectClass);
        criteria.add(Restrictions.eq("id", id));

        return (T) criteria.uniqueResult();
    }

    @Override
    public List<T> findAll() {
        return this.findAll(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll(Order order) {
        Criteria criteria = getSession().createCriteria(objectClass);

        if (order != null) {
            criteria.addOrder(order);
        }

        return (List<T>) criteria.list();
    }

    @Override
    public Long count() {
        Criteria criteria = getSession().createCriteria(objectClass);
        criteria.setProjection(Projections.rowCount());

        return Long.valueOf(criteria.uniqueResult().toString());
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<T> findForDataTable(String hql,
                                    Map<String, Object> params,
                                    int startRow,
                                    int maxResults,
                                    String sortColumn,
                                    String sortDirection) {
        Session session = getSession();

        StringBuilder hqlBuilder = new StringBuilder();

        hqlBuilder.append(hql);
        appendOrderClause(sortColumn, sortDirection, hqlBuilder);

        Query query = session.createQuery(hqlBuilder.toString());

        Set<String> str = params.keySet();
        for (String string : str) {
            Object value = params.get(string);
            if (value instanceof Collection) {
                query.setParameterList(string, (Collection) value);
            } else {
                query.setParameter(string, value);
            }
        }

        query.setFirstResult(startRow);
        query.setMaxResults(maxResults);

        return (List<T>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findForDataTable(String sSearch,
                                           String[] columns,
                                           int startRow,
                                           int maxResults,
                                           Integer sortColumnIndex,
                                           String sortDirection,
                                           boolean virtualDelete) {
        Session session = getSession();

        String hql = createQueryDataTable(sSearch, 
                                          columns, 
                                          sortColumnIndex,
                                          sortDirection,
                                          virtualDelete);

        Query query = session.createQuery(hql);

        query.setFirstResult(startRow);
        query.setMaxResults(maxResults);

        return (List<Object[]>) query.list();
    }

    protected T findByHQL(String hql, Map<String, Object> param) {
        List<T> list = selectHQL(hql, param);

        T result = null;
        if (list != null && !list.isEmpty()) {
            result = list.get(0);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> selectHQL(String hql, Map<String, Object> param) {
        return selectGenericWithHQL(hql, param);
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion criterion) {
        Criteria criteria = getSession().createCriteria(objectClass);
        if (criterion == null) {
            return null;
        } else {
            criteria.add(criterion);
        }
        return (List<T>) criteria.list();
    }

    @SuppressWarnings("rawtypes")
    protected List selectGenericWithHQL(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);

        Set<String> str = params.keySet();

        for (String string : str) {
            Object value = params.get(string);
            if (value instanceof Collection) {
                query.setParameterList(string, (Collection) value);
            } else {
                query.setParameter(string, value);
            }
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByCriteriaWithOrder(List<Criterion> criterion,
                                              Order order) {
        Criteria criteria = getSession().createCriteria(objectClass);
        if (criterion == null) {
            return null;
        }
        List<Criterion> list = criterion;
        for (Criterion c : list) {
            criteria.add(c);
        }
        if (order != null) {
            criteria.addOrder(order);
        }
        return (List<T>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByCriteriaWithOrder(Criterion criterion, Order order) {
        Criteria criteria = getSession().createCriteria(objectClass);

        if (criterion == null) {
            return null;
        }
        criteria.add(criterion);
        if (order != null) {
            criteria.addOrder(order);
        }

        return (List<T>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    protected T findByCriteriaFirstResult(Criterion criterion) {
        Criteria criteria = getSession().createCriteria(objectClass);
        if (criterion == null) {
            return null;
        }
        criteria.add(criterion);
        return (T) criteria.uniqueResult();
    }

    private String createQueryDataTable(String sSearch,
                                        String[] columns,
                                        Integer sortColumnIndex,
                                        String sortDirection,
                                        boolean virtualDelete) {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT ");

        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append("t.");
            builder.append(columns[i]);
        }

        builder.append(" FROM ");
        builder.append(objectClass.getSimpleName());
        builder.append(" t");

        appendWhereClause(columns, sSearch, builder, virtualDelete);

        if (sortColumnIndex != null && sortColumnIndex < columns.length) {
            appendOrderClause("t." + columns[sortColumnIndex], sortDirection,
                              builder);
        }

        return builder.toString();
    }

    private void appendWhereClause(String[] columns,
                                   String search,
                                   StringBuilder builder,
                                   boolean virtualDelete) {
        String or = " WHERE ";
        
        if (virtualDelete) {
            builder.append(or);
            builder.append(" t.situacao = 1 ");
            or = " AND ";
        }

        if (search != null && !search.isEmpty()) {
            for (String column : columns) {
                builder.append(or);

                builder.append(" lower(cast(").append("t.");
                builder.append(column);
                builder.append(" as string))").append(" LIKE ").append("'%");
                builder.append(search);
                builder.append("%' ");

                or = " OR ";
            }
        }
    }

    private void appendOrderClause(String sortColumn, String sortDirection,
                                   StringBuilder builder) {
        if (sortColumn != null) {
            builder.append(" ORDER BY ");
            builder.append(sortColumn);
            builder.append((sortDirection != null && sortDirection
                            .equalsIgnoreCase("DESC")) ? " DESC" : " ASC");
        }
    }

    protected abstract Session getSession();
}
