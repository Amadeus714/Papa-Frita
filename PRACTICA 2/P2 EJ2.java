package Practica2;


import java.util.*;
import java.time.LocalDate;


// Enumeraciones
enum TipoNovela {
   CIENCIA_FICCION, ROMANCE, MISTERIO, JUVENIL, POLICIAL
}


enum TipoTecnico {
   INGENIERIA, CIENCIAS_NATURALES, CIENCIAS_SOCIALES
}


enum Formato {
   TAPAS_DURAS, EDICION_ECONOMICA
}


// Clase Editorial
class Editorial {
   private String nombre;


   public Editorial(String nombre) {
       this.nombre = nombre;
   }


   public String getNombre() {
       return nombre;
   }
}


// Clase Proveedor
class Proveedor {
   private String nombre;
   private List<Editorial> editoriales;


   public Proveedor(String nombre) {
       this.nombre = nombre;
       this.editoriales = new ArrayList<>();
   }


   public void agregarEditorial(Editorial editorial) {
       editoriales.add(editorial);
   }


   public boolean representaEditorial(Editorial editorial) {
       return editoriales.contains(editorial);
   }


   public String getNombre() {
       return nombre;
   }
}


// Clase abstracta Libro
abstract class Libro {
   protected String titulo;
   protected List<String> autores;
   protected Editorial editorial;
   protected int añoEdicion;
   protected Formato formato;
   protected String isbn;
   protected int stock;


   public Libro(String titulo, List<String> autores, Editorial editorial, int añoEdicion, Formato formato, String isbn, int stock) {
       this.titulo = titulo;
       this.autores = autores;
       this.editorial = editorial;
       this.añoEdicion = añoEdicion;
       this.formato = formato;
       this.isbn = isbn;
       this.stock = stock;
   }


   public void vender() {
       if (stock > 0) {
           stock--;
       } else {
           throw new IllegalStateException("No hay stock disponible");
       }
   }


   public int verificarStock() {
       return stock;
   }


   public String getIsbn() {
       return isbn;
   }


   public Editorial getEditorial() {
       return editorial;
   }


   public String getTitulo() {
       return titulo;
   }


   public String toString() {
       return titulo + " (" + isbn + ") - Stock: " + stock;
   }
}


// Clase Novela
class Novela extends Libro {
   private TipoNovela tipo;


   public Novela(String titulo, List<String> autores, Editorial editorial, int añoEdicion, Formato formato, String isbn, int stock, TipoNovela tipo) {
       super(titulo, autores, editorial, añoEdicion, formato, isbn, stock);
       this.tipo = tipo;
   }
}


// Clase LibroTecnico
class LibroTecnico extends Libro {
   private TipoTecnico tipo;


   public LibroTecnico(String titulo, List<String> autores, Editorial editorial, int añoEdicion, Formato formato, String isbn, int stock, TipoTecnico tipo) {
       super(titulo, autores, editorial, añoEdicion, formato, isbn, stock);
       this.tipo = tipo;
   }
}


// Clase Cliente
class Cliente {
   private String nombre;
   private List<Encargo> encargos = new ArrayList<>();


   public Cliente(String nombre) {
       this.nombre = nombre;
   }


   public void agregarEncargo(Encargo encargo) {
       encargos.add(encargo);
   }


   public String getNombre() {
       return nombre;
   }


   public List<Encargo> getEncargos() {
       return encargos;
   }
}


// Clase Encargo
class Encargo {
   private Libro libro;
   private Cliente cliente;
   private LocalDate fecha;


   public Encargo(Libro libro, Cliente cliente) {
       this.libro = libro;
       this.cliente = cliente;
       this.fecha = LocalDate.now();
   }


   public Libro getLibro() {
       return libro;
   }


   public Cliente getCliente() {
       return cliente;
   }


   public String toString() {
       return "Encargo de '" + libro.getTitulo() + "' para " + cliente.getNombre() + " el " + fecha;
   }
}


// Clase Libreria
class Libreria {
   private List<Libro> libros = new ArrayList<>();
   private List<Proveedor> proveedores = new ArrayList<>();


   public void agregarLibro(Libro libro) {
       libros.add(libro);
   }


   public void agregarProveedor(Proveedor proveedor) {
       proveedores.add(proveedor);
   }


   public Libro buscarLibroPorTitulo(String titulo) {
       for (Libro libro : libros) {
           if (libro.getTitulo().equalsIgnoreCase(titulo)) {
               return libro;
           }
       }
       return null;
   }


   public Libro buscarLibroPorIsbn(String isbn) {
       for (Libro libro : libros) {
           if (libro.getIsbn().equals(isbn)) {
               return libro;
           }
       }
       return null;
   }


   public void venderLibro(String isbn) {
       Libro libro = buscarLibroPorIsbn(isbn);
       if (libro != null) {
           try {
               libro.vender();
               System.out.println("Libro vendido correctamente: " + libro.getTitulo());
           } catch (IllegalStateException e) {
               System.out.println(e.getMessage());
           }
       } else {
           System.out.println("Libro no encontrado.");
       }
   }


   public void encargarLibro(String isbn, Cliente cliente) {
       Libro libro = buscarLibroPorIsbn(isbn);
       if (libro == null) {
           System.out.println("Libro no encontrado.");
           return;
       }
       if (libro.verificarStock() > 0) {
           System.out.println("Hay stock disponible, no se requiere encargo.");
           return;
       }
       for (Proveedor proveedor : proveedores) {
           if (proveedor.representaEditorial(libro.getEditorial())) {
               Encargo encargo = new Encargo(libro, cliente);
               cliente.agregarEncargo(encargo);
               System.out.println("Encargo realizado al proveedor: " + proveedor.getNombre());
               return;
           }
       }
       System.out.println("No se encontró proveedor para la editorial del libro.");
   }
}


// Clase principal de ejemplo
public class Main {
   public static void main(String[] args) {
       Editorial ed1 = new Editorial("Planeta");
       Editorial ed2 = new Editorial("Siglo XXI");


       Proveedor proveedor1 = new Proveedor("Distribuidora Sur");
       proveedor1.agregarEditorial(ed1);
       Proveedor proveedor2 = new Proveedor("Distribuidora Norte");
       proveedor2.agregarEditorial(ed2);


       Libreria libreria = new Libreria();
       libreria.agregarProveedor(proveedor1);
       libreria.agregarProveedor(proveedor2);


       Libro novela1 = new Novela("Viaje a Marte", Arrays.asList("J. Pérez"), ed1, 2021, Formato.TAPAS_DURAS, "ISBN123", 2, TipoNovela.CIENCIA_FICCION);
       Libro tecnico1 = new LibroTecnico("Introducción a la Ingeniería", Arrays.asList("M. Gómez"), ed2, 2019, Formato.EDICION_ECONOMICA, "ISBN456", 0, TipoTecnico.INGENIERIA);


       libreria.agregarLibro(novela1);
       libreria.agregarLibro(tecnico1);


       Cliente cliente = new Cliente("Ana López");


       libreria.venderLibro("ISBN123"); // Vender un libro con stock
       libreria.venderLibro("ISBN123"); // Vender el último en stock
       libreria.venderLibro("ISBN123"); // Ya no hay stock


       libreria.encargarLibro("ISBN456", cliente); // Encargar libro sin stock
       libreria.encargarLibro("ISBN123", cliente); // No requiere encargo


       System.out.println("\nEncargos realizados por " + cliente.getNombre() + ":");
       for (Encargo e : cliente.getEncargos()) {
           System.out.println(e);
       }
   }
}
