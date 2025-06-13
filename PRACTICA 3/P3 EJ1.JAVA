
package Practica3;


public class Main {
   public static void main(String[] args) {
       Estudiante estudiante = new Estudiante("Ana", "12345678", 21, "2025-001");
       estudiante.mostrarDatos();
   }
}

public class Estudiante extends Persona {
   public String matricula;    // Atributo propio de la subclase


   public Estudiante(String nombre, String documento, int edad, String matricula) {
       super(nombre, documento, edad);
       this.matricula = matricula;
   }


   public void mostrarDatos() {
       System.out.println("=== Datos del Estudiante ===");


       // ✅ Se puede acceder al campo protected
       System.out.println("Nombre (protected): " + nombre);


       // ❌ No se puede acceder directamente a un campo private de la superclase
       // System.out.println("Documento (private): " + documento); // Error


       // ✅ Se puede acceder al campo public
       System.out.println("Edad (public): " + edad);


       // ✅ Se puede llamar a metodo protected
       mostrarNombre();


       // ❌ No se puede llamar a metodo private
       // mostrarDocumento(); // Error


       // ✅ Se puede llamar a metodo public
       mostrarEdad();


       // ✅ Podemos mostrar el documento usando un metodo público de la superclase
       mostrarDocumentoPublico();


       System.out.println("Matrícula: " + matricula);
   }
}

public class Persona {
   protected String nombre;        // Accesible en subclases
   private String documento;       // No accesible fuera de esta clase
   public int edad;                // Accesible desde cualquier parte


   public Persona(String nombre, String documento, int edad) {
       this.nombre = nombre;
       this.documento = documento;
       this.edad = edad;
   }


   protected void mostrarNombre() {
       System.out.println("Nombre: " + nombre);
   }


   private void mostrarDocumento() {
       System.out.println("Documento: " + documento);
   }


   public void mostrarEdad() {
       System.out.println("Edad: " + edad);
   }


   // Metodo público para mostrar el documento (solo si es necesario desde fuera)
   public void mostrarDocumentoPublico() {
       System.out.println("Documento (acceso público indirecto): " + documento);
   }
}
