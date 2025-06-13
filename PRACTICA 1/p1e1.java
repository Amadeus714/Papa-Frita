package P1;
import java.util.Scanner;

// Clase principal
public class Club {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);


       // 1. Crear dos socios
       System.out.print("Ingrese el nombre del primer socio: ");
       String nombreSocio1 = scanner.nextLine();
       Socio socio1 = new Socio(nombreSocio1);
       socio1.mostrarDatos();


       System.out.print("Ingrese el nombre del segundo socio: ");
       String nombreSocio2 = scanner.nextLine();
       Socio socio2 = new Socio(nombreSocio2);
       socio2.mostrarDatos();


       // 2. Crear administrativos y comparar sueldos
       System.out.println("\n--- Crear Administrativo ---");
       // Ejemplo 1
       System.out.print("Ingrese el nombre del administrativo: ");
       String nombreAdmin = scanner.nextLine();
       System.out.print("Ingrese el teléfono del administrativo: ");
       String telefonoAdmin = scanner.nextLine();
       System.out.print("Ingrese el sueldo del administrativo: ");
       double sueldoAdmin = scanner.nextDouble();
       Administrativo admin1 = new Administrativo(nombreAdmin, telefonoAdmin, sueldoAdmin);
       admin1.mostrarDatos();


       // Ejemplo 2
       System.out.println("\n--- Crear otro Administrativo ---");
       scanner.nextLine();  // Limpiar el buffer del scanner
       System.out.print("Ingrese el nombre del segundo administrativo: ");
       String nombreAdmin2 = scanner.nextLine();
       System.out.print("Ingrese el teléfono del segundo administrativo: ");
       String telefonoAdmin2 = scanner.nextLine();
       System.out.print("Ingrese el sueldo del segundo administrativo: ");
       double sueldoAdmin2 = scanner.nextDouble();
       Administrativo admin2 = new Administrativo(nombreAdmin2, telefonoAdmin2, sueldoAdmin2);
       admin2.mostrarDatos();


       // Comparar sueldos de los dos administrativos
       Administrativo.ejemplo2(admin1, admin2);


       // Cerrar el scanner
       scanner.close();
   }
}

class Socio {
   private String nombre;
   private int numero;
   private static int proximoNumero = 1;


   // Constructor
   public Socio(String nombre) {
       this.nombre = nombre;
       this.numero = proximoNumero++;
   }


   // Metodos de acceso y modificación
   public String getNombre() {
       return nombre;
   }


   public void setNombre(String nombre) {
       this.nombre = nombre;
   }


   public int getNumero() {
       return numero;
   }


   public void setNumero(int numero) {
       this.numero = numero;
   }


   // Metodo para mostrar los datos del socio
   public void mostrarDatos() {
       System.out.println("Socio: " + nombre + ", Número: " + numero);
   }
}

// Clase Administrativo
class Administrativo {
   private String nombre;
   private String telefono;
   private double sueldo;


   // Constructor
   public Administrativo(String nombre, String telefono, double sueldo) {
       this.nombre = nombre;
       this.telefono = telefono;
       this.sueldo = sueldo;
   }


   // Metodos de acceso y modificación
   public String getNombre() {
       return nombre;
   }


   public void setNombre(String nombre) {
       this.nombre = nombre;
   }


   public String getTelefono() {
       return telefono;
   }


   public void setTelefono(String telefono) {
       this.telefono = telefono;
   }


   public double getSueldo() {
       return sueldo;
   }


   public void setSueldo(double sueldo) {
       this.sueldo = sueldo;
   }


   // Metodo para mostrar los datos del administrativo
   public void mostrarDatos() {
       System.out.println("Administrativo: " + nombre + ", Teléfono: " + telefono + ", Sueldo: " + sueldo);
   }


   // Metodo para comparar sueldos
   public static void ejemplo2(Administrativo admin1, Administrativo admin2) {
       if (admin1.getSueldo() > admin2.getSueldo()) {
           System.out.println(admin1.getNombre() + " gana más.");
       } else if (admin1.getSueldo() < admin2.getSueldo()) {
           System.out.println(admin2.getNombre() + " gana más.");
       } else {
           System.out.println("Ambos ganan lo mismo.");
       }
   }
}
