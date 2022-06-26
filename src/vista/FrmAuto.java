/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.Autos.Services.AutoServicio;
import controlador.tda.lista.ListaEnlazada;
import controlador.tda.lista.ListaEnlazadaServices;
import controlador.utiles.TipoOrdenacion;
import controladorAuto.AutoController;
import javax.swing.JOptionPane;
import modelo.Auto;
import vista.tabla.ListaTabla;
import vista.tabla.TablaAuto;

/**
 *
 * @author jy
 */
public class FrmAuto extends javax.swing.JFrame {

     private AutoServicio ac = new AutoServicio();
     private TablaAuto ta = new TablaAuto();
     private ListaEnlazada le = new ListaEnlazada();
     private ListaTabla lt = new ListaTabla();

     /**
      * Creates new form FrmCine
      */
     public FrmAuto() {
          initComponents();
          setLocationRelativeTo(null);
          Limpiar();
     }

     public void Limpiar() {
//        txtColor.setText("");
//        txtModelo.setText("");
//        txtPlaca.setText("");
          ac.setAuto(null);
          CargarTabla();
     }

     public void Agregar() {
          try {
//            ac.getAuto().setColor(txtColor.getText());
//            ac.getAuto().setModelo(txtModelo.getText());
//            ac.getAuto().setPlaca(txtPlaca.getText());
               ac.guardar();
               Limpiar();
          } catch (Exception e) {
               System.out.println("erro" + e);
          }
     }

     public void generardatos() {
          String[] marcas = {"HONDA", "AUDI", "CHEVROLET", "TOYOTA", "NISSAN", "MERCEDES", "ASTON MARTIN"
               + "ALFA ROMEO", "BMW", "BYD", "DACIA0", "FERRARI", "FIAT", "FORD", "HYUNDAI", "JEEP", "JAGUAR", "KIA",
               "LADA", "LAMBORGHINI", "LEXUS", "MASERATI", "MORGAN", "OPEL", "PROSHE", "SEAT", "SMART",
               "SUSUKI", "TESLA", "VOLKSWAGEN", "VOLVO"};

          String[] colores = {"Blanco", "Negro", "Azul", "Amarillo", "Tomate", "Gris", "Verde", "Magenta", "Cafe", "Plata"};
          try {
               for (int i = 0; i < 200; i++) {
                    ac.getAuto().setColor(colores[(int) (Math.floor(Math.random() * ((colores.length - 1) - 0 + 1) + 0))]);
                    ac.getAuto().setModelo(marcas[(int) (Math.floor(Math.random() * ((marcas.length - 1) - 0 + 1) + 0))]);
                    ac.getAuto().setPlaca(generarPlaca());
                    ac.guardar();
                    // Limpiar();

               }
               JOptionPane.showMessageDialog(null, "EXito");
               CargarTabla();
               //Limpiar();
          } catch (Exception e) {
          }

     }

     public void CargarTabla() {
          ta.setLista(ac.getListaArchivo());
          // ta.fireTableStructureChanged();
          tbl_tabla.setModel(ta);
          tbl_tabla.updateUI();
          //generarConsonante();Ordenamiento
     }

     private void ordenar() throws Exception {
          ListaEnlazada<Auto> aux = null;
          TipoOrdenacion select;
          if (cbxOrden.getSelectedIndex() == 0) {
               select = TipoOrdenacion.ASCENDENTE;
          } else {
               select = TipoOrdenacion.DESCENDENTE;
          }
          int select1 = cbxMetodo.getSelectedIndex();
          String atributo = cbxTipo.getSelectedItem().toString();
          if (select1 == 0) {
               try {
//                   aux = ac.getListaArchivo().getLista().quisortLista(atributo,0,ac.getListaArchivo().getSize() - 1 , TipoOrdenacion.ASCENDENTE);
                    long inicio = System.currentTimeMillis();
                    aux = ac.getListaArchivo().getLista().QuickShortLista(atributo, select);
                    long fin = System.currentTimeMillis();
                    //aux= ac.getListaArchivo().QuisortClase(atributo, 0, ac.getListaArchivo().getSize() - 1, TipoOrdenacion.ASCENDENTE);
                    Double tiempo = (double) ((fin - inicio) / 1000);
                    lbltiempo.setText((String) tiempo.toString());
               } catch (Exception e) {
                    System.out.println("error" + e);
               }
          } else {
//               aux = ac.getListaArchivo().ShellClase(TipoOrdenacion.ASCENDENTE, atributo);
               long inicio = System.currentTimeMillis();
               aux = ac.getListaArchivo().getLista().shellLista(atributo, select);
               long fin = System.currentTimeMillis();
               Double tiempo = (double) ((fin - inicio) / 1000);
               lbltiempo.setText((String) tiempo.toString());
          }
          lt.setLista(aux);
          tbl_tabla.setModel(lt);
          tbl_tabla.updateUI();
     }

     public static String generarPlaca() {

          char placa[] = new char[9];
          placa[0] = generarConsonante();
          placa[1] = generarConsonante();
          placa[2] = generarVocal();
          placa[3] = '-';
          placa[4] = generarNumero();
          placa[5] = generarNumero();
          placa[6] = generarNumero();
          // placa[7] = '-';
          // placa[8] = generarVocal();

          return String.valueOf(placa);
     }

