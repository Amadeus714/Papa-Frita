package P2_1;


import java.util.Scanner;


public class Main {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       CatalogoDVD catalogo = new CatalogoDVD();


       while (true) {
           try {
               System.out.println("\n--- Menú de opciones ---");
               System.out.println("1. Agregar DVD/BluRay/LaserDisc");
               System.out.println("2. Eliminar DVD/BluRay/LaserDisc");
               System.out.println("3. Modificar DVD/BluRay/LaserDisc");
               System.out.println("4. Listar todos los DVDs/BluRays/LaserDisc");
               System.out.println("5. Listar DVDs/BluRays/LaserDisc que tengo");
               System.out.println("6. Listar DVDs/BluRays/LaserDisc por duración");
               System.out.println("7. Listar DVDs/BluRays/LaserDisc ordenados por título");
               System.out.println("8. Salir");
               System.out.print("Seleccione una opción: ");


               int opcion = Integer.parseInt(scanner.nextLine());


               switch (opcion) {
                   case 1:
                       catalogo.crearDVDConValidaciones(scanner);
                       break;
                   case 2:
                       System.out.print("Ingrese el título del DVD a eliminar: ");
                       String tituloEliminar = scanner.nextLine();
                       if (catalogo.eliminarDVD(tituloEliminar)) {
                           System.out.println("DVD eliminado exitosamente.");
                       } else {
                           System.out.println("No se encontró el DVD con ese título.");
                       }
                       break;
                   case 3:
                       System.out.print("Ingrese el título del DVD a modificar: ");
                       String tituloModificar = scanner.nextLine();
                       if (catalogo.modificarDVDConValidacion(tituloModificar, scanner)) {
                           System.out.println("DVD modificado exitosamente.");
                       } else {
                           System.out.println("No se encontró el DVD con ese título.");
                       }
                       break;
                   case 4:
                       catalogo.listarTodos();
                       break;
                   case 5:
                       catalogo.listarTengo();
                       break;
                   case 6:
                       System.out.print("Ingrese la duración máxima (en minutos): ");
                       int maxDuracion = Integer.parseInt(scanner.nextLine());
                       catalogo.listarPorDuracion(maxDuracion);
                       break;
                   case 7:
                       catalogo.listarOrdenadoPorTitulo();
                       break;
                   case 8:
                       System.out.println("¡Hasta luego!");
                       scanner.close();
                       return;
                   default:
                       System.out.println("Opción no válida.");
               }
           } catch (Exception e) {
               System.out.println("⚠️ Error: " + e.getMessage());
           }
       }
   }
}

abstract class Disco {
   private String titulo;
   private String genero;
   private int duracion; // en minutos
   private boolean loTengo;
   private String comentario;


   public Disco(String titulo, String genero, int duracion, boolean loTengo, String comentario) {
       this.titulo = titulo;
       this.genero = genero;
       this.duracion = duracion;
       this.loTengo = loTengo;
       this.comentario = comentario;
   }


   public String getTitulo() {
       return titulo;
   }


   public void setTitulo(String titulo) {
       this.titulo = titulo;
   }


   public String getGenero() {
       return genero;
   }


   public void setGenero(String genero) {
       this.genero = genero;
   }


   public int getDuracion() {
       return duracion;
   }


   public void setDuracion(int duracion) {
       this.duracion = duracion;
   }


   public boolean loTengo() {
       return loTengo;
   }


   public void setLoTengo(boolean loTengo) {
       this.loTengo = loTengo;
   }


   public String getComentario() {
       return comentario;
   }


   public void setComentario(String comentario) {
       if (comentario.length() > 100) {
           this.comentario = comentario.substring(0, 100);
       } else {
           this.comentario = comentario;
       }
   }


   @Override
   public String toString() {
       return String.format("Título: %s | Género: %s | Duración: %d min | Lo tengo: %s | Comentario: %s",
               titulo, genero, duracion, loTengo ? "Sí" : "No", comentario);
   }
}

import java.util.*;


public class CatalogoDVD {
   private final List<Disco> dvds;


   public CatalogoDVD() {
       dvds = new ArrayList<>();
   }


