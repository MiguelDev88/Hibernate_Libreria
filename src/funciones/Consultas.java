
package funciones;
import POJOS.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import libreriahibernate.HibernateUtil;
import org.hibernate.Session;


// @author Miguel

public class Consultas {
    
    
    public static void consulta (BufferedReader leer) throws IOException {
        
        Session sesion=HibernateUtil.getSession();
        byte op=1;
        
        try{

            while(op!=0)
            {
                op=Menu.menuConsultas(leer);
                switch (op){
                    case 1:
                        verAutores(sesion);
                        break;
                    case 2:
                        verLibros(sesion);
                        break;
                    case 3:
                        consultarAutor(leer);
                        break;
                    case 4:
                        consultarLibro(leer);
                        break;
                }

            }
            
            sesion.close();
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
  
    }
    
    public static void consultarAutor (BufferedReader leer) throws IOException {
        
        String nombre;
        Session sesion;
        C_Autor autor;
        
        System.out.println("Nombre del autor a buscar:");
        nombre=leer.readLine();
        
        try{
            sesion=HibernateUtil.getSession();
            List listaAutores=sesion.createQuery("FROM "+C_Autor.class.getName()+" A WHERE A.nombre='"+nombre+"'").list();
            Iterator autores=listaAutores.iterator();
            
            while(autores.hasNext())
            {
                autor=(C_Autor)autores.next();
                verLibrosAutor(autor);
            }
            sesion.close();
         
            
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void consultarLibro (BufferedReader leer) throws IOException {
        
        Session sesion;
        String titulo;
        C_Libro libro;
        
        
        System.out.println("Título del libro a buscar:");
        titulo=leer.readLine();
        
        try{
            sesion=HibernateUtil.getSession();
            List listaLibros=sesion.createQuery("FROM "+C_Libro.class.getName()+" L WHERE L.titulo='"+titulo+"'").list();
            Iterator libros=listaLibros.iterator();
            
            while(libros.hasNext())
            {
                libro=(C_Libro)libros.next();
                verAutoresLibro(libro);
            }
            sesion.close();
         
            
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
    }
     
    public static void verAutores (Session sesion) throws IOException {
        
        C_Autor autor;
        
        try{
            List<C_Autor> listaAutores = sesion.createCriteria(C_Autor.class).list();
            Iterator autores=listaAutores.iterator();
            
            System.out.println("----------------------------------------");
            System.out.println("| ID_AUTOR |   NOMBRE   | NACIONALIDAD |");
            
            while(autores.hasNext())
            {
                autor=(C_Autor)autores.next();
                System.out.printf("|  %5s   |%10s  | %10s   |%n",autor.getIdAutor(),autor.getNombre(),autor.getNacionalidad());
            }
            
            System.out.println("----------------------------------------");                   
         
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void verLibros (Session sesion) throws IOException {
        
        C_Libro libro;
        
        try{
            
            List listaLibros=sesion.createCriteria(C_Libro.class).list();
            Iterator libros=listaLibros.iterator();
            
            System.out.println("------------------------------------------------");
            System.out.println("| ID_LIBRO |          TITULO          | PRECIO |");
            
            while(libros.hasNext()){
                libro=(C_Libro)libros.next();
                System.out.printf("|  %5s   |  %22s  | %3s €  | %n",libro.getIdLibro(),libro.getTitulo(),libro.getPrecio());
            }
            System.out.println("------------------------------------------------");

            
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void verLibrosAutor (C_Autor autor) throws IOException {
        
        Iterator libros=autor.getLibros().iterator();
        System.out.println("LIBROS DEL AUTOR  "+autor.getNombre());
        System.out.println("------------------------------------------------");
        System.out.println("| ID_LIBRO |          TITULO          | PRECIO |");
        
        while(libros.hasNext())
            {
                C_Libro libro=(C_Libro)libros.next();
                System.out.printf("|  %5s   |  %22s  | %3s €  |%n",libro.getIdLibro(),libro.getTitulo(),libro.getPrecio());
            }
            System.out.println("------------------------------------------------");
        
    }
    
    public static void verAutoresLibro (C_Libro libro) throws IOException {
        
        
        Iterator autores=libro.getAutores().iterator();
        System.out.println("AUTORES DEL LIBRO "+libro.getTitulo());
        System.out.println("----------------------------------------");
        System.out.println("| ID_AUTOR |   NOMBRE   | NACIONALIDAD |");

        while(autores.hasNext()){
            C_Autor autor=(C_Autor)autores.next();
            System.out.printf("|  %5s   |%10s  | %10s   |%n",autor.getIdAutor(),autor.getNombre(),autor.getNacionalidad());
        }
        System.out.println("----------------------------------------");
        
    }

}
