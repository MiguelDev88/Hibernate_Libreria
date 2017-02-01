
package libreriahibernate;
import funciones.*;
import java.io.*;


// @author Miguel

public class Libreria {

    public static void main(String[] args) {
        
        BufferedReader leer= new BufferedReader(new InputStreamReader(System.in));
        int op=0;

        do{
            try{
                op=Menu.menuInicio(leer);
                switch(op){
                    case 1:
                        CrearTablas.CrearTablas();
                        break;
                    case 2:
                        Altas.altas(leer);
                        break;
                    case 3:
                        Eliminar.eliminar(leer);
                        break;
                    case 4:
                        Modificar.modificar(leer);
                        break;
                    case 5:
                        Consultas.consulta(leer);
                        break;
                    case 0:
                        System.out.println("--FIN DEL PROGRAMA--");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                }
            
            }catch (IOException e){

                System.out.println(e.getMessage());
            }catch (RuntimeException e){

                System.out.println(e.getMessage());
            }catch (Exception e){

                System.out.println(e.getMessage());
            }catch (Throwable e){
                System.out.println("ERROR EN SESSION FACTORY");
                System.exit(0);
            }
        }while(op!=0);

    }
    
}
