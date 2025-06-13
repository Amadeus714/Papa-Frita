import java.io.*;
import java.util.Scanner;

public class ConversorTexto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rutaEntrada = "entrada.txt";
        String rutaSalida = "salida.txt";
        String contenidoFijo = "Hola Mundo. Este es un texto de prueba.";

        // Crear archivo de entrada con contenido fijo
        try (BufferedWriter escritorInicial = new BufferedWriter(new FileWriter(rutaEntrada))) {
            escritorInicial.write(contenidoFijo);
        } catch (IOException e) {
            System.out.println("Error al crear archivo de entrada: " + e.getMessage());
            return;
        }

        System.out.print("¿Convertir a (M)ayúsculas o (m)inúsculas?: ");
        String opcion = scanner.nextLine();

        if (opcion.equals("M")) {
            // Convertir a MAYÚSCULAS
            convertirArchivo(rutaEntrada, rutaSalida, true);
        } else if (opcion.equals("m")) {
            // Convertir a minúsculas
            convertirArchivo(rutaEntrada, rutaSalida, false);
        } else {
            System.out.println("Opción inválida. Por favor, ingresa solo 'M' o 'm'.");
            scanner.close();
            return;
        }

        scanner.close();
    }

    private static void convertirArchivo(String rutaEntrada, String rutaSalida, boolean aMayusculas) {
        System.out.println("\nTexto convertido a " + (aMayusculas ? "MAYÚSCULAS" : "minúsculas") + ":\n");

        try (
                BufferedReader lector = new BufferedReader(new FileReader(rutaEntrada));
                BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaSalida))
        ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String lineaConvertida = aMayusculas ? linea.toUpperCase() : linea.toLowerCase();

                escritor.write(lineaConvertida);
                escritor.newLine();

                System.out.println(lineaConvertida);
            }

            System.out.println("\nTexto convertido guardado en: " + rutaSalida);

        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
