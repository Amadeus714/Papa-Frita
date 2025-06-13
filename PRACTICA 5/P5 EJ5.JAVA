// DVD.java
public class DVD {
   private String titulo;
   private String genero;


   public DVD(String titulo, String genero) {
       this.titulo = titulo;
       this.genero = genero;
   }


   public String getTitulo() {
       return titulo;
   }


   public String getGenero() {
       return genero;
   }
}


// GeneroNoEncontradoException.java
public class GeneroNoEncontradoException extends Exception {
   public GeneroNoEncontradoException(String mensaje) {
       super(mensaje);
   }
}


// Coleccion.java
import java.util.ArrayList;
import java.util.List;


public class Coleccion {
   private List<DVD> dvds = new ArrayList<>();


   public void agregarDVD(DVD dvd) {
       dvds.add(dvd);
   }


   public List<DVD> buscarPorGenero(String genero) throws GeneroNoEncontradoException {
       List<DVD> resultado = new ArrayList<>();
       for (DVD dvd : dvds) {
           if (dvd.getGenero().equalsIgnoreCase(genero)) {
               resultado.add(dvd);
           }
       }


       if (resultado.isEmpty()) {
           throw new GeneroNoEncontradoException("No se encontraron DVDs del género: " + genero);
       }


       return resultado;
   }
}


// Main.java
import java.util.List;


public class Main {
   public static void main(String[] args) {
       Coleccion coleccion = new Coleccion();


       coleccion.agregarDVD(new DVD("Matrix", "Accion"));
       coleccion.agregarDVD(new DVD("Titanic", "Romance"));
       coleccion.agregarDVD(new DVD("El Padrino", "Drama"));


       try {
           List<DVD> comedias = coleccion.buscarPorGenero("Comedia");
           for (DVD dvd : comedias) {
               System.out.println("Encontrado: " + dvd.getTitulo());
           }
       } catch (GeneroNoEncontradoException e) {
           System.out.println("Error: " + e.getMessage());
       }
   }
}
