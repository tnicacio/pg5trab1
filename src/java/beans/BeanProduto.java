package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.dao.DaoFactory;
import model.dao.ProdutoDAO;
import model.entities.Produto;

/**
 *
 * @author tnica
 */

@ManagedBean
public class BeanProduto {
    
    private Integer idproduto;
    private String descricao;
    private String observacao;
    private Float preco;
    private Float estoque;
    private Integer idcategoria;
    private List<Produto> lista = new ArrayList<>();
    
    ProdutoDAO produtoDAO = DaoFactory.createProdutoDao();
    
    public void listar(){
        if (idcategoria != null && idcategoria > 0) {
            this.findByDescricaoAndIdCategoria(descricao, idcategoria);
        } else {
            this.findByName(descricao);
        }
    }
    
    public void insert(Produto produto){
        produtoDAO.insert(produto);
        updatePrivateFields(produto);
    }
    
    public void update(Produto produto){
        produtoDAO.update(produto);
        updatePrivateFields(produto);
    }
    
    public void deleteById(Integer id){
        produtoDAO.deleteById(id);
        clearPrivateFields();
    }
    
    public Produto findById(Integer id){
        Produto produto = produtoDAO.findById(id);
        updatePrivateFields(produto);
        return produto;
    }

    public List<Produto> findByName(String descricao){
        lista = produtoDAO.findByName(descricao);
        return lista;
    }
    
    public List<Produto> findAll(){
        lista = produtoDAO.findAll();
        return lista;
    }
    
    public List<Produto> findByDescricaoAndIdCategoria(String descricao, Integer idcategoria){
        lista = produtoDAO.findByDescricaoAndIdCategoria(descricao, idcategoria);
        return lista;
    }
    
    private void updatePrivateFields(Produto obj){
        idproduto = obj.getIdproduto();
        descricao = obj.getDescricao();
        observacao = obj.getObservacao();
        preco = obj.getPreco();
        estoque = obj.getEstoque();
        idcategoria = obj.getIdcategoria();
    }
    
    private void clearPrivateFields(){
        idproduto = null;
        descricao = null;
        observacao = null;
        preco = null;
        estoque = null;
        idcategoria = null;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Float getEstoque() {
        return estoque;
    }

    public void setEstoque(Float estoque) {
        this.estoque = estoque;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public List<Produto> getLista() {
        return lista;
    }
     
}
