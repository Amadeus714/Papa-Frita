import java.io.*;
import java.util.*;

public class GestionClientes {
    public static void main(String[] args) {
        List<String> clientesIniciales = inicializarClientes();
        String archivoClientes = "Clientes.txt";
        String archivoClientes2 = "Clientes2.txt";
        // 1. Guardar los 10 clientes iniciales en archivo Clientes.txt y mostrar
        guardarClientesEnArchivo(clientesIniciales, archivoClientes);
        System.out.println("Clientes iniciales guardados:");
        mostrarArchivo(archivoClientes);
        // 2. Pedir 5 clientes nuevos por teclado y agregarlos al principio
        List<String> nuevosClientes = pedirClientesPorTeclado(5);
        List<String> todosClientes = new ArrayList<>();
        todosClientes.addAll(nuevosClientes);   // primero los nuevos
        todosClientes.addAll(clientesIniciales); // después los iniciales
        guardarClientesEnArchivo(todosClientes, archivoClientes);
        System.out.println("\nArchivo actualizado con 5 nuevos clientes al principio:");
        mostrarArchivo(archivoClientes);
        // 3. Crear archivo Clientes2.txt con número de cuenta y tipo de cuenta
        generarArchivoClientes2(todosClientes, archivoClientes2);
        System.out.println("\nArchivo Clientes2.txt generado con más datos:");
        mostrarArchivo(archivoClientes2);
    }
    // Inicializa lista con 10 clientes fijos
    public static List<String> inicializarClientes() {
        return new ArrayList<>(Arrays.asList(
                "Juan Pérez",
                "María López",
                "Carlos Sánchez",
                "Ana Gómez",
                "Luis Fernández",
                "Sofía Martínez",
                "Pedro Díaz",
                "Laura Romero",
                "Jorge Herrera",
                "Marta Castro"
        ));
    }
    // Guarda la lista de clientes en un archivo (uno por línea)
    public static void guardarClientesEnArchivo(List<String> clientes, String nombreArchivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String cliente : clientes) {
                escritor.write(cliente);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
    // Muestra el contenido de un archivo por pantalla
    public static void mostrarArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
    // Pide por teclado n clientes (nombre y apellido)
    public static List<String> pedirClientesPorTeclado(int cantidad) {
        Scanner scanner = new Scanner(System.in);
        List<String> nuevosClientes = new ArrayList<>();
        System.out.println("\nIngrese " + cantidad + " nuevos clientes (nombre y apellido):");
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Cliente " + i + ": ");
            String cliente = scanner.nextLine().trim();
            while (cliente.isEmpty()) {
                System.out.print("No puede estar vacío. Reingrese Cliente " + i + ": ");
                cliente = scanner.nextLine().trim();
            }
            nuevosClientes.add(cliente);
        }
        // No cerramos scanner para evitar cerrar System.in (puede causar problemas en IDEs)
        return nuevosClientes;
    }
    // Genera archivo Clientes2.txt con número de cuenta y tipo de cuenta
    public static void generarArchivoClientes2(List<String> clientes, String nombreArchivo) {
        Random rnd = new Random();
        String[] tiposCuenta = {"Caja de ahorro", "Cuenta corriente"};
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String cliente : clientes) {
                // Generar número de cuenta aleatorio: 10 dígitos
                long numCuenta = 1000000000L + (Math.abs(rnd.nextLong()) % 9000000000L);
                // Elegir tipo de cuenta aleatoriamente
                String tipoCuenta = tiposCuenta[rnd.nextInt(tiposCuenta.length)];
                escritor.write(String.format("Número de cuenta: %d%n", numCuenta));
                escritor.write(String.format("Cliente: %s%n", cliente));
                escritor.write(String.format("Tipo de cuenta: %s%n", tipoCuenta));
                escritor.write(System.lineSeparator()); // línea vacía entre clientes
            }
        } catch (IOException e) {
            System.out.println("Error al generar archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
