package P1_3;


// Clase Vendedor
class Vendedor {
   // Propiedades
   private double salarioBase;
   private double porcentajeComision;
   private double ventasTotales;


   // Constructor
   public Vendedor() {
       this.salarioBase = 2000.0; // Salario base
       this.porcentajeComision = 0.06; // Porcentaje de comisión
       this.ventasTotales = 0.0; // Inicializar las ventas a cero
   }


   // Metodo para agregar ventas de un día
   public void agregarVentas(double ventasDelDia) {
       this.ventasTotales += ventasDelDia;
   }


   // Metodo para calcular la comisión
   public double calcularComision() {
       return ventasTotales * porcentajeComision;
   }


   // Metodo para calcular el ingreso total
   public double calcularIngresoTotal() {
       return salarioBase + calcularComision();
   }


   // Metodo para obtener las ventas totales
   public double obtenerVentasTotales() {
       return ventasTotales;
   }
}

// Clase principal


public class VendedorLenovo {
   public static void main(String[] args) {


       // Crear un objeto Vendedor
       Vendedor vendedor = new Vendedor();


       // Crear un scanner para leer las entradas
       Scanner scanner = new Scanner(System.in);


       // Instrucción inicial
       System.out.println("Ingrese el monto de las ventas para cada día de la semana:");


       // Leer las ventas de cada día (7 días de la semana)
       for (int dia = 1; dia <= 7; dia++) {
           System.out.print("Ventas del día " + dia + ": $");
           double ventasDelDia = scanner.nextDouble();
           vendedor.agregarVentas(ventasDelDia); // Agregar ventas del día al total
       }


       // Calcular y mostrar el resumen
       double ventasTotales = vendedor.obtenerVentasTotales();
       double comision = vendedor.calcularComision();
       double ingresoTotal = vendedor.calcularIngresoTotal();


       // Mostrar el resultado
       System.out.println("\nResumen de ingresos del vendedor:");
       System.out.println("Ventas totales de la semana: $" + ventasTotales);
       System.out.println("Comisión ganada: $" + comision);
       System.out.println("Ingreso total (salario base + comisión): $" + ingresoTotal);


       // Cerrar el scanner
       scanner.close();
   }
}
