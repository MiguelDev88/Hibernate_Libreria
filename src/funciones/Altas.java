
package funciones;
import POJOS.*;
import java.io.*;
import libreriahibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

// @author Miguel

public class Altas {
    
    
    public static void altas (BufferedReader leer) throws IOException {
        
        byte op=1;
                
        while(op!=0)
        {
            op=Menu.menuAltas(leer);
            switch (op){
                case 1:
                    altaAutor(leer);
                    break;
                case 2:
                    altaLibro(leer);
                    break;
            }
        }
    }
    
    public static void altaAutor (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Autor autor;
        byte op;
        
        try{
            sesion=HibernateUtil.getSession();
            autor=nuevoAutor(leer);
            
            System.out.println("¿Desea asociar algún libro a este autor?"
                        + "\n1.SI"
                        + "\n2.NO");
            op=Byte.parseByte(leer.readLine());
            if(op==1)
                Asociar.asociarLibro(autor, sesion, leer);
            
            sesion.beginTransaction();
            sesion.save(autor);
            sesion.getTransaction().commit();
            sesion.close();
            
            System.out.println("\n - AUTOR CREADO - \n");
            
        }catch(HibernateException e){
            System.out.println("Error en la Base de Datos");
            
        }catch(Exception e){
            System.out.println("Error en la creación del Autor, no se han guardado los datos");
        } 
    }
    
    public static void altaLibro (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Libro libro;
        byte op;
        
        try{
            sesion=HibernateUtil.getSession();
            libro=nuevoLibro(leer);    
            
            System.out.println("¿Desea asociar algún autor a este libro?"
                            + "\n1.SI"
                            + "\n2.NO");
            op=Byte.parseByte(leer.readLine());
            if(op==1)
                Asociar.asociarAutor(libro, sesion, leer);
            
            sesion.beginTransaction();
            sesion.save(libro);
            sesion.getTransaction().commit();
            sesion.close();

            System.out.println("\n - LIBRO CREADO - \n");
            
        }catch(HibernateException e){
            System.out.println("Error en la Base de Datos");
            
        }catch(Exception e){
            System.out.println("Error en la creación del Libro, no se han guardado los datos");
        }
    }
    
    public static C_Autor nuevoAutor (BufferedReader leer) throws IOException {
        
        String nombre,nacionalidad;

        System.out.println("Nombre del autor:");
        nombre=leer.readLine();
        System.out.println("Nacionalidad del autor:");
        nacionalidad=leer.readLine();
        
        C_Autor autor=new C_Autor(nombre,nacionalidad);
        
        return autor;
        
    }
    
    public static C_Libro nuevoLibro (BufferedReader leer) throws IOException {
        
        String titulo;
        int precio;
        
        System.out.println("Título del libro:");
        titulo=leer.readLine();
        System.out.println("Precio del libro:");
        precio=Integer.parseInt(leer.readLine());

        C_Libro libro=new C_Libro(titulo,precio);
        
        return libro;
        
    }
    
}
