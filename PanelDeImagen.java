import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

/**
 * Un PanelImagen es una componente Swing que puede mostrar un ImagenOF
 * Se construye como una subclase de JComponent con una funcionalidad añadida
 * de colocar una imagenOF para ser mostrada en ella
 * 
 * @author  Apah (traducción de la clase ImagePanel)
 * @version 10.04.2012
 */
public class PanelDeImagen extends JComponent
{
    private int anchura, altura;     // Anchura y altura actuales del panel
    private ImagenOF panelImagen;     // Un buffer interno de la imagen que se usa para pintar. Después se copa a la pantalla

    /**
     * Crea un nuevo y vacío PanelImagen
     */
    public PanelDeImagen()
    {
        anchura = 360;    // Tamaño inicial del panel
        altura = 240;
        panelImagen = null;
    }

    /**
     * Pone la imagen que el panel debe mostrar
     * 
     * @param imagen La imagen a ser mostrada
     */
    public void setImagen(ImagenOF imagen)
    {
        if(imagen != null) {
            anchura = imagen.getWidth();
            altura = imagen.getHeight();
            panelImagen = imagen;
            repaint();
        }
    }
    
    /**
     * Limpia la imagen del panel
     */
    public void limpiarImagen()
    {
        Graphics imagenGraphica = panelImagen.getGraphics();
        imagenGraphica.setColor(Color.LIGHT_GRAY);
        imagenGraphica.fillRect(0, 0, anchura, altura);
        repaint();
    }
    
    // ---------------- Los siguientes métodos son redefiniciones de métodos heredados de la superclase
    
    /**
     * Indica al gestor de diseño cómo son las dimensiones
     * Este método es llamado por elgestor para disponer las componentes
     * 
     * @return Las dimensiones preferidas para esta compoenente
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(anchura, altura);
    }
    
    /**
     * Esta componente necesita ser mostrada nuevamente. Copia la imgen interna a la pantalla
     * (Este método es llamado cuando Swinfg pinta la pantalla cada vez que se quier mostra esta componente
     * 
     * @param g Es el contexto gráfico que puede ser usado para dibujar sobre esta componente
     */
    public void paintComponent(Graphics g)
    {
        Dimension tamaño = getSize();
        g.clearRect(0, 0, tamaño.width, tamaño.height);
        if(panelImagen != null) {
            g.drawImage(panelImagen, 0, 0, null);
        }
    }
}
