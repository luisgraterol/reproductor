
package reproductor;
import java.io.Serializable;

/**
 * @author Santiago Lossada & Luis Graterol
 */

public class Cancion implements Serializable {
    protected String titulo;
    protected String interprete;
    protected int track;
    protected String album;
    protected int duracion;
    protected boolean esVideo;
    
    public Cancion(){
        
    }
    
    public Cancion(String titulo, String interprete, String album, int track, int duracion){
        this.titulo = titulo;
        this.interprete = interprete;
        this.album = album;
        this.track = track;
        this.duracion = duracion;
    }
    
    @Override
    public String toString() {
        return titulo + "     -     " + interprete + "     -     " + album + "     -     " + track + "     -     " + duracion + "\n";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean esVideo() {
        return esVideo;
    }

    public void setEsVideo(boolean esVideo) {
        this.esVideo = esVideo;
    }
    
    
}
