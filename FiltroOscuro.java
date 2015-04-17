
/**
 * Descripción de la clase.
 * 
 * @author Inés Castel
 * @version 26/03/2015
 */
public class FiltroOscuro extends Filtro
{
    /**
     * Constructor for objects of class FiltroOscuro
     */
    public FiltroOscuro(String nombre)
    {
        super(nombre);
    }
    
    /**
     * Recorre todos los píxeles de la imágen y los oscurece un poco.
     */
    public void aplicar(ImagenOF imagen)
    {
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                imagen.setPixel(x, y, imagen.getPixel(x, y).darker());
            }
        }
    }
}
