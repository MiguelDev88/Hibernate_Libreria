
package funciones;
import POJOS.*;
import java.io.BufferedReader;
import java.io.IOException;
import libreriahibernate.HibernateUtil;
import org.hibernate.Session;

// @author Miguel

public class Modificar {
    
    
    public static void modificar (BufferedReader leer) throws IOException {
        
        byte op=1;
        
        while(op!=0)
        {
            op=Menu.menuModificar(leer);
            switch (op){
                case 1:
                    Modificar.modificarAutor(leer);
                    break;
                case 2:
                    Modificar.modificarLibro(leer);
                    break;
            }
        }
        
    }
        
    public static void modificarAutor (BufferedReader leer) throws IOException {
        
        Session sesion;
        int idAutor,op,b;
        C_Autor autor;
        String nombre,nacionalidad;
        
        
        try{
            sesion=HibernateUtil.getSession();
            Consultas.verAutores(sesion);
            
            System.out.println("Introducir id del autor a modificar:");
            idAutor=Integer.parseInt(leer.readLine());

            autor=(C_Autor)sesion.get(C_Autor.class, idAutor);
            
            if(autor!=null)
            {
                System.out.println("¿Qué desea modificar?"
                                + "\n1.Nombre"
                                + "\n2.Nacionalidad");
                op=Integer.parseInt(leer.readLine());

                switch(op) {
                    case 1:
                        System.out.println("Introducir nuevo nombre:");
                        nombre=leer.readLine();
                        autor.setNombre(nombre);
                        System.out.println(" - Nombre actualizado -");
                        break;
                    case 2:
                        System.out.println("Introducir nueva nacionalidad:");
                        nacionalidad=leer.readLine();
                        autor.setNacionalidad(nacionalidad);
                        System.out.println(" - Nacionalidad actualizada -");
                        break;
                }
                
                sesion.beginTransaction();
                sesion.update(autor);
                sesion.getTransaction().commit();
                sesion.close();
                
            }else
                System.out.println("\n No se ha encontrado al autor \n");
                
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }  
    }
    
    public static void modificarLibro (BufferedReader leer) throws IOException {
        
        Session sesion;
        int precio,idLibro,op;
        String titulo;
        C_Libro libro;
        
        try{
            sesion=HibernateUtil.getSession();
            Consultas.verLibros(sesion);
            System.out.println("Introducir id del libro a modificar:");
            idLibro=Integer.parseInt(leer.readLine());
        
        
            libro=(C_Libro)sesion.get(C_Libro.class, idLibro);
            
            if(libro!=null)
            {
                System.out.println("¿Qué desea modificar?"
                                    + "1.Título"
                                    + "2.Precio");
                op=Integer.parseInt(leer.readLine());

                switch(op) {
                    case 1:
                        System.out.println("Introdcir nuevo título:");
                        titulo=leer.readLine();
                        libro.setTitulo(titulo);
                        System.out.println(" - Título actualizado -");
                        break;
                    case 2:
                        System.out.println("Introdcir nuevo precio:");
                        precio=Integer.parseInt(leer.readLine());
                        libro.setPrecio(precio);
                        System.out.println(" - Precio actualizado -");
                        break;
                }

                sesion.beginTransaction();
                sesion.update(libro);
                sesion.getTransaction().commit();
                sesion.close();
                
            }else
                System.out.println("\n No se ha encontrado el libro \n");
                
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
    }
}
