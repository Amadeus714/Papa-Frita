import java.io.*;
import java.util.*;
public class GestionDVD {

    static class DVD {
        String id;
        String titulo;
        String genero;
        int año;
        boolean eliminado;

        public DVD(String id, String titulo, String genero, int año, boolean eliminado) {
            this.id = id;
            this.titulo = titulo;
            this.genero = genero;
            this.año = año;
            this.eliminado = eliminado;
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Título: %s | Género: %s | Año: %d | Eliminado: %s",
                    id, titulo, genero, año, eliminado ? "Sí" : "No");
        }

        public String toArchivo() {
            return String.join("|", id, titulo, genero, String.valueOf(año), String.valueOf(eliminado));
        }
    }

    static final String ARCHIVO_DVDS = "dvds.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<DVD> listaDVDs = cargarDVDs();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    altaDVD(listaDVDs, scanner);
                    break;
                case "2":
                    bajaLogicaDVD(listaDVDs, scanner);
                    break;
                case "3":
                    modificarDVD(listaDVDs, scanner);
                    break;
                case "4":
                    consultaDVD(listaDVDs, scanner);
                    break;
                case "5":
                    cantidadElementos(listaDVDs);
                    break;
                case "6":
                    cantidadConCondicion(listaDVDs, scanner);
                    break;
                case "7":
                    listarTodos(listaDVDs);
                    break;
                case "8":
                    listadoCondicion(listaDVDs, scanner);
                    break;
                case "9":
                    salir = true;
                    guardarDVDs(listaDVDs);
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
            System.out.println();
        }
        scanner.close();
    }

    // Menú
    public static void mostrarMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Agregar nuevo DVD (alta)");
        System.out.println("2. Eliminar DVD (baja lógica)");
        System.out.println("3. Modificar DVD");
        System.out.println("4. Consultas");
        System.out.println("5. Cantidad total de elementos");
        System.out.println("6. Cantidad que cumplen condición");
        System.out.println("7. Listado de todos los DVDs");
        System.out.println("8. Listado que cumplen condición");
        System.out.println("9. Salir");
    }

    // Cargar DVDs desde archivo
    public static List<DVD> cargarDVDs() {
        List<DVD> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_DVDS);
        if (!archivo.exists()) {
            return lista; // Si no existe archivo, devolver lista vacía
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 5) {
                    DVD dvd = new DVD(
                            partes[0],
                            partes[1],
                            partes[2],
                            Integer.parseInt(partes[3]),
                            Boolean.parseBoolean(partes[4])
                    );
                    lista.add(dvd);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar DVDs: " + e.getMessage());
        }
        return lista;
    }

    // Guardar lista DVDs a archivo
    public static void guardarDVDs(List<DVD> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_DVDS))) {
            for (DVD dvd : lista) {
                bw.write(dvd.toArchivo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar DVDs: " + e.getMessage());
        }
    }

    // Alta DVD
    public static void altaDVD(List<DVD> lista, Scanner scanner) {
        System.out.println("Agregar nuevo DVD:");
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        if (buscarDVD(lista, id) != null) {
            System.out.println("ID ya existe. No se puede agregar.");
            return;
        }
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Género: ");
        String genero = scanner.nextLine().trim();
        System.out.print("Año: ");
        int año;
        try {
            año = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Año inválido.");
            return;
        }
        lista.add(new DVD(id, titulo, genero, año, false));
        System.out.println("DVD agregado correctamente.");
    }

    // Baja lógica DVD
    public static void bajaLogicaDVD(List<DVD> lista, Scanner scanner) {
        System.out.print("Ingrese ID para eliminar (baja lógica): ");
        String id = scanner.nextLine().trim();
        DVD dvd = buscarDVD(lista, id);
        if (dvd == null) {
            System.out.println("DVD no encontrado.");
            return;
        }
        if (dvd.eliminado) {
            System.out.println("DVD ya está eliminado.");
            return;
        }
        dvd.eliminado = true;
        System.out.println("DVD marcado como eliminado.");
    }

    // Modificar DVD
    public static void modificarDVD(List<DVD> lista, Scanner scanner) {
        System.out.print("Ingrese ID para modificar: ");
        String id = scanner.nextLine().trim();
        DVD dvd = buscarDVD(lista, id);
        if (dvd == null) {
            System.out.println("DVD no encontrado.");
            return;
        }
        System.out.println("Dejar vacío para no modificar.");
        System.out.print("Nuevo título (actual: " + dvd.titulo + "): ");
        String titulo = scanner.nextLine().trim();
        if (!titulo.isEmpty()) {
            dvd.titulo = titulo;
        }
        System.out.print("Nuevo género (actual: " + dvd.genero + "): ");
        String genero = scanner.nextLine().trim();
        if (!genero.isEmpty()) {
            dvd.genero = genero;
        }
        System.out.print("Nuevo año (actual: " + dvd.año + "): ");
        String añoStr = scanner.nextLine().trim();
        if (!añoStr.isEmpty()) {
            try {
                dvd.año = Integer.parseInt(añoStr);
            } catch (NumberFormatException e) {
                System.out.println("Año inválido, no se modificó.");
            }
        }
        System.out.println("DVD modificado.");
    }

    // Buscar DVD por ID
    public static DVD buscarDVD(List<DVD> lista, String id) {
        for (DVD dvd : lista) {
            if (dvd.id.equals(id)) {
                return dvd;
            }
        }
        return null;
    }

    // Consultas
    public static void consultaDVD(List<DVD> lista, Scanner scanner) {
        System.out.print("Ingrese ID para buscar: ");
        String id = scanner.nextLine().trim();
        DVD dvd = buscarDVD(lista, id);
        if (dvd == null) {
            System.out.println("DVD no encontrado.");
            return;
        }
        System.out.println(dvd);
    }

    // Cantidad total elementos (no eliminados)
    public static void cantidadElementos(List<DVD> lista) {
        long count = lista.stream().filter(dvd -> !dvd.eliminado).count();
        System.out.println("Cantidad total de DVDs activos: " + count);
    }

    // Cantidad que cumplen condición (por género o año mínimo)
    public static void cantidadConCondicion(List<DVD> lista, Scanner scanner) {
        System.out.println("Ingrese condición para contar:");
        System.out.println("1. Género");
        System.out.println("2. Año mínimo");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine().trim();
        long count = 0;
        switch (opcion) {
            case "1":
                System.out.print("Ingrese género: ");
                String genero = scanner.nextLine().trim();
                count = lista.stream()
                        .filter(dvd -> !dvd.eliminado && dvd.genero.equalsIgnoreCase(genero))
                        .count();
                break;
            case "2":
                System.out.print("Ingrese año mínimo: ");
                try {
                    int añoMin = Integer.parseInt(scanner.nextLine());
                    count = lista.stream()
                            .filter(dvd -> !dvd.eliminado && dvd.año >= añoMin)
                            .count();
                } catch (NumberFormatException e) {
                    System.out.println("Año inválido.");
                    return;
                }
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }
        System.out.println("Cantidad que cumplen condición: " + count);
    }

    // Listado todos (no eliminados)
    public static void listarTodos(List<DVD> lista) {
        System.out.println("Listado completo de DVDs activos:");
        lista.stream()
                .filter(dvd -> !dvd.eliminado)
                .forEach(System.out::println);
    }

    // Listado con condición
    public static void listadoCondicion(List<DVD> lista, Scanner scanner) {
        System.out.println("Ingrese condición para listado:");
        System.out.println("1. Género");
        System.out.println("2. Año mínimo");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine().trim();

        List<DVD> filtrados = new ArrayList<>();

        switch (opcion) {
            case "1":
                System.out.print("Ingrese género: ");
                String genero = scanner.nextLine().trim();
                filtrados = lista.stream()
                        .filter(dvd -> !dvd.eliminado && dvd.genero.equalsIgnoreCase(genero))
                        .toList();
                break;
            case "2":
                System.out.print("Ingrese año mínimo: ");
                try {
                    int añoMin = Integer.parseInt(scanner.nextLine());
                    filtrados = lista.stream()
                            .filter(dvd -> !dvd.eliminado && dvd.año >= añoMin)
                            .toList();
                } catch (NumberFormatException e) {
                    System.out.println("Año inválido.");
                    return;
                }
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        if (filtrados.isEmpty()) {
            System.out.println("No hay DVDs que cumplan la condición.");
        } else {
            System.out.println("DVDs que cumplen la condición:");
            filtrados.forEach(System.out::println);
        }
    }
}
