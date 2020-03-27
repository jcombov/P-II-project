package centrosuniversitarios;

import java.util.TreeMap;

/**
 * Forma parte de persona, son aquellos que reciben docencia. Se encarga de
 * almacenar todos los alumnos del sistema y permitir sus datos.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Alumnos extends Persona {
	/** Email alumno. Es el email del alumno */
	private String email;
	/** Fecha Ingreso. Es la fecha en la que el alumno ingresó al centro */
	private String FechaIngreso;
	/**
	 * Asignaturas superadas. Contiene las asignaturas superadas por el alumno, se
	 * indica la asignatura, la nota y el año en el que la supero
	 */
	private TreeMap<String, AsignaturasSuperadas> listaAsignaturasSuperadas = new TreeMap<String, AsignaturasSuperadas>();
	/**
	 * Asignaturas matriculadas. Asignaturas en las que está el alumno matriculado
	 */
	private TreeMap<Integer, Docencia> listaDocenciaRecibida = new TreeMap<Integer, Docencia>();

	/**
	 * Constructor que genera un alumno pasandole los datos pertinentes
	 * 
	 * @param nombreyapellidos
	 *            Nombre y apellidos del alumno.
	 * @param fechadenacimiento
	 *            Fecha de nacimiento del alumno.
	 * @param dni
	 *            DNI del alumno.
	 * @param email
	 *            email del alumno
	 * @param fechadeingreso
	 *            Fecha de ingreso.
	 */

	public Alumnos(String nombreyapellidos, String dni, String fechadenacimiento, String email, String fechadeingreso) {
		super(nombreyapellidos, dni, fechadenacimiento);
		this.email = email;
		this.FechaIngreso = fechadeingreso;

		// TODO Auto-generated constructor stub
	}

	// GETTERS
	/**
	 * Devuelve las asignaturas superadas
	 * 
	 * @return Asignaturas superadas
	 */

	public TreeMap<String, AsignaturasSuperadas> getListaAsignaturasSuperadas() {
		return this.listaAsignaturasSuperadas;
	}

	/**
	 * Devuelve las asignaturas en las que está el alumno matriculado
	 * 
	 * @return Asignaturas matriculadas
	 */
	public TreeMap<Integer, Docencia> getlistaDocenciaRecibida() {
		return this.listaDocenciaRecibida;
	}

	/**
	 * Devuelve el email del alumno
	 * 
	 * @return email alumno
	 */
	public String email() {
		return this.email;
	}

	/**
	 * Devuelve la fecha de ingreso
	 * 
	 * @return FechaIngreso
	 */

	public String FechaIngreso() {
		return this.FechaIngreso;
	}

	// SETTERS

	/**
	 * Modificar el email de un alumno
	 * 
	 * @param email.
	 *            email del alumno.
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Modificar la fecha de ingreso de un alumno
	 * 
	 * @param FechaIngreso.
	 *            fecha de ingreso del alumno.
	 */
	public void setFechaIngreso(String FechaIngreso) {
		this.FechaIngreso = FechaIngreso;
	}

	/**
	 * Modificar las asignaturas matriculadas de un alumno
	 * 
	 * @param listaDocenciaRecibida.
	 *            asignaturas matriculadas.
	 */
	public void setlistaDocenciaRecibida(TreeMap<Integer, Docencia> listaDocenciaRecibida) {
		this.listaDocenciaRecibida = listaDocenciaRecibida;
	}

	/**
	 * Modificar las asignaturas superadas de un alumno
	 * 
	 * @param listaAsignaturasSuperadas.
	 *            asignaturas superadas.
	 */
	public void setlistaAsignaturasSuperadas(TreeMap<String, AsignaturasSuperadas> listaAsignaturasSuperadas) {
		this.listaAsignaturasSuperadas = listaAsignaturasSuperadas;
	}

}
