
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Descripción de la clase.
 * 
 * @author Inés Castel
 * @version 26/03/2015
 */
public class Umbral extends Filtro
{
   private static final int UMBRAL_NEGRO = 85;     // Umbral hasta el que se consideran todos los colores el negro
   private static final int UMBRAL_GRIS = 170;     // Umbral hasta el que se consideran todos los colores el gris 
    
   /**
     * Constructor 
     */
    public Umbral(String nombre)
    {
        super(nombre);
    }
    
   /**
    * Recorre todos los pixeles de la imagen 
    * y los que están por debajo de un umbral los hace blancos, por debajo de otro grises
    * y el resto negros
    */
   public void aplicar(ImagenOF imagen)
   {
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                Color color = imagen.getPixel(x,y);
                int brillo = (color.getRed() + color.getGreen() + color.getBlue())/3; 
                if (brillo < UMBRAL_NEGRO) {
                    imagen.setPixel (x,y,Color.BLACK);
                }
                else if (brillo < UMBRAL_GRIS) {
                    imagen.setPixel (x,y,Color.GRAY);
                }
                else {
                    imagen.setPixel (x,y,Color.WHITE);
                }
            }
        }
   }
}
