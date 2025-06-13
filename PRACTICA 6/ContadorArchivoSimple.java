import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ContadorArchivoSimple {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear el archivo con un string fijo
        String nombreArchivo = "frase.txt";
        String contenidoArchivo = "Hola mundo, esto es una prueba.";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenidoArchivo);
            System.out.println("Archivo '" + nombreArchivo + "' \ncreado con contenido del archivo: " + contenidoArchivo);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            return;
        }

        // Pedir opción al usuario
        System.out.print("Ingrese opción (c = caracteres, l = líneas, cl = ambos): ");
        String opciones = scanner.nextLine().toLowerCase();

        boolean contarCaracteres = opciones.contains("c");
        boolean contarLineas = opciones.contains("l");

        int cantidadCaracteres = 0;
        int cantidadLineas = 0;

        // Leer el archivo y contar
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (contarLineas) cantidadLineas++;
                if (contarCaracteres) cantidadCaracteres += linea.length();
            }

            if (contarCaracteres) System.out.println("Cantidad de caracteres: " + cantidadCaracteres);
            if (contarLineas) System.out.println("Cantidad de líneas: " + cantidadLineas);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}