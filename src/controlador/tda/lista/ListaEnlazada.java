/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.tda.lista;

import Controlador.Autos.Services.AutoServicio;
import controlador.tda.lista.exception.PosicionException;
import controlador.utiles.TipoOrdenacion;
import controlador.utiles.Utilidades;
import static controlador.utiles.Utilidades.getMethod;
import controladorAuto.AutoController;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import modelo.Auto;

/**
 *
 * @author jona
 */
public class ListaEnlazada<E> {

     private NodoLista<E> cabecera;
     private Integer size;

     public NodoLista<E> getCabecera() {
          return cabecera;
     }

     public void setCabecera(NodoLista<E> cabecera) {
          this.cabecera = cabecera;
     }

     /**
      * Constructor de la clase se inicializa la lista en null y el tamanio en 0
      */
     public ListaEnlazada() {
          cabecera = null;
          size = 0;
     }

     /**
      * Permite ver si la lista esta vacia
      *
      * @return Boolean true si esta vacia, false si esta llena
      */
     public Boolean estaVacia() {
          return cabecera == null;
     }

     private void insertar(E dato) {
          NodoLista<E> nuevo = new NodoLista<>(dato, null);
          if (estaVacia()) {
               cabecera = nuevo;
          } else {
               NodoLista<E> aux = cabecera;
               while (aux.getSiguiente() != null) {
                    aux = aux.getSiguiente();
               }
               aux.setSiguiente(nuevo);
          }
          size++;
     }

     public void insertarCabecera(E dato) {
          if (estaVacia()) {
               insertar(dato);
          } else {
               NodoLista<E> nuevo = new NodoLista<>(dato, null);

               nuevo.setSiguiente(cabecera);
               cabecera = nuevo;
               size++;
          }
     }