   public void crearDVDConValidaciones(Scanner scanner) {
       try {
           System.out.print("Tipo (D: DVD, B: BluRay, L: LaserDisc): ");
           String tipo = scanner.nextLine().trim().toUpperCase();


           if (!tipo.equals("D") && !tipo.equals("B") && !tipo.equals("L")) {
               throw new IllegalArgumentException("Tipo inválido. Debe ser D, B o L.");
           }


           System.out.print("Título: ");
           String titulo = scanner.nextLine().trim();
           if (buscarPorTitulo(titulo) != null) {
               System.out.println("⚠️ Ya existe un DVD con ese título.");
               return;
           }


           System.out.print("Género (S)uspenso, (C)omedia o (T)error: ");
           String genero = scanner.nextLine().trim().toUpperCase();
           if (!genero.equals("S") && !genero.equals("C") && !genero.equals("T")) {
               throw new IllegalArgumentException("Género inválido. Debe ser S, C o T.");
           }


           System.out.print("Duración (1-600): ");
           int duracion = Integer.parseInt(scanner.nextLine());


           if (duracion < 1 || duracion > 600) {
               throw new IllegalArgumentException("La duración debe estar entre 1 y 600 minutos.");
           }


           System.out.print("¿Lo tienes? (s/n): ");
           String tengo = scanner.nextLine().trim().toLowerCase();
           boolean loTengo = tengo.equals("s");


           System.out.print("Comentario (máx 100 caracteres): ");
           String comentario = scanner.nextLine();
           if (comentario.length() > 100) {
               throw new IllegalArgumentException("El comentario no puede exceder los 100 caracteres.");
           }


           // Crear el DVD o tipo correspondiente
           if (tipo.equals("B")) {
               System.out.print("Calidad (HD, Full HD, 4K): ");
               String calidad = scanner.nextLine().trim();


               System.out.print("Capas (1-4): ");
               int capas = Integer.parseInt(scanner.nextLine());


               System.out.print("Calidad de sonido (Estéreo, Dolby, DTS): ");
               String sonido = scanner.nextLine();


               agregarDVD(new Bluray(titulo, genero, duracion, loTengo, comentario, calidad, capas, sonido));
           } else if (tipo.equals("L")) {
               System.out.print("Tamaño (pulgadas, usual 12): ");
               double tamano = Double.parseDouble(scanner.nextLine());


               System.out.print("Formato de video (NTSC/PAL/SECAM): ");
               String formatoVideo = scanner.nextLine().trim();


               System.out.print("¿Es edición especial? (s/n): ");
               boolean especial = scanner.nextLine().trim().equalsIgnoreCase("s");


               agregarDVD(new LaserDisc(titulo, genero, duracion, loTengo, comentario, tamano, formatoVideo, especial));
           } else {
               agregarDVD(new DVD(titulo, genero, duracion, loTengo, comentario));
           }


       } catch (NumberFormatException e) {
           System.out.println("⚠️ Error: Entrada inválida para un número. Por favor, ingrese un valor válido.");
       } catch (Exception e) {
           System.out.println("⚠️ Error: " + e.getMessage());
       }
   }


   public Disco buscarPorTitulo(String titulo) {
       return dvds.stream()
               .filter(d -> d.getTitulo().equalsIgnoreCase(titulo))
               .findFirst()
               .orElse(null);
   }


   public void agregarDVD(Disco disco) {
       if (buscarPorTitulo(disco.getTitulo()) != null) {
           throw new IllegalArgumentException("Ya existe un disco con ese título.");
       }
       dvds.add(disco);
   }


   public boolean eliminarDVD(String titulo) {
       return dvds.removeIf(d -> d.getTitulo().equalsIgnoreCase(titulo));
   }


   public boolean modificarDVDConValidacion(String titulo, Scanner scanner) {
       Disco d = buscarPorTitulo(titulo);
       if (d == null) return false;


       try {
           System.out.print("Nuevo género (S)uspenso, (C)omedia o (T)error: ");
           d.setGenero(scanner.nextLine());


           System.out.print("Nueva duración (1-600): ");
           d.setDuracion(Integer.parseInt(scanner.nextLine()));


           System.out.print("¿Lo tienes? (s/n): ");
           String loTengo = scanner.nextLine().trim().toLowerCase();
           d.setLoTengo(loTengo.equals("s"));


           System.out.print("Nuevo comentario (máx 100 caracteres): ");
           d.setComentario(scanner.nextLine());


           if (d instanceof Bluray bluray) {
               System.out.print("Nueva calidad (HD, Full HD, 4K): ");
               bluray.setCalidad(scanner.nextLine());


               System.out.print("Capas (1-4): ");
               bluray.setCapas(Integer.parseInt(scanner.nextLine()));


               System.out.print("Calidad de sonido (Estéreo, Dolby, DTS): ");
               bluray.setCalidadSonido(scanner.nextLine());
           } else if (d instanceof LaserDisc ld) {
               System.out.print("Nuevo tamaño (en pulgadas): ");
               ld.setTamano(Double.parseDouble(scanner.nextLine()));


               System.out.print("Nuevo formato de video (NTSC/PAL/SECAM): ");
               ld.setFormatoVideo(scanner.nextLine());


               System.out.print("¿Es edición especial? (s/n): ");
               ld.setEsEdicionEspecial(scanner.nextLine().trim().equalsIgnoreCase("s"));
           }


           return true;
       } catch (NumberFormatException e) {
           System.out.println("⚠️ Error: Entrada inválida para un número.");
           return false;
       } catch (Exception e) {
           System.out.println("⚠️ Error: " + e.getMessage());
           return false;
       }
   }


