
/**
 * Descripción de la clase
 * 
 * @author Inés Castel
 * @version 26/03/2015
 */
public abstract class Filtro
{
    private String nombre;

    /**
     * Crea un nuevo filtro con un nombre determinado.
     * @param nombre El nombre del filtro.
     * 
     * */
    public Filtro(String nombre)
    {
       this.nombre = nombre;
    }
    
    /**
     * Devuelve el nombre del filtro.
     * @return nombre El nombre del filtro.
     */
    public String getNombre()
    {
        return nombre;
    }
    
    public abstract void aplicar(ImagenOF imagen);
    
}
