/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.core.dao.iface;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Order;

/**
 *
 * @author macorin
 */
public interface IGenericDao<T, PK> {

    public void save(T vo);
    
    public void delete(T vo);

    public void deleteAll();

    public T findById(PK id);

    public List<T> findAll();

    public List<T> findAll(Order order);

    public Long count();

    public List<T> findForDataTable(String hql, 
                                    Map<String, Object> params, 
                                    int startRow, 
                                    int maxResults, 
                                    String sortColumn, 
                                    String sortDirection);

    public List<Object[]> findForDataTable(String sSearch, 
                                           String[] columns, 
                                           int startRow, 
                                           int maxResults, 
                                           Integer sortColumnIndex, 
                                           String sortDirection,
                                           boolean virtualDelete);
}
