public class Preso extends Persona {
    public int nivelDelito;

    public Preso(String nombre, String apellido, int id, int nivel, char patio) {
        super(nombre, apellido, id);
        this.nivelDelito = nivel;
        setCargo("Preso patio " + patio);
    }

    public String toString() {
        return getCargo() + "\nNombre y apellido: " + this.getNombre() + " " + getApellido() + "\nIdentificacion: "
                + this.getId() + "\n";
    }

}
