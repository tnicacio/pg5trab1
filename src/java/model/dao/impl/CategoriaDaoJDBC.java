package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE categoria set "
                    + "descricao = ? "
                    + "where idcategoria = ?");
            st.setString(1, obj.getDescricao());
            st.setInt(2, obj.getIdcategoria());

            st.executeUpdate();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE from categoria where id = ?");
            st.setInt(1, id);
            st.executeUpdate();

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Categoria findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("select c.* "
                    + "from categoria c "
                    + "where c.idcategoria = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if(rs.next()){
                Categoria obj = instantiateCategoria(rs);
                return obj;
            }
            return null;
            
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Categoria> findByName(String descricao) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            String sql = "select c.* from categoria c "
                      + "where upper(c.descricao) like upper('%" + descricao + "%')";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            List<Categoria> categorias = new ArrayList<>();
            while (rs.next()){
                Categoria categoria = instantiateCategoria(rs);
                categorias.add(categoria);
            }
            return categorias;
            
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Categoria> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
        Categoria obj = new Categoria();
        obj.setIdcategoria(rs.getInt("idcategoria"));
        obj.setDescricao(rs.getString("descricao"));
        return obj;
    }
    
}
