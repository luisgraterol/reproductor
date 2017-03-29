package reproductor;
import java.io.Serializable;

/**
*	
*	class CnodoL
*
*	@author David Delgado
*	@version 1.0  Mayo 2002
*/
public class NodoL implements Serializable {
	private Cancion data;
	private NodoL proximo;
	
	public NodoL() {
		data = null;
		proximo = null;
	}
	
	public NodoL(Cancion data) {
		this.data = data;
		proximo = null;
	}
	
	public Cancion getData() {
		return data;
	}
	
	public void setData(Cancion data) {
		this.data = data;
	}
	
	public NodoL getProximo() {
		return proximo;
	}
	
	public void setProximo(NodoL proximo) {
		this.proximo = proximo;
	}
	
	public void imprimirNodo(int cont) {
            System.out.print(cont+". ");
            System.out.print(data.toString());
	}
        public void imprimirNodo() {
            System.out.print(data.toString());
	}
}