package P1_2;


public class PruebaFactura {


   public static void main(String[] args) {
       // Crear una factura
       Factura factura1 = new Factura("001", "A123", "Taladro", 3, 150.75);
       System.out.println(factura1);  // Imprime la factura con los detalles y monto total


       // Crear otra factura con valores inválidos
       Factura factura2 = new Factura("002", "B456", "Martillo", -5, -100);
       System.out.println(factura2);  // Verifica que la cantidad y el precio sean ajustados a valores válidos


       // Crear una factura con valores positivos
       Factura factura3 = new Factura("003", "C789", "Destornillador", 2, 50.0);
       System.out.println(factura3);  // Imprime los detalles de la factura con un monto correcto
   }
}

public class Factura {
   // Variables de instancia
   private String numeroFactura;
   private String numeroArticulo;
   private String descripcionArticulo;
   private int cantidad;
   private double precioPorArticulo;


   // Constructor
   public Factura(String numeroFactura, String numeroArticulo, String descripcionArticulo, int cantidad, double precioPorArticulo) {
       this.numeroFactura = numeroFactura;
       this.numeroArticulo = numeroArticulo;
       this.descripcionArticulo = descripcionArticulo;
       // Verifica que la cantidad y precio sean positivos
       setCantidad(cantidad);
       setPrecioPorArticulo(precioPorArticulo);
   }


   // Métodos get y set
   public String getNumeroFactura() {
       return numeroFactura;
   }


   public void setNumeroFactura(String numeroFactura) {
       this.numeroFactura = numeroFactura;
   }


   public String getNumeroArticulo() {
       return numeroArticulo;
   }


   public void setNumeroArticulo(String numeroArticulo) {
       this.numeroArticulo = numeroArticulo;
   }


   public String getDescripcionArticulo() {
       return descripcionArticulo;
   }


   public void setDescripcionArticulo(String descripcionArticulo) {
       this.descripcionArticulo = descripcionArticulo;
   }


   public int getCantidad() {
       return cantidad;
   }


   public void setCantidad(int cantidad) {
       if (cantidad > 0) {
           this.cantidad = cantidad;
       } else {
           this.cantidad = 0;  // Si la cantidad no es positiva, la ponemos en 0
       }
   }


   public double getPrecioPorArticulo() {
       return precioPorArticulo;
   }


   public void setPrecioPorArticulo(double precioPorArticulo) {
       if (precioPorArticulo > 0) {
           this.precioPorArticulo = precioPorArticulo;
       } else {
           this.precioPorArticulo = 0.0;  // Si el precio no es positivo, lo ponemos en 0.0
       }
   }


   // Método para calcular el monto total de la factura
   public double obtenerMontoFactura() {
       return cantidad * precioPorArticulo;
   }


   // Método toString para mostrar la información de la factura
   @Override
   public String toString() {
       return "Factura Nº: " + numeroFactura + "\n" +
               "Artículo: " + descripcionArticulo + "\n" +
               "Número de Artículo: " + numeroArticulo + "\n" +
               "Cantidad: " + cantidad + "\n" +
               "Precio por Artículo: " + precioPorArticulo + "\n" +
               "Monto Total: " + obtenerMontoFactura();
   }
}
