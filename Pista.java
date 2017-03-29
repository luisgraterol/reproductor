
package reproductor;

/**
 * @author Santiago Lossada & Luis Graterol
 */
public class Pista extends Cancion{
    
    private String nombreArchivoLetra;
    
    public Pista(){
        super();
    }

    public String getNombreArchivoLetra() {
        return nombreArchivoLetra;
    }

    public void setNombreArchivoLetra(String nombreArchivoLetra) {
        this.nombreArchivoLetra = nombreArchivoLetra;
    }
    
    
}
