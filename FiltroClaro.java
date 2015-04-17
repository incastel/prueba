
/**
 * Descripción de la clase.
 * 
 * @author Inés Castel
 * @version 26/03/2015
 */
public class FiltroClaro extends Filtro
{
    /**
     * Constructor 
     */
    public FiltroClaro(String nombre)
    {
        super(nombre);
    }
    
    /**
     * Recorre todos los píxeles de la imágen y los aclara un poco.
     */
    public void aplicar(ImagenOF imagen)
    {
       int alto = imagen.getHeight();
       int ancho = imagen.getWidth();
       for (int y = 0; y < alto; y++) {
           for (int x = 0; x < ancho; x++) {
               imagen.setPixel(x, y, imagen.getPixel(x, y).brighter());
           }
       }
    }
}
