package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Agenda {
  private Map<String, Contacto> contactos = new TreeMap<>();
  private Set<String> grupos = new TreeSet<>();
  private Map<String, Set<String>> gruposContactos = new TreeMap<>();
  private List<Contacto> historial = new ArrayList<>();

  public void agregarContacto(Contacto contacto) {
    if (contactos.containsKey(contacto.getNombre())) {
      System.out.println("Error: Ya existe un contacto con el nombre '" + contacto.getNombre() + "'");
      return;
    }
    contactos.put(contacto.getNombre(), contacto);
    historial.add(contacto);
    System.out.println("Contacto agregado: " + contacto.getNombre());
  }

  public Contacto buscarContacto(String nombre) {
    return contactos.get(nombre);
  }

  public void eliminarContacto(String nombre) {
    Contacto contacto = contactos.get(nombre);
    if (contacto == null) {
      System.out.println("Contacto no encontrado: " + nombre);
      return;
    }

    contactos.remove(nombre);
    historial.removeIf(c -> c.getNombre().equals(nombre));
    for (Set<String> miembros : gruposContactos.values()) {
      miembros.remove(nombre);
    }
    System.out.println("Contacto eliminado: " + nombre);
  }

  public void listarContactos() {
    if (contactos.isEmpty()) {
      System.out.println("No hay contactos registrados.");
      return;
    }
    for (Contacto c : contactos.values()) {
      System.out.println(c);
    }
  }

  public void listarHistorial() {
    if (historial.isEmpty()) {
      System.out.println("El historial esta vacio.");
      return;
    }
    for (Contacto c : historial) {
      System.out.println(c);
    }
  }

  public void agregarGrupo(String nombreGrupo) {
    if (grupos.contains(nombreGrupo)) {
      System.out.println("Error: El grupo '" + nombreGrupo + "' ya existe");
      return;
    }
    grupos.add(nombreGrupo);
    gruposContactos.put(nombreGrupo, new TreeSet<>());
    System.out.println("Grupo '" + nombreGrupo + "' creado");
  }

  public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
    if (!contactos.containsKey(nombreContacto)) {
      System.out.println("Error: El contacto '" + nombreContacto + "' no existe");
      return;
    }
    if (!grupos.contains(nombreGrupo)) {
      System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
      return;
    }
    gruposContactos.get(nombreGrupo).add(nombreContacto);
    System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'");
  }

  public void listarGrupos() {
    if (grupos.isEmpty()) {
      System.out.println("No hay grupos registrados.");
      return;
    }
    for (String grupo : grupos) {
      System.out.println(grupo);
    }
  }

  public void mostrarContactosPorGrupo(String nombreGrupo) {
    if (!grupos.contains(nombreGrupo)) {
      System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
      return;
    }
    Set<String> miembros = gruposContactos.get(nombreGrupo);
    if (miembros.isEmpty()) {
      System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos");
      return;
    }
    for (String nombre : miembros) {
      Contacto c = contactos.get(nombre);
      if (c != null) {
        System.out.println(c);
      }
    }
  }
}
