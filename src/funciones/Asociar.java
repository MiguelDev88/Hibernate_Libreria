
package funciones;
import POJOS.*;
import java.io.BufferedReader;
import java.io.IOException;
import org.hibernate.Session;

// @author Miguel

public class Asociar {
    
    public static void asociarLibro (C_Autor autor, Session sesion, BufferedReader leer) throws IOException {
        
        
        byte op=1;
        C_Libro libro;
        int idLibro;
        
        while(op!=0){
            System.out.println("¿Desea asociar un Libro nuevo o uno ya existente?"
                    + "\n1.Nuevo"
                    + "\n2.Existente"
                    + "\n0.Finalizar");
            op=Byte.parseByte(leer.readLine());
            switch(op){
                case 1:
                    libro=Altas.nuevoLibro(leer);
                    autor.getLibros().add(libro);
                    System.out.println("\n - LIBRO ASOCIADO - \n");
                    break;
                case 2:
                    Consultas.verLibros(sesion);
                    System.out.println("Introducir código del libro a asociar:");
                    idLibro=Integer.parseInt(leer.readLine());
                    libro=(C_Libro)sesion.get(C_Libro.class, idLibro);
                    if(libro!=null)
                    {
                        autor.getLibros().add(libro);
                        System.out.println("\n - LIBRO ASOCIADO - \n");
                    }
                    else
                        System.out.println("\n No se ha encontrado el libro \n");
                    break;
            }
        }
        
    }
    
    public static void asociarAutor (C_Libro libro, Session sesion, BufferedReader leer) throws IOException {
        
        byte op=1;
        C_Autor autor;
        int idAutor;

        while(op!=0){
            
            System.out.println("¿Desea asociar un Autor nuevo o uno existente?"
                    + "\n1.Nuevo"
                    + "\n2.Existente"
                    + "\n0.Finalizar");
            op=Byte.parseByte(leer.readLine());
            switch(op){
                case 1:
                    autor=Altas.nuevoAutor(leer);
                    //autor.getLibros().add(libro);
                    libro.getAutores().add(autor);
                    System.out.println("\n - AUTOR ASOCIADO - \n");
                    break;
                case 2:
                    Consultas.verAutores(sesion);
                    System.out.println("Introducir código del autor a asociar:");
                    idAutor=Integer.parseInt(leer.readLine());
                    autor=(C_Autor)sesion.get(C_Autor.class, idAutor);
                    if(autor!=null)
                    {
                        autor.getLibros().add(libro);
                        libro.getAutores().add(autor);
                        System.out.println("\n - AUTOR ASOCIADO - \n");
                    }
                    else
                        System.out.println("\n No se ha encontrado el autor \n");

                    break;
            }
        }
        
    }
}
