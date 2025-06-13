public class BancoTest {
   public static void main(String[] args) {
       // Crear cuenta con valores iniciales
       Cuenta cuenta1 = new Cuenta(40123456L, 10000.0, 5.0);


       // Crear cuenta por defecto y modificar luego
       Cuenta cuenta2 = new Cuenta();
       cuenta2.setDni(40234567L);
       cuenta2.setSaldo(5000.0);
       cuenta2.setInteresAnual(3.5);


       // Mostrar datos iniciales
       System.out.println("Estado inicial de las cuentas:");
       cuenta1.mostrarDatos();
       cuenta2.mostrarDatos();


       // Realizar operaciones
       cuenta1.ingresar(1500.0);
       cuenta2.retirar(1000.0);
       cuenta1.actualizarSaldo();
       cuenta2.actualizarSaldo();


       // Mostrar datos finales
       System.out.println("Estado después de operaciones:");
       cuenta1.mostrarDatos();
       cuenta2.mostrarDatos();
   }
}

public class Cuenta {
   // Atributos
   private static long contadorCuentas = 100001; // Se incrementa con cada nueva cuenta
   private final long numeroCuenta;
   private long dni;
   private double saldo;
   private double interesAnual; // En porcentaje, ej: 5.0 para 5%


   // Constructor por defecto
   public Cuenta() {
       this.numeroCuenta = contadorCuentas++;
       this.dni = 0;
       this.saldo = 0.0;
       this.interesAnual = 0.0;
   }
   // Constructor con parámetros (sin número de cuenta, que se asigna automáticamente)
   public Cuenta(long dni, double saldo, double interesAnual) {
       this.numeroCuenta = contadorCuentas++;
       this.dni = dni;
       this.saldo = saldo;
       this.interesAnual = interesAnual;
   }


   // Métodos get
   public long getNumeroCuenta() {
       return numeroCuenta;
   }


   public long getDni() {
       return dni;
   }
   public double getSaldo() {
       return saldo;
   }
   public double getInteresAnual() {
       return interesAnual;
   }
   // Métodos set (modificadores)
   public void setDni(long dni) {
       this.dni = dni;
   }
   public void setSaldo(double saldo) {
       this.saldo = saldo;
   }
   public void setInteresAnual(double interesAnual) {
       this.interesAnual = interesAnual;
   }
   // Actualizar saldo aplicando el interés diario
   public void actualizarSaldo() {
       double interesDiario = interesAnual / 365 / 100;
       saldo += saldo * interesDiario;
   }
   // Ingresar dinero
   public void ingresar(double cantidad) {
       if (cantidad > 0) {
           saldo += cantidad;
       }
   }
   // Retirar dinero si hay saldo suficiente
   public void retirar(double cantidad) {
       if (cantidad > 0 && cantidad <= saldo) {
           saldo -= cantidad;
       }
   }
   // Mostrar todos los datos de la cuenta
   public void mostrarDatos() {
       System.out.println("Número de cuenta: " + numeroCuenta);
       System.out.println("DNI del cliente: " + dni);
       System.out.printf("Saldo actual: %.2f\n", saldo);
       System.out.printf("Interés anual: %.2f%%\n", interesAnual);
       System.out.println("-----------------------------");
   }
}
