package centrosuniversitarios;

/**
 * Clase que gestiona las asignaturas que puede impartir un profesor, tanto las
 * horas como los grupos
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class POD {

	/** siglas de la asignatura */
	String siglas;
	/** tipo del grupo de asignatura */
	char tipogrupo;
	/** numero de grupos */
	float nGrupos;

	/**
	 * Constructor que genera un alumno pasandole los datos pertinentes
	 * 
	 * @param siglas
	 *            Siglas de la asignatura.
	 * @param tipogrupo
	 *            Grupo de teoría o de práctica.
	 * @param nGrupos
	 *            Número de grupos.
	 */
	public POD(String siglas, char tipogrupo, float nGrupos) {
		this.siglas = siglas;
		this.tipogrupo = tipogrupo;
		this.nGrupos = nGrupos;

	}

	// GETTERS

	/**
	 * Obtenemos las siglas de la asignatura.
	 * 
	 * @return siglas Siglas de la asignatura.
	 */

	public String getsiglas() {
		return this.siglas;
	}

	/**
	 * Obtenemos el numero de grupos.
	 * 
	 * @return nGrupos numero de grupos.
	 */

	public float getnGrupos() {
		return this.nGrupos;
	}

	/**
	 * Obtenemos tipo de grupo.
	 * 
	 * @return tipogrupo Grupo A o B.
	 */

	public char gettipogrupo() {
		return this.tipogrupo;
	}
	// SETTERS

	/**
	 * Establecemos las siglas de la asignatura
	 * 
	 * @param siglas
	 *            Siglas de la asignatura.
	 */

	public void setsiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * Establecemos el número de grupos
	 * 
	 * @param nGrupos
	 *            número de grupos de la asignatura.
	 */
	public void setnGrupos(float nGrupos) {
		this.nGrupos = nGrupos;
	}

	/**
	 * Establecemos el tipo de grupo
	 * 
	 * @param tipogrupo
	 *            Grupo A o B.
	 */
	public void settipogrupo(char tipogrupo) {
		this.tipogrupo = tipogrupo;
	}
}
