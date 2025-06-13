import java.util.ArrayList;
import java.util.Scanner;


public class CursoUniversitario {
   private ArrayList<Estudiante> estudiantes;


   public CursoUniversitario() {
       this.estudiantes = new ArrayList<>();
   }


   public static void main(String[] args) {
       CursoUniversitario curso = new CursoUniversitario();
       Scanner scanner = new Scanner(System.in);
       int opcion;


       do {
           System.out.println("\n--- MENÚ DE GESTIÓN DE CURSO ---");
           System.out.println("1. Añadir estudiante");
           System.out.println("2. Buscar estudiante");
           System.out.println("3. Eliminar estudiante");
           System.out.println("4. Cargar notas de estudiantes");
           System.out.println("5. Calcular promedio del curso");
           System.out.println("6. Mostrar estudiantes aprobados");
           System.out.println("7. Salir");
           System.out.print("Seleccione una opción: ");


           opcion = scanner.nextInt();
           scanner.nextLine(); // Limpiar buffer


           switch (opcion) {
               case 1:
                   curso.agregarEstudiante(scanner);
                   break;
               case 2:
                   curso.buscarEstudiante(scanner);
                   break;
               case 3:
                   curso.eliminarEstudiante(scanner);
                   break;
               case 4:
                   curso.cargarNotas(scanner);
                   break;
               case 5:
                   curso.calcularPromedioCurso();
                   break;
               case 6:
                   curso.mostrarAprobados();
                   break;
               case 7:
                   System.out.println("Saliendo del sistema...");
                   break;
               default:
                   System.out.println("Opción no válida. Intente nuevamente.");
           }
       } while (opcion != 7);


       scanner.close();
   }


   // Clase interna para representar a un estudiante
   private static class Estudiante {
       private String nombre;
       private String apellidos;
       private String codigo;
       private int semestre;
       private double notaFinal;


       public Estudiante(String nombre, String apellidos, String codigo, int semestre) {
           this.nombre = nombre;
           this.apellidos = apellidos;
           this.codigo = codigo;
           this.semestre = semestre;
           this.notaFinal = 0.0;
       }


       // Getters y setters
       public String getCodigo() {
           return codigo;
       }


       public double getNotaFinal() {
           return notaFinal;
       }


       public void setNotaFinal(double notaFinal) {
           this.notaFinal = notaFinal;
       }


       @Override
       public String toString() {
           return String.format("Código: %s\nNombre: %s %s\nSemestre: %d\nNota Final: %.2f",
                   codigo, nombre, apellidos, semestre, notaFinal);
       }
   }


   // Metodo para agregar un nuevo estudiante
   public void agregarEstudiante(Scanner scanner) {
       System.out.println("\n--- AGREGAR NUEVO ESTUDIANTE ---");


       System.out.print("Ingrese el código del estudiante: ");
       String codigo = scanner.nextLine();


       // Verificar si el código ya existe
       if (buscarPorCodigo(codigo) != null) {
           System.out.println("Error: Ya existe un estudiante con este código.");
           return;
       }


       System.out.print("Ingrese el nombre del estudiante: ");
       String nombre = scanner.nextLine();


       System.out.print("Ingrese los apellidos del estudiante: ");
       String apellidos = scanner.nextLine();


       System.out.print("Ingrese el semestre del estudiante: ");
       int semestre = scanner.nextInt();
       scanner.nextLine(); // Limpiar buffer


       Estudiante nuevoEstudiante = new Estudiante(nombre, apellidos, codigo, semestre);
       estudiantes.add(nuevoEstudiante);


       System.out.println("Estudiante agregado exitosamente!");
   }


   // Metodo para buscar un estudiante por código
   public void buscarEstudiante(Scanner scanner) {
       System.out.println("\n--- BUSCAR ESTUDIANTE ---");
       System.out.print("Ingrese el código del estudiante a buscar: ");
       String codigo = scanner.nextLine();


       Estudiante encontrado = buscarPorCodigo(codigo);


       if (encontrado != null) {
           System.out.println("\nESTUDIANTE ENCONTRADO:");
           System.out.println(encontrado);
       } else {
           System.out.println("No se encontró ningún estudiante con ese código.");
       }
   }


