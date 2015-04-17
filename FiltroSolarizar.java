
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Descripción de la clase.
 * 
 * @author Inés Castel 
 * @version 30/03/2015
 */
public class FiltroSolarizar extends Filtro
{
    private String nombre;
    private static final int UMBRAL = 128;
    
    /**
     * Constructor for objects of class FiltroSolarizar
     */
    public FiltroSolarizar(String nombre)
    {
        super(nombre);
    }

    public void aplicar(ImagenOF imagen)
    {
        ImagenOF imagenOriginal = new ImagenOF(imagen);
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
          for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                Color pixel = imagen.getPixel(x, y);
                int red = (pixel.getRed() < UMBRAL) ? 255 : pixel.getRed();
                int green = (pixel.getGreen() < UMBRAL) ? 255 : pixel.getGreen();
                int blue = (pixel.getBlue() < UMBRAL) ? 255 : pixel.getBlue();
                Color nuevoColor = new Color(red, green, blue);
                imagen.setPixel(x, y, nuevoColor);
            }
        }
    }
}
