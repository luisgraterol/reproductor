
package reproductor;

/**
 *
 * @author Santiago
 */
public class Arbol_A {
    private NodoA_A raiz;

    // Constructores:
    public Arbol_A() {
        raiz = null;
    }

    public Arbol_A(NodoA_A nodo) {
        raiz = nodo;
    }

    public Arbol_A(String[] vec){
        raiz = new NodoA_A(vec[0]);

        for(int i=1; i<vec.length; i++) {
            insertar(raiz, new NodoA_A(vec[i]));
        }
    }
    
    public Arbol_A(ListaSimple canciones){
        if(!canciones.estaVacia()){
            NodoL aux = canciones.getCabeza();
        
            while(aux != null){
                String artista = aux.getData().getInterprete();
                
                if(buscar(raiz, artista) != null){
                    NodoA_A encontrado = buscar(raiz, artista);
                    encontrado.getCanciones().InsertaFinal(aux);
                }
                else {
                    NodoA_A nuevo = new NodoA_A( artista );
                    nuevo.getCanciones().InsertaFinal(aux);
                    insertar(raiz, nuevo);
                }
                aux = aux.getProximo();
            }
        
        }
    }
    



    // Getters y Setters:
    public NodoA_A getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoA_A nodo) {
        raiz = nodo;
    }
    
    //Metodo: Determina cual nodo tiene un nombre "MENOR" siendo A menorTitulo que Z.
    public NodoA_A menor (NodoA_A nodo1, NodoA_A nodo2){

        char[] vec1 = nodo1.getArtista().toLowerCase().toCharArray();
        char[] vec2 = nodo2.getArtista().toLowerCase().toCharArray();

        int minLength = Math.min(vec1.length, vec2.length);
        int cont = 0;
        
        for(int i = 0; i < minLength; i++)
        {
                if (vec1[i] == vec2[i])
                {
                    cont++;    
                }
                else
                    break;
        }
        if(cont == vec1.length)
            return nodo1;
        else if (cont == vec2.length)
            return nodo2;
        else if(vec1[cont] < vec2[cont])
            return nodo1;
        else
            return nodo2;
    }

    // Metodos Basicos de Arboles:
    public boolean arbolVacio() {
        return (raiz==null);
    }

    public boolean estaNodo(NodoA_A aux, NodoA_A nodo) {
        if(aux == null) {
            return false;
        }
        else {
            if(aux.getArtista().equals(nodo.getArtista())) {   // Si el nodo auxiliar es el nodo que se busca
                return true;
            }
            else if (menor(aux,nodo)==aux){
                return estaNodo(aux.getHijoI(), nodo);
            }
            else {
                return estaNodo(aux.getHijoD(), nodo);
            }
        }
    }

    public boolean estaData(NodoA_A aux, String artista) {
        if(aux == null) {
            return false;
        }
        else {
            NodoA_A nodo_data= new NodoA_A(artista); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getArtista().equals(artista)) {   // Si el nodo auxiliar contiene el dato que se busca
                return true;
            }
            else if (menor(aux,nodo_data)==aux) {
                return estaData(aux.getHijoI(), artista);
            }
            else {
                return estaData(aux.getHijoD(), artista);
            }
        }
    }

    public void imprimirArbol(NodoA_A aux){
        if(aux != null) {
            imprimirArbol( aux.getHijoI() );
            aux.imprimirNodo();
            imprimirArbol( aux.getHijoD() );
        }
    }



    // Operaciones Basicas:
    // Metodo: Inserta un nodo en un arbol dado.
    public void insertar(NodoA_A r, NodoA_A nodo) {

        if(r == null) {
            raiz = nodo;
        }
        else {
            if(menor(nodo, r)==nodo) {
                if (r.getHijoI() == null)
                    r.setHijoI(nodo);
                else
                    insertar(r.getHijoI(), nodo);
            }
            else if (menor(nodo, r)==r){
                if (r.getHijoD() == null)
                    r.setHijoD(nodo);
                else
                    insertar(r.getHijoD(), nodo);
            }
        }
    }

    // Metodo: Busca un nodo en un arbol dado.
    public NodoA_A buscar(NodoA_A r, String artista) {
        if (r == null) {
            return null;
        }
        else {
            NodoA_A nodo= new NodoA_A(artista); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(r.getArtista().equals(artista)) {
                return r;
            }
            else if(menor(nodo,r)==nodo) {
                return buscar( r.getHijoI(), artista);
            }
            else {
                return buscar( r.getHijoD(), artista);
            }
        }
    }

    // Metodo: Retorna el padre del nodo que se quiere eliminar.
    public NodoA_A buscarEliminar(NodoA_A aux, String artista) {
        if(aux == null) {
            return null;
        }
        else {
            NodoA_A nodo_data = new NodoA_A(artista); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getArtista().equals(artista)) {
                return aux;
            }
            else if((aux.getHijoI().getArtista().equals(artista)) || (aux.getHijoD().getArtista().equals(artista))) {
                return aux;
            }
            else if(menor(aux,nodo_data)==aux) {
                return buscarEliminar(aux.getHijoI(), artista);
            }
            else {
                return buscarEliminar(aux.getHijoD(), artista);
            }
        }
    }

    // Metodo: Elimina un nodo y lo retorna, sabiendo su padre y su data.
    public NodoA_A eliminarNodo(NodoA_A padre, String artista) {
        // El metodo buscarEliminar() determina cual es el padre y se lo pasa
        // por parametro a este metodo.
        if(padre == null) {
            return null;
        }
        else {
            
            if((raiz != null) && (raiz.getArtista().equals(artista) )){
                return suprimirRaiz();
            }
            else if((padre.getHijoI() != null) && (padre.getHijoI().getArtista().equals(artista))) {
                return suprimirIzq(padre);
            }
            else if ((padre.getHijoD() != null) && (padre.getHijoD().getArtista().equals(artista))){
                return suprimirDer(padre);
            }
            else {
                return null;
            }
        }
    }

    // Metodo: Calcula la altura de un arbol dado.
    public int altura(NodoA_A aux, int altura) {
        if( aux.esHoja() ) {
            return altura;
        }
        else {
            if((aux.getHijoI() != null) && (aux.getHijoD() != null)) {

                return mayor( altura(aux.getHijoI(), altura + 1), altura(aux.getHijoD(), altura + 1) );
            }
            else if (aux.getHijoI() != null){
                return altura( aux.getHijoI(), altura + 1);
            }
            else {
                return altura( aux.getHijoD(), altura + 1);
            }
        }
    }

    // Metodo: Calcula el nivel de un nodo dado.
    public int nivel(NodoA_A aux, String artista, int nivel) {
        if(aux == null)
            return -1;
        else {
            NodoA_A nodo_data= new NodoA_A(artista); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getArtista().equals(artista)) {
                return nivel;
            }
            else if(menor(aux,nodo_data)==aux) {
                return nivel( aux.getHijoI(), artista, nivel + 1);
            }
            else {
                return nivel( aux.getHijoD(), artista, nivel + 1);
            }
        }
    }

    // Metodo: Cuenta cuantos nodos tiene un arbol dado.
    public int contarNodos(NodoA_A aux) {
        if(aux == null){
            return 0;
        }
        else {
            return 1 + contarNodos(aux.getHijoI()) + contarNodos(aux.getHijoD());
        }
    }

    // Metodo: Calcula la suma de los valores de todos los nodos de un arbol.
    /**
    public int sumarNodos(NodoA_C aux) {
        if (aux != null)
            return aux.getDataA() + sumarNodos(aux.getHijoI()) + sumarNodos(aux.getHijoD());
        else
            return 0;
    }
    */

    // Metodo: Elimina la raiz de un arbol y lo reconstruye.
    public NodoA_A suprimirRaiz() {
        NodoA_A viejaRaiz = raiz;
        raiz = null;

        if((viejaRaiz.getHijoI() != null) && (viejaRaiz.getHijoD() != null)) {
            NodoA_A aux = viejaRaiz.getHijoI();
            raiz = aux;

            while(aux.getHijoD() != null)
                aux = aux.getHijoD();

            aux.setHijoD( viejaRaiz.getHijoD() );
            viejaRaiz.setHijoI(null);
            viejaRaiz.setHijoD(null);
            return viejaRaiz;
        }
        else if (viejaRaiz.getHijoI() != null) {
            raiz = viejaRaiz.getHijoI();
            viejaRaiz.setHijoI(null);
            viejaRaiz.setHijoD(null);
            return viejaRaiz;
        }
        else {
            raiz = viejaRaiz.getHijoD();
            viejaRaiz.setHijoI(null);
            viejaRaiz.setHijoD(null);
            return viejaRaiz;
        }
    }

    // Metodo: Recibe el padre de un nodo de tipo hijo izquierdo, lo elimina y reconstruye el arbol.
    public NodoA_A suprimirIzq(NodoA_A padre) {
        if(padre.getHijoI() != null) {
            NodoA_A aEliminar = padre.getHijoI();
            padre.setHijoI(null);   // Desconectas el padre del nodo a eliminar.

            if((aEliminar.getHijoI() != null) && (aEliminar.getHijoD() != null)) {

                NodoA_A aux = aEliminar.getHijoI();
                padre.setHijoI(aux);

                while(aux.getHijoD() != null)
                    aux = aux.getHijoD();
                // aux sera el ultimo hijo derecho.

                aux.setHijoD(aEliminar.getHijoD());
            }
            else if(aEliminar.getHijoI() != null){
                // Conectas el padre al hijo izquierdo del nodo a eliminar.
                padre.setHijoI( aEliminar.getHijoI() );
            }
            else {
                // Conectas el padre al hijo derecho del nodo a eliminar.
                padre.setHijoI( aEliminar.getHijoD() );
            }

            // Indiferentemente del caso, siempre se termina desconectando el nodo de todo y retornandolo.
            aEliminar.setHijoI(null);
            aEliminar.setHijoD(null);
            return aEliminar;
        }
        else {
            return null;
        }
    }

    // Metodo: Recibe el padre de un nodo de tipo hijo derecho, lo elimina y reconstruye el arbol.
    public NodoA_A suprimirDer(NodoA_A padre) {
        if(padre.getHijoD() != null) {
            NodoA_A aEliminar = padre.getHijoD();
            padre.setHijoD(null);   // Desconectas el padre del nodo a eliminar.

            if((aEliminar.getHijoI() != null) && (aEliminar.getHijoD() != null)) {

                NodoA_A aux = aEliminar.getHijoD();
                padre.setHijoD(aux);

                while(aux.getHijoI() != null)
                    aux = aux.getHijoI();
                // aux sera el ultimo hijo izquierdo.

                aux.setHijoI(aEliminar.getHijoI());
            }
            else if(aEliminar.getHijoI() != null) {
                // Conectas el padre al hijo izquierdo del nodo a eliminar.
                padre.setHijoD( aEliminar.getHijoI() );
            }
            else {
                // Conectas el padre al hijo derecho del nodo a eliminar.
                padre.setHijoD( aEliminar.getHijoD() );
            }

            // Indiferentemente del caso, siempre se termina desconectando el nodo de todo y retornandolo.
            aEliminar.setHijoI(null);
            aEliminar.setHijoD(null);
            return aEliminar;
        }
        else {
            return null;
        }
    }

    // Metodo: Recorrer en un arbol dado de la forma NodoA, HI, HD y lo imprime.
    public void recorrerPreOrden(NodoA_A aux) {

        if(aux != null) {
            aux.imprimirNodo();
            recorrerPreOrden(aux.getHijoI());
            recorrerPreOrden(aux.getHijoD());
        }
    }

    // Metodo: Recorrer en un arbol dado de la forma HI, NodoA, HD y lo imprime.
    public void imprimirEnOrden(NodoA_A aux) {
        if(aux != null) {
            imprimirEnOrden(aux.getHijoI());
            aux.imprimirNodo();
            imprimirEnOrden(aux.getHijoD());
        }
    }

    // Metodo: Recorrer en un arbol dado de la forma HI, HD, NodoA y lo imprime.
    public void recorrerPostOrden(NodoA_A aux) {
        if(aux != null) {
            recorrerPostOrden(aux.getHijoI());
            recorrerPostOrden(aux.getHijoD());
            aux.imprimirNodo();
        }
    }



    // Metodo Extra:
    public int mayor(int a, int b) {
        if(a > b)
            return a;
        else
            return b;
    }

    
    
    public NodoA_A buscarEliminar2(NodoA_A aux, String artista){
        if(aux==null)
            return null;
        else
        {
            NodoA_A nodo_data= new NodoA_A(artista); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getArtista().equals(artista))
                return aux;
            else if(aux.getHijoI().getArtista().equals(artista) || aux.getHijoD().getArtista().equals(artista))
                return aux;
            else if(menor(aux,nodo_data)==aux)
                return buscarEliminar2(aux.getHijoD(),artista);
            else
                return buscarEliminar2(aux.getHijoI(), artista);
        }
    }
    
    public NodoA_A eliminarNodo2(NodoA_A p, String artista){
        if(p==null)
            return null;
        else
        {
            if(raiz!=null && raiz.getArtista().equals(artista))
                return suprimirRaiz2();
            else if(p.getHijoD()!=null && p.getHijoD().getArtista().equals(artista))
                return suprimirHijoD2(p);
            else if(p.getHijoI()!=null && p.getHijoI().getArtista().equals(artista))
                return suprimirHijoI2(p);
            else
                return null;
        }
    }
    
    public NodoA_A suprimirRaiz2(){
        NodoA_A vieja=raiz;
        raiz=null;
        if(vieja.getHijoD()!=null && vieja.getHijoI()!=null)
        {
            NodoA_A aux=vieja.getHijoI();
            raiz=aux;
            while(aux.getHijoD()!=null)
                aux=aux.getHijoD();
            aux.setHijoD(vieja.getHijoD());
            vieja.setHijoD(null);
            vieja.setHijoI(null);
            return vieja;
        }
        else if(vieja.getHijoI()!=null)
        {
            raiz=vieja.getHijoI();
            vieja.setHijoD(null);
            vieja.setHijoI(null);
            return vieja;
        }
        else
        {
            raiz=vieja.getHijoD();
            vieja.setHijoD(null);
            vieja.setHijoI(null);
            return null;
        }
        
    }
    
    public NodoA_A suprimirHijoD2(NodoA_A p){
        if(p.getHijoD()!=null)
        {
            NodoA_A aux=p.getHijoD();
            if(aux.getHijoD()!=null && aux.getHijoI()!=null)
            {
                p.setHijoD(aux.getHijoI());
                NodoA_A temp=aux.getHijoI();
                while(temp.getHijoD()!=null)
                    temp=temp.getHijoD();
                temp.setHijoD(aux.getHijoD());
            }
            else if(aux.getHijoI()!=null)
                p.setHijoD(aux.getHijoI());
            else
                p.setHijoD(aux.getHijoD());
            aux.setHijoD(null);
            aux.setHijoI(null);
            return aux;
        }
        else
            return null;
    }
    
    public NodoA_A suprimirHijoI2(NodoA_A p){
        if(p.getHijoI()!=null)
        {
            NodoA_A aux=p.getHijoI();
            if(aux.getHijoD()!=null && aux.getHijoI()!=null)
            {
                p.setHijoI(aux.getHijoD());
                NodoA_A temp=aux.getHijoD();
                while(temp.getHijoI()!=null)
                    temp=temp.getHijoI();
                temp.setHijoI(aux.getHijoI());
            }
            else if(aux.getHijoD()!=null)
                p.setHijoI(aux.getHijoD());
            else
                p.setHijoI(aux.getHijoI());
            aux.setHijoD(null);
            aux.setHijoI(null);
            return aux;
        }
        else
            return null;
    }
    
    
}