   public void listarTodos() {
       dvds.forEach(System.out::println);
   }


   public void listarTengo() {
       dvds.stream().filter(Disco::loTengo).forEach(System.out::println);
   }


   public void listarPorDuracion(int maxDuracion) {
       dvds.stream().filter(d -> d.getDuracion() < maxDuracion).forEach(System.out::println);
   }


   public void listarOrdenadoPorTitulo() {
       dvds.stream()
               .sorted(Comparator.comparing(Disco::getTitulo))  // Usar Disco::getTitulo en lugar de DVD::getTitulo
               .forEach(System.out::println);
   }


   public int cantidadTotal() {
       return dvds.size();
   }


   public long cantidadTengo() {
       return dvds.stream().filter(Disco::loTengo).count();
   }
}

public class LaserDisc extends Disco {
   private double tamano;
   private String formatoVideo;
   private boolean esEdicionEspecial;


   public LaserDisc(String titulo, String genero, int duracion, boolean loTengo, String comentario,
                    double tamano, String formatoVideo, boolean esEdicionEspecial) {
       super(titulo, genero, duracion, loTengo, comentario);
       setTamano(tamano);
       setFormatoVideo(formatoVideo);
       this.esEdicionEspecial = esEdicionEspecial;
   }


   public double getTamano() { return tamano; }
   public String getFormatoVideo() { return formatoVideo; }
   public boolean isEsEdicionEspecial() { return esEdicionEspecial; }


   public void setTamano(double tamano) {
       if (tamano <= 0)
           throw new IllegalArgumentException("El tamaño debe ser mayor que 0.");
       this.tamano = tamano;
   }


   public void setFormatoVideo(String formatoVideo) {
       if (!formatoVideo.equalsIgnoreCase("NTSC") &&
               !formatoVideo.equalsIgnoreCase("PAL") &&
               !formatoVideo.equalsIgnoreCase("SECAM"))
           throw new IllegalArgumentException("Formato de video no válido (NTSC, PAL, SECAM).");
       this.formatoVideo = formatoVideo;
   }


   public void setEsEdicionEspecial(boolean esEdicionEspecial) {
       this.esEdicionEspecial = esEdicionEspecial;
   }


   @Override
   public String toString() {
       return "[LaserDisc] " + super.toString() +
               " | Tamaño: " + tamano + " pulgadas" +
               " | Formato video: " + formatoVideo +
               " | Ed. Especial: " + (esEdicionEspecial ? "Sí" : "No");
   }
}

public class DVD extends Disco {
   public DVD(String titulo, String genero, int duracion, boolean loTengo, String comentario) {
       super(titulo, genero, duracion, loTengo, comentario);
   }


   @Override
   public String toString() {
       return "[DVD] " + super.toString();
   }
}

public class Bluray extends Disco {
   private String calidad;
   private int capas;
   private String calidadSonido;


   public Bluray(String titulo, String genero, int duracion, boolean loTengo, String comentario,
                 String calidad, int capas, String calidadSonido) {
       super(titulo, genero, duracion, loTengo, comentario);
       setCalidad(calidad);
       setCapas(capas);
       setCalidadSonido(calidadSonido);
   }


   public String getCalidad() { return calidad; }
   public int getCapas() { return capas; }
   public String getCalidadSonido() { return calidadSonido; }


   public void setCalidad(String calidad) {
       if (!calidad.equalsIgnoreCase("HD") &&
               !calidad.equalsIgnoreCase("Full HD") &&
               !calidad.equalsIgnoreCase("4K"))
           throw new IllegalArgumentException("Calidad inválida. Debe ser HD, Full HD o 4K.");
       this.calidad = calidad;
   }


   public void setCapas(int capas) {
       if (capas < 1 || capas > 4)
           throw new IllegalArgumentException("Capas debe estar entre 1 y 4.");
       this.capas = capas;
   }


   public void setCalidadSonido(String calidadSonido) {
       if (!calidadSonido.equalsIgnoreCase("Estéreo") &&
               !calidadSonido.equalsIgnoreCase("Dolby") &&
               !calidadSonido.equalsIgnoreCase("DTS"))
           throw new IllegalArgumentException("Calidad de sonido inválida. Use Estéreo, Dolby o DTS.");
       this.calidadSonido = calidadSonido;
   }


   @Override
   public String toString() {
       return "[BluRay] " + super.toString() + ", Calidad: " + calidad + ", Capas: " + capas +
               ", Sonido: " + calidadSonido;
   }
}



