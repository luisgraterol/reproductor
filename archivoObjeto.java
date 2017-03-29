
package reproductor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


public class archivoObjeto {
 
    /**
     * Metodo: deserializar
     * Construye el objeto a partir del archivo dado.
     * @param nombreArchivo de tipo String.
     * @return un objeto que puede ser de cualquier clase.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserializar(String nombreArchivo) throws IOException, ClassNotFoundException {
            FileInputStream fileIS = new FileInputStream(nombreArchivo);
            BufferedInputStream bufferIS = new BufferedInputStream(fileIS);
            ObjectInputStream objectIS = new ObjectInputStream(bufferIS);
            Object objeto = objectIS.readObject();
            objectIS.close();
            return objeto;
    }
 
    /**
     * Metodo: serializar
     * Toma un objeto, lo serializa y lo guarda en un archivo dado.
     * @param objeto a ser serializado.
     * @param nombreArchivo donde sera serializado el objeto.
     * @throws IOException
     */
    public static void serializar(Object objeto, String nombreArchivo) throws IOException {
            FileOutputStream fileOS = new FileOutputStream(nombreArchivo);
            BufferedOutputStream bufferOS = new BufferedOutputStream(fileOS);
            ObjectOutputStream objectOS = new ObjectOutputStream(bufferOS);
            objectOS.writeObject(objeto);
            objectOS.close();
    }
}
