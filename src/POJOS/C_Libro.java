
package POJOS;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


// @author Miguel
 
public class C_Libro implements Serializable {
    
    private int idLibro;
    private String titulo;
    private int precio;
    private Set <C_Autor> autores;
    
    public C_Libro (){}
    
    public C_Libro (String titulo, int precio) {
        
        //this.idLibro=id;
        this.titulo=titulo;
        this.precio=precio;
        this.autores=new HashSet<C_Autor>();
        
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Set<C_Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<C_Autor> autores) {
        this.autores = autores;
    }
    
    
    
}