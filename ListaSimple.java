package reproductor;
import java.io.Serializable;

/**
*		
*	class ListaSimple
*	@author David Delgado
*	@version 1.0  Mayo 2002
*       
*       Refactored by Luis Graterol.
*
*/
public class ListaSimple implements Serializable {
	private NodoL cabeza;
	private NodoL cola;
	
	public ListaSimple() {
		cabeza = null;
		cola = null;
	}
        
        public ListaSimple(NodoL nodo) {
		cabeza = nodo;
		cola = null;
	}
	
	public boolean estaVacia() {
		return (cabeza==null);
	}
        
	public NodoL getCabeza() {
		return cabeza;
	}
        
	public NodoL getCola(){
		return cola;
	}
        
        public int getTamano(){
            if (cabeza==null)
                return 0;
            else
            {
                NodoL aux=cabeza;
                int cont=0;
                
                while(aux!=null)
                {
                    aux=aux.getProximo();
                    cont++;
                }
                return cont;
            }
        }
        
	public void setCabeza(NodoL nodo){
		cabeza = nodo;
	}
        
	public void setCola(NodoL nodo){
		cola = nodo;
	}
        
	public void InsertaPrimero(NodoL nuevo) {
		if (cabeza == null)
		{
			cabeza = nuevo;
			cola = nuevo;
		}
		else
		{
			nuevo.setProximo(cabeza);
			cabeza = nuevo;
		}
	}
	
	public void InsertaFinal(NodoL nuevo) {
		if (cabeza == null)
		{
			cabeza = nuevo;
			cola = nuevo;
		}
		else
		{
			NodoL temp = cola;
			temp.setProximo(nuevo);
			cola = nuevo;
			cola.setProximo(null);
		}
	}
	
	public NodoL EliminarPrimero() {
		if (cabeza != null)
		{
			NodoL temp  = cabeza;
			cabeza = cabeza.getProximo();
			temp.setProximo(null);
			return temp;
		}
		else
		{
			return cabeza;
		}
	}
	
	public NodoL EliminaFinal() {
	if (cabeza != null)
		{
		if (cabeza.getProximo() == null)
		{
			NodoL temp = cabeza;
			cabeza = null;
			cola = null;
			return temp;
		}
		else
		{
			NodoL temp  = cabeza;
			while (temp.getProximo().getProximo() != null)
			{
				temp = temp.getProximo();
			}
			
			NodoL auxi = temp.getProximo();
			cola = temp;
			cola.setProximo(null);
			return auxi;
		}
	}
	else 
	{
			return cabeza;
		}
	}
	
	public void InsertaPos(NodoL nuevo,int pos) {
	if (cabeza == null) 
	{
		cabeza = nuevo;
		cola =	nuevo;
	}
	else
		{
		if (pos == 1)
		{
		nuevo.setProximo(cabeza);
		cabeza = nuevo;
		}		
		else
		{
			if (cabeza.getProximo() == null)
			{
				InsertaFinal(nuevo);
			}
			else
			{
				
				NodoL temp = cabeza;
				int cont = 1;
				while ((cont < (pos - 1)) && (temp.getProximo() != null))
				{
					cont++;
					temp = temp.getProximo();
				}
				if (temp.getProximo() != null)
				{
					nuevo.setProximo(temp.getProximo());
					temp.setProximo(nuevo);
				}
				else
				{
					temp.setProximo(nuevo);
				}
			}
		}
	}
}
	
	public NodoL EliminaPos(int x) {
	if (cabeza != null)
		{
		NodoL temp = new NodoL();
		NodoL siguiente = new NodoL();
		NodoL auxi = new NodoL();
		int pos = 1;
		temp = cabeza;
		siguiente = cabeza.getProximo();
		
		if (cabeza.getProximo() == null)
		{
			auxi = cabeza;
			cabeza = null;
			return auxi;
		}
		else
		{
			
			if (x == 1)
			{
				auxi = EliminarPrimero();
				return auxi;
			}
			else
			{
				while (pos < x-1)
				{
					temp = siguiente;
					siguiente = siguiente.getProximo();
					pos++;
				}
				auxi = temp.getProximo();
				temp.setProximo(siguiente.getProximo());
				return auxi;
			}
		}
	}
	else
		{
			return cabeza;
		}
	}

