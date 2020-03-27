package centrosuniversitarios;

/**
 * Se encarga de invocar al programa y de pasarle los par�metros correctamente.
 * 
 * @author Andr�s �lvarez L�pez
 * @author Jorge Combo Val
 */
public class Proyecto10 {

	/**
	 * M�todo que invoca a las funciones y maneja los datos de la aplicaci�n.
	 * 
	 * @param args
	 *            none
	 */
	public static void main(String[] args) {

		Lista lista = new Lista();
		LFicheros.leerPersonas(lista);
		LFicheros.leerAsignaturas(lista);
		LFicheros.leerAulas(lista);
		LFicheros.leerEjecucion(lista);
		LFicheros.escribirFich(lista);

	}

}
