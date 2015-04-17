import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

/**
 * AdministradorDeArchivos es una clase con métodos para cargar y salvar archivos
 * 
 * Los ficheros en disco pueden ser JPG o PNG. Para los ficheros escritos por esta clase,
 * el formato viene determinado por la constante FORMATO_IMAGEN
 * 
 * @author Apah (Es la traducción de la clase ImageFileManager
 * @version 10.04.2012
 */
public class AdministradorDeArchivos
{
    private static final String FORMATO_IMAGEN = "jpg"; // Constante que indica el formato de los archivos que escribe la clase
    private static JFileChooser selectorArchivos = new JFileChooser(System.getProperty("user.dir"));
    
    /**
     * Abre un selector de archivos y permite al usuario seleccionar un fichero imagen
     * Lee el fichero elegido y lo devuelve como una imagen.
     * Puede leer ficheros en formato JPG y PNG.
     * Si ocurre algún problema en la lectura, el método devuelve un null
     * 
     * @return El objeto imagen, o null si ha ocurrido algún problema
     */
    public static ImagenOF getImagen()
    {
        int valorDevuelto = selectorArchivos.showOpenDialog(null);

        if(valorDevuelto != JFileChooser.APPROVE_OPTION) {
            return null;  // Se cancela la operación
        }
        File archivoSeleccionado = selectorArchivos.getSelectedFile();
        return cargarImagen(archivoSeleccionado);
    }

    /**
     * Lee un archivo del disco y lo devuelve como un BufferedImage. 
     * Puede leer ficheros en formato JPG y PNG.
     * Si ocurre algún problema en la lectura, el método devuelve un null
     * 
     * @return  El objeto imagen o null si ha ocurrido algún problema
     * @param archivoImagen  El archivo de imagen a ser cargado
     */
    public static ImagenOF cargarImagen(File archivoImagen)
    {
        try {
            BufferedImage imagen = ImageIO.read(archivoImagen);
            if(imagen == null || (imagen.getWidth(null) < 0)) {
                // No se ha podido cargar la imagen. Posiblemente era un formato invalido
                return null;
            }
            return new ImagenOF(imagen);
        }
        catch(IOException exc) {
            return null;
        }
    }

    /**
     * Escribe un fichero imagen en el disco. El formato con el que escribe es JPG.
     * En caso de haber algún problema, el método no devuelve nada
     * 
     * @param imagen  La imagen a ser salvada
     * @param archivo El archivo que se guarda
     */
    public static void salvarImagen(ImagenOF imagen, File archivo)
    {
        try {
            ImageIO.write(imagen, FORMATO_IMAGEN, archivo);
        }
        catch(IOException exc) {
            return;
        }
    }
}
