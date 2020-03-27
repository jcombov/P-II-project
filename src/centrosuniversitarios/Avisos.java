package centrosuniversitarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Genera los posibles avisos durante la ejecución del programa.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Avisos {

	/**
	 * Recibe un mensaje de comando incorrecto.
	 * 
	 * @param mensaje
	 *            Mensaje del aviso.
	 */
	public Avisos(String mensaje) {
		emitirAviso("", mensaje);
	}

	/**
	 * Recibe un mensaje de error.
	 * 
	 * @param mensaje
	 *            Mensaje del aviso.
	 * @param comando
	 *            Comando correspondiente al error
	 */
	public Avisos(String comando, String mensaje) {
		emitirAviso(comando, mensaje);
	}

	/**
	 * Funcion que recibe los parámetros de los anteriores constructores y genera el
	 * aviso correspondiente.
	 * 
	 * @param mensaje
	 *            Mensaje del aviso.
	 * @param comando
	 *            Comando correspondiente al error
	 */
	private void emitirAviso(String comando, String mensaje) {

		String aviso;
		if (comando.length() == 0) {
			aviso = "Comando incorrecto: " + mensaje;
		} else {
			aviso = comando + " -- " + mensaje;

		}
		escribeFichero(aviso);
	}

	/**
	 * Funcion que recibe los parámetros de los anteriores constructores y escribe
	 * el correspondiente mensaje en "avisos.txt".
	 * 
	 * @param aviso
	 *            Aviso generado anteriormente
	 */

	public static void escribeFichero(String aviso) {

		File fichero = new File("avisos.txt");

		if (fichero.exists()) {
			try {
				FileWriter punteroEscritura = new FileWriter("avisos.txt", true);
				punteroEscritura.write(System.getProperty("line.separator") + aviso);
				punteroEscritura.close();
			} catch (IOException e) {

			}
		} else {
			try {

				FileWriter punteroEscritura = new FileWriter(fichero);
				punteroEscritura.write(aviso);
				punteroEscritura.close();
			} catch (IOException e) {

			}
		}

	}

}
