public class Nodo {
    public Persona persona;
    public Nodo padre;
    public Nodo derecha;
    public Nodo izquierda;
    public int llave;

    public Nodo(int indice){
        llave = indice;
        derecha = null;
        izquierda = null;
        padre = null;
        persona = null;
    }
}
