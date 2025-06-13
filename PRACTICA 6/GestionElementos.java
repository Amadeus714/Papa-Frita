import java.io.*;
import java.util.*;

public class GestionElementos {
    static class Elemento {
        String clave;
        String nombre;
        String descripcion;
        boolean eliminado;

        public Elemento(String clave, String nombre, String descripcion, boolean eliminado) {
            this.clave = clave;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.eliminado = eliminado;
        }

        @Override
        public String toString() {
            return String.format("Clave: %s | Nombre: %s | Descripción: %s | Eliminado: %s",
                    clave, nombre, descripcion, eliminado ? "Sí" : "No");
        }

        public String toArchivo() {
            return String.join("|", clave, nombre, descripcion, String.valueOf(eliminado));
        }
    }

    static final String ARCHIVO = "elementos.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Elemento> lista = cargarElementos();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    altaElemento(lista, scanner);
                    break;
                case "2":
                    bajaLogicaElemento(lista, scanner);
                    break;
                case "3":
                    modificarElemento(lista, scanner);
                    break;
                case "4":
                    consultaElemento(lista, scanner);
                    break;
                case "5":
                    cantidadElementos(lista);
                    break;
                case "6":
                    cantidadCondicion(lista, scanner);
                    break;
                case "7":
                    listadoTodos(lista);
                    break;
                case "8":
                    listadoCondicion(lista, scanner);
                    break;
                case "9":
                    salir = true;
                    guardarElementos(lista);
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
            System.out.println();
        }
        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Agregar nuevo elemento (alta)");
        System.out.println("2. Eliminar elemento (baja lógica)");
        System.out.println("3. Modificar elemento");
        System.out.println("4. Consultas (buscar por clave)");
        System.out.println("5. Cantidad total de elementos activos");
        System.out.println("6. Cantidad que cumplen condición");
        System.out.println("7. Listado de todos los elementos activos");
        System.out.println("8. Listado de elementos que cumplen condición");
        System.out.println("9. Salir");
    }

    public static List<Elemento> cargarElementos() {
        List<Elemento> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 4) {
                    lista.add(new Elemento(
                            partes[0],
                            partes[1],
                            partes[2],
                            Boolean.parseBoolean(partes[3])
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar elementos: " + e.getMessage());
        }
        return lista;
    }

    public static void guardarElementos(List<Elemento> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Elemento e : lista) {
                bw.write(e.toArchivo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar elementos: " + e.getMessage());
        }
    }

    public static Elemento buscarPorClave(List<Elemento> lista, String clave) {
        for (Elemento e : lista) {
            if (e.clave.equals(clave)) return e;
        }
        return null;
    }

    public static void altaElemento(List<Elemento> lista, Scanner scanner) {
        System.out.print("Clave: ");
        String clave = scanner.nextLine().trim();
        if (buscarPorClave(lista, clave) != null) {
            System.out.println("Clave ya existe.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine().trim();
        lista.add(new Elemento(clave, nombre, descripcion, false));
        System.out.println("Elemento agregado.");
    }

    public static void bajaLogicaElemento(List<Elemento> lista, Scanner scanner) {
        System.out.print("Ingrese clave para eliminar (baja lógica): ");
        String clave = scanner.nextLine().trim();
        Elemento e = buscarPorClave(lista, clave);
        if (e == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }
        if (e.eliminado) {
            System.out.println("Elemento ya está eliminado.");
            return;
        }
        e.eliminado = true;
        System.out.println("Elemento marcado como eliminado.");
    }

    public static void modificarElemento(List<Elemento> lista, Scanner scanner) {
        System.out.print("Ingrese clave para modificar: ");
        String clave = scanner.nextLine().trim();
        Elemento e = buscarPorClave(lista, clave);
        if (e == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }
        System.out.println("Dejar vacío para no modificar.");
        System.out.print("Nuevo nombre (actual: " + e.nombre + "): ");
        String nombre = scanner.nextLine().trim();
        if (!nombre.isEmpty()) e.nombre = nombre;

        System.out.print("Nueva descripción (actual: " + e.descripcion + "): ");
        String descripcion = scanner.nextLine().trim();
        if (!descripcion.isEmpty()) e.descripcion = descripcion;

        System.out.println("Elemento modificado.");
    }

    public static void consultaElemento(List<Elemento> lista, Scanner scanner) {
        System.out.print("Ingrese clave para buscar: ");
        String clave = scanner.nextLine().trim();
        Elemento e = buscarPorClave(lista, clave);
        if (e == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }
        System.out.println(e);
    }

    public static void cantidadElementos(List<Elemento> lista) {
        long count = lista.stream().filter(e -> !e.eliminado).count();
        System.out.println("Cantidad total de elementos activos: " + count);
    }

    public static void cantidadCondicion(List<Elemento> lista, Scanner scanner) {
        System.out.println("Ingrese condición para contar:");
        System.out.println("1. Nombre contiene");
        System.out.println("2. Descripción contiene");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine().trim();
        long count = 0;
        switch (opcion) {
            case "1":
                System.out.print("Ingrese texto para nombre: ");
                String textoNombre = scanner.nextLine().trim().toLowerCase();
                count = lista.stream()
                        .filter(e -> !e.eliminado && e.nombre.toLowerCase().contains(textoNombre))
                        .count();
                break;
            case "2":
                System.out.print("Ingrese texto para descripción: ");
                String textoDesc = scanner.nextLine().trim().toLowerCase();
                count = lista.stream()
                        .filter(e -> !e.eliminado && e.descripcion.toLowerCase().contains(textoDesc))
                        .count();
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }
        System.out.println("Cantidad que cumplen condición: " + count);
    }

    public static void listadoTodos(List<Elemento> lista) {
        System.out.println("Listado de todos los elementos activos:");
        lista.stream()
                .filter(e -> !e.eliminado)
                .forEach(System.out::println);
    }

    public static void listadoCondicion(List<Elemento> lista, Scanner scanner) {
        System.out.println("Ingrese condición para listado:");
        System.out.println("1. Nombre contiene");
        System.out.println("2. Descripción contiene");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine().trim();

        List<Elemento> filtrados = new ArrayList<>();
        switch (opcion) {
            case "1":
                System.out.print("Ingrese texto para nombre: ");
                String textoNombre = scanner.nextLine().trim().toLowerCase();
                filtrados = lista.stream()
                        .filter(e -> !e.eliminado && e.nombre.toLowerCase().contains(textoNombre))
                        .toList();
                break;
            case "2":
                System.out.print("Ingrese texto para descripción: ");
                String textoDesc = scanner.nextLine().trim().toLowerCase();
                filtrados = lista.stream()
                        .filter(e -> !e.eliminado && e.descripcion.toLowerCase().contains(textoDesc))
                        .toList();
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }
        if (filtrados.isEmpty()) {
            System.out.println("No hay elementos que cumplan la condición.");
        } else {
            System.out.println("Elementos que cumplen la condición:");
            filtrados.forEach(System.out::println);
        }
    }
}
