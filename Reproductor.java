
package reproductor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Santiago Lossada & Luis Graterol
 */

public class Reproductor {
    
    public void iniciarLista(){
        
        // Crea una lista de canciones:
        ListaSimple listaCanciones = new ListaSimple();
        Cancion cancion0 = new Cancion("Bahia","Rawayana","Razon para ser libre",1,320);
        Cancion cancion1 = new Cancion("El Zar","La Vida Boheme","Nuestra",4,245);
        Cancion cancion2 = new Cancion("Despacito","Luis Fonsi ft. Daddy Yankee","Sencillo",0,332);
        Cancion cancion3 = new Cancion("Flamingo","La Vida Boheme","Nuestra",3,145);
        Cancion cancion4 = new Cancion("Vocabulario Basico","Rawayana","Rawayanaland",3,404);
        Cancion cancion5 = new Cancion("La Bilirrubina","Juan Luis Guerra","Grandes Hits",8,404);
        
        // Inserta las canciones en la nueva lista:
        listaCanciones.InsertaFinal(new NodoL(cancion0));
        listaCanciones.InsertaFinal(new NodoL(cancion1));
        listaCanciones.InsertaFinal(new NodoL(cancion2));
        listaCanciones.InsertaFinal(new NodoL(cancion3));
        listaCanciones.InsertaFinal(new NodoL(cancion4));
        listaCanciones.InsertaFinal(new NodoL(cancion5));
        
        try {
            // Escribe en el archivo:
            archivoObjeto.serializar(listaCanciones, "datos.dat");
        } catch (IOException ex) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ListaSimple obtenerLista(){
        iniciarLista();
        
        try{
            // Lee el archivo:
            ListaSimple l = (ListaSimple) archivoObjeto.deserializar("datos.dat");     
            return l;
            
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public void menu() {
        System.out.println(); System.out.println();
        System.out.println("REPRODUCTOR DE MUSICA");
        System.out.println("Como desea ordenar las canciones?");
        System.out.println("1. Titulo");
        System.out.println("2. Artista");
        System.out.println("3. Album");
        System.out.println("4. Numero de Cancion");
        System.out.println("5. Duracion");
        System.out.println("6. Aleatorio");
        System.out.println("Introduzca el numero correspondiente a su eleccion: ");
    }
    
    // Metodo: Recibe como parametro la raiz de un arbol y crea una lista simple de canciones ordenadas por album.
    public ListaSimple ordenarPorAlbum(Arbol_C arbol) {
        ListaSimple ordenada = new ListaSimple();
        
        while(!arbol.arbolVacio()){
            ordenada.InsertarOrdenadoAlbum( new NodoL( arbol.suprimirRaiz().getDataA() ));
        }

        return ordenada;
    }
    
    public ListaSimple ordenarPorNro(Arbol_C arbol) {
        ListaSimple ordenada = new ListaSimple();
        
        while(!arbol.arbolVacio()){
            NodoL nuevo = new NodoL( arbol.suprimirRaiz().getDataA() );
            ordenada.InsertarOrdenadoNro( nuevo );
        }
        
        return ordenada;
    }
    
    public ListaSimple ordenarPorDuracion(Arbol_C arbol) {
        ListaSimple ordenada = new ListaSimple();
        
        while(!arbol.arbolVacio()){
            NodoL nuevo = new NodoL( arbol.suprimirRaiz().getDataA() );
            ordenada.InsertarOrdenadoDuracion( nuevo );
        }
        
        return ordenada;
    }
    
    public ListaSimple ordenarAleatorio(Arbol_C aux) {
        ListaSimple random = new ListaSimple();
        ListaSimple temp = new ListaSimple();
        while (!aux.arbolVacio())
        {
            temp.InsertaPrimero(new NodoL(aux.suprimirRaiz().getDataA()));
        }
        while (!temp.estaVacia())
        {
            int cont=temp.getTamano();
            int r = (int)(Math.random() * cont + 1);
            random.InsertaFinal(temp.EliminaPos(r));
        }
        return random;
    }
    
    public void agregarCancion(){
        
    }
    
    
    
    public static void main(String[] args) {
        
        Reproductor reproductor = new Reproductor();
        
        // Crea los arboles que seran las estructuras principales.
        Arbol_C arbolC = new Arbol_C( reproductor.obtenerLista() );
        Arbol_A arbolA = new Arbol_A( reproductor.obtenerLista() );
        
        reproductor.menu();
        
        ListaSimple aux = new ListaSimple();
        int opcion = CTeclado.ReadInt();
        
        // Validacion:
        while((opcion < 0) || (opcion > 6)){
            System.out.println("Error - El dato introducido es invalido.\nPor favor, introduzca nuevamente su eleccion:");
            opcion = CTeclado.ReadInt();
        }
        
        switch(opcion){
            case 1:
                arbolC.imprimirEnOrden( arbolC.getRaiz());
                break;
            case 2:
                arbolA.imprimirEnOrden( arbolA.getRaiz() );
                break;
            case 3:
                aux = reproductor.ordenarPorAlbum( arbolC );
                aux.imprimirLista();
                break;
            case 4:
                aux = reproductor.ordenarPorNro( arbolC );
                aux.imprimirLista();
                break;
            case 5:
                aux = reproductor.ordenarPorDuracion( arbolC );
                aux.imprimirLista();
                break;
            case 6:
                aux = reproductor.ordenarAleatorio( arbolC );
                aux.imprimirLista();
                break;
            default:
                System.out.println("Error en el switch de opciones.");
                
        }
        
        System.out.println("Ingrese el numero del indice correspondiente a la cancion que quiere agregar a su lista de reproduccion:");
        
        boolean seguir = true;
        int indice = CTeclado.ReadInt();
        
        // Validacion:
        while((indice < 0) || (indice > aux.getTamano())){
            System.out.println("Error - El dato introducido es invalido.\nPor favor, introduzca nuevamente su eleccion:");
            indice = CTeclado.ReadInt();
        }
        
        while(seguir){
            reproductor.agregarCancion();
        }
        
    }
    
}

