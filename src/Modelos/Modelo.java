package Modelos;

/**
 * 
 * @author Familia Hernández
 */
public class Modelo {
    
    // creamos el metodo GET y SET de la Variable que indica la Ruta
    private String path = ""; 

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    //Creamos el Metodo GET y SET del mensaje que se muestra
    private String message = ""; 

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
