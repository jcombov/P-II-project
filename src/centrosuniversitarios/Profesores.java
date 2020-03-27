package centrosuniversitarios;

/** Extiende a persona. Se encarga de almacenar los datos de todos los profesores del sistema y permitir su manejo
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val*/

import java.util.TreeMap;

public class Profesores extends Persona {
	/** departamento al que pertenece el profesor. */
	private String departamento;
	/** categoria del profesor, titular o asociado */
	private String categoria;
	/** asignaturas que coordina el profesor titular. */
	private TreeMap<Integer, POD> listaCoordinacion = new TreeMap<Integer, POD>();
	/** grupos a los que el profesor da clase. */
	private TreeMap<Integer, Docencia> listaGruposAsignados = new TreeMap<Integer, Docencia>();

	// Hereda los constructores, getters y setters de persona

	/**
	 * Constructor que genera un profesor pasandole los datos pertinentes
	 * 
	 * @param nombreyapellidos
	 *            Nombre y apellidos del profesor.
	 * @param dni
	 *            DNI del profesor.
	 * @param categoria
	 *            categoria del profesor.
	 * @param departamento
	 *            Departamento al que pertenece el profesor.
	 * @param fechadenacimiento
	 *            Fecha de nacimiento del profesor.
	 */
	public Profesores(String nombreyapellidos, String dni, String fechadenacimiento, String categoria,
			String departamento) {
		super(nombreyapellidos, dni, fechadenacimiento);
		this.categoria = categoria;
		this.departamento = departamento;
	}

	// GETTERS
	/**
	 * Obtenemos las asignaturas que coordina el profesor.
	 * 
	 * @return asignaturas que coordina el profesor titular.
	 */
	public TreeMap<Integer, POD> getListaCoordinacion() {
		return this.listaCoordinacion;
	}

	/**
	 * Obtenemos los grupos del profesor.
	 * 
	 * @return grupos a los que el profesor da clase.
	 */
	public TreeMap<Integer, Docencia> getListaGruposAsignados() {
		return this.listaGruposAsignados;
	}

	/**
	 * Obtenemos el departamento al que pertenece el profesor.
	 * 
	 * @return departamento al que pertenece el profesor.
	 */
	public String getDepartamento() {
		return this.departamento;
	}

	/**
	 * Obtenemos la categoria del profesor.
	 * 
	 * @return categoria del profesor, titular o asociado.
	 */
	public String getCategoria() {
		return this.categoria;
	}

	// SETTERS
	/**
	 * Establecemos el departamento del profesor
	 * 
	 * @param departamento
	 *            al que pertenece el profesor.
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * Establecemos la categoria del profesor
	 * 
	 * @param categoria
	 *            del profesor, titular o asociado
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Establecemos la lista de grupos asignados al profesor
	 * 
	 * @param listaGruposAsignados
	 *            a los que el profesor da clase
	 */
	public void setListaGruposAsignados(TreeMap<Integer, Docencia> listaGruposAsignados) {
		this.listaGruposAsignados = listaGruposAsignados;
	}

	/**
	 * Establecemos las asignaturas que coordina el profesor
	 * 
	 * @param listaCoordinacion
	 *            que coordina el profesor titular.
	 */
	public void setListaCoordinacion(TreeMap<Integer, POD> listaCoordinacion) {
		this.listaCoordinacion = listaCoordinacion;
	}

}