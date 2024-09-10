package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InventarioTienda {
    private static Object[][] productos = new Object[10][3];

    public static void main(String[] args) {
        inicializarProductos();
        mostrarMenu();
    }

    public static String preguntar(String pregunta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(pregunta);
        String respuesta = scanner.nextLine();
        return respuesta;
    }

    public static void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            opcion = obtenerOpcion();
            procesarOpcion(opcion);
        }
    }

    public static int obtenerOpcion() {
        Scanner scanner = new Scanner(System.in);
        mostrarOpciones();
        try {
            System.out.print("Seleccione una opción: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Por favor ingrese un número.");
            scanner.next(); 
            return -1;
        }
    }

    public static void mostrarOpciones() {
        System.out.println("\n--- Menú de Inventario ---");
        System.out.println("1. Agregar Productos");
        System.out.println("2. Restar Productos");
        System.out.println("3. Consultar Disponibilidad");
        System.out.println("4. Listar Todos los Productos");
        System.out.println("0. Salir");
    }

    public static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarProductos();
                break;
            case 2:
                restarProductos();
                break;
            case 3:
                consultarDisponibilidad();
                break;
            case 4:
                listarProductos();
                break;
            case 0:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    public static void inicializarProductos() {
        productos[0] = new Object[]{0, "Camiseta", 50};
        productos[1] = new Object[]{1, "Pantalones", 30};
        productos[2] = new Object[]{2, "Zapatos", 20};
        productos[3] = new Object[]{3, "Sombrero", 15};
        productos[4] = new Object[]{4, "Chaqueta", 10};
        productos[5] = new Object[]{5, "Bufanda", 25};
        productos[6] = new Object[]{6, "Guantes", 40};
        productos[7] = new Object[]{7, "Cinturón", 35};
        productos[8] = new Object[]{8, "Ropa Interior", 60};
        productos[9] = new Object[]{9, "Calcetines", 55};
    }

    public static void agregarProductos() {
        try {
            int idProducto = Integer.parseInt(preguntar("Ingrese el ID del producto: "));
            if (!validarProducto(idProducto)) {
                return;
            }

            int cantidad = Integer.parseInt(preguntar("Ingrese la cantidad a agregar: "));
            int cantidadActual = (int) productos[idProducto][2];
            productos[idProducto][2] = cantidadActual + cantidad;

            System.out.println("Producto actualizado: " + productos[idProducto][1] + ", Nueva cantidad: " + productos[idProducto][2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada inválida.");
        }
    }

    public static void restarProductos() {
        try {
            int idProducto = Integer.parseInt(preguntar("Ingrese el ID del producto: "));
            if (!validarProducto(idProducto)) {
                return;
            }

            int cantidad = Integer.parseInt(preguntar("Ingrese la cantidad a restar: "));
            if (!validarCantidadDisponible(idProducto, cantidad)) {
                return;
            }

            int cantidadActual = (int) productos[idProducto][2];
            productos[idProducto][2] = cantidadActual - cantidad;

            System.out.println("Producto actualizado: " + productos[idProducto][1] + ", Nueva cantidad: " + productos[idProducto][2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada inválida.");
        }
    }

    public static void consultarDisponibilidad() {
        try {
            int idProducto = Integer.parseInt(preguntar("Ingrese el ID del producto: "));
            if (!validarProducto(idProducto)) {
                return;
            }

            System.out.println("Cantidad disponible de " + productos[idProducto][1] + ": " + productos[idProducto][2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada inválida.");
        }
    }

    public static void listarProductos() {
        System.out.println("\n------ Lista de Productos ------");
        for (Object[] producto : productos) {
            System.out.println("ID: " + producto[0] + ", Nombre: " + producto[1] + ", Cantidad: " + producto[2]);
        }
    }

    public static boolean validarProducto(int idProducto) {
        if (idProducto >= 0 && idProducto < productos.length) {
            return true;
        } else {
            System.out.println("Error: Producto no encontrado.");
            return false;
        }
    }

    public static boolean validarCantidadDisponible(int idProducto, int cantidad) {
        int cantidadActual = (int) productos[idProducto][2];
        if (cantidad > cantidadActual) {
            System.out.println("Error: Cantidad insuficiente en el inventario.");
            return false;
        }
        return true;
    }
}
