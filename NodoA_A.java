
package reproductor;

/**
 *
 * @author Santiago
 */
public class NodoA_A {
    private ListaSimple canciones;
    private String artista;
    private NodoA_A hijoI;
    private NodoA_A hijoD;

    public NodoA_A() {
        canciones = new ListaSimple();
        artista = null;
        hijoI = null;
        hijoD = null;
    }

    public NodoA_A(String a) {
        artista = a;
        canciones = new ListaSimple();
        hijoI = null;
        hijoD = null;
    }

    public NodoA_A getHijoI() {
        return hijoI;
    }

    public NodoA_A getHijoD() {
        return hijoD;
    }

    public void setHijoI(NodoA_A nodo) {
        hijoI = nodo;
    }

    public void setHijoD(NodoA_A nodo) {
        hijoD = nodo;
    }

    public boolean esHoja() {
        return ((hijoI==null) && (hijoD==null));
    }

    public void imprimirNodo() {
        System.out.println("(" + artista + ")");
    }

    public ListaSimple getCanciones() {
        return canciones;
    }
    
    public void agregarCancion(NodoL nodo){
        canciones.InsertaFinal( nodo );
    }

    public void setCanciones(ListaSimple canciones) {
        this.canciones = canciones;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
    
    
}
