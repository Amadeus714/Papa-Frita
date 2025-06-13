// Clase abstracta base
abstract static class Empleado {
   protected int legajo;
   protected String nombre;
   protected String apellido;


   public Empleado(int legajo, String nombre, String apellido) {
       this.legajo = legajo;
       this.nombre = nombre;
       this.apellido = apellido;
   }


   public abstract double calcularSueldo();


   public void mostrarInformacion() {
       System.out.println("Legajo: " + legajo);
       System.out.println("Nombre: " + nombre + " " + apellido);
   }
}


// Empleado Asalariado
static class EmpleadoAsalariado extends Empleado {
   private double sueldoMensual;


   public EmpleadoAsalariado(int legajo, String nombre, String apellido, double sueldoMensual) {
       super(legajo, nombre, apellido);
       this.sueldoMensual = sueldoMensual;
   }


   @Override
   public double calcularSueldo() {
       return sueldoMensual;
   }


   public double calcularSueldo(String dato) {
       if (dato.toLowerCase().contains("bono")) {
           return sueldoMensual + 5000;
       }
       return sueldoMensual;
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Asalariado");
       System.out.println("Sueldo base: $" + sueldoMensual);
   }
}


// Empleado Por Horas
static class EmpleadoPorHoras extends Empleado {
   private int horas;
   private double valorHora;


   public EmpleadoPorHoras(int legajo, String nombre, String apellido, int horas, double valorHora) {
       super(legajo, nombre, apellido);
       this.horas = horas;
       this.valorHora = valorHora;
   }


   @Override
   public double calcularSueldo() {
       return horas * valorHora;
   }


   public double calcularSueldo(String dato) {
       if (dato.toLowerCase().contains("extra")) {
           return (horas + 10) * valorHora;
       }
       return calcularSueldo();
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Por horas");
       System.out.println("Horas trabajadas: " + horas);
       System.out.println("Valor hora: $" + valorHora);
   }
}


// Empleado Por Comisión
static class EmpleadoPorComision extends Empleado {
   private double ventas;
   private double tasa;


   public EmpleadoPorComision(int legajo, String nombre, String apellido, double ventas, double tasa) {
       super(legajo, nombre, apellido);
       this.ventas = ventas;
       this.tasa = tasa;
   }


   @Override
   public double calcularSueldo() {
       return ventas * tasa;
   }


   public double calcularSueldo(String dato) {
       if (dato.toLowerCase().contains("doble")) {
           return calcularSueldo() * 2;
       }
       return calcularSueldo();
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Por comisión");
       System.out.println("Ventas: $" + ventas);
       System.out.println("Tasa: " + (tasa * 100) + "%");
   }
}


// Empleado Base + Comisión
static class EmpleadoBaseMasComision extends Empleado {
   private double base;
   private double ventas;
   private double tasa;


   public EmpleadoBaseMasComision(int legajo, String nombre, String apellido, double base, double ventas, double tasa) {
       super(legajo, nombre, apellido);
       this.base = base;
       this.ventas = ventas;
       this.tasa = tasa;
   }


   @Override
   public double calcularSueldo() {
       return base + (ventas * tasa);
   }


   public double calcularSueldo(String dato) {
       if (dato.toLowerCase().contains("incentivo")) {
           return calcularSueldo() + 8000;
       }
       return calcularSueldo();
   }


   @Override
   public void mostrarInformacion() {
       super.mostrarInformacion();
       System.out.println("Tipo: Base + comisión");
       System.out.println("Base: $" + base);
       System.out.println("Ventas: $" + ventas);
       System.out.println("Tasa: " + (tasa * 100) + "%");
   }
}


public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);


   Empleado[] empleados = new Empleado[4];
   empleados[0] = new EmpleadoAsalariado(1, "Ana", "Martínez", 120000);
   empleados[1] = new EmpleadoPorHoras(2, "Luis", "Gómez", 160, 900);
   empleados[2] = new EmpleadoPorComision(3, "Carlos", "López", 300000, 0.05);
   empleados[3] = new EmpleadoBaseMasComision(4, "Sofía", "Fernández", 80000, 150000, 0.03);


   for (Empleado emp : empleados) {
       emp.mostrarInformacion();
       System.out.print("Ingrese palabra clave (bono, extra, incentivo, doble): ");
       String input = sc.nextLine();


       double sueldoFinal;
       if (emp instanceof EmpleadoAsalariado) {
           sueldoFinal = ((EmpleadoAsalariado) emp).calcularSueldo(input);
       } else if (emp instanceof EmpleadoPorHoras) {
           sueldoFinal = ((EmpleadoPorHoras) emp).calcularSueldo(input);
       } else if (emp instanceof EmpleadoPorComision) {
           sueldoFinal = ((EmpleadoPorComision) emp).calcularSueldo(input);
       } else if (emp instanceof EmpleadoBaseMasComision) {
           sueldoFinal = ((EmpleadoBaseMasComision) emp).calcularSueldo(input);
       } else {
           sueldoFinal = emp.calcularSueldo();
       }


       System.out.println("Sueldo final calculado: $" + sueldoFinal);
       System.out.println("------------------------------------------------");
   }


   sc.close();
}


