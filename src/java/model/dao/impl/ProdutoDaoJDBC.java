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
import model.dao.ProdutoDAO;
import model.entities.Produto;

/**
 *
 * @author tnica
 */
public class ProdutoDaoJDBC implements ProdutoDAO {
    
    private Connection conn;
    
    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO produto(descricao, observacao, preco, estoque, idcategoria) " +
                    "values(?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getDescricao());
            st.setString(2, obj.getObservacao());
            st.setFloat(3, obj.getPreco());
            st.setFloat(4, obj.getEstoque());
            st.setInt(5, obj.getIdcategoria());
            
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setIdproduto(id);
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
    public void update(Produto obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE produto set "
                    + "descricao = ?, "
                    + "observacao = ?, "
                    + "preco = ?, "
                    + "estoque = ?, "
                    + "idcategoria = ? "
                    + "where idproduto = ?");
            st.setString(1, obj.getDescricao());
            st.setString(2, obj.getObservacao());
            st.setFloat(3, obj.getPreco());
            st.setFloat(4, obj.getEstoque());
            st.setInt(5, obj.getIdcategoria());
            st.setInt(6, obj.getIdproduto());

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
            st = conn.prepareStatement("DELETE from produto where idproduto = ?");
            st.setInt(1, id);
            st.executeUpdate();

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Produto findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("select p.* "
                    + "from produto p "
                    + "where p.idproduto = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if(rs.next()){
                Produto obj = instantiateProduto(rs);
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
    public List<Produto> findByName(String descricao) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            String sql = "select p.* from produto p "
                      + "where upper(p.descricao) like upper('%" + descricao + "%') "
                      + "order by p.descricao";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()){
                Produto produto = instantiateProduto(rs);
                produtos.add(produto);
            }
            return produtos;
            
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Produto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            String sql = "select p.* from produto p "
                      + "order by p.descricao";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()){
                Produto produto = instantiateProduto(rs);
                produtos.add(produto);
            }
            return produtos;
            
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    
    @Override
    public List<Produto> findByDescricaoAndIdCategoria(String descricao, Integer idcategoria) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            String sql = "select p.* from produto p "
                      + "where upper(p.descricao) like upper('%" + descricao + "%') "
                      + "and p.idcategoria = " + idcategoria + " "
                      + "order by p.descricao";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()){
                Produto produto = instantiateProduto(rs);
                produtos.add(produto);
            }
            return produtos;
            
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    
    private Produto instantiateProduto(ResultSet rs) throws SQLException {
        Produto obj = new Produto();
        obj.setIdproduto(rs.getInt("idproduto"));
        obj.setDescricao(rs.getString("descricao"));
        obj.setObservacao(rs.getString("observacao"));
        obj.setPreco(rs.getFloat("preco"));
        obj.setEstoque(rs.getFloat("estoque"));
        obj.setIdcategoria(rs.getInt("idcategoria"));
        return obj;
    }
}
