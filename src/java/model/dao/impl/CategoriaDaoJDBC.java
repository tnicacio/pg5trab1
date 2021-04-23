package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.dao.CategoriaDAO;
import model.entities.Categoria;

/**
 *
 * @author tnica
 */
public class CategoriaDaoJDBC implements CategoriaDAO {
    
    private Connection conn;
    
    public CategoriaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Categoria obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO categoria(descricao) " +
                    "values(?)";
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getDescricao());
            
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setIdcategoria(id);
                }
            } else {
                throw new DbException("Unexpected error! No rows were affected!");  
            }
            
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        } 
    }

    @Override
    public void update(Categoria obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
