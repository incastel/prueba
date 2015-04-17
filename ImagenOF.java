import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * ImagenOF es una clase que define una imagen en un formato OF.
 * 
 * @author  Apah (traducción de la clase OFImage)
 * @version 10.04.2012
 */
public class ImagenOF extends BufferedImage
{   
    /**
     * Crea una ImagenOF copiada desde un BufferedImage.
     * @param imagen La imagen a copiar
     */
    public ImagenOF(BufferedImage imagen)
    {
         super(imagen.getColorModel(), imagen.copyData(null),imagen.isAlphaPremultiplied(), null);
    }

    /**
     * Crea una imagenOF con un tamaño especificado y sin contenido (de momento).
     * @param anchura La anchura de la imagen.
     * @param altura La altura de la imagen.
     */
    public ImagenOF(int anchura, int altura)
    {
        super(anchura, altura, TYPE_INT_RGB);
    }

    /**
     * Pone un pixel de la imagen a un color determinado. 
     * El color es representado por un valor rgb
     * @param x La posición x del pixel
     * @param y La posición y del pixel
     * @param color El color del pixel.
     */
    public void setPixel(int x, int y, Color color)
    {
        int pixel = color.getRGB();
        setRGB(x, y, pixel);
    }
    
    /**
     * Devuelve el color de un pixel determinado de la imagen
     * @param x La posición x del pixel
     * @param y La posición y del pixel
     * @return El color del pixel de esa posición
     */
    public Color getPixel(int x, int y)
    {
        int pixel = getRGB(x, y);
        return new Color(pixel);
    }   
    
    /**
     * 
     */
     public ArrayList<Color> pixelesAlrededor(int x, int y)
    {  
        int alto = getHeight();      // Alto de la imagen
        int ancho = getWidth();      // Ancho de la imagen
        ArrayList<Color> listaPixeles = new ArrayList<Color>();
        
        for(int i = -1; i <= 1; i++){
            int posX = x + i;
            if(posX >= 0 && posX < ancho){      // Es una coordenada posible
               for(int j = -1; j <= 1; j++){
                    int posY = y + j;
                    if(posY >= 0 && posY < alto){           // Es una coordenada posible
                        listaPixeles.add(getPixel(posX, posY));
                    }      
               }
            }
        }
        return listaPixeles;
    }
    
}
