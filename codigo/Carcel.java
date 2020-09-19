
//Arbol
import java.util.Scanner;

public class Carcel {
    Nodo raiz;
    final static Scanner sc = new Scanner(System.in);

    public Carcel() {
        raiz = null;
    }

    public void insertarGuardia(int i, String nombre, String apellido, int id, String cargo) {
        Nodo n = new Nodo(i);
        n.persona = new Guardia(nombre, apellido, id, cargo);

        // ordenar para meter los guardias en el orden y los presos en su respectivo
        // orden también
        if (raiz == null) {
            raiz = n;
        } else {
            Nodo aux = raiz;
            while (aux != null) {
                n.padre = aux;
                if (n.llave >= aux.llave) {
                    aux = aux.derecha;
                } else {
                    aux = aux.izquierda;
                }
            }
            if (n.llave < n.padre.llave) {
                n.padre.izquierda = n;
            } else {
                n.padre.derecha = n;
            }
        }
    }

    public void insertarPreso() {
        /*
        */
        int nivelDelito = 0;
        int indice = 0, id = 0;
        String nombre, apellido;
        char patio;

        System.out.print("Datos Preso\nIngrese nombre >> ");
        nombre = sc.next();
        System.out.print("Ingrese apellido >> ");
        apellido = sc.next();
        System.out.print("Identificacion CC >> ");
        id = Integer.parseInt(sc.next());
        System.out.print("Nivel del delito >> ");
        nivelDelito = Integer.parseInt(sc.next());

        while (nivelDelito < 1 || nivelDelito > 4) {
            System.out.println(
                    "Ingrese el nivel del delito correcto\n" + "Los niveles de delitos son (1), (2), (3) y (4)");
            nivelDelito = Integer.parseInt(sc.next());
        }
        System.out.println("** Cada preso recibira un >Id Carcel< de acuerdo al numero de su celda **");
        do {
            if (nivelDelito == 1) {
                patio = 'A';
                System.out.println("Patio A\nCeldas disponibles: (1 - 14 y 16 - 29)");
                indice = Integer.parseInt(sc.next());
            } else if (nivelDelito == 2) {
                patio = 'B';
                System.out.println("Patio B\nCeldas disponibles: (31 - 44 y 46 - 59)");
                indice = Integer.parseInt(sc.next());
            } else if (nivelDelito == 3) {
                patio = 'C';
                System.out.println("Patio C\nCeldas disponibles: (61 - 74 y 76 - 89)");
                indice = Integer.parseInt(sc.next());
            } else {
                patio = 'D';
                System.out.println("Patio D\nCeldas disponibles: (91 - 104 y 105 - 119)");
                indice = Integer.parseInt(sc.next());
            }
        } while (!(indice > 0 && indice < 120));

        /*
        */

        Nodo n = new Nodo(indice);
        n.persona = new Preso(nombre, apellido, id, nivelDelito, patio);
        System.out.println("*** Preso Ingresado ***\n");
        Nodo aux = raiz;
        while (aux != null) {
            n.padre = aux;
            if (n.llave >= aux.llave) {
                aux = aux.derecha;
            } else {
                aux = aux.izquierda;
            }
        }
        if (n.llave < n.padre.llave) {
            n.padre.izquierda = n;
        } else {
            n.padre.derecha = n;
        }

    }

    public void predeterminado() {
        insertarGuardia(60, "Juan", "Zamora", 123, "Jefe Carcel");
        insertarGuardia(30, "Gustavo", "Melo", 456, "Supervisor Patios A y B");
        insertarGuardia(90, "Michael", "Paredes", 789, "Supervisor Patios C y D");
        insertarGuardia(15, "Julian", "Aley", 012, "Guardia Patio A");
        insertarGuardia(45, "Juan", "Restrepo", 142, "Guardia Patio B");
        insertarGuardia(75, "Miguel", "Diaz", 347, "Guardia Patio C");
        insertarGuardia(105, "Luis", "Sanchez", 763, "Guardia Patio D");
    }

