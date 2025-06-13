public class BibliotecaTest {
   public static void main(String[] args) {
       // Crear libro original que no se presta
       Libro libro1 = new Libro("Don Quijote - Original", true, false);


       // Crear libro fotocopia que sí se presta
       Libro libro2 = new Libro("Don Quijote - Fotocopia", false, true);


       // Mostrar información de ambos libros
       libro1.mostrarLibro();
       libro2.mostrarLibro();


       // También podrías usar individualmente los métodos de instancia, por ejemplo:
       System.out.println("¿Libro 1 es original? " + libro1.esOriginal());
       System.out.println("¿Libro 2 se presta? " + libro2.sePresta());
   }
}

public class BibliotecaTest {
   public static void main(String[] args) {
       // Crear libro original que no se presta
       Libro libro1 = new Libro("Don Quijote - Original", true, false);


       // Crear libro fotocopia que sí se presta
       Libro libro2 = new Libro("Don Quijote - Fotocopia", false, true);


       // Mostrar información de ambos libros
       libro1.mostrarLibro();
       libro2.mostrarLibro();


       // También podrías usar individualmente los métodos de instancia, por ejemplo:
       System.out.println("¿Libro 1 es original? " + libro1.esOriginal());
       System.out.println("¿Libro 2 se presta? " + libro2.sePresta());
   }
}
