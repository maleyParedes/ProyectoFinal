/**
 * Guardia
 */
public class Guardia extends Persona {


    public Guardia(String nombre, String apellido, int id, String cargo) {
        super(nombre, apellido, id);
        setCargo(cargo); 
    }
    public String toString() {
        return getCargo() + "\nNombre y Apellido: " + getNombre() + " " + getApellido() 
        + "\nIdentificacion: " + getId() + "\n";
    }
}