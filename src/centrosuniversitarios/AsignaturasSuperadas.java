package centrosuniversitarios;

/**
 * Clase que tiene que ver solo con los alumnos. Cada alumno puede tener una
 * serie de asignaturas superadas que se modelan aquí
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class AsignaturasSuperadas {

	/** siglas de la asignatura. */
	private String siglas;
	/** curso en el que se aprobó la asignatura. */
	private String cursoacademico;
	/** nota con la que se aprobó la asignatura. */
	private float nota;

	/**
	 * Constructor que genera una asignatura.
	 * 
	 * @param siglas
	 *            Siglas de la asignatura.
	 * @param cursoacademico
	 *            Curso academico en el que se aprobó la asignatura.
	 * @param nota
	 *            Nota obtenida en la asignatura.
	 */
	public AsignaturasSuperadas(String siglas, String cursoacademico, float nota) {

		this.siglas = siglas;
		this.cursoacademico = cursoacademico;
		this.nota = nota;

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
	 * Devuelve el curso en el que se superó la asignatura
	 * 
	 * @return cursoacademico
	 */
	public String getcursoacademico() {
		return this.cursoacademico;
	}

	/**
	 * Devuelve la nota obtenida en la asignatura
	 * 
	 * @return nota
	 */
	public float getnota() {
		return this.nota;
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
	 * Establecemos el curso en el que se aprobó la asignatura asignaturas
	 * 
	 * @param cursoacademico
	 *            Curso en el que se superó la asignatura.
	 */
	public void setcurso(String cursoacademico) {
		this.cursoacademico = cursoacademico;
	}

	/**
	 * Establecemos la nota con la que se superó la asignatura
	 * 
	 * @param nota
	 *            Nota de la asignatura.
	 */
	public void setnota(float nota) {
		this.nota = nota;
	}

}
