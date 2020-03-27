package centrosuniversitarios;

/**
 * Contiene las aulas del centro en las que se imparten las diferentes
 * asignaturas.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Aulas {
	/** siglas de la aula. */
	String siglas;
	/** tipo de asignaturas que se imparte en el aula. */
	String tipgrupo;
	/** capacidad de la aula. */
	String capacidad;

	/**
	 * Constructor que genera las aulas.
	 * 
	 * @param datosasig
	 *            datos de la asignatura
	 */

	public Aulas(String datosasig) {

		String[] datosSeparados = datosasig.split("\\&");
		this.siglas = datosSeparados[0].trim();
		this.tipgrupo = datosSeparados[1].trim();
		this.capacidad = datosSeparados[2].trim();

		// System.out.println(getsiglas()+ " " +gettipgrupo()+ " " +getcapacidad());

	}

	// Getters

	/**
	 * Devuelve las siglas de la aula
	 * 
	 * @return siglas
	 */
	public String getsiglas() {
		return this.siglas;
	}

	/**
	 * Devuelve el tipo de grupo que se imparte en el aula
	 * 
	 * @return aula
	 */
	public String gettipgrupo() {
		return this.tipgrupo;
	}

	/**
	 * Devuelve la capacidad de la aula
	 * 
	 * @return capacidad
	 */
	public String getcapacidad() {
		return this.capacidad;
	}

	// Setters

	/**
	 * Establecemos las siglas de la aula
	 * 
	 * @param siglas
	 *            Siglas de la asignatura.
	 */
	public void setsiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * Establecemos el id de los grupos que se imparten en la aula
	 * 
	 * @param tipgrupo
	 *            Tipo grupo de la aula.
	 */
	public void settipgrupo(String tipgrupo) {
		this.tipgrupo = tipgrupo;
	}

	/**
	 * Establecemos la capacidad de la aula
	 * 
	 * @param capacidad
	 *            capacidad de la aula.
	 */
	public void setcapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

}
