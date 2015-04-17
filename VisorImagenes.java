import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * Visor de Imagenes es la clase principal de la aplicación Visor de Imágenes.
 * Construye y muestra la IGU e inicializa el resto de componenetes.
 * 
 * @author Apah (es una traducción de ImageViewer04 
 * @version 10.04.2012
 */
public class VisorImagenes
{
    // Campos estáticos
    private static final String VERSION = "Version 1.0";
    private static JFileChooser selectorArchivos = new JFileChooser(System.getProperty("user.dir"));

    private JFrame ventana;
    private PanelDeImagen panelImagen;
    private JLabel etiquetaNombreArchivo;
    private JLabel etiquetaEstado;
    private ImagenOF imagenActual;
    
    private List<Filtro> listaDeFiltros;
    
    /**
     * Crea una ventana que sirve de visor de imágenes
     */
    public VisorImagenes()
    {
        imagenActual = null;
        crearListaFiltros();
        construirVentana();
    }
    
    /**
     * Crea los filtros a usar con la imagen.
     */
    public void crearListaFiltros()
    {
        listaDeFiltros = new ArrayList<Filtro>();
        listaDeFiltros.add(new FiltroOscuro("Oscuro"));
        listaDeFiltros.add(new FiltroClaro("Claro"));
        listaDeFiltros.add(new Umbral("Umbral"));
        listaDeFiltros.add(new FiltroGrises("Escala de grises"));
        listaDeFiltros.add(new FiltroEspejo("Filtro espejo"));
        listaDeFiltros.add(new FiltroInvertir("Invertir"));
        listaDeFiltros.add(new FiltroAlisar("Alisar"));
        listaDeFiltros.add(new FiltroAlisar("Solarizar"));
    }


    // ---- implementación de las funciones del menú ----
    
    /**
     * Función Abrir. Abre un fichero de imagen
     */
    private void abrirArchivo()
    {
        int valorDevuelto = selectorArchivos.showOpenDialog(ventana);

        if(valorDevuelto != JFileChooser.APPROVE_OPTION) {
            return;  // Se cancela el método
        }
        
        File ficheroSeleccionado = selectorArchivos.getSelectedFile();
        imagenActual =  AdministradorDeArchivos.cargarImagen(ficheroSeleccionado);
        panelImagen.setImagen(imagenActual);
        mostrarNombreArchivo (ficheroSeleccionado.getPath());
        mostrarEstado ("Archivo cargado");
        ventana.pack();
    }
    
    /**
     * Función Salir: Sale de la aplicación.
     */
    private void salir()
    {
        System.exit(0);
    }
    
    // ---- Se construye la ventana y todos sus componentes ----
    
    /**
     * Create la ventana y sus componentes.
     */
    private void construirVentana()
    {
        ventana = new JFrame("Visor de Imágenes");
        construirBarraMenus(ventana);
        
        Container contenidoVentana = ventana.getContentPane();
        contenidoVentana.setLayout (new BorderLayout());
        
        etiquetaNombreArchivo = new JLabel ("Nombre de archivo ...");
        contenidoVentana.add (etiquetaNombreArchivo, BorderLayout.NORTH);
        
        panelImagen = new PanelDeImagen();
        contenidoVentana.add(panelImagen, BorderLayout.CENTER);

        etiquetaEstado = new JLabel ("Versión 1.0");
        contenidoVentana.add (etiquetaEstado, BorderLayout.SOUTH);
        
        // La construcción está hecha. Se organizan los componentes y se muestran
        ventana.pack();
        ventana.setVisible(true);
    }
       
    /**
     * Crea la barra de menús de la ventana principal
     * @param ventana   La ventana donde se mostrará la barra de menús
     */
    private void construirBarraMenus(JFrame ventana)
    {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar barraDeMenus = new JMenuBar();
        ventana.setJMenuBar(barraDeMenus);
        
        // ------------------------------ Crea el menú Archivo
        
        JMenu menuArchivo = new JMenu("Archivo");
        barraDeMenus.add(menuArchivo);
        
        JMenuItem itemAbrirArchivo = new JMenuItem("Abrir");
        itemAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, SHORTCUT_MASK)); // Para abrir con Ctrl + A
        itemAbrirArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { abrirArchivo(); }
        });
        menuArchivo.add(itemAbrirArchivo);

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK)); // Para salirr con Ctrl + S
        itemSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { salir(); }
        });
        menuArchivo.add(itemSalir);

        //-------------------------------- Crea el menú Filtro
        
        JMenu menuFiltro = new JMenu ("Filtro");
        barraDeMenus.add (menuFiltro);
        
        for(final Filtro filtro : listaDeFiltros){
            JMenuItem itemFiltro = new JMenuItem (filtro.getNombre());
            itemFiltro.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evento){
                    aplicarFiltro(filtro);
                }
            });
            menuFiltro.add(itemFiltro);
        }
        
        //----------------------------------- Crea el menú Ayuda
        
        JMenu menuAyuda = new JMenu ("Ayuda");
        barraDeMenus.add (menuAyuda);
        
        JMenuItem itemAcercaDe = new JMenuItem ("Acerca del Visor de Imágenes");
        itemAcercaDe.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent evento) {
                mostrarAcercaDe ();
            }
        });
        menuAyuda.add (itemAcercaDe);
        
    }
    
    /**
     * Aplica el filtro.
     */
    private void aplicarFiltro(Filtro filtro)
    {
        if (imagenActual != null) {
            filtro.aplicar(imagenActual);
            ventana.repaint();
            mostrarEstado ("Filtro aplicado " + filtro.getNombre());
        }
        else {
            mostrarEstado ("No hay ninguna imagen cargada");
        }
    }
    
    /**
     * Muestra el estado en la etiqueta correspondiente
     */
    private void mostrarEstado (String estado)
    {
        etiquetaEstado.setText (estado);
    }
    
    /**
     * Muestra el nombre del archivo abierto en la etiqueta correspondiente
     */
    private void mostrarNombreArchivo (String nombreArchivo)
    {
        etiquetaNombreArchivo.setText (nombreArchivo);
    }
    
    private void mostrarAcercaDe()
    {
        JOptionPane.showMessageDialog(ventana,"VisorImagenes\n" + VERSION,"Acerca de VisorImagenes",JOptionPane.INFORMATION_MESSAGE);
    }
    
}
