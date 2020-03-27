package centrosuniversitarios;

/**
 * Se encarga de invocar al programa y de pasarle los parámetros correctamente.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */
public class Proyecto10 {

	/**
	 * Método que invoca a las funciones y maneja los datos de la aplicación.
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