    public void eliminar() throws Exception {
        int llave;
        do {
            System.out.print("Ingrese el documento (Id carcel) del preso a sacar\n>> ");
            llave = sc.nextInt();
        } while (llave == 15 || llave == 30 || llave == 45 || llave == 60 || llave == 75 || llave == 90
                || llave == 105);
        raiz = eliminarN(raiz, llave);
        System.out.println("** Operacion exitosa **");
    }

    protected Nodo eliminarN(Nodo aux, int data) throws Exception {
        if (aux == null) {
            throw new Exception("Nodo no encontrado");

        } else if (data < aux.llave) {
            Nodo iz = eliminarN(aux.izquierda, data);
            aux.izquierda = iz;
        } else if (data > aux.llave) {
            Nodo der = eliminarN(aux.derecha, data);
            aux.derecha = der;
        } else {
            Nodo p = aux;
            if (p.derecha == null) {
                aux = p.izquierda;
            } else if (p.izquierda == null) {
                aux = p.derecha;
            } else {
                p = cambiar(p);
            }
            p = null;
        }
        return aux;
    }

    protected Nodo cambiar(Nodo aux) {
        Nodo p = aux;
        Nodo a = aux.izquierda;
        while (a.derecha != null) {
            p = a;
            a = a.derecha;
        }
        aux.persona = a.persona;
        if (p == aux) {
            p.izquierda = a.izquierda;
        } else {
            p.derecha = a.izquierda;
        }
        return a;
    }

    public void inorden(Nodo n) {
        if (n != null) {
            inorden(n.izquierda);
            System.out.println("Id carcel " + n.llave + " >> " + n.persona + "\n");
            inorden(n.derecha);
        }
    }

    public void postorden(Nodo n) {
        if (n != null) {
            postorden(n.izquierda);
            postorden(n.derecha);
            System.out.println("Id carcel " + n.llave + " >> " + n.persona + "\n");
        }
    }

    public void preorden(Nodo n) {
        if (n != null) {
            System.out.println("Id carcel " + n.llave + " >> " + n.persona + "\n");
            preorden(n.izquierda);
            preorden(n.derecha);
        }
    }

    protected Nodo buscarNodo() {
        System.out.println("Ingrese la Id Carcel a modificar");
        int llave = sc.nextInt();
        Nodo aux = raiz;
        while (aux.llave != llave) {
            if (llave < aux.llave) {
                aux = aux.izquierda;
            } else {
                aux = aux.derecha;
            }
            if (aux == null) {
                return null;
            }
        }
        return aux;
    }

    public void modificarGuardia() {
        Nodo n = buscarNodo();
        if (n != null) {
            System.out.print("** Actualizacion de datos Guardia **\nNombre: ");
            String nombre = sc.next();
            System.out.print("Apellido: ");
            String apellido = sc.next();
            System.out.print("Identificacion CC: ");
            int id = sc.nextInt();

            n.persona.setNombre(nombre);
            n.persona.setApellido(apellido);
            n.persona.setId(id);
            System.out.println("** Actualizacion Exitosa **\n\n");
        } else {
            System.out.println("Ningun guardia encontrado con el >Id Carcel< ingresado\n\n");
        }
    }

    public void modificarPreso() {
        Nodo n = buscarNodo();
        if (n != null) {
            System.out.print("** Actualizacion de datos Preso **\nNombre: ");
            String nombre = sc.next();
            System.out.print("Apellido: ");
            String apellido = sc.next();
            System.out.print("Identificacion CC: ");
            int id = sc.nextInt();

            n.persona.setNombre(nombre);
            n.persona.setApellido(apellido);
            n.persona.setId(id);
            System.out.println("** Actualizacion Exitosa **\n\n");
        } else {
            System.out.println("Ningun preso encontrado con el >Id Carcel< ingresado\n\n");
        }
    }

}
