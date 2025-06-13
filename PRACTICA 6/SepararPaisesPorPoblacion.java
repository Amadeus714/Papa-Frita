import java.io.*;
import java.util.Scanner;

public class SepararPaisesPorPoblacion {

    public static void main(String[] args) {
        String archivoEntrada = "paises.txt";
        String archivoMenos30 = "paises_menor_30.txt";
        String archivoMayorIgual30 = "paises_mayor_igual_30.txt";

        // 1. Crear archivo de entrada con formato legible
        String[][] datosPaises = {
                {"Argentina", "45.3", "45000.5"},
                {"Uruguay", "3.5", "18000.2"},
                {"Paraguay", "7.0", "12000.7"},
                {"Chile", "19.5", "28000.3"},
                {"Brasil", "211.0", "150000.1"},
                {"Bolivia", "11.6", "9000.0"}
        };

        try (BufferedWriter escritorEntrada = new BufferedWriter(new FileWriter(archivoEntrada))) {
            for (String[] pais : datosPaises) {
                escritorEntrada.write(String.format("NOMBRE DEL PAÍS: %s%n", pais[0]));
                escritorEntrada.write(String.format("POBLACIÓN: %s%n", pais[1]));
                escritorEntrada.write(String.format("PBI: %s%n", pais[2]));
                escritorEntrada.write(System.lineSeparator()); // línea vacía entre países
            }
            System.out.println("Archivo de entrada creado con datos fijos y formato legible.");
        } catch (IOException e) {
            System.out.println("Error al crear archivo de entrada: " + e.getMessage());
            return;
        }

        // 2. Leer archivo de entrada y separar en dos archivos según población
        try (
                BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
                BufferedWriter escritorMenor30 = new BufferedWriter(new FileWriter(archivoMenos30));
                BufferedWriter escritorMayorIgual30 = new BufferedWriter(new FileWriter(archivoMayorIgual30));
        ) {
            String nombrePais = null;
            String poblacionStr = null;
            String pbiStr = null;

            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    // línea vacía indica que se terminó un registro
                    if (nombrePais != null && poblacionStr != null && pbiStr != null) {
                        double poblacion = Double.parseDouble(poblacionStr);
                        BufferedWriter escritor = poblacion < 30 ? escritorMenor30 : escritorMayorIgual30;

                        // Escribir el bloque completo con formato
                        escritor.write("NOMBRE DEL PAÍS: " + nombrePais);
                        escritor.newLine();
                        escritor.write("POBLACIÓN: " + poblacionStr);
                        escritor.newLine();
                        escritor.write("PBI: " + pbiStr);
                        escritor.newLine();
                        escritor.newLine();
                    }
                    // resetear variables para próximo país
                    nombrePais = null;
                    poblacionStr = null;
                    pbiStr = null;
                } else {
                    if (linea.startsWith("NOMBRE DEL PAÍS:")) {
                        nombrePais = linea.substring("NOMBRE DEL PAÍS:".length()).trim();
                    } else if (linea.startsWith("POBLACIÓN:")) {
                        poblacionStr = linea.substring("POBLACIÓN:".length()).trim();
                    } else if (linea.startsWith("PBI:")) {
                        pbiStr = linea.substring("PBI:".length()).trim();
                    }
                }
            }
            // Para el último país si el archivo no termina con línea vacía
            if (nombrePais != null && poblacionStr != null && pbiStr != null) {
                double poblacion = Double.parseDouble(poblacionStr);
                BufferedWriter escritor = poblacion < 30 ? escritorMenor30 : escritorMayorIgual30;

                escritor.write("NOMBRE DEL PAÍS: " + nombrePais);
                escritor.newLine();
                escritor.write("POBLACIÓN: " + poblacionStr);
                escritor.newLine();
                escritor.write("PBI: " + pbiStr);
                escritor.newLine();
                escritor.newLine();
            }

            System.out.println("Archivos generados correctamente con formato legible.");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al procesar archivos: " + e.getMessage());
            return;
        }

        // 3. Preguntar qué archivo mostrar
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n¿Qué archivo querés ver?");
        System.out.println("1 - Países con población MENOR a 30 millones");
        System.out.println("2 - Países con población MAYOR o IGUAL a 30 millones");
        System.out.print("Elegí 1 o 2: ");

        String opcion = scanner.nextLine();

        String archivoAMostrar = null;
        if (opcion.equals("1")) {
            archivoAMostrar = archivoMenos30;
        } else if (opcion.equals("2")) {
            archivoAMostrar = archivoMayorIgual30;
        } else {
            System.out.println("Opción inválida. Saliendo...");
            scanner.close();
            return;
        }

        // 4. Mostrar archivo con formato legible
        System.out.println("\nContenido del archivo seleccionado (" + archivoAMostrar + "):\n");
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoAMostrar))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        scanner.close();
    }
}
