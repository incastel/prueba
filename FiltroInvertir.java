
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Descripción de la clase.
 * 
 * @author Inés Castel
 * @version 27/03/2015
 */
public class FiltroInvertir extends Filtro
{
    private String nombre;

    /**
      * Constructor 
      */
    public FiltroInvertir(String nombre)
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
            for (int x = 0; x < ancho; x++) {                 
                Color color = imagen.getPixel(x, y);        // saca el color del pixel
                int rojo = color.getRed();      //saca el tono de rojo
                int verde = color.getGreen();   // saca el tono de verde
                int azul = color.getBlue();     // saca el tono de azul
                Color nuevoColor = new Color(255 - rojo, 255 - verde, 255 - azul);      // crea el color nuevo
                imagen.setPixel(x, y, nuevoColor);      // cambia el color del pixel
            }
        }
    }
}