     public void insertaroporpos(E dato, Integer pos) throws PosicionException {
          //lista size = 1
          if (estaVacia()) {
               insertar(dato);
          } else if (pos >= 0 && pos < size) {
               NodoLista<E> nuevo = new NodoLista<>(dato, null);
               if (pos == (size - 1)) {
                    insertar(dato);

               } else {

                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos - 1; i++) {
                         aux = aux.getSiguiente();
                    }
                    NodoLista<E> siguiente = aux.getSiguiente();
                    aux.setSiguiente(nuevo);
                    nuevo.setSiguiente(siguiente);
                    size++;
               }

          } else {
               throw new PosicionException("Error en insertar: No existe la posicion dada");
          }
     }

     public void imprimir() {
          System.out.println("**************************");
          NodoLista<E> aux = cabecera;
          for (int i = 0; i < getSize(); i++) {
               System.out.print(aux.getDato().toString() + "\t");
               aux = aux.getSiguiente();
          }
          System.out.println("\n" + "**************************");
     }

     public Integer getSize() {
          return size;
     }

     /**
      * Metodo que permite obtener un dato segun la posicion
      *
      * @param pos posicion en la lista
      * @return Elemento
      */
     public E obtenerDato(Integer pos) throws PosicionException {
          if (!estaVacia()) {
               if (pos >= 0 && pos < size) {
                    E dato = null;
                    if (pos == 0) {
                         dato = cabecera.getDato();
                    } else {
                         NodoLista<E> aux = cabecera;
                         for (int i = 0; i < pos; i++) {
                              aux = aux.getSiguiente();
                         }
                         dato = aux.getDato();
                    }

                    return dato;
               } else {
                    throw new PosicionException("Error en obtener dato: No existe la posicion dada");
               }

          } else {
               throw new PosicionException("Error en obtener dato: La lista esta vacia, por endde no hay esa posicion");
          }
     }

     public E eliminarDato(Integer pos) throws PosicionException {
          E auxDato = null;
          if (!estaVacia()) {
               if (pos >= 0 && pos < size) {
                    if (pos == 0) {
                         auxDato = cabecera.getDato();
                         cabecera = cabecera.getSiguiente();
                         size--;
                    } else {
                         NodoLista<E> aux = cabecera;
                         for (int i = 0; i < pos - 1; i++) {
                              aux = aux.getSiguiente();
                         }
                         auxDato = aux.getDato();
                         NodoLista<E> proximo = aux.getSiguiente();
                         aux.setSiguiente(proximo.getSiguiente());
                         size--;
                    }
                    return auxDato;

               } else {
                    throw new PosicionException("Error en eliminar dato: No existe la posicion dada");
               }

          } else {
               throw new PosicionException("Error en eliminar dato: La lista esta vacia, por endde no hay esa posicion");
          }
     }

     public void vaciar() {
          cabecera = null;
          size = 0;
     }

     public void modificarDato(Integer pos, E datoM) throws PosicionException {
          if (!estaVacia()) {
               if (pos >= 0 && pos < size) {
                    if (pos == 0) {
                         cabecera.setDato(datoM);
                    } else {
                         NodoLista<E> aux = cabecera;
                         for (int i = 0; i < pos; i++) {
                              aux = aux.getSiguiente();
                         }
                         aux.setDato(datoM);
                    }

               } else {
                    throw new PosicionException("Error en obtener dato: No existe la posicion dada");
               }

          } else {
               throw new PosicionException("Error en obtener dato: La lista esta vacia, por endde no hay esa posicion");
          }
     }

     public ListaEnlazada<E> ordenar_seleccion(String atributo, TipoOrdenacion tipoOrdenacion) throws Exception {
          Class<E> clazz = null;
          E[] matriz = null;
          if (size > 0) {
               matriz = toArray();
               E t = null;
               clazz = (Class<E>) cabecera.getDato().getClass();//primitivo, Dato envolvente, Object
               Boolean isObject = Utilidades.isObject(clazz);//si es objeto
               Integer i, j, k = 0;
               Integer n = matriz.length;
               Integer cont = 0;
               for (i = 0; i < n - 1; i++) {
                    k = i;
                    t = matriz[i];
                    for (j = i + 1; j < n; j++) {
                         if (isObject) {
                              Field field = Utilidades.getField(atributo, clazz);
                              Method method = getMethod("get" + Utilidades.capitalizar(atributo), t.getClass());
                              Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), matriz[j].getClass());
                              Object[] aux = evaluaCambiarDato(field.getType(), t, matriz[j], method, method1, tipoOrdenacion, j);
                              if (aux[0] != null) {
                                   t = (E) aux[0];
                                   k = (Integer) aux[1];
                              }
                         } else {
                              Object[] aux = evaluaCambiarDatoNoObjeto(clazz, t, matriz[j], tipoOrdenacion, j);
                              if (aux[0] != null) {
                                   t = (E) aux[0];
                                   k = (Integer) aux[1];
                              }
                         }
                    }
                    matriz[k] = matriz[i];//intercambias cuando encountra el valor
                    matriz[i] = t;
               }
          }
          if (matriz != null) {
               toList(matriz);
          }
          return this;
     }

     public ListaEnlazada<E> burbuja(String atributo, TipoOrdenacion tipoOrdenacion) throws PosicionException, Exception {
          Class<E> clazz = null;//sirve para ver el tipo de dato
          E[] matriz = null;
          if (size > 0) {
               clazz = (Class<E>) cabecera.getDato().getClass();//primitivo, Dato envolvente, Object
               Boolean isObject = Utilidades.isObject(clazz);//si es objeto
               System.out.println("TRANFORMANDO A MATRIZ");
               matriz = toArray();
               if (isObject) {
                    for (int i = matriz.length; i > 1; i--) {
                         for (int j = 0; j < i - 1; j++) {
                              //E auxJ = this.obtenerDato(j);
                              //E auxJ1 = this.obtenerDato(j + 1);//getApellido
                              E auxJ = matriz[j];
                              E auxJ1 = matriz[j + 1];//getApellido
                              Field field = Utilidades.getField(atributo, clazz);
                              Method method = getMethod("get" + Utilidades.capitalizar(atributo), auxJ.getClass());
                              Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), auxJ1.getClass());
                              //llamar a utilidades
                              //if (field.getType().getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
                              evaluaCambiarDato(field.getType(), auxJ, auxJ1, method, method1, tipoOrdenacion, j);

                         }
                    }
               } else {
                    System.out.println("METODO BURBUJA");
                    for (int i = matriz.length; i > 1; i--) {
                         for (int j = 0; j < i - 1; j++) {
                              //E auxJ = this.obtenerDato(j);
                              //E auxJ1 = this.obtenerDato(j + 1);//getApellido
                              E auxJ = matriz[j];
                              E auxJ1 = matriz[j + 1];
                              evaluaCambiarDatoNoObjeto(clazz, auxJ, auxJ1, tipoOrdenacion, j);
                         }
                    }
               }

          }
          System.out.println("TRANFORMANDO A LISTA");
          if (matriz != null) {
               toList(matriz);
          }
          return this;
     }

     /**
      * Sirve para realizar el intercambio del metodo burbuja
      *
      * @param j Posicion
      * @throws Exception Excepciones de tipo PosicionExcepcion
      */
     /* private void cambioBurbuja(Integer j, E[] matriz) throws Exception {
     E aux = matriz[j];
     matriz[j] = matriz[j + 1];
     matriz[j + 1] = aux;
     //E aux = this.obtenerDato(j);
     //this.modificarDato(j, this.obtenerDato(j + 1));
     //this.modificarDato(j + 1, aux);
     //  System.out.println("Cambio");
     }*/
     /**
      * Permite hacer el cambio con datos que no son objetos
      *
      * @param clazz El tipo de clase q permite identificar q tipo de dato es
      * @param auxJ Dato en la posicion J
      * @param auxJ1 Dato en la posicion J + 1
      * @param tipoOrdenacion El tipo de ordenacion si es Ascendente o
      * Descendente
      * @param j Posicion
      * @throws Exception
      */
     private Object[] evaluaCambiarDatoNoObjeto(Class clazz, E auxJ, E auxJ1, TipoOrdenacion tipoOrdenacion, int j) throws Exception {
          Object aux[] = new Object[2];//aux[0];--->null
          if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
               // Number datoJ = (Number) auxJ;
               // Number datoJ1 = (Number) auxJ1;
               if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    if ((((Number) auxJ).doubleValue() > ((Number) auxJ1).doubleValue())) {
                         aux[0] = auxJ1;
                         aux[1] = j;
                         //  cambioBurbuja(j, matriz);
                    }
               } else {
                    if ((((Number) auxJ).doubleValue() < ((Number) auxJ1).doubleValue())) {
                         // cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               }
          } else if (Utilidades.isString(clazz)) {
               String datoJ = (String) auxJ;
               String datoJ1 = (String) auxJ1;
               if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) > 0)) {
                         //cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               } else {
                    if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) < 0)) {
                         //cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               }

          } else if (Utilidades.isCharacter(clazz)) {
               Character datoJ = (Character) auxJ;
               Character datoJ1 = (Character) auxJ1;
               if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    if (datoJ > datoJ1) {
                         //cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               } else {
                    if (datoJ < datoJ1) {
                         //cambioBurbuja(j, matriz);
                         aux[0] = auxJ1;
                         aux[1] = j;
                    }
               }

          }
          return aux;
     }

     /**
      * Permite hacer el cambio con datos que son objetos del modelo
      *
      * @param clazz El tipo de clase del atributo
      * @param auxJ dato en la posicion J
      * @param auxJ1 dato en la posicion J + 1
      * @param method El metodo get de J
      * @param method1 El metodo get de J + 1
      * @param tipoOrdenacion El tipo de ordenacion si es Ascendente o
      * Descendente
      * @param j posicion
      * @throws Exception
      */
     private Object[] evaluaCambiarDato(Class clazz, E auxJ, E auxJ1, Method method, Method method1, TipoOrdenacion tipoOrdenacion, int j) throws Exception {
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
//                         aux[1] = j;
                    }
               }

          }
          return aux;
     }

     private Field getField(String nombre, Class<E> clazz) {
          for (Field field : clazz.getDeclaredFields()) {
               if (field.getName().equalsIgnoreCase(nombre)) {
                    field.setAccessible(true);
                    return field;
               }
          }
          return null;
     }

