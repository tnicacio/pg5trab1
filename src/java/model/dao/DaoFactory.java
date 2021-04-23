package model.dao;

import db.DB;
import model.dao.impl.CategoriaDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;

/**
 *
 * @author tnica
 */
public class DaoFactory {
    
    public static CategoriaDAO createCategoriaDao(){
         return new CategoriaDaoJDBC(DB.getConnection());
    }

    public static ProdutoDAO createProdutoDao(){
         return new ProdutoDaoJDBC(DB.getConnection());
    }
    
}
