
package reproductor;

/**
 *
 * @author Santiago
 */
public class NodoD {
    private Cancion data;
    private NodoD proximo;
    private NodoD anterior;

    public NodoD() {
            data = null;
            proximo = null;
            anterior = null;
    }

    public NodoD(Cancion data) {
            this.data = data;
            proximo = null;
    }

    public Cancion getData() {
            return data;
    }

    public void setData(Cancion data) {
            this.data = data;
    }

    public NodoD getProximo() {
            return proximo;
    }

    public void setProximo(NodoD proximo) {
            this.proximo = proximo;
    }

    public NodoD getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoD anterior) {
        this.anterior = anterior;
    }

    public void displayNodo() {
            System.out.print("{" + data +  "} ");
    }
}
