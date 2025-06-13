package Ejercicio2;

public class Main {
   public static void main(String[] args) {
       System.out.println("=== Liquidación de Haberes - Mi Luz S.A. ===\n");


       Empleado e1 = new EmpleadoAsalariado(1001, "Juan", "Pérez", 120000);
       Empleado e2 = new EmpleadoPorHoras(1002, "Laura", "Gómez", 160, 900);
       Empleado e3 = new EmpleadoPorComision(1003, "Carlos", "López", 350000, 0.05);
       Empleado e4 = new EmpleadoBaseMasComision(1004, "María", "Ramírez", 80000, 200000, 0.03);


       e1.mostrarInformacion();
       System.out.println();
       e2.mostrarInformacion();
       System.out.println();
       e3.mostrarInformacion();
       System.out.println();
       e4.mostrarInformacion();
   }
}

public abstract class Empleado {
   protected int legajo;
   protected String nombre;
   protected String apellido;


   public Empleado(int legajo, String nombre, String apellido) {
       this.legajo = legajo;
       this.nombre = nombre;
       this.apellido = apellido;
   }


   public int getLegajo() { return legajo; }
   public void setLegajo(int legajo) { this.legajo = legajo; }


   public String getNombre() { return nombre; }
   public void setNombre(String nombre) { this.nombre = nombre; }


   public String getApellido() { return apellido; }
   public void setApellido(String apellido) { this.apellido = apellido; }


   public abstract double calcularSueldo();


   public void mostrarInformacion() {
       System.out.println("Legajo: " + legajo);
       System.out.println("Nombre: " + nombre + " " + apellido);
   }
}

public class EmpleadoAsalariado extends Empleado {
   private double sueldoMensual;


   public EmpleadoAsalariado(int legajo, String nombre, String apellido, double sueldoMensual) {
       super(legajo, nombre, apellido);
       this.sueldoMensual = sueldoMensual;
   }


   public double getSueldoMensual() { return sueldoMensual; }
   public void setSueldoMensual(double sueldoMensual) { this.sueldoMensual = sueldoMensual; }


   @Override
   public double calcularSueldo() {
       return sueldoMensual;
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Asalariado");
       System.out.println("Sueldo mensual: $" + sueldoMensual);
       System.out.println("Sueldo a pagar: $" + calcularSueldo());
   }
}

public class EmpleadoPorHoras extends Empleado {
   private int horasTrabajadas;
   private double valorHora;


   public EmpleadoPorHoras(int legajo, String nombre, String apellido, int horasTrabajadas, double valorHora) {
       super(legajo, nombre, apellido);
       this.horasTrabajadas = horasTrabajadas;
       this.valorHora = valorHora;
   }


   public int getHorasTrabajadas() { return horasTrabajadas; }
   public void setHorasTrabajadas(int horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }


   public double getValorHora() { return valorHora; }
   public void setValorHora(double valorHora) { this.valorHora = valorHora; }


   @Override
   public double calcularSueldo() {
       return horasTrabajadas * valorHora;
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Por horas");
       System.out.println("Horas trabajadas: " + horasTrabajadas);
       System.out.println("Valor hora: $" + valorHora);
       System.out.println("Sueldo a pagar: $" + calcularSueldo());
   }
}

public class EmpleadoPorComision extends Empleado {
   private double ventas;
   private double tasaComision;


   public EmpleadoPorComision(int legajo, String nombre, String apellido, double ventas, double tasaComision) {
       super(legajo, nombre, apellido);
       this.ventas = ventas;
       this.tasaComision = tasaComision;
   }


   public double getVentas() { return ventas; }
   public void setVentas(double ventas) { this.ventas = ventas; }


   public double getTasaComision() { return tasaComision; }
   public void setTasaComision(double tasaComision) { this.tasaComision = tasaComision; }


   @Override
   public double calcularSueldo() {
       return ventas * tasaComision;
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Por comisión");
       System.out.println("Ventas: $" + ventas);
       System.out.println("Tasa de comisión: " + (tasaComision * 100) + "%");
       System.out.println("Sueldo a pagar: $" + calcularSueldo());
   }
}

public class EmpleadoBaseMasComision extends Empleado {
   private double sueldoBase;
   private double ventas;
   private double tasaComision;


   public EmpleadoBaseMasComision(int legajo, String nombre, String apellido, double sueldoBase, double ventas, double tasaComision) {
       super(legajo, nombre, apellido);
       this.sueldoBase = sueldoBase;
       this.ventas = ventas;
       this.tasaComision = tasaComision;
   }


   public double getSueldoBase() { return sueldoBase; }
   public void setSueldoBase(double sueldoBase) { this.sueldoBase = sueldoBase; }


   public double getVentas() { return ventas; }
   public void setVentas(double ventas) { this.ventas = ventas; }


   public double getTasaComision() { return tasaComision; }
   public void setTasaComision(double tasaComision) { this.tasaComision = tasaComision; }


   @Override
   public double calcularSueldo() {
       return sueldoBase + (ventas * tasaComision);
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Base + comisión");
       System.out.println("Sueldo base: $" + sueldoBase);
       System.out.println("Ventas: $" + ventas);
       System.out.println("Tasa de comisión: " + (tasaComision * 100) + "%");
       System.out.println("Sueldo a pagar: $" + calcularSueldo());
   }
}

