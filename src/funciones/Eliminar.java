
package funciones;
import POJOS.*;
import java.io.BufferedReader;
import java.io.IOException;
import libreriahibernate.HibernateUtil;
import org.hibernate.Session;

// @author Miguel

public class Eliminar {
       
    
    public static void eliminar (BufferedReader leer) throws IOException {
        
        byte op=1;

        while(op!=0)
        {
            op=Menu.menuEliminar(leer);
            switch (op){
                case 1:
                    eliminarAutor(leer);
                    break;
                case 2:
                    eliminarLibro(leer);
                    break;
            }
        }

    }
        
    public static void eliminarAutor (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Autor autor;
        int idAutor;
        
        try{
            sesion=HibernateUtil.getSession();
            Consultas.verAutores(sesion);
            System.out.println("Introduzca Código del autor a eliminar:");
            idAutor=Integer.parseInt(leer.readLine());

            autor=(C_Autor)sesion.get(C_Autor.class, idAutor);

            if(autor !=null)
            {
                if(Menu.menuConfirmar(leer)==1){
                    sesion.beginTransaction();
                    sesion.delete(autor);
                    sesion.getTransaction().commit();
                }
            }else
                System.out.println("\n No se ha encontrado al autor \n");
            
            sesion.close();
            
        }catch(Exception e){
            
            System.out.println("Error al borrar");
        }            
    }
           
    public static void eliminarLibro (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Libro libro;
        int idLibro;
        
        try{
            sesion=HibernateUtil.getSession();
            Consultas.verLibros(sesion);
            System.out.println("Introduzca Código del libro a eliminar:");
            idLibro=Integer.parseInt(leer.readLine());

            libro=(C_Libro)sesion.get(C_Libro.class, idLibro);
            if(libro!=null)
            {
                if(Menu.menuConfirmar(leer)==1){
                    sesion.beginTransaction();
                    sesion.delete(libro);
                    sesion.getTransaction().commit();
                }
            }else
                System.out.println("No se ha encontrado el libro");
            
            sesion.close();
            
        }catch(Exception e){
            
            System.out.println("Error al borrar");
        }
    }
    
}
