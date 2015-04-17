
/**
 * Descripción de la clase.
 * 
 * @author Inés Castel
 * @version 30/03/2015
 */
public class DetectorBordes
{
    
    private String nombre;

    /**
     * Constructor 
     */
    public DetectorBordes(String nombre)
    {
        super(nombre);
    }

    /**
     * Recorre todos los pixeles de la imagen y los va cambiando por la diferencia
     * @param imagen Es la imagen a la que se aplica el filtro.
     */
    public void aplicar(ImagenOF imagen)
    {
        ImagenOF imagenOriginal = new ImagenOF(imagen);     // Para ir trabajando
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; y++){
            for(int x = 0; x < ancho; x++){
                Color colorNuevo = detectorDeBordes(x, y, imagenOriginal);
                imagen.setPixel(x, y, colorNuevo);
            }
        }
        // Queda mejor si además se invierte
        new FiltroInvertir("invertir").aplicar(imagen);
    }
    
    /**
     * Devuelve un valor que es la diferencia entre el mayor y el menor de todos los pixeles de
     * alrededor.
     * @param x Es la coordenada x del pixel a estudiar.
     * @param y Es la coordenada y del pixel a estudiar.
     */
    private Color detectorDeBordes(int x, int y, ImagenOF imagen)
    {
        int maxRojo = 0, minRojo = 255;
        int maxVerde = 0, minVerde = 255;
        int maxAzul = 0, minAzul = 255;
        ArrayList<>
        
        for(){
        int rojo = color.getRed();
        if(rojo > maxRojo){
            maxRojo = rojo;
        }
        if(rojo < minRojo){
            minRojo = rojo;
        }
        
        int verde = color.getGreen();
        if(verde > maxVerde){
            maxVerde = verde;
        }
        if(verde < minVerde){
            minVerde = verde;
        }
        
        int azul = color.getBlue();
        if(azul > maxAzul){
            maxAzul = azul;
        }
        if(azul < minAzul){
            minAzul = azul;
        }
    }
        return new Color(maxRojo - minRojo, maxVerde - minVerde, maxAzul - minAzul);
    }
}