	public void imprimirLista() {
		NodoL esteNodo = cabeza;
		int cont=1;
		while (esteNodo != null)
		{
			esteNodo.imprimirNodo(cont);
			esteNodo = esteNodo.getProximo();
                        cont++;
		}
	}
        
        
        
        public void InsertarOrdenadoTitulo(NodoL nodo){
            if(cabeza == null) {
                cabeza = nodo;
                cola = nodo;
            } else {
                NodoL aux = cabeza;
                int cont = 1;
                
                while((menorTitulo(aux, nodo) == aux) && (aux.getProximo() != null)){
                    aux = aux.getProximo();
                    cont++;
                }
                
            }
        }
        
        public void InsertarOrdenadoAlbum(NodoL nodo){
            if(cabeza == null) {
                cabeza = nodo;
                cola = nodo;
         } else {
                NodoL aux = cabeza;
                int cont = 1;
                
                while((menorAlbum(aux, nodo) == aux) && (aux.getProximo() != null)){
                    aux = aux.getProximo();
                    cont++;
                }

                InsertaPos(nodo, cont); 
            }
        }
        
        public void InsertarOrdenadoNro(NodoL nodo){
            if(cabeza == null) {
                cabeza = nodo;
                cola = nodo;
            } else {
                NodoL aux = cabeza;
                int cont = 1;
                
                while(aux!= null){
                    if (aux.getData().getTrack() <= nodo.getData().getTrack())
                    {
                        aux = aux.getProximo();
                        cont++;
                    }
                    else
                        break;
                }
                
                InsertaPos(nodo, cont); 
            }
        }
        
        public void InsertarOrdenadoDuracion(NodoL nodo){
            if(cabeza == null) {
                cabeza = nodo;
                cola = nodo;
            } else {
                NodoL aux = cabeza;
                int cont = 1;
                
                while (aux != null){
                    if(aux.getData().getDuracion() <= nodo.getData().getDuracion())
                    {
                        aux = aux.getProximo();
                        cont++;
                    }
                    else
                        break;
                }
                
                InsertaPos(nodo, cont); 
            }
        }
        
        
        public NodoL menorTitulo(NodoL nodo1, NodoL nodo2){
        
        String w1 = nodo1.getData().getTitulo();
        String w2 = nodo2.getData().getTitulo();
        char[] first  = w1.toLowerCase().toCharArray();
        char[] second = w2.toLowerCase().toCharArray();

        int minLength = Math.min(first.length, second.length);
        int counter = 0;
        for(int i = 0; i < minLength; i++)
        {
                if (first[i] == second[i])
                {
                    counter++;    
                }
                else
                    break;
        }
        if(counter == first.length)
            return nodo1;
        else if (counter == second.length)
            return nodo2;
        else if(first[counter] < second[counter])
            return nodo1;
        else
            return nodo2;
    }
        
        public NodoL menorAlbum(NodoL nodo1, NodoL nodo2){
        
            String w1 = nodo1.getData().getAlbum();
            String w2 = nodo2.getData().getAlbum();
            char[] first  = w1.toLowerCase().toCharArray();
            char[] second = w2.toLowerCase().toCharArray();

            int minLength = Math.min(first.length, second.length);
            int counter = 0;
            for(int i = 0; i < minLength; i++)
            {
                if (first[i] == second[i])
                {
                    counter++;    
                }
                else
                    break;
            }
            if(counter == first.length)
                return nodo1;
            else if (counter == second.length)
                return nodo2;
            else if(first[counter] < second[counter])
                return nodo1;
            else
                return nodo2;
        }
        
        
        
    
    
}
