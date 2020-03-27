package centrosuniversitarios;

/**
 * Clase que gestiona las asignaturas que se imparten o se cursan en relación a
 * si la persona es profesor o alumno.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Docencia {
	/** Tipo de docencia, impartida o recibida */
	char docencia; // Puede ser tipo I "Impartida" o tipo R "Recibida"
	/** Siglas de la asignatura */
	String siglas;
	/** Tipo de grupo de la asignatura */
	char tipogrupo; // Puede ser A o B
	/** ID del grupo */
	int IDGrupo;

	/**
	 * Constructor que establece la docencia, impartida o recibida, de un profesor o
	 * alumno.
	 * 
	 * @param docencia
	 *            Tipo de docencia.
	 * @param siglas
	 *            Siglas de la asignatura.
	 * @param tipogrupo
	 *            Tipo de grupo de la asignatura.
	 * @param IDGrupo
	 *            Id del grupo de la asignatura
	 */

	public Docencia(char docencia, String siglas, char tipogrupo, int IDGrupo) {
		this.docencia = docencia;
		this.siglas = siglas;
		this.tipogrupo = tipogrupo;
		this.IDGrupo = IDGrupo;

	}

	// GETTERS
	/**
	 * Devuelve las siglas de la asignatura
	 * 
	 * @return siglas
	 */
	public String getsiglas() {
		return this.siglas;
	}

	/**
	 * Devuelve el tipo de docencia de la asigntura
	 * 
	 * @return docencia
	 */
	public char getdocencia() {
		return this.docencia;
	}

	/**
	 * Devuelve el tipo del grupo de la asignatura
	 * 
	 * @return tipogrupo
	 */
	public char gettipogrupo() {
		return this.tipogrupo;
	}

	/**
	 * Devuelve el ID del grupo de la asignatura
	 * 
	 * @return IDGrupo
	 */
	public float getIDGrupo() {
		return this.IDGrupo;
	}

	// SETTERS

	/**
	 * Establecemos las siglas de las asignaturas
	 * 
	 * @param siglas
	 *            Siglas de la asignatura.
	 */
	public void setsiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * Establecemos la docencia de la asignatura
	 * 
	 * @param docencia
	 *            Tipo docencia de la asignatura.
	 */
	public void setdocencia(char docencia) {
		this.docencia = docencia;
	}

	/**
	 * Establecemos el tipo de grupo de la docencia
	 * 
	 * @param tipogrupo
	 *            Tipo del grupo de la asignatura.
	 */

	public void settipogrupo(char tipogrupo) {
		this.tipogrupo = tipogrupo;
	}

	/**
	 * Establecemos el ID del grupo de la asignatura
	 * 
	 * @param IDGrupo
	 *            Id del grupo de la asignatura.
	 */
	public void setIDGrupo(int IDGrupo) {
		this.IDGrupo = IDGrupo;
	}

}
