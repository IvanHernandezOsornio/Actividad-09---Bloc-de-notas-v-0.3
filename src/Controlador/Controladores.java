package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelos.Modelo;
import Vistas.Vista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;                              // Es el Nos Muestra los archivos que eligamos
import javax.swing.filechooser.FileNameExtensionFilter;       //Selecciona las extenciones de los archivos
 
public class Controladores {

    Modelo modelos; //Creamos un Objeto del modelo
    Vista vistas; //Creamos un objto de las Vistas

    //el siguiente objeto es para el selector de archivos
    JFileChooser selector_archivos = new JFileChooser(); 
    FileNameExtensionFilter filtro_extensiones = new FileNameExtensionFilter("Archivos de Texto", "txt"); //Cremos esta condicion para soo extensiones 

    ActionListener actionlistener = new ActionListener() {
        @Override
        // Valora cual de las opciones utiliza el usuario.
        //archivio, guardar, cifrar, decifrar, cerrar
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vistas.ji_archivo) { 
                ji_archivo_action_performed();
            } else if (e.getSource() == vistas.ji_guardar) { 
                ji_guardar_action_performed();
            } else if (e.getSource() == vistas.ji_cifrar) { 
                ji_cifrar_action_performed();
            } else if (e.getSource() == vistas.ji_decifrar) { 
                ji_descifrar_action_performed();
            } else if (e.getSource() == vistas.ji_cerrar) { 
                System.exit(0);
            }
        }
    };

    /**
     * Componentes
     *
     * @param modelos
     * @param vistas
     */
    public Controladores(Modelo modelos, Vista vista) {
        this.modelos = modelos;
        this.vistas = vista;

        this.vistas.ji_archivo.addActionListener(actionlistener);
        this.vistas.ji_guardar.addActionListener(actionlistener);
        this.vistas.ji_cifrar.addActionListener(actionlistener);
        this.vistas.ji_decifrar.addActionListener(actionlistener);
        this.vistas.ji_cerrar.addActionListener(actionlistener);
        initComponents();
    }

    /**
     * Metodo de Apertura de archivos
     */
    public void ji_archivo_action_performed() {
        selector_archivos.setFileFilter(filtro_extensiones); // Asigna el filtro de extensión .txt
        selector_archivos.showOpenDialog(vistas.jt_archivo); //Abre la ventana para buscar archivo
        File archivo = selector_archivos.getSelectedFile(); 
        String ruta = archivo.getPath(); // lleava el archivo de la ruta

        modelos.setPath(ruta); 

        this.readFile(modelos.getPath()); 
    }

    /**
     * Metodo para Guardar Archivos
     */
    public void ji_guardar_action_performed() {
        selector_archivos.setFileFilter(filtro_extensiones); // Asigna el filtro de extensión .txt
        selector_archivos.showSaveDialog(vistas.jt_archivo); //Abre la ventana para buscar archivo
        File archivo = selector_archivos.getSelectedFile(); // Guarda los archivos
        String ruta = archivo.getPath(); // Guarda la ruta 

        modelos.setPath(ruta); // Asigna la ruta del archivo y la guarda

        modelos.setMessage(vistas.jt_archivo.getText()); // lleva el contenido a la variable del mensaje
        this.writeFile(modelos.getPath(), modelos.getMessage()); 
    }

    /**
     *Metodo de Cifrar El Texto
     */
    public void ji_cifrar_action_performed() {
        String texto_area = vistas.jt_archivo.getText();
        String texto_cifrado = "";
        //Metodo (ciclo) para cifrar 
        for (int i = 0; i < texto_area.length(); i++) { 
            char caracter = texto_area.charAt(i);
            int ascii_char = (int) caracter;
            ascii_char = ascii_char + 7;            //Utiliza el codigo ascii //(+ 7) opcional 
            caracter = (char) ascii_char;
            texto_cifrado += caracter;
        }
        //Muestra el texto cifrado
        vistas.jt_archivo.setText(texto_cifrado); 
    }

    /**
     * Metodo para decifrar el texto
     */
    public void ji_descifrar_action_performed() {
        String texto_area = vistas.jt_archivo.getText();
        String texto_descifrado = "";
        //Metodo (ciclo) para decifrar el texto
        for (int i = 0; i < texto_area.length(); i++) { 
            char caracter = texto_area.charAt(i);
            int ascii_char = (int) caracter;
            ascii_char = ascii_char - 7;                            //Utiliza el codigo ascii //(- 7) para debolver el caracter
            caracter = (char) ascii_char;
            texto_descifrado += caracter; 
        }
         //Muestra el texto decifrado
        vistas.jt_archivo.setText(texto_descifrado);
    }

    
    
    

    /**
     * Método escribir y leer los archivos 
     *
     * @param path: Es la ruta 
     * @return
     */
    public String readFile(String path) {
        try {
            String row; 
            String acumulador_texto = ""; 
            try (FileReader archivo = new FileReader(path)) { 
                BufferedReader bufferedreader = new BufferedReader(archivo); 
                while ((row = bufferedreader.readLine()) != null) {
                    acumulador_texto += row + "\n"; 
                }
                vistas.jt_archivo.setText(acumulador_texto);
            }
        } catch (FileNotFoundException err) { 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        } catch (IOException err) { 
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    }
    
    /**
     * Metodo de Guardar el Nuevo Contenido 
     * @param path: Ruta para del archivo 
     * @param message: Mensaje que contiene el texto 
     */
    public void writeFile(String path, String message) {
        try {
            File archivo = new File(path);                                  // Abre el archivo de la ruta especificada, si no existe, lo crea (según el path o ruta)
            FileWriter filewriter = new FileWriter(archivo, false);          // Permite escribir en el archivo especificado.
            try (PrintWriter printwriter = new PrintWriter(filewriter)) {       // Permite guardar el nuevo contenido en del archivo especificado.
                printwriter.println(message);
                printwriter.close();
            }
        } catch (FileNotFoundException err) { //Detecta los errores
            System.err.println("Archivo no encontrado: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
    }

    /**
     * Método para meter los componentes
     */
    public void initComponents() {
        vistas.setVisible(true);
    }

}