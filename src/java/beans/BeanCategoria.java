package beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import model.dao.CategoriaDAO;
import model.dao.DaoFactory;
import model.entities.Categoria;

/**
 *
 * @author tnica
 */
@ManagedBean
public class BeanCategoria {
    
    private Integer idcategoria;
    private String descricao;
    private List<Categoria> lista = new ArrayList<>();
    
    CategoriaDAO categoriaDAO = DaoFactory.createCategoriaDao();
    
    @PostConstruct
    public void listar(){
        lista = this.findAll();
        this.setPseudoNullOptionOnCategoria();
    }
    
    public void insert(Categoria categoria){
        categoriaDAO.insert(categoria);
        updatePrivateFields(categoria);
    }
    
    public void update(Categoria categoria){
        categoriaDAO.update(categoria);
        updatePrivateFields(categoria);
    }
    
    public void deleteById(Integer id){
        categoriaDAO.deleteById(id);
        clearPrivateFields();
    }
    
    public Categoria findById(Integer id){
        Categoria categoria = categoriaDAO.findById(id);
        updatePrivateFields(categoria);
        return categoria;
    }

    public List<Categoria> findByName(String descricao){
        lista = categoriaDAO.findByName(descricao);
        this.setPseudoNullOptionOnCategoria();
        return lista;
    }
    
    public List<Categoria> findAll(){
        lista = categoriaDAO.findAll();
        this.setPseudoNullOptionOnCategoria();
        return lista;
    }
    
    private void updatePrivateFields(Categoria obj){
        idcategoria = obj.getIdcategoria();
        descricao = obj.getDescricao();
    }

    private void clearPrivateFields(){
        idcategoria = null;
        descricao = null;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Categoria> getLista() {
        return lista;
    }
    
    private void setPseudoNullOptionOnCategoria() {
        if (lista != null && lista.get(0) != null
                && lista.get(0).getIdcategoria() != 0){
            lista.add(0, new Categoria(0,"---"));
        }
    }

}
