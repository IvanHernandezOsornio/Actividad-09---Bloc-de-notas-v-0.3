
package main;

import Controlador.Controladores;
import Modelos.Modelo;
import Vistas.Vista;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Colocamos los componentes 
        Modelo models = new Modelo();
        Vista viewblocnotas = new Vista();
        Controladores controllerblocnotas = new Controladores(models, viewblocnotas); 
        
    }
    
}
