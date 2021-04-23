package model.entities.dao;

import java.util.List;
import model.entities.Categoria;

/**
 *
 * @author tnica
 */
public interface CategoriaDAO {
    
    void insert(Categoria obj);
    void update(Categoria obj);
    void deleteById(Integer id);
    Categoria findById(Integer id);
    List<Categoria> findByName(String name);
    List<Categoria> findAll();
}
