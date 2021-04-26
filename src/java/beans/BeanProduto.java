package beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.dao.CategoriaDAO;
import model.dao.DaoFactory;
import model.dao.ProdutoDAO;
import model.entities.Categoria;
import model.entities.Produto;

/**
 *
 * @author tnica
 */

@ManagedBean
@ViewScoped
public class BeanProduto {
    
    private Integer idproduto;
    private String descricao = "";
    private String observacao;
    private Float preco;
    private Float estoque;
    private Integer idcategoria;
    
    private boolean isFiltrarCategoria;
    private String categoriaDescricao;

    public Boolean getIsFiltrarCategoria() {
        return isFiltrarCategoria;
    }

    public void setIsFiltrarCategoria(Boolean isFiltrarCategoria) {
        this.isFiltrarCategoria = isFiltrarCategoria;
    }
    private List<Produto> lista = new ArrayList<>();
    
    ProdutoDAO produtoDAO = DaoFactory.createProdutoDao();
    CategoriaDAO categoriaDAO = DaoFactory.createCategoriaDao();
    
    @PostConstruct
    public void init(){
        findByName(descricao);
    }
    
    public void listar(){
        if (isFiltrarCategoria && idcategoria != null && idcategoria > 0) {;
            findByDescricaoAndIdCategoria(descricao, idcategoria);
        } else {
            findByName(descricao);
        }
    }
    
    public String redirectEditar(Integer idproduto){
        if (!isValidInteger(idproduto)) {
            return "";
        }
        return "cadastros?faces-redirect=true&idproduto=" + idproduto;
    }
    
    public void loadFieldsByIdProduto(Integer idproduto){
        if (isValidInteger(idproduto)){
            findById(idproduto);
        }
    }
    
    public String excludeItem(Integer idproduto){
        String returnPath = "";
        if (isValidInteger(idproduto)) {
            deleteById(idproduto);
            returnPath = "index?faces-redirect=true";
        }
        return returnPath;
    }
    
    public String salvar(Integer idprodutoparam){
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;
        String redirectTo = "";

        if (descricao == null || descricao.trim().equals("")) {
            msg = new FacesMessage("Informe a Descrição");
            view.addMessage(null, msg);
        }

        if (idcategoria == null || idcategoria == 0) {
            msg = new FacesMessage("Selecione a Categoria");
            view.addMessage(null, msg);
        } 

        if (preco == null) {
            msg = new FacesMessage("Informe o Preço");
            view.addMessage(null, msg);
        }
        
        if (estoque == null) {
            msg = new FacesMessage("Informe a Quantidade em Estoque");
            view.addMessage(null, msg);
        }
        
        if (observacao == null || observacao.trim().equals("")) {
            msg = new FacesMessage("Informe a Observação");
            view.addMessage(null, msg);
        }

        if (msg == null) {
            try {
                Produto prod = new Produto(null, descricao, observacao, preco, estoque, idcategoria);

                if (isValidInteger(idprodutoparam)) {
                    prod.setIdproduto(idprodutoparam);
                    update(prod);
                    msg = new FacesMessage("Produto atualizado com sucesso");
                } else {
                    insert(prod);
                    msg = new FacesMessage("Produto salvo com sucesso");
                }
                
                view.addMessage(null, msg);
                redirectTo = "index?faces-redirect=true";
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return redirectTo;
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
    
    public String getCategoriaDescricao(Integer idcat){
        String desc = "";
        if (idcat == null){
            return desc;
        } 
        Categoria cat = categoriaDAO.findById(idcat);
        if (cat != null) {
            desc = cat.getDescricao();
        }
        return desc;
    }
    
    private boolean isValidInteger(Integer number){
        return (number instanceof Integer && number >0);
    }

}
