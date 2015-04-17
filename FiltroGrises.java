
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Descripción de la clase.
 * 
 * @author Inés Castel 
 * @version 27/03/2015
 */
public class FiltroGrises extends Filtro
{
    
    private String nombre;

     /**
     * Constructor for objects of class FiltroOscuro
     */
    public FiltroGrises(String nombre)
    {
        super(nombre);
    }

     /**
     * Recorre todos los pixeles de la imagen y los hace todos grises conservando el brillo.
     * Cada pizel toma un tono de gris asignando el mismo valor a los tres componentes.
     * @param imagen es la imagen sobre la que se aplica el filtro
     */
    public void aplicar(ImagenOF imagen)
    {
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        int tono;
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                tono = (imagen.getPixel(x, y).getRed() + imagen.getPixel(x, y).getBlue() + imagen.getPixel(x, y).getGreen()) / 3;
                Color gris = new Color(tono, tono, tono);
                imagen.setPixel(x, y, gris);
            }
        }
    }
}