     public static char generarConsonante() {
          return generarRandomChar("BCDFGHJKLMNPQRSTVWXYZ");
     }

     public static char generarVocal() {
          return generarRandomChar("AEIOU");
     }

     public static char generarNumero() {
          return generarRandomChar("0123456789");
     }

     private static char generarRandomChar(String str) {
          char caracteres[] = str.toCharArray();
          int index = (int) (Math.random() * caracteres.length);
          return caracteres[index];
     }

     /**
      * This method is called from within the constructor to initialize the
      * form. WARNING: Do NOT modify this code. The content of this method is
      * always regenerated by the Form Editor.
      */
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          jPanel2 = new javax.swing.JPanel();
          jScrollPane2 = new javax.swing.JScrollPane();
          tbl_tabla = new javax.swing.JTable();
          btnGuardar = new javax.swing.JButton();
          btnCancelar = new javax.swing.JButton();
          lbltiempo = new javax.swing.JLabel();
          jLabel7 = new javax.swing.JLabel();
          jLabel4 = new javax.swing.JLabel();
          jPanel12 = new javax.swing.JPanel();
          jPanel13 = new javax.swing.JPanel();
          cbxOrden9 = new javax.swing.JComboBox<>();
          jLabel32 = new javax.swing.JLabel();
          cbxTipo9 = new javax.swing.JComboBox<>();
          jLabel33 = new javax.swing.JLabel();
          cbxMetodo9 = new javax.swing.JComboBox<>();
          jLabel34 = new javax.swing.JLabel();
          btnOrdenar9 = new javax.swing.JButton();
          jPanel14 = new javax.swing.JPanel();
          cbxOrden10 = new javax.swing.JComboBox<>();
          jLabel35 = new javax.swing.JLabel();
          cbxTipo10 = new javax.swing.JComboBox<>();
          jLabel36 = new javax.swing.JLabel();
          cbxMetodo10 = new javax.swing.JComboBox<>();
          jLabel37 = new javax.swing.JLabel();
          btnOrdenar10 = new javax.swing.JButton();
          jPanel15 = new javax.swing.JPanel();
          cbxOrden11 = new javax.swing.JComboBox<>();
          jLabel38 = new javax.swing.JLabel();
          cbxTipo11 = new javax.swing.JComboBox<>();
          jLabel39 = new javax.swing.JLabel();
          cbxMetodo11 = new javax.swing.JComboBox<>();
          jLabel40 = new javax.swing.JLabel();
          btnOrdenar11 = new javax.swing.JButton();
          jPanel16 = new javax.swing.JPanel();
          cbxOrden12 = new javax.swing.JComboBox<>();
          jLabel41 = new javax.swing.JLabel();
          cbxTipo12 = new javax.swing.JComboBox<>();
          jLabel42 = new javax.swing.JLabel();
          cbxMetodo12 = new javax.swing.JComboBox<>();
          jLabel43 = new javax.swing.JLabel();
          btnOrdenar12 = new javax.swing.JButton();
          jPanel17 = new javax.swing.JPanel();
          cbxOrden13 = new javax.swing.JComboBox<>();
          jLabel44 = new javax.swing.JLabel();
          cbxTipo13 = new javax.swing.JComboBox<>();
          jLabel45 = new javax.swing.JLabel();
          cbxMetodo13 = new javax.swing.JComboBox<>();
          jLabel46 = new javax.swing.JLabel();
          btnOrdenar13 = new javax.swing.JButton();
          jPanel18 = new javax.swing.JPanel();
          cbxOrden14 = new javax.swing.JComboBox<>();
          jLabel47 = new javax.swing.JLabel();
          cbxTipo14 = new javax.swing.JComboBox<>();
          jLabel48 = new javax.swing.JLabel();
          cbxMetodo14 = new javax.swing.JComboBox<>();
          jLabel49 = new javax.swing.JLabel();
          btnOrdenar14 = new javax.swing.JButton();
          jPanel19 = new javax.swing.JPanel();
          cbxOrden15 = new javax.swing.JComboBox<>();
          jLabel50 = new javax.swing.JLabel();
          cbxTipo15 = new javax.swing.JComboBox<>();
          jLabel51 = new javax.swing.JLabel();
          cbxMetodo15 = new javax.swing.JComboBox<>();
          jLabel52 = new javax.swing.JLabel();
          btnOrdenar15 = new javax.swing.JButton();
          jLabel6 = new javax.swing.JLabel();
          cbxTipoBusqueda = new javax.swing.JComboBox<>();
          txtBusqueda = new javax.swing.JTextField();
          btnBuscar = new javax.swing.JButton();
          jPanel20 = new javax.swing.JPanel();
          jPanel21 = new javax.swing.JPanel();
          cbxOrden16 = new javax.swing.JComboBox<>();
          jLabel53 = new javax.swing.JLabel();
          cbxTipo16 = new javax.swing.JComboBox<>();
          jLabel54 = new javax.swing.JLabel();
          cbxMetodo16 = new javax.swing.JComboBox<>();
          jLabel55 = new javax.swing.JLabel();
          btnOrdenar16 = new javax.swing.JButton();
          jPanel22 = new javax.swing.JPanel();
          cbxOrden17 = new javax.swing.JComboBox<>();
          jLabel56 = new javax.swing.JLabel();
          cbxTipo17 = new javax.swing.JComboBox<>();
          jLabel57 = new javax.swing.JLabel();
          cbxMetodo17 = new javax.swing.JComboBox<>();
          jLabel58 = new javax.swing.JLabel();
          btnOrdenar17 = new javax.swing.JButton();
          jPanel23 = new javax.swing.JPanel();
          cbxOrden18 = new javax.swing.JComboBox<>();
          jLabel59 = new javax.swing.JLabel();
          cbxTipo18 = new javax.swing.JComboBox<>();
          jLabel60 = new javax.swing.JLabel();
          cbxMetodo18 = new javax.swing.JComboBox<>();
          jLabel61 = new javax.swing.JLabel();
          btnOrdenar18 = new javax.swing.JButton();
          jPanel24 = new javax.swing.JPanel();
          cbxOrden19 = new javax.swing.JComboBox<>();
          jLabel62 = new javax.swing.JLabel();
          cbxTipo19 = new javax.swing.JComboBox<>();
          jLabel63 = new javax.swing.JLabel();
          cbxMetodo19 = new javax.swing.JComboBox<>();
          jLabel64 = new javax.swing.JLabel();
          btnOrdenar19 = new javax.swing.JButton();
          jPanel25 = new javax.swing.JPanel();
          cbxOrden20 = new javax.swing.JComboBox<>();
          jLabel65 = new javax.swing.JLabel();
          cbxTipo20 = new javax.swing.JComboBox<>();
          jLabel66 = new javax.swing.JLabel();
          cbxMetodo20 = new javax.swing.JComboBox<>();
          jLabel67 = new javax.swing.JLabel();
          btnOrdenar20 = new javax.swing.JButton();
          jPanel26 = new javax.swing.JPanel();
          cbxOrden21 = new javax.swing.JComboBox<>();
          jLabel68 = new javax.swing.JLabel();
          cbxTipo21 = new javax.swing.JComboBox<>();
          jLabel69 = new javax.swing.JLabel();
          cbxMetodo21 = new javax.swing.JComboBox<>();
          jLabel70 = new javax.swing.JLabel();
          btnOrdenar21 = new javax.swing.JButton();
          jPanel27 = new javax.swing.JPanel();
          cbxOrden22 = new javax.swing.JComboBox<>();
          jLabel71 = new javax.swing.JLabel();
          cbxTipo22 = new javax.swing.JComboBox<>();
          jLabel72 = new javax.swing.JLabel();
          cbxMetodo22 = new javax.swing.JComboBox<>();
          jLabel73 = new javax.swing.JLabel();
          btnOrdenar22 = new javax.swing.JButton();
          jLabel30 = new javax.swing.JLabel();
          cbxTipo = new javax.swing.JComboBox<>();
          jLabel9 = new javax.swing.JLabel();
          cbxMetodo = new javax.swing.JComboBox<>();
          jLabel3 = new javax.swing.JLabel();
          cbxOrden = new javax.swing.JComboBox<>();
          btnOrdenar1 = new javax.swing.JButton();
          jLabel10 = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          jPanel1.setBackground(new java.awt.Color(255, 255, 255));
          jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel1.setForeground(new java.awt.Color(255, 142, 38));
          jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          jPanel2.setBackground(new java.awt.Color(255, 255, 255));
          jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          tbl_tabla.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
               },
               new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
               }
          ));
          jScrollPane2.setViewportView(tbl_tabla);

          jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 610, 640));

          btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
          btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
          btnGuardar.setText("Generar");
          btnGuardar.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardarActionPerformed(evt);
               }
          });
          jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 30));

          btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
          btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
          btnCancelar.setText("Actualizar");
          btnCancelar.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCancelarActionPerformed(evt);
               }
          });
          jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 140, 30));

          lbltiempo.setText("0");
          jPanel2.add(lbltiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

          jLabel7.setText("El tiempo es:");
          jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

          jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 640, 710));

          jLabel4.setFont(new java.awt.Font("Roboto Condensed, Light", 1, 48)); // NOI18N
          jLabel4.setForeground(new java.awt.Color(51, 51, 51));
          jLabel4.setText("DAIMLER");
          jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, -1));

          jPanel12.setBackground(new java.awt.Color(255, 255, 255));
          jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel12.setForeground(new java.awt.Color(0, 0, 0));
          jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          jPanel13.setBackground(new java.awt.Color(255, 255, 255));
          jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel13.setForeground(new java.awt.Color(0, 0, 0));
          jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden9.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden9.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel13.add(cbxOrden9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel32.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel32.setForeground(new java.awt.Color(51, 51, 51));
          jLabel32.setText("Orden");
          jPanel13.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo9.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo9.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo9.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo9ActionPerformed(evt);
               }
          });
          jPanel13.add(cbxTipo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel33.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel33.setForeground(new java.awt.Color(51, 51, 51));
          jLabel33.setText("Tipo");
          jPanel13.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo9.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo9.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel13.add(cbxMetodo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel34.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel34.setForeground(new java.awt.Color(51, 51, 51));
          jLabel34.setText("Metodo");
          jPanel13.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar9.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar9.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar9.setText("Ordenar");
          btnOrdenar9.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar9ActionPerformed(evt);
               }
          });
          jPanel13.add(btnOrdenar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel14.setBackground(new java.awt.Color(255, 255, 255));
          jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel14.setForeground(new java.awt.Color(0, 0, 0));
          jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden10.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden10.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel14.add(cbxOrden10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel35.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel35.setForeground(new java.awt.Color(51, 51, 51));
          jLabel35.setText("Orden");
          jPanel14.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo10.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo10.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo10.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo10ActionPerformed(evt);
               }
          });
          jPanel14.add(cbxTipo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel36.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel36.setForeground(new java.awt.Color(51, 51, 51));
          jLabel36.setText("Tipo");
          jPanel14.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo10.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo10.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel14.add(cbxMetodo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel37.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel37.setForeground(new java.awt.Color(51, 51, 51));
          jLabel37.setText("Metodo");
          jPanel14.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar10.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar10.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar10.setText("Ordenar");
          btnOrdenar10.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar10ActionPerformed(evt);
               }
          });
          jPanel14.add(btnOrdenar10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel15.setBackground(new java.awt.Color(255, 255, 255));
          jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel15.setForeground(new java.awt.Color(0, 0, 0));
          jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden11.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden11.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel15.add(cbxOrden11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel38.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel38.setForeground(new java.awt.Color(51, 51, 51));
          jLabel38.setText("Orden");
          jPanel15.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo11.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo11.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo11.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo11ActionPerformed(evt);
               }
          });
          jPanel15.add(cbxTipo11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel39.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel39.setForeground(new java.awt.Color(51, 51, 51));
          jLabel39.setText("Tipo");
          jPanel15.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo11.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo11.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel15.add(cbxMetodo11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel40.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel40.setForeground(new java.awt.Color(51, 51, 51));
          jLabel40.setText("Metodo");
          jPanel15.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar11.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar11.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar11.setText("Ordenar");
          btnOrdenar11.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar11ActionPerformed(evt);
               }
          });
          jPanel15.add(btnOrdenar11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel12.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 660, 620, 90));

          jPanel16.setBackground(new java.awt.Color(255, 255, 255));
          jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel16.setForeground(new java.awt.Color(0, 0, 0));
          jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden12.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden12.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel16.add(cbxOrden12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel41.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel41.setForeground(new java.awt.Color(51, 51, 51));
          jLabel41.setText("Orden");
          jPanel16.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo12.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo12.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo12.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo12ActionPerformed(evt);
               }
          });
          jPanel16.add(cbxTipo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel42.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel42.setForeground(new java.awt.Color(51, 51, 51));
          jLabel42.setText("Tipo");
          jPanel16.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo12.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo12.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel16.add(cbxMetodo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel43.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel43.setForeground(new java.awt.Color(51, 51, 51));
          jLabel43.setText("Metodo");
          jPanel16.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar12.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar12.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar12.setText("Ordenar");
          btnOrdenar12.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar12ActionPerformed(evt);
               }
          });
          jPanel16.add(btnOrdenar12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel17.setBackground(new java.awt.Color(255, 255, 255));
          jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel17.setForeground(new java.awt.Color(0, 0, 0));
          jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden13.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden13.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel17.add(cbxOrden13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel44.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel44.setForeground(new java.awt.Color(51, 51, 51));
          jLabel44.setText("Orden");
          jPanel17.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo13.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo13.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo13.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo13ActionPerformed(evt);
               }
          });
          jPanel17.add(cbxTipo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel45.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel45.setForeground(new java.awt.Color(51, 51, 51));
          jLabel45.setText("Tipo");
          jPanel17.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo13.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo13.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel17.add(cbxMetodo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel46.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel46.setForeground(new java.awt.Color(51, 51, 51));
          jLabel46.setText("Metodo");
          jPanel17.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar13.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar13.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar13.setText("Ordenar");
          btnOrdenar13.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar13ActionPerformed(evt);
               }
          });
          jPanel17.add(btnOrdenar13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel18.setBackground(new java.awt.Color(255, 255, 255));
          jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel18.setForeground(new java.awt.Color(0, 0, 0));
          jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden14.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden14.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel18.add(cbxOrden14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel47.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel47.setForeground(new java.awt.Color(51, 51, 51));
          jLabel47.setText("Orden");
          jPanel18.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo14.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo14.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo14.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo14ActionPerformed(evt);
               }
          });
          jPanel18.add(cbxTipo14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel48.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel48.setForeground(new java.awt.Color(51, 51, 51));
          jLabel48.setText("Tipo");
          jPanel18.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo14.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo14.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel18.add(cbxMetodo14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel49.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel49.setForeground(new java.awt.Color(51, 51, 51));
          jLabel49.setText("Metodo");
          jPanel18.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar14.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar14.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar14.setText("Ordenar");
          btnOrdenar14.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar14ActionPerformed(evt);
               }
          });
          jPanel18.add(btnOrdenar14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel19.setBackground(new java.awt.Color(255, 255, 255));
          jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel19.setForeground(new java.awt.Color(0, 0, 0));
          jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden15.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden15.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel19.add(cbxOrden15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel50.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel50.setForeground(new java.awt.Color(51, 51, 51));
          jLabel50.setText("Orden");
          jPanel19.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo15.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo15.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo15.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo15ActionPerformed(evt);
               }
          });
          jPanel19.add(cbxTipo15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel51.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel51.setForeground(new java.awt.Color(51, 51, 51));
          jLabel51.setText("Tipo");
          jPanel19.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo15.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo15.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel19.add(cbxMetodo15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel52.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel52.setForeground(new java.awt.Color(51, 51, 51));
          jLabel52.setText("Metodo");
          jPanel19.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar15.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar15.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar15.setText("Ordenar");
          btnOrdenar15.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar15ActionPerformed(evt);
               }
          });
          jPanel19.add(btnOrdenar15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel18.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel16.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 660, 620, 90));

          jPanel12.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 620, 90));

          jLabel6.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel6.setForeground(new java.awt.Color(51, 51, 51));
          jLabel6.setText("Busqueda");
          jPanel12.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 110, 27));

          cbxTipoBusqueda.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipoBusqueda.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Modelo", "Placa", "Color" }));
          jPanel12.add(cbxTipoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

          txtBusqueda.setBackground(new java.awt.Color(255, 255, 255));
          txtBusqueda.setForeground(new java.awt.Color(0, 0, 0));
          txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBusquedaActionPerformed(evt);
               }
          });
          jPanel12.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, -1));

          btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
          btnBuscar.setForeground(new java.awt.Color(0, 0, 0));
          btnBuscar.setText("Buscar");
          jPanel12.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 80, 30));

          jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 420, 180, 340));

          jPanel20.setBackground(new java.awt.Color(255, 255, 255));
          jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel20.setForeground(new java.awt.Color(0, 0, 0));
          jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          jPanel21.setBackground(new java.awt.Color(255, 255, 255));
          jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel21.setForeground(new java.awt.Color(0, 0, 0));
          jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden16.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden16.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel21.add(cbxOrden16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel53.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel53.setForeground(new java.awt.Color(51, 51, 51));
          jLabel53.setText("Orden");
          jPanel21.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo16.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo16.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo16.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo16ActionPerformed(evt);
               }
          });
          jPanel21.add(cbxTipo16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel54.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel54.setForeground(new java.awt.Color(51, 51, 51));
          jLabel54.setText("Tipo");
          jPanel21.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo16.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo16.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel21.add(cbxMetodo16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel55.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel55.setForeground(new java.awt.Color(51, 51, 51));
          jLabel55.setText("Metodo");
          jPanel21.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar16.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar16.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar16.setText("Ordenar");
          btnOrdenar16.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar16ActionPerformed(evt);
               }
          });
          jPanel21.add(btnOrdenar16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel22.setBackground(new java.awt.Color(255, 255, 255));
          jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel22.setForeground(new java.awt.Color(0, 0, 0));
          jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden17.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden17.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel22.add(cbxOrden17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel56.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel56.setForeground(new java.awt.Color(51, 51, 51));
          jLabel56.setText("Orden");
          jPanel22.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo17.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo17.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo17.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo17ActionPerformed(evt);
               }
          });
          jPanel22.add(cbxTipo17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel57.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel57.setForeground(new java.awt.Color(51, 51, 51));
          jLabel57.setText("Tipo");
          jPanel22.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo17.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo17.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel22.add(cbxMetodo17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel58.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel58.setForeground(new java.awt.Color(51, 51, 51));
          jLabel58.setText("Metodo");
          jPanel22.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar17.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar17.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar17.setText("Ordenar");
          btnOrdenar17.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar17ActionPerformed(evt);
               }
          });
          jPanel22.add(btnOrdenar17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel23.setBackground(new java.awt.Color(255, 255, 255));
          jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel23.setForeground(new java.awt.Color(0, 0, 0));
          jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden18.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden18.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel23.add(cbxOrden18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel59.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel59.setForeground(new java.awt.Color(51, 51, 51));
          jLabel59.setText("Orden");
          jPanel23.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo18.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo18.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo18.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo18ActionPerformed(evt);
               }
          });
          jPanel23.add(cbxTipo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel60.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel60.setForeground(new java.awt.Color(51, 51, 51));
          jLabel60.setText("Tipo");
          jPanel23.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo18.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo18.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel23.add(cbxMetodo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel61.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel61.setForeground(new java.awt.Color(51, 51, 51));
          jLabel61.setText("Metodo");
          jPanel23.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar18.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar18.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar18.setText("Ordenar");
          btnOrdenar18.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar18ActionPerformed(evt);
               }
          });
          jPanel23.add(btnOrdenar18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel20.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 660, 620, 90));

          jPanel24.setBackground(new java.awt.Color(255, 255, 255));
          jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel24.setForeground(new java.awt.Color(0, 0, 0));
          jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden19.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden19.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel24.add(cbxOrden19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel62.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel62.setForeground(new java.awt.Color(51, 51, 51));
          jLabel62.setText("Orden");
          jPanel24.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo19.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo19.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo19.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo19ActionPerformed(evt);
               }
          });
          jPanel24.add(cbxTipo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel63.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel63.setForeground(new java.awt.Color(51, 51, 51));
          jLabel63.setText("Tipo");
          jPanel24.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo19.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo19.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel24.add(cbxMetodo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel64.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel64.setForeground(new java.awt.Color(51, 51, 51));
          jLabel64.setText("Metodo");
          jPanel24.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar19.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar19.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar19.setText("Ordenar");
          btnOrdenar19.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar19ActionPerformed(evt);
               }
          });
          jPanel24.add(btnOrdenar19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel25.setBackground(new java.awt.Color(255, 255, 255));
          jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel25.setForeground(new java.awt.Color(0, 0, 0));
          jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden20.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden20.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel25.add(cbxOrden20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel65.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel65.setForeground(new java.awt.Color(51, 51, 51));
          jLabel65.setText("Orden");
          jPanel25.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo20.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo20.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo20.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo20ActionPerformed(evt);
               }
          });
          jPanel25.add(cbxTipo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel66.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel66.setForeground(new java.awt.Color(51, 51, 51));
          jLabel66.setText("Tipo");
          jPanel25.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo20.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo20.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel25.add(cbxMetodo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel67.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel67.setForeground(new java.awt.Color(51, 51, 51));
          jLabel67.setText("Metodo");
          jPanel25.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar20.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar20.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar20.setText("Ordenar");
          btnOrdenar20.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar20ActionPerformed(evt);
               }
          });
          jPanel25.add(btnOrdenar20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel24.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel26.setBackground(new java.awt.Color(255, 255, 255));
          jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel26.setForeground(new java.awt.Color(0, 0, 0));
          jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden21.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden21.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel26.add(cbxOrden21, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel68.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel68.setForeground(new java.awt.Color(51, 51, 51));
          jLabel68.setText("Orden");
          jPanel26.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo21.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo21.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo21.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo21ActionPerformed(evt);
               }
          });
          jPanel26.add(cbxTipo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel69.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel69.setForeground(new java.awt.Color(51, 51, 51));
          jLabel69.setText("Tipo");
          jPanel26.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo21.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo21.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel26.add(cbxMetodo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel70.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel70.setForeground(new java.awt.Color(51, 51, 51));
          jLabel70.setText("Metodo");
          jPanel26.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar21.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar21.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar21.setText("Ordenar");
          btnOrdenar21.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar21ActionPerformed(evt);
               }
          });
          jPanel26.add(btnOrdenar21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel27.setBackground(new java.awt.Color(255, 255, 255));
          jPanel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          jPanel27.setForeground(new java.awt.Color(0, 0, 0));
          jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

          cbxOrden22.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden22.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel27.add(cbxOrden22, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, -1));

          jLabel71.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel71.setForeground(new java.awt.Color(51, 51, 51));
          jLabel71.setText("Orden");
          jPanel27.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 27));

          cbxTipo22.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo22.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo", "Placa", "Color" }));
          cbxTipo22.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipo22ActionPerformed(evt);
               }
          });
          jPanel27.add(cbxTipo22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

          jLabel72.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel72.setForeground(new java.awt.Color(51, 51, 51));
          jLabel72.setText("Tipo");
          jPanel27.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 27));

          cbxMetodo22.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo22.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quicksort" }));
          jPanel27.add(cbxMetodo22, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

          jLabel73.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel73.setForeground(new java.awt.Color(51, 51, 51));
          jLabel73.setText("Metodo");
          jPanel27.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 60, 27));

          btnOrdenar22.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar22.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar22.setText("Ordenar");
          btnOrdenar22.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar22ActionPerformed(evt);
               }
          });
          jPanel27.add(btnOrdenar22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 110, 30));

          jPanel26.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 620, 90));

          jPanel24.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 660, 620, 90));

          jPanel20.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 620, 90));

          jLabel30.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel30.setForeground(new java.awt.Color(51, 51, 51));
          jLabel30.setText("Tipo");
          jPanel20.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 60, 27));

          cbxTipo.setBackground(new java.awt.Color(255, 255, 255));
          cbxTipo.setForeground(new java.awt.Color(0, 0, 0));
          cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "modelo", "placa", "color" }));
          cbxTipo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipoActionPerformed(evt);
               }
          });
          jPanel20.add(cbxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

          jLabel9.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel9.setForeground(new java.awt.Color(51, 51, 51));
          jLabel9.setText("Metodo");
          jPanel20.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 60, 27));

          cbxMetodo.setBackground(new java.awt.Color(255, 255, 255));
          cbxMetodo.setForeground(new java.awt.Color(0, 0, 0));
          cbxMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "quicksort", "shell" }));
          jPanel20.add(cbxMetodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 150, -1));

          jLabel3.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel3.setForeground(new java.awt.Color(51, 51, 51));
          jLabel3.setText("Orden");
          jPanel20.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 60, 27));

          cbxOrden.setBackground(new java.awt.Color(255, 255, 255));
          cbxOrden.setForeground(new java.awt.Color(0, 0, 0));
          cbxOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
          jPanel20.add(cbxOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 150, -1));

          btnOrdenar1.setBackground(new java.awt.Color(255, 255, 255));
          btnOrdenar1.setForeground(new java.awt.Color(0, 0, 0));
          btnOrdenar1.setText("Ordenar");
          btnOrdenar1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOrdenar1ActionPerformed(evt);
               }
          });
          jPanel20.add(btnOrdenar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 110, 30));

          jLabel10.setFont(new java.awt.Font("Roboto Condensed, Light", 0, 14)); // NOI18N
          jLabel10.setForeground(new java.awt.Color(51, 51, 51));
          jLabel10.setText("Ordenamiento");
          jPanel20.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 110, 27));

          jPanel1.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 180, 340));

          getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 800));

          pack();
     }// </editor-fold>//GEN-END:initComponents

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

     private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

          //0//Agregar();
          //CargarTabla();
          generardatos();
     }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnOrdenar15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar15ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar15ActionPerformed

    private void cbxTipo15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo15ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo15ActionPerformed

    private void btnOrdenar14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar14ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar14ActionPerformed

    private void cbxTipo14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo14ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo14ActionPerformed

    private void btnOrdenar13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar13ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar13ActionPerformed

    private void cbxTipo13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo13ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo13ActionPerformed

    private void btnOrdenar12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar12ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar12ActionPerformed

    private void cbxTipo12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo12ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo12ActionPerformed

    private void btnOrdenar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar11ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar11ActionPerformed

    private void cbxTipo11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo11ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo11ActionPerformed

    private void btnOrdenar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar10ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar10ActionPerformed

    private void cbxTipo10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo10ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo10ActionPerformed

    private void btnOrdenar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar9ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar9ActionPerformed

    private void cbxTipo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo9ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo9ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
         CargarTabla();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbxTipo16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo16ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo16ActionPerformed

    private void btnOrdenar16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar16ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar16ActionPerformed

    private void cbxTipo17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo17ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo17ActionPerformed

    private void btnOrdenar17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar17ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar17ActionPerformed

    private void cbxTipo18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo18ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo18ActionPerformed

    private void btnOrdenar18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar18ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar18ActionPerformed

    private void cbxTipo19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo19ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo19ActionPerformed

    private void btnOrdenar19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar19ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar19ActionPerformed

    private void cbxTipo20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo20ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo20ActionPerformed

    private void btnOrdenar20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar20ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar20ActionPerformed

    private void cbxTipo21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo21ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo21ActionPerformed

    private void btnOrdenar21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar21ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar21ActionPerformed

    private void cbxTipo22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo22ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo22ActionPerformed

    private void btnOrdenar22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar22ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar22ActionPerformed

    private void cbxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoActionPerformed

    private void btnOrdenar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar1ActionPerformed
         try {
              ordenar();
         } catch (Exception e) {
         }
         // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenar1ActionPerformed

     /**
      * @param args the command line arguments
      */
     public static void main(String args[]) {
          /* Set the Nimbus look and feel */
          //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
          /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
           */
          try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
               }
          } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(FrmAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(FrmAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(FrmAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(FrmAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the form */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    new FrmAuto().setVisible(true);
               }
          });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton btnBuscar;
     private javax.swing.JButton btnCancelar;
     private javax.swing.JButton btnGuardar;
     private javax.swing.JButton btnOrdenar1;
     private javax.swing.JButton btnOrdenar10;
     private javax.swing.JButton btnOrdenar11;
     private javax.swing.JButton btnOrdenar12;
     private javax.swing.JButton btnOrdenar13;
     private javax.swing.JButton btnOrdenar14;
     private javax.swing.JButton btnOrdenar15;
     private javax.swing.JButton btnOrdenar16;
     private javax.swing.JButton btnOrdenar17;
     private javax.swing.JButton btnOrdenar18;
     private javax.swing.JButton btnOrdenar19;
     private javax.swing.JButton btnOrdenar20;
     private javax.swing.JButton btnOrdenar21;
     private javax.swing.JButton btnOrdenar22;
     private javax.swing.JButton btnOrdenar9;
     private javax.swing.JComboBox<String> cbxMetodo;
     private javax.swing.JComboBox<String> cbxMetodo10;
     private javax.swing.JComboBox<String> cbxMetodo11;
     private javax.swing.JComboBox<String> cbxMetodo12;
     private javax.swing.JComboBox<String> cbxMetodo13;
     private javax.swing.JComboBox<String> cbxMetodo14;
     private javax.swing.JComboBox<String> cbxMetodo15;
     private javax.swing.JComboBox<String> cbxMetodo16;
     private javax.swing.JComboBox<String> cbxMetodo17;
     private javax.swing.JComboBox<String> cbxMetodo18;
     private javax.swing.JComboBox<String> cbxMetodo19;
     private javax.swing.JComboBox<String> cbxMetodo20;
     private javax.swing.JComboBox<String> cbxMetodo21;
     private javax.swing.JComboBox<String> cbxMetodo22;
     private javax.swing.JComboBox<String> cbxMetodo9;
     private javax.swing.JComboBox<String> cbxOrden;
     private javax.swing.JComboBox<String> cbxOrden10;
     private javax.swing.JComboBox<String> cbxOrden11;
     private javax.swing.JComboBox<String> cbxOrden12;
     private javax.swing.JComboBox<String> cbxOrden13;
     private javax.swing.JComboBox<String> cbxOrden14;
     private javax.swing.JComboBox<String> cbxOrden15;
     private javax.swing.JComboBox<String> cbxOrden16;
     private javax.swing.JComboBox<String> cbxOrden17;
     private javax.swing.JComboBox<String> cbxOrden18;
     private javax.swing.JComboBox<String> cbxOrden19;
     private javax.swing.JComboBox<String> cbxOrden20;
     private javax.swing.JComboBox<String> cbxOrden21;
     private javax.swing.JComboBox<String> cbxOrden22;
     private javax.swing.JComboBox<String> cbxOrden9;
     private javax.swing.JComboBox<String> cbxTipo;
     private javax.swing.JComboBox<String> cbxTipo10;
     private javax.swing.JComboBox<String> cbxTipo11;
     private javax.swing.JComboBox<String> cbxTipo12;
     private javax.swing.JComboBox<String> cbxTipo13;
     private javax.swing.JComboBox<String> cbxTipo14;
     private javax.swing.JComboBox<String> cbxTipo15;
     private javax.swing.JComboBox<String> cbxTipo16;
     private javax.swing.JComboBox<String> cbxTipo17;
     private javax.swing.JComboBox<String> cbxTipo18;
     private javax.swing.JComboBox<String> cbxTipo19;
     private javax.swing.JComboBox<String> cbxTipo20;
     private javax.swing.JComboBox<String> cbxTipo21;
     private javax.swing.JComboBox<String> cbxTipo22;
     private javax.swing.JComboBox<String> cbxTipo9;
     private javax.swing.JComboBox<String> cbxTipoBusqueda;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel30;
     private javax.swing.JLabel jLabel32;
     private javax.swing.JLabel jLabel33;
     private javax.swing.JLabel jLabel34;
     private javax.swing.JLabel jLabel35;
     private javax.swing.JLabel jLabel36;
     private javax.swing.JLabel jLabel37;
     private javax.swing.JLabel jLabel38;
     private javax.swing.JLabel jLabel39;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel40;
     private javax.swing.JLabel jLabel41;
     private javax.swing.JLabel jLabel42;
     private javax.swing.JLabel jLabel43;
     private javax.swing.JLabel jLabel44;
     private javax.swing.JLabel jLabel45;
     private javax.swing.JLabel jLabel46;
     private javax.swing.JLabel jLabel47;
     private javax.swing.JLabel jLabel48;
     private javax.swing.JLabel jLabel49;
     private javax.swing.JLabel jLabel50;
     private javax.swing.JLabel jLabel51;
     private javax.swing.JLabel jLabel52;
     private javax.swing.JLabel jLabel53;
     private javax.swing.JLabel jLabel54;
     private javax.swing.JLabel jLabel55;
     private javax.swing.JLabel jLabel56;
     private javax.swing.JLabel jLabel57;
     private javax.swing.JLabel jLabel58;
     private javax.swing.JLabel jLabel59;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JLabel jLabel60;
     private javax.swing.JLabel jLabel61;
     private javax.swing.JLabel jLabel62;
     private javax.swing.JLabel jLabel63;
     private javax.swing.JLabel jLabel64;
     private javax.swing.JLabel jLabel65;
     private javax.swing.JLabel jLabel66;
     private javax.swing.JLabel jLabel67;
     private javax.swing.JLabel jLabel68;
     private javax.swing.JLabel jLabel69;
     private javax.swing.JLabel jLabel7;
     private javax.swing.JLabel jLabel70;
     private javax.swing.JLabel jLabel71;
     private javax.swing.JLabel jLabel72;
     private javax.swing.JLabel jLabel73;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel12;
     private javax.swing.JPanel jPanel13;
     private javax.swing.JPanel jPanel14;
     private javax.swing.JPanel jPanel15;
     private javax.swing.JPanel jPanel16;
     private javax.swing.JPanel jPanel17;
     private javax.swing.JPanel jPanel18;
     private javax.swing.JPanel jPanel19;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JPanel jPanel20;
     private javax.swing.JPanel jPanel21;
     private javax.swing.JPanel jPanel22;
     private javax.swing.JPanel jPanel23;
     private javax.swing.JPanel jPanel24;
     private javax.swing.JPanel jPanel25;
     private javax.swing.JPanel jPanel26;
     private javax.swing.JPanel jPanel27;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JLabel lbltiempo;
     private javax.swing.JTable tbl_tabla;
     private javax.swing.JTextField txtBusqueda;
     // End of variables declaration//GEN-END:variables
}
