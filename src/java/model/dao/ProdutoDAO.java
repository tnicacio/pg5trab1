package model.dao;

import java.util.List;
import model.entities.Produto;

/**
 *
 * @author tnica
 */
public interface ProdutoDAO {
    
    void insert(Produto obj);
    void update(Produto obj);
    void deleteById(Integer id);
    Produto findById(Integer id);
    List<Produto> findByName(String name);
    List<Produto> findAll();
    List<Produto> findByCategoriaId(Integer idcategoria);

}
