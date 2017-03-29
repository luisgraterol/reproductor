
//Este es el ARBOL de canciones, todo sirve para trabajar solo con canciones, si quieres usarlo tienes que mandarle canciones y asi busca, inserta, etc.

package reproductor;

/**
 * @author Luis Graterol & Santiago Lossada
 * 
 */

public class Arbol_C {

    private NodoA_C raiz;

    // Constructores:
    public Arbol_C() {
        raiz = null;
    }

    public Arbol_C(NodoA_C nodo) {
        raiz = nodo;
    }

    public Arbol_C(Cancion[] vec){
        raiz = new NodoA_C(vec[0]);

        for(int i=1; i<vec.length; i++) {
            insertar(raiz, new NodoA_C(vec[i]));
        }
    }
    
    public Arbol_C(ListaSimple canciones){
        
        raiz = new NodoA_C(canciones.EliminarPrimero().getData());
        NodoA_C aux;
       
        while(!canciones.estaVacia()){
            aux = new NodoA_C( canciones.EliminarPrimero().getData());
            insertar( raiz, aux );
        }
    }
    
    


    // Getters y Setters:
    public NodoA_C getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoA_C nodo) {
        raiz = nodo;
    }
    
    //Metodo: Determina cual nodo tiene un nombre "MENOR" siendo A menorTitulo que Z.
    public NodoA_C menor (NodoA_C nodo1, NodoA_C nodo2){

        String w1 = nodo1.getDataA().getTitulo();
        String w2 = nodo2.getDataA().getTitulo();
        char[] vec1 = w1.toLowerCase().toCharArray();
        char[] vec2 = w2.toLowerCase().toCharArray();

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

    public boolean estaNodo(NodoA_C aux, NodoA_C nodo) {
        if(aux == null) {
            return false;
        }
        else {
            if(aux.getDataA() == nodo.getDataA()) {   // Si el nodo auxiliar es el nodo que se busca
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

    public boolean estaData(NodoA_C aux, Cancion data) {
        if(aux == null) {
            return false;
        }
        else {
            NodoA_C nodo_data= new NodoA_C(data); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getDataA() == data) {   // Si el nodo auxiliar contiene el dato que se busca
                return true;
            }
            else if (menor(aux,nodo_data)==aux) {
                return estaData(aux.getHijoI(), data);
            }
            else {
                return estaData(aux.getHijoD(), data);
            }
        }
    }

    public void imprimirArbol(NodoA_C aux){
        if(aux != null) {
            imprimirArbol( aux.getHijoI() );
            aux.imprimirNodo();
            imprimirArbol( aux.getHijoD() );
        }
    }



    // Operaciones Basicas:
    // Metodo: Inserta un nodo en un arbol dado.
    public void insertar(NodoA_C r, NodoA_C nodo) {

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
    public NodoA_C buscar(NodoA_C r, Cancion data) {
        if (r == null) {
            return null;
        }
        else {
            NodoA_C nodo= new NodoA_C(data); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(data == r.getDataA()) {
                return r;
            }
            else if(menor(nodo,r)==nodo) {
                return buscar( r.getHijoI(), data );
            }
            else {
                return buscar( r.getHijoD(), data );
            }
        }
    }

    // Metodo: Retorna el padre del nodo que se quiere eliminar.
    public NodoA_C buscarEliminar(NodoA_C aux, Cancion data) {
        if(aux == null) {
            return null;
        }
        else {
            NodoA_C nodo_data = new NodoA_C(data); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getDataA() == data) {
                return aux;
            }
            else if((aux.getHijoI().getDataA() == data) || (aux.getHijoD().getDataA() == data)) {
                return aux;
            }
            else if(menor(aux,nodo_data)==aux) {
                return buscarEliminar(aux.getHijoI(), data);
            }
            else {
                return buscarEliminar(aux.getHijoD(), data);
            }
        }
    }

    // Metodo: Elimina un nodo y lo retorna, sabiendo su padre y su data.
    public NodoA_C eliminarNodo(NodoA_C padre, Cancion data) {
        // El metodo buscarEliminar() determina cual es el padre y se lo pasa
        // por parametro a este metodo.
        if(padre == null) {
            return null;
        }
        else {
            
            if((raiz != null) && (raiz.getDataA() == data)){
                return suprimirRaiz();
            }
            else if((padre.getHijoI() != null) && (padre.getHijoI().getDataA() == data)) {
                return suprimirIzq(padre);
            }
            else if ((padre.getHijoD() != null) && (padre.getHijoD().getDataA() == data)){
                return suprimirDer(padre);
            }
            else {
                return null;
            }
        }
    }

    // Metodo: Calcula la altura de un arbol dado.
    public int altura(NodoA_C aux, int altura) {
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
    public int nivel(NodoA_C aux, Cancion data, int nivel) {
        if(aux == null)
            return -1;
        else {
            NodoA_C nodo_data= new NodoA_C(data); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getDataA() == data) {
                return nivel;
            }
            else if(menor(aux,nodo_data)==aux) {
                return nivel( aux.getHijoI(), data, nivel + 1);
            }
            else {
                return nivel( aux.getHijoD(), data, nivel + 1);
            }
        }
    }

    // Metodo: Cuenta cuantos nodos tiene un arbol dado.
    public int contarNodos(NodoA_C aux) {
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
    public NodoA_C suprimirRaiz() {
        NodoA_C viejaRaiz = raiz;
        raiz = null;

        if((viejaRaiz.getHijoI() != null) && (viejaRaiz.getHijoD() != null)) {
            NodoA_C aux = viejaRaiz.getHijoI();
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
    public NodoA_C suprimirIzq(NodoA_C padre) {
        if(padre.getHijoI() != null) {
            NodoA_C aEliminar = padre.getHijoI();
            padre.setHijoI(null);   // Desconectas el padre del nodo a eliminar.

            if((aEliminar.getHijoI() != null) && (aEliminar.getHijoD() != null)) {

                NodoA_C aux = aEliminar.getHijoI();
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
    public NodoA_C suprimirDer(NodoA_C padre) {
        if(padre.getHijoD() != null) {
            NodoA_C aEliminar = padre.getHijoD();
            padre.setHijoD(null);   // Desconectas el padre del nodo a eliminar.

            if((aEliminar.getHijoI() != null) && (aEliminar.getHijoD() != null)) {

                NodoA_C aux = aEliminar.getHijoD();
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
    public void recorrerPreOrden(NodoA_C aux) {

        if(aux != null) {
            aux.imprimirNodo();
            recorrerPreOrden(aux.getHijoI());
            recorrerPreOrden(aux.getHijoD());
        }
    }

    // Metodo: Recorrer en un arbol dado de la forma HI, NodoA, HD y lo imprime.
    public void imprimirEnOrden(NodoA_C aux) {
        if(aux != null) {
            
            imprimirEnOrden(aux.getHijoI());
            aux.imprimirNodo();
            imprimirEnOrden(aux.getHijoD());
        }
    }

    // Metodo: Recorrer en un arbol dado de la forma HI, HD, NodoA y lo imprime.
    public void recorrerPostOrden(NodoA_C aux) {
        if(aux != null) {
            recorrerPostOrden(aux.getHijoI());
            recorrerPostOrden(aux.getHijoD());
            aux.imprimirNodo();
        }
    }
    
//    public ListaSimple recorrerEnOrden(NodoA_C aux) {
//        if(aux != null) {
//            recorrerEnOrden(aux.getHijoI());
//            NodoL nuevo = new NodoL( aux.getDataA() );
//            ListaSimple
//            recorrerEnOrden(aux.getHijoD());
//        }
//    }



    // Metodo Extra:
    public int mayor(int a, int b) {
        if(a > b)
            return a;
        else
            return b;
    }

    
    
    public NodoA_C buscarEliminar2(NodoA_C aux, Cancion data){
        if(aux==null)
            return null;
        else
        {
            NodoA_C nodo_data= new NodoA_C(data); //Se crea un nodo con la data para que el metodo menorTitulo sirva
            if(aux.getDataA()==data)
                return aux;
            else if(aux.getHijoI().getDataA()==data || aux.getHijoD().getDataA()==data)
                return aux;
            else if(menor(aux,nodo_data)==aux)
                return buscarEliminar2(aux.getHijoD(),data);
            else
                return buscarEliminar2(aux.getHijoI(), data);
        }
    }
    
    public NodoA_C eliminarNodo2(NodoA_C p, Cancion data){
        if(p==null)
            return null;
        else
        {
            if(raiz!=null && raiz.getDataA()==data)
                return suprimirRaiz2();
            else if(p.getHijoD()!=null && p.getHijoD().getDataA()==data)
                return suprimirHijoD2(p);
            else if(p.getHijoI()!=null && p.getHijoI().getDataA()==data)
                return suprimirHijoI2(p);
            else
                return null;
        }
    }
    
    public NodoA_C suprimirRaiz2(){
        NodoA_C vieja=raiz;
        raiz=null;
        if(vieja.getHijoD()!=null && vieja.getHijoI()!=null)
        {
            NodoA_C aux=vieja.getHijoI();
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
    
    public NodoA_C suprimirHijoD2(NodoA_C p){
        if(p.getHijoD()!=null)
        {
            NodoA_C aux=p.getHijoD();
            if(aux.getHijoD()!=null && aux.getHijoI()!=null)
            {
                p.setHijoD(aux.getHijoI());
                NodoA_C temp=aux.getHijoI();
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
    
    public NodoA_C suprimirHijoI2(NodoA_C p){
        if(p.getHijoI()!=null)
        {
            NodoA_C aux=p.getHijoI();
            if(aux.getHijoD()!=null && aux.getHijoI()!=null)
            {
                p.setHijoI(aux.getHijoD());
                NodoA_C temp=aux.getHijoD();
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

    