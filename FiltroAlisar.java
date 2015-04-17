
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Descripción de la clase
 * 
 * @author Inés Castel
 * @version 27/03/2015
 */
public class FiltroAlisar extends Filtro
{
    private String nombre;

    /**
     * Constructor 
     */
    public FiltroAlisar(String nombre)
    {
        super(nombre);
    }

    /**
      * Recorre todos los pixeles de la imagen y los alisa.
      * @param imagen es la imagen sobre la que se aplica el filtro.
      */
    public void aplicar(ImagenOF imagen)
    {
        ImagenOF imagenOriginal = new ImagenOF(imagen);     // Para ir trabajando con 
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {                 
               Color colorAlisado = alisarColorMejor(x, y, imagenOriginal);
               imagen.setPixel(x, y, colorAlisado);
            }
        }
    }
    
//     private Color alisarColor(int x, int y, ImagenOF imagen)
//     {
//         int nu
//         
//         int posX;
//         int posY;                       // Cada una de las posiciones vecinas a y
//         
//         int alto = imagen.getHeight();      // Alto de la imagen
//         int ancho = imagen.getWidth();      // 
//         
//         for(int i = -1; i <= 1; i++){
//             posX = x + i;
//             if(posX >= 0 && posX < ancho){
//                 for(int j = -1; j <= 1; j++){
//                     porY = y + j;
//                     if(posY >= 0 && posY < alto){
//                         Color color = imagen.getPixel(posX, posY);
//                         sumaRojo += color.getRed();
//                         sumaVerde += color.getGreen();
//                         sumaAzul += color.getBlue();
//                         numVecinos++;
//                     }
//                 }
//             }
//         }
//     }
    
    /**
     * Devuelve un valor que es el promedio de todos los píxeles de alrededor.
     * @param x Es la coordenada x del pixel a alisar.
     * @param y Es la coordenada y del pixel a alisar.
     * @param imagen es la imagen sobre la que se aplica el filtro.
     */
    private Color alisarColorMejor(int x, int y, ImagenOF imagen)
    {
        int sumaRojo = 0;       // Suma de todo el rojo de alrededor de x, y
        int sumaVerde = 0;      // Suma de todo el verde de alrededor de x, y
        int sumaAzul = 0;       // Suma de todo el azul de alrededor de x, y
        
        ArrayList<Color> listaPixeles = imagen.pixelesAlrededor(x, y);
        
        for(Color color : listaPixeles){
            sumaRojo += color.getRed();
            sumaVerde += color.getGreen();
            sumaAzul += color.getBlue();
        }
        
        return new Color (sumaRojo / listaPixeles.size(), sumaVerde / listaPixeles.size(), sumaAzul / listaPixeles.size());
    }
}
