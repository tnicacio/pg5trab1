package model.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author tnica
 */
public class Categoria implements Serializable {
    
    private Integer idcategoria;
    private String descricao;
    
    public Categoria(){
    }

    public Categoria(Integer idcategoria, String descricao) {
        this.idcategoria = idcategoria;
        this.descricao = descricao;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idcategoria);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (!Objects.equals(this.idcategoria, other.idcategoria)) {
            return false;
        }
        return true;
    }
    
}
