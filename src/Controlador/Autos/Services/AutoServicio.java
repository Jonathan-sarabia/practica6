/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Autos.Services;

import controlador.tda.lista.ListaEnlazadaServices;
import controladorAuto.AutoController;
import modelo.Auto;

/**
 *
 * @author jona
 */
public class AutoServicio {
     private AutoController ac = new AutoController();
     
      public Auto getAuto() {
        return ac.getAuto();
    }
    
    public ListaEnlazadaServices<Auto> getLista() {
        return ac.getLisautos();
    }
    
    public ListaEnlazadaServices<Auto> getListaArchivo() {
        return ac.listado();
    }
    
    public Boolean guardar() {
       return ac.guardar();
    }
    
    public Boolean modificar(Integer pos) {
        return ac.modificar(pos);
    }
    
    public void setAuto(Auto auto) {
        ac.setAuto(auto);
    }
     
     
}
