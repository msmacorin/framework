/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.core.dao;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;

/**
 *
 * @author macorin
 * @param <T>
 * @param <PK>
 */
public interface IDao<T, PK> {

    public void save(T vo);

    public void delete(PK vo);

    public T findById(PK id);
    
    public T find(String query);

    public List<T> findAll(Order... orders);
    
    public List<T> select(String query);
    
    public T findOneResult(SimpleExpression... expressions);

    public Long count(Criterion... criterions);

    public List<T> selectPagination(String hql,
            int startRow,
            int maxResults,
            String sortColumn,
            String sortDirection);

    public List<T> selectForListBean(String[] columns,
            int startRow,
            int maxResults,
            String sortColumn,
            String sortDirection,
            Map<String, String> filters,
            boolean virtualDelete);
}
