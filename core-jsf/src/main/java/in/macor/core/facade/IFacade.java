/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.core.facade;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Order;

/**
 *
 * @author macorin
 * @param <T>
 * @param <PK>
 */
public interface IFacade<T, PK> {
    
    public void salvar(T entity);
    public void deletar(PK id);
    public T buscarPorId(PK id);
    public List<T> selecionarTodos();
    public List<T> selecionarTodos(Order... ordem);
    public List<T> selecionarListBean(String[] camposListBean, 
            int linhaInicial, 
            int maximoResultados, 
            String colunaOrdenacao, 
            String direcaoOrdenacao,
            Map<String, String> filtros);
    public boolean exclusaoVirtual();
    
}