// aqui buscamos o retornamos el dato que el getfie nos recorre de todos los datos del mismo campo o ativuto
     private Object evaluarCampoDato(E dato, String atributo, Class clazz) throws Exception {
          //System.out.println(getField(atributo).get(dato));
          return getField(atributo, clazz).get(dato);
     }

//    public ListaEnlazada<E> quisortLista(String atributo, int primero, int ultimo, TipoOrdenacion tipoOrdenacion) throws Exception {
//          Integer i, j, central;
//          Class<E> clazz = null;
//          E[] matriz = null;
//          central = (primero + ultimo) / 2;
//          i = primero;
//          j = ultimo;
//          if (size > 0) {
//               clazz = (Class<E>) getCabecera().getDato().getClass();//primitivo, Dato envolvente, Object
//               Field field = Utilidades.getField(atributo, clazz);
//               Method method = getMethod("get" + Utilidades.capitalizar(atributo), obtenerDato(i).getClass());
//               Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), obtenerDato(j).getClass());
////               System.out.println(method);
////               System.out.println(method1);
//               try {
//                    matriz = toArray();
////                    Boolean isObject = Utilidades.isObject(clazz);//si es objeto
//                    if (field.getType().getSimpleName().toString().equalsIgnoreCase("Number")) {
//                         do {
//                              if (tipoOrdenacion.toString().equals(TipoOrdenacion.ASCENDENTE)) {
//                                   while (((Number) evaluarCampoDato(obtenerDato(i), atributo, clazz)).doubleValue() < ((Number) evaluarCampoDato(obtenerDato(central), atributo, clazz)).doubleValue()) {
////                                   while (((Number) evaluaCambiarDato(clazz, t, t, method, method1, tipoOrdenacion, j, matriz) < ((Number) matriz[central].doubleValue())) {
//                                        i++;
//                                   }
//                                   while (((Number) evaluarCampoDato(obtenerDato(j), atributo, clazz)).doubleValue() > ((Number) evaluarCampoDato(obtenerDato(central), atributo, clazz)).doubleValue()) {
//                                        j--;
//                                   }
//                              } else {
//                                   while (((Number) evaluarCampoDato(obtenerDato(i), atributo, clazz)).doubleValue() > ((Number) evaluarCampoDato(obtenerDato(central), atributo, clazz)).doubleValue()) {
//                                        i++;
//                                   }
//                                   while (((Number) evaluarCampoDato(obtenerDato(j), atributo, clazz)).doubleValue() < ((Number) evaluarCampoDato(obtenerDato(central), atributo, clazz)).doubleValue()) {
//                                        j--;
//                                   }
//                              }
//
//                              if (i <= j) {
//                                   E aux = obtenerDato(i);
//                                   modificarDato(i, obtenerDato(j));
//                                   modificarDato(j, (E) aux);
//                                   i++;
//                                   j--;
//                              }
//                         } while (i <= j);
//
//                    } else {
//                         do {
//                              if (tipoOrdenacion.toString().equals(TipoOrdenacion.ASCENDENTE)) {
////                                 
//                                   while (evaluarCampoDato(matriz[central], atributo, clazz).toString().
//                                           compareTo(evaluarCampoDato(matriz[i], atributo, clazz).toString()) > 0 && i < j) {
//                                        i++;
//                                   }
////                            
//                                   while (evaluarCampoDato(matriz[j], atributo, clazz).toString().
//                                           compareTo(evaluarCampoDato(matriz[central], atributo, clazz).toString()) > 0) {
//                                        System.out.println("pasa por aqui nose 4");
//                                        j--;
//                                   }
//                              } else {
////                                
//                                   while (evaluarCampoDato(matriz[central], atributo, clazz).toString().
//                                           compareTo(evaluarCampoDato(matriz[i], atributo, clazz).toString()) > 0 && i < j) {
//                                        System.out.println("pasa por aqui nose 5");
////                                      System.out.print(Value(obtenerDato(central), atributo).toString().compareTo(Value(obtenerDato(i), atributo).toString()) + "               ");
//                                        System.out.println("");
//                                        i++;
//                                   }
////                               
//                                   while (evaluarCampoDato(matriz[j], atributo, clazz).toString().
//                                           compareTo(evaluarCampoDato(matriz[central], atributo, clazz).toString()) > 0) {
////                                      while (matriz[j].toString().compareTo(matriz[central].toString()) > 0) {
//                                        System.out.println("pasa por aqui nose 6");
//                                        System.out.println("");
//                                        j--;
//                                   }
//                              }
//                              if (i <= j) { //borre el igual
//                                   Object aux = matriz[j];
//                                   matriz[i] = matriz[j-1];
//                                   matriz[j] = (E) aux;
//                                   i++; //i
//                                   j--;
//                              }
//                         } while (i <= j);
//                    }
//                    if (primero < j) {
//                         quisortLista(atributo, primero, j, tipoOrdenacion);
//                    }
//                    if (i < ultimo) {
//                         quisortLista(atributo, i, ultimo, tipoOrdenacion);
//                    }
//
//               } catch (Exception e) {
//                    e.printStackTrace();
//               }
//
//          }
//          if (matriz != null) {
//               toList(matriz);
//          }
//          return this;
//     }
    


     private Object EvaluarDato(E dato, String atributo, Class clazz) throws Exception {
          return getField(atributo, clazz).get(dato);
     }

     public ListaEnlazadaServices<E> toList(ListaEnlazada<E> no) throws PosicionException {
          ListaEnlazadaServices<E> aux = null;
          try {
               this.vaciar();
               for (int i = 0; i < no.size; i++) {
                    aux.insertarAlInicio(no.obtenerDato(i));
               }
               return aux;
          } catch (Exception e) {
               return null;
          }
     }
     public ListaEnlazada<E> QuickShortLista(String atributo, TipoOrdenacion tipoOrdenacion) throws PosicionException, Exception {
        Class<E> clazz = null;
        E[] arreglo = null;
        if (size > 0) {
            clazz = (Class<E>) cabecera.getDato().getClass();
            Field field = Utilidades.getField(atributo, clazz);
            arreglo = toArray();
            metodoQuickSort(field.getType(), arreglo,atributo, 0, arreglo.length - 1,  tipoOrdenacion);
        }
        if (arreglo != null) {
            toList(arreglo);
        }
        return this;
    }
     private void metodoQuickSort(Class clazz, E[] arreglo, String atributo,int izq, int der,  TipoOrdenacion tipoOrdenacion) throws Exception {
        E pivote = arreglo[izq];
        int i = izq;
        int j = der;

        while (i < j) {
            Method method = getMethod("get" + Utilidades.capitalizar(atributo), arreglo[i].getClass());
            Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), arreglo[j].getClass());
            Method method2 = getMethod("get" + Utilidades.capitalizar(atributo), pivote.getClass());
            if (Utilidades.isNumber(clazz)) {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    while (((Number) method.invoke(arreglo[i])).doubleValue() <= ((Number) method2.invoke(pivote)).doubleValue() && i < j) {
                        i++;
                    }
                    while (((Number) method1.invoke(arreglo[j])).doubleValue() > ((Number) method2.invoke(pivote)).doubleValue()) {
                        j--;
                    }
                } else {
                    while (((Number) method.invoke(arreglo[i])).doubleValue() >= ((Number) method2.invoke(pivote)).doubleValue() && i < j) {
                        i++;
                    }
                    while (((Number) method1.invoke(arreglo[j])).doubleValue() < ((Number) method2.invoke(pivote)).doubleValue()) {
                        j--;
                    }
                }
                if (i < j) {
                    E aux = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = aux;
                }

            } else if (Utilidades.isString(clazz)) {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    while (((String) method.invoke(arreglo[i])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) <= 0 && i < j) {
                        i++;
                    }
                    while (((String) method1.invoke(arreglo[j])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) > 0) {
                        j--;
                    }
                } else {
                    while (((String) method.invoke(arreglo[i])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) >= 0 && i < j) {
                        i++;
                    }
                    while (((String) method1.invoke(arreglo[j])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) < 0) {
                        j--;
                    }
                }
                if (i < j) {
                    E aux = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = aux;
                }

            } else if (Utilidades.isCharacter(clazz)) {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    while (((Character) method.invoke(arreglo[i])) <= ((Character) method2.invoke(pivote)) && i < j) {
                        i++;
                    }
                    while (((Character) method1.invoke(arreglo[j])) > ((Character) method2.invoke(pivote))) {
                        j--;
                    }
                } else {
                    while (((Character) method.invoke(arreglo[i])) >= ((Character) method2.invoke(pivote)) && i < j) {
                        i++;
                    }
                    while (((Character) method1.invoke(arreglo[j])) < ((Character) method2.invoke(pivote))) {
                        j--;
                    }
                }
                if (i < j) {
                    E aux = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = aux;
                }

            }
        }
        arreglo[izq] = arreglo[j];
        arreglo[j] = pivote;

        if (izq < j - 1) {
            metodoQuickSort(clazz, arreglo, atributo,izq, j - 1,  tipoOrdenacion);
        }

        if (j + 1 < der) {
            metodoQuickSort(clazz, arreglo ,atributo, j + 1, der,  tipoOrdenacion);
        }

    }




     public E[] toArray() {
          E[] matriz = (E[]) (new Object[this.size]);
          NodoLista<E> aux = cabecera;
          for (int i = 0; i < this.size; i++) {
               matriz[i] = aux.getDato();
               //System.out.print(aux.getDato().toString() + "\t");
               aux = aux.getSiguiente();
          }
          return matriz;
     }

     public ListaEnlazada<E> shellLista(String atributo, TipoOrdenacion tipoOrdenacion) throws Exception {
        Class<E> clazz = null;
        E[] matriz = null;
        if (size > 0) {
            clazz = (Class<E>) cabecera.getDato().getClass();
            Boolean isObject = Utilidades.isObject(clazz);
            matriz = toArray();
            int k, salto;
            boolean cambiar;

            for (salto = matriz.length / 2; salto != 0; salto /= 2) {
                cambiar = true;

                while (cambiar) {
                    cambiar = false;

                    for (k = salto; k < matriz.length; k++) {
                        if (isObject) {
                            Field field = Utilidades.getField(atributo, clazz);
                            Method method = getMethod("get" + Utilidades.capitalizar(atributo), matriz[k - salto].getClass());
                            Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), matriz[k].getClass());
                            Object[] aux = evaluaCambiarDato(field.getType(), matriz[k - salto], matriz[k], method, method1, tipoOrdenacion, k - salto);
                            if (aux[0] != null) {
                                E temp = matriz[k];
                                matriz[k] = matriz[k - salto];
                                matriz[k - salto] = temp;
                                cambiar = true;
                            }
                        } else {
                            Object[] aux = evaluaCambiarDatoNoObjeto(clazz, matriz[k - salto], matriz[k], tipoOrdenacion, k - salto);
                            if (aux[0] != null) {
                                E temp = matriz[k];
                                matriz[k] = matriz[k - salto];
                                matriz[k - salto] = temp;
                                cambiar = true;
                            }

                        }
                    }
                }
            }

        }
        toList(matriz);

        return this;

    }

     public ListaEnlazada<E> toList(E[] matriz) {
          //E[] matriz = (E[]) (new Object[this.size]);
          this.vaciar();
          for (int i = 0; i < matriz.length; i++) {
               this.insertar(matriz[i]);
          }
          return this;
     }

     public static void main(String[] args) {

          try {
               ListaEnlazada<Auto> lista = new AutoServicio().getListaArchivo().getLista();
//               ListaEnlazada<Auto> lista = new ListaEnlazada<>();
//               AutoController ac = new AutoController();
//
//               AutoController c1 = new AutoController();
//               c1.getAuto().setPlaca("AAA-153");
//               c1.getAuto().setModelo("AAA");
//               c1.getAuto().setColor("azul");
//               c1.guardar();
//               lista.insertar(c1.getAuto());
//               
//               AutoController c2 = new AutoController();
//               c2.getAuto().setPlaca("ACD-123");
//               c2.getAuto().setModelo("KIA");
//               c2.getAuto().setColor("rojo");
//               c2.guardar();
//               lista.insertar(c2.getAuto());
//               
//                AutoController c3 = new AutoController();
//               c3.getAuto().setPlaca("AER-453");
//               c3.getAuto().setModelo("MAXDA");
//               c3.getAuto().setColor("negro");
//               c3.guardar();
//               lista.insertar(c3.getAuto());
//               
               lista.imprimir();
               System.out.println("Quicksort");
//              lista.quicksort("modelo", 0, lista.size-1, TipoOrdenacion.ASCENDENTE);
               //lista.ordenacion_MetodoShell("modelo", 0, lista.size - 1, TipoOrdenacion.ASCENDENTE);
//               
               
               //lista.quisortLista("color", 0, lista.size-1, TipoOrdenacion.ASCENDENTE);
               lista.QuickShortLista("color", TipoOrdenacion.ASCENDENTE);
               System.out.println("metodo ordenado");
               lista.imprimir();
          } catch (Exception e) {
               System.out.println("erro " + e);
               e.printStackTrace();
          }

     }

   

}
