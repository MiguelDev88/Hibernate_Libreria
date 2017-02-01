
package libreriahibernate;
import java.sql.*;


// @author Miguel
 
public class CrearTablas {
    
    public static void CrearTablas () {
        
        try{
            
            Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3307/?user=root&password=usbw");
            Statement sentencia=conexion.createStatement();

            sentencia.execute("CREATE DATABASE IF NOT EXISTS LIBRERIA_HIBERNATE_MIGUEL;");
            sentencia.execute("USE LIBRERIA_HIBERNATE_MIGUEL;");


            sentencia.execute("CREATE TABLE IF NOT EXISTS AUTORES"+ 
                              "(idAutor INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"+
                              "nombre VARCHAR(30) NOT NULL,"+
                              "nacionalidad VARCHAR(30) NOT NULL,"+
                              "PRIMARY KEY (idAutor))"+
                              "ENGINE INNODB;");

            sentencia.execute("CREATE TABLE IF NOT EXISTS LIBROS "+
                              "(idLibro INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"+
                              "titulo VARCHAR(30) NOT NULL,"+
                              "precio INT NOT NULL,"+
                              "PRIMARY KEY (idLibro))"+
                              "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS LIBROS_AUTORES "+
                              "(autor INT(4) UNSIGNED NOT NULL, "+
                              "libro INT(4) UNSIGNED NOT NULL, "+
                              "PRIMARY KEY (autor,libro), "+
                              "CONSTRAINT fk1_autor"+
                                 " FOREIGN KEY (autor) references AUTORES (idAutor)"+
                                 " ON DELETE CASCADE "+
                                 " ON UPDATE CASCADE ,"+
                              "CONSTRAINT fk2_libro"+
                                 " FOREIGN KEY (libro) references LIBROS (idLibro)"+
                                 " ON DELETE CASCADE"+
                                 " ON UPDATE CASCADE)"+
                              "ENGINE INNODB;");
            conexion.close();
            
            System.out.println("-- BASE DE DATOS LISTA --");

        }catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
    }
    
}
