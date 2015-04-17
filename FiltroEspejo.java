
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Descripción de la clase.
 * 
 * @author Inés Castel
 * @version 27//03/2015
 */
public class FiltroEspejo extends Filtro
{
    private String nombre;
    
    /**
     * Constructor 
     */
    public FiltroEspejo(String nombre)
    {
        super(nombre);
    }

    /**
     * 
     */
    public void aplicar(ImagenOF imagen)
    {
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho / 2; x++) {                 
                int xSimetrica = ancho - 1 - x;
                Color pixel1 = imagen.getPixel(x, y);
                Color pixel2 = imagen.getPixel(xSimetrica, y);
                imagen.setPixel(x, y, pixel2);
                imagen.setPixel(xSimetrica, y, pixel1);
            }
        }
    }
}
