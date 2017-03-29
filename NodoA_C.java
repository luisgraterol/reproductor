
package reproductor;

/**
 * @author Luis Alejandro Graterol
 * 18 de marzo de 2017
 */
public class NodoA_C {

    private Cancion dato;
    private NodoA_C hijoI;
    private NodoA_C hijoD;

    public NodoA_C() {
        dato = null;
        hijoI = null;
        hijoD = null;
    }

    public NodoA_C(Cancion dato) {
        this.dato = dato;
        hijoI = null;
        hijoD = null;
    }

    public NodoA_C getHijoI() {
        return hijoI;
    }

    public NodoA_C getHijoD() {
        return hijoD;
    }

    public void setHijoI(NodoA_C nodo) {
        hijoI = nodo;
    }

    public void setHijoD(NodoA_C nodo) {
        hijoD = nodo;
    }

    public Cancion getDataA() {
        return dato;
    }

    public boolean esHoja() {
        return ((hijoI==null) && (hijoD==null));
    }

    public int imprimirNodo(int cont) {
        System.out.print(cont+". ");
        System.out.println(dato.getTitulo());
        return (cont+1);
    }
    
    public void imprimirNodo() {
        System.out.println(dato.getTitulo());
    }
}
