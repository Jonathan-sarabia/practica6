/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.tda.lista;

import controlador.tda.lista.exception.PosicionException;
import controlador.utiles.TipoOrdenacion;
import controlador.utiles.Utilidades;
import static controlador.utiles.Utilidades.getMethod;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import modelo.Auto;

/**
 *
 * @author jona
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public class ListaEnlazadaServices<E> {

     private ListaEnlazada<E> lista;

     public ListaEnlazada<E> getLista() {
          if (this.lista == null) {
               this.lista = new ListaEnlazada<>();
          }

          return lista;
     }

     public void setLista(ListaEnlazada<E> lista) {
          this.lista = lista;
     }

     public ListaEnlazadaServices() {
          this.lista = new ListaEnlazada<>();
     }

     public Boolean insertarAlInicio(E dato) {
          lista.insertarCabecera(dato);
          return true;

     }

     public Boolean insertarAlFinal(E dato) {
          try {
               //lista.getSize() 1 
               lista.insertaroporpos(dato, lista.getSize() - 1);
               return true;
          } catch (PosicionException e) {
               System.out.println(e);
          }
          return false;
     }

     public Boolean insertar(E dato, Integer pos) {
          try {
               lista.insertaroporpos(dato, pos);
               return true;
          } catch (PosicionException e) {
               System.out.println(e);
          }
          return false;
     }

     public Integer getSize() {
          return lista.getSize();
     }

     public E obtenerDato(Integer pos) {
          try {
               return lista.obtenerDato(pos);
          } catch (Exception e) {
               System.out.println(e);
               return null;
          }
     }

     public Boolean eliminarCabecera() {
          try {
               lista.eliminarDato(0);
               return true;
          } catch (PosicionException e) {
               System.out.println(e);
          }
          return false;
     }

     public Boolean eliminarUltimo() {
          try {
               lista.eliminarDato(lista.getSize() - 1);
               return true;
          } catch (PosicionException e) {
               System.out.println(e);
          }
          return false;
     }

     public Boolean eliminarPosicion(Integer pos) {
          try {
               lista.eliminarDato(pos);
               return true;
          } catch (PosicionException e) {
               System.out.println(e);
          }
          return false;
     }

     public Boolean modificarDatoPosicion(Integer pos, E dato) throws Exception {
          try {
               //lista.modificarDato(pos, dato);
               modificarDatoPosicion(pos, dato);
               return true;
          } catch (PosicionException e) {
               System.out.println(e);
          }
          return false;
     }

     public void limpiarLista() {
          lista.vaciar();
     }

     public E[] toArray() {
          E[] matriz = (E[]) (new Object[this.lista.getSize()]);
          NodoLista<E> aux = lista.getCabecera();
          for (int i = 0; i < this.getSize(); i++) {
               matriz[i] = aux.getDato();
               //System.out.print(aux.getDato().toString() + "\t");
               aux = aux.getSiguiente();
          }
          return matriz;
     }

     private Field getField(String nombre) {
          for (Field field : lista.getClass().getDeclaredFields()) {
               if (field.getName().equalsIgnoreCase(nombre)) {
                    field.setAccessible(true);
                    return field;
               }
          }
          return null;
     }

     private Object Value(E dato, String atributo) throws Exception {
          return getField(atributo).get(dato);
     }

     public Object testReflect(E dato, String atributo) throws Exception {
          return this.getField(atributo).get(dato);
     }

     private Object[] evaluaCambiarDato(Class clazz, E auxJ, E auxJ1, Method method, Method method1, TipoOrdenacion tipoOrdenacion, Integer j, E[] matriz) throws Exception {
          Object aux[] = new Object[2];
          if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
               Number datoJ = (Number) method.invoke(auxJ);
               Number datoJ1 = (Number) method1.invoke(auxJ1);
               if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    if ((datoJ.doubleValue() > datoJ1.doubleValue())) {
                         // cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               } else {
                    if ((datoJ.doubleValue() < datoJ1.doubleValue())) {
                         //    cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               }
          } else if (Utilidades.isString(clazz)) {
               String datoJ = (String) method.invoke(auxJ);
               String datoJ1 = (String) method1.invoke(auxJ1);
               if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) > 0)) {
                         //   cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               } else {
                    if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) < 0)) {
                         //  cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               }

          } else if (Utilidades.isCharacter(clazz)) {
               Character datoJ = (Character) method.invoke(auxJ);
               Character datoJ1 = (Character) method1.invoke(auxJ1);
               if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    if (datoJ > datoJ1) {
                         // cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               } else {
                    if (datoJ < datoJ1) {
                         //  cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               }

          }
          return aux;
     }

     public ListaEnlazadaServices<E> QuisortClase(String atributo, int primero, int ultimo, TipoOrdenacion tipoOrdenacion) throws Exception {

          Integer i, j, central;
          Class<E> clazz = null;
          E[] matriz = null;
          i = primero;
          j = ultimo;
          central = (primero + ultimo) / 2;
          clazz = (Class<E>) lista.getCabecera().getDato().getClass();//primitivo, Dato envolvente, Object
          Object pivote = obtenerDato(central);
          pivote = (Class<E>) obtenerDato(central);
          Field field = Utilidades.getField(atributo, clazz);
          if (lista.getSize() > 0) {
               try {
                    matriz = toArray();
                    E t = null;
                    Boolean isObject = Utilidades.isObject(clazz);//si es objet
                    Integer n = matriz.length;
                    Integer cont = 0;
                    Method method = getMethod("get" + Utilidades.capitalizar(atributo), obtenerDato(j).getClass());
                    Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), obtenerDato(i).getClass());
                    if (field.getType().getSimpleName().toString().equalsIgnoreCase("Number")) {
                         do {

                              if (tipoOrdenacion.toString().equals(TipoOrdenacion.ASCENDENTE)) {
                                   while (((Number) Value(obtenerDato(i), atributo)).doubleValue() < ((Number) Value(obtenerDato(central), atributo)).doubleValue()) {
                                        i++;
                                   }
                                   while (((Number) Value(obtenerDato(j), atributo)).doubleValue() > ((Number) Value(obtenerDato(central), atributo)).doubleValue()) {
                                        j--;
                                   }
                              } else {
                                   while (((Number) Value(obtenerDato(i), atributo)).doubleValue() > ((Number) Value(obtenerDato(central), atributo)).doubleValue()) {
                                        i++;
                                   }
                                   while (((Number) Value(obtenerDato(j), atributo)).doubleValue() < ((Number) Value(obtenerDato(central), atributo)).doubleValue()) {
                                        j--;
                                   }
                              }

                              if (i <= j) {
                                   Object[] aux = evaluaCambiarDato(field.getType(), t, matriz[j], method, method1, tipoOrdenacion, j, matriz);
                                   modificarDatoPosicion(i, obtenerDato(j));
                                   modificarDatoPosicion(j, (E) aux);
                                   i++;
                                   j--;
                              }
                         } while (i <= j);

                    } else {
                         do {
//                         String aunise = (String)method.invoke();
                              System.out.println("pasa por aqui");
                              if (tipoOrdenacion.toString().equals(TipoOrdenacion.ASCENDENTE)) {

                                   while (Value(obtenerDato(central), atributo).toString().compareTo(Value(obtenerDato(i), atributo).toString()) > 0) {
                                        i++;
                                   }
                                   while (Value(obtenerDato(j), atributo).toString().compareTo(Value(obtenerDato(central), atributo).toString()) > 0) {
                                        j--;
                                   }
                              } else {
                                   while (Value(obtenerDato(central), atributo).toString().compareTo(Value(obtenerDato(i), atributo).toString()) < 0) {
                                        i++;
                                   }
                                   while (Value(obtenerDato(j), atributo).toString().compareTo(Value(obtenerDato(central), atributo).toString()) < 0) {
                                        j--;
                                   }
                              }
                              if (i <= j) {
                                   Object[] aux = evaluaCambiarDato(field.getType(), t, matriz[j], method, method1, tipoOrdenacion, j, matriz);
                                   modificarDatoPosicion(i, obtenerDato(j));
                                   modificarDatoPosicion(j, (E) aux);
                                   i++;
                                   j--;
                              }
                         } while (i <= j);

                    }
                    if (primero < j) {
                         QuisortClase(atributo, primero, j, tipoOrdenacion);
                    }
                    if (i < ultimo) {
                         QuisortClase(atributo, i, ultimo, tipoOrdenacion);
                    }

               } catch (Exception e) {
                    e.printStackTrace();
               }

          }

          return this;
     }

     public ListaEnlazadaServices<E> ShellClase(TipoOrdenacion tipoOrdenacion, String atributo) {
          try {
               int intervalo, i, j, k;
               int n = lista.getSize();
               Class<E> clazz = (Class<E>) lista.getCabecera().getDato().getClass();
               intervalo = n / 2;
               while (intervalo > 0) {
                    for (i = intervalo; i < n; i++) {
                         j = i - intervalo;
                         if (clazz.getClass().getSimpleName().toString().equalsIgnoreCase("Number")) {
                              while (j >= 0) {
                                   k = j + intervalo;
                                   if (tipoOrdenacion.toString().equals(TipoOrdenacion.ASCENDENTE)) {
                                        if (((Number) testReflect(lista.obtenerDato(j), atributo)).intValue() <= ((Number) testReflect(obtenerDato(k), atributo)).intValue()) {
                                             j = -1;
                                        } else {
                                             E aux = obtenerDato(j);
                                             modificarDatoPosicion(j, lista.obtenerDato(j + 1));
                                             modificarDatoPosicion(j + 1, aux);
                                             j -= intervalo;
                                        }
                                   } else {
                                        if (((Number) testReflect(lista.obtenerDato(j), atributo)).intValue() >= ((Number) testReflect(obtenerDato(k), atributo)).intValue()) {
                                             j = -1;
                                        } else {
                                             E aux = obtenerDato(j);
                                             modificarDatoPosicion(j, lista.obtenerDato(j + 1));
                                             modificarDatoPosicion(j + 1, aux);
                                             j -= intervalo;
                                        }
                                   }
                              }
                         } else {
                              while (j >= 0) {
                                   k = j + intervalo;
                                   if (tipoOrdenacion.toString().equals(TipoOrdenacion.ASCENDENTE)) {
                                        if (testReflect(lista.obtenerDato(k), atributo).toString().compareTo(testReflect(obtenerDato(j), atributo).toString()) >= 0) {
                                             j = -1;
                                        } else {
                                             E aux = obtenerDato(j);
                                             modificarDatoPosicion(j, lista.obtenerDato(j + 1));
                                             modificarDatoPosicion(j + 1, aux);
                                             j -= intervalo;
                                        }
                                   } else {
                                        if (testReflect(obtenerDato(j), atributo).toString().compareTo(testReflect(obtenerDato(k), atributo).toString()) >= 0) {
                                             j = -1;
                                        } else {
                                             E aux = obtenerDato(j);
                                             modificarDatoPosicion(j, lista.obtenerDato(j + 1));
                                             modificarDatoPosicion(j + 1, aux);
                                             j -= intervalo;
                                        }
                                   }
                              }
                         }
                    }
                    intervalo = intervalo / 2;
               }
          } catch (Exception e) {
               System.out.println("Error :C en Shell:  " + e);
               e.printStackTrace();
          }
          return this;
     }

}