   // Metodo para eliminar un estudiante
   public void eliminarEstudiante(Scanner scanner) {
       System.out.println("\n--- ELIMINAR ESTUDIANTE ---");
       System.out.print("Ingrese el código del estudiante a eliminar: ");
       String codigo = scanner.nextLine();


       Estudiante encontrado = buscarPorCodigo(codigo);


       if (encontrado != null) {
           System.out.println("\nESTUDIANTE A ELIMINAR:");
           System.out.println(encontrado);


           System.out.print("\n¿Está seguro que desea eliminar este estudiante? (S/N): ");
           String confirmacion = scanner.nextLine();


           if (confirmacion.equalsIgnoreCase("S")) {
               estudiantes.remove(encontrado);
               System.out.println("Estudiante eliminado exitosamente.");
           } else {
               System.out.println("Operación cancelada.");
           }
       } else {
           System.out.println("No se encontró ningún estudiante con ese código.");
       }
   }


   // Metodo para cargar notas de estudiantes
   public void cargarNotas(Scanner scanner) {
       System.out.println("\n--- CARGAR NOTAS DE ESTUDIANTES ---");


       if (estudiantes.isEmpty()) {
           System.out.println("No hay estudiantes registrados en el curso.");
           return;
       }


       System.out.println("Lista de estudiantes:");
       for (Estudiante est : estudiantes) {
           System.out.println(est.getCodigo() + " - " + est.nombre + " " + est.apellidos);
       }


       System.out.print("\nIngrese el código del estudiante a calificar: ");
       String codigo = scanner.nextLine();


       Estudiante encontrado = buscarPorCodigo(codigo);


       if (encontrado != null) {
           System.out.print("Ingrese la nota final del estudiante (0.0 - 5.0): ");
           double nota = scanner.nextDouble();
           scanner.nextLine(); // Limpiar buffer


           if (nota >= 0.0 && nota <= 5.0) {
               encontrado.setNotaFinal(nota);
               System.out.println("Nota asignada correctamente.");
           } else {
               System.out.println("Error: La nota debe estar entre 0.0 y 5.0");
           }
       } else {
           System.out.println("No se encontró ningún estudiante con ese código.");
       }
   }


   // Metodo para calcular el promedio del curso
   public void calcularPromedioCurso() {
       if (estudiantes.isEmpty()) {
           System.out.println("\nNo hay estudiantes registrados en el curso.");
           return;
       }


       double sumaNotas = 0.0;
       int contador = 0;


       for (Estudiante est : estudiantes) {
           if (est.getNotaFinal() > 0.0) { // Solo contar estudiantes con nota asignada
               sumaNotas += est.getNotaFinal();
               contador++;
           }
       }


       if (contador == 0) {
           System.out.println("\nNo hay notas asignadas para calcular el promedio.");
       } else {
           double promedio = sumaNotas / contador;
           System.out.printf("\nPromedio del curso: %.2f (basado en %d estudiantes)%n", promedio, contador);
       }
   }


   // Metodo para mostrar estudiantes aprobados
   public void mostrarAprobados() {
       if (estudiantes.isEmpty()) {
           System.out.println("\nNo hay estudiantes registrados en el curso.");
           return;
       }


       int aprobados = 0;
       int totalConNota = 0;


       for (Estudiante est : estudiantes) {
           if (est.getNotaFinal() > 0.0) { // Solo contar estudiantes con nota asignada
               totalConNota++;
               if (est.getNotaFinal() >= 4.0) {
                   aprobados++;
               }
           }
       }


       if (totalConNota == 0) {
           System.out.println("\nNo hay notas asignadas para calcular aprobados.");
       } else {
           double porcentaje = (double) aprobados / totalConNota * 100;
           System.out.printf("\nEstudiantes aprobados: %d de %d (%.2f%%)%n",
                   aprobados, totalConNota, porcentaje);
       }
   }


   // Metodo auxiliar para buscar estudiante por código
   private Estudiante buscarPorCodigo(String codigo) {
       for (Estudiante est : estudiantes) {
           if (est.getCodigo().equalsIgnoreCase(codigo)) {
               return est;
           }
       }
       return null;
   }
}

