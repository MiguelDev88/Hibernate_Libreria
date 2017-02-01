
package POJOS;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


// @author Miguel
 
public class C_Autor implements Serializable {
    
    private int idAutor;
    private String nombre;
    private String nacionalidad;
    private Set <C_Libro> libros;
    
    public C_Autor (){}
    
    public C_Autor (String nombre, String nacionalidad) {
        
        //this.idAutor=id;
        this.nombre=nombre;
        this.nacionalidad=nacionalidad;
        this.libros=new HashSet<C_Libro>();
        
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Set<C_Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<C_Libro> libros) {
        this.libros = libros;
    }
    
    
    
}
