import java.util.Scanner;

/**
 * Principal
 */
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu, cantidadPresos = 0, espacioDis = 112;
        /*
         * nivel 1 = Jefe carcel nivel 2 = supervisores patios A, B, C y D nivel 3 =
         * Guardias patios A, B, C y D nivel 4 e inferior = Presos
         */

        /*
         * Nivel del delito: (1) Patio A (2) Patio B  (3) Patio C  (4) Patio D
         * 
         */

        /*
         * Cargo: 60 = Jefe carcel 30 y 90 = supervisores 15 = Guardia patio A 
         * 45 = Guardia patio B 75 = Guardia patio C 105 = Guardia patio D
         */
        Carcel carcel = new Carcel();
        carcel.predeterminado(); // Se crea los nodos de guardias

        do {
            System.out.print("\n** Menu Carcel RESO **\n  Que desea realizar  \n1. Ingresar preso"
                    + "\n2. Mostrar carcel" + "\n3. Sacar preso" + "\n4. Cambiar guardia" + "\n5. Cambiar preso"
                    + "\n6. Cerrar Sistema\n>> ");
            menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1:
                    System.out.println("¿Cuantos presos desea ingresar?" + "\nEspacio en la carcel " + espacioDis);
                    cantidadPresos = Integer.parseInt(sc.nextLine());
                    if (espacioDis > 0) {
                        espacioDis -= cantidadPresos;
                        for (int i = 0; i < cantidadPresos; i++) {
                            carcel.insertarPreso();
                        }

                    } else {
                        System.out.println("No hay espacio en la carcel");
                    }
                    break;
                case 2:
                    // carcel.inorden(carcel.raiz);
                    // carcel.preorden(carcel.raiz);
                    carcel.postorden(carcel.raiz);
                    System.out.print("Presos: " + cantidadPresos);
                    break;
                case 3:
                    if (cantidadPresos > 0) {
                        try {
                            carcel.eliminar();
                            cantidadPresos--;
                        } catch (Exception e) {

                        }
                    } else {
                        System.out.println("** No hay presos para sacar **");
                    }
                    break;
                case 4:
                    carcel.modificarGuardia();
                    break;
                case 5:
                    if (cantidadPresos > 0) {
                        carcel.modificarPreso();
                    } else {
                        System.out.println("** No hay presos para modificar **");
                    }
                    break;
                case 6:
                    System.out.println("** Cerrando Sistema **\n\n------------");
                    break;
                default:
                    System.out.println("La opcion no se encuentra en el menu\n");
                    break;
            }

        } while (menu != 6);

    }
}