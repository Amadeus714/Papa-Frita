public class Planeta {
   // Enumeración para el tipo de planeta
   public enum TipoPlaneta {
       GASEOSO, TERRESTRE, ENANO
   }


   // Atributos
   private String nombre = null;
   private int cantidadSatelites = 0;
   private double masa = 0.0; // en kilogramos
   private double volumen = 0.0; // en km³
   private int diametro = 0; // en km
   private int distanciaMediaSol = 0; // en millones de km
   private TipoPlaneta tipo = TipoPlaneta.TERRESTRE;
   private boolean observable = false;


   // Constructor
   public Planeta(String nombre, int cantidadSatelites, double masa, double volumen, int diametro,
                  int distanciaMediaSol, TipoPlaneta tipo, boolean observable) {
       this.nombre = nombre;
       this.cantidadSatelites = cantidadSatelites;
       this.masa = masa;
       this.volumen = volumen;
       this.diametro = diametro;
       this.distanciaMediaSol = distanciaMediaSol;
       this.tipo = tipo;
       this.observable = observable;
   }


   // Método para mostrar los datos del planeta
   public void mostrarInformacion() {
       System.out.println("Nombre: " + nombre);
       System.out.println("Cantidad de satélites: " + cantidadSatelites);
       System.out.println("Masa (kg): " + masa);
       System.out.println("Volumen (km³): " + volumen);
       System.out.println("Diámetro (km): " + diametro);
       System.out.println("Distancia media al Sol (millones km): " + distanciaMediaSol);
       System.out.println("Tipo de planeta: " + tipo);
       System.out.println("¿Observable a simple vista?: " + (observable ? "Sí" : "No"));
       System.out.println("-----------------------------");
   }


   // Método para calcular la densidad
   public double calcularDensidad() {
       if (volumen > 0) {
           return masa / volumen;
       } else {
           return 0.0;
       }
   }


   // Método para determinar si es un planeta exterior
   public boolean esExterior() {
       double distanciaEnKm = distanciaMediaSol * 1_000_000.0;
       double UA = 149_597_870.0;
       double distanciaUA = distanciaEnKm / UA;
       return distanciaUA > 3.4;
   }
}


public class SistemaSolarTest {
   public static void main(String[] args) {
       // Crear dos planetas
       Planeta jupiter = new Planeta(
               "Júpiter",
               79,
               1.898e27,
               1.431e15,
               139820,
               778, // millones de km
               Planeta.TipoPlaneta.GASEOSO,
               true
       );


       Planeta marte = new Planeta(
               "Marte",
               2,
               6.417e23,
               1.6318e11,
               6779,
               228,
               Planeta.TipoPlaneta.TERRESTRE,
               true
       );


       // Mostrar información
       System.out.println("PLANETA 1:");
       jupiter.mostrarInformacion();
       System.out.printf("Densidad: %.2f kg/km³\n", jupiter.calcularDensidad());
       System.out.println("¿Es un planeta exterior?: " + (jupiter.esExterior() ? "Sí" : "No"));
       System.out.println();


       System.out.println("PLANETA 2:");
       marte.mostrarInformacion();
       System.out.printf("Densidad: %.2f kg/km³\n", marte.calcularDensidad());
       System.out.println("¿Es un planeta exterior?: " + (marte.esExterior() ? "Sí" : "No"));
   }
}
