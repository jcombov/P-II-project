package centrosuniversitarios;

import java.util.TreeMap;

/**
 * Contiene todas las listas que utilizaremos durante la ejecuci�n del programa
 * 
 * @author Andr�s �lvarez L�pez
 * @author Jorge Combo Val
 */

public class Lista {

	/** Lista con todas las personas del sistema. */
	private static TreeMap<String, Persona> personas = new TreeMap<String, Persona>();

	/**
	 * M�todo que devuelve todas las personas del sistema.
	 * 
	 * @return personas Lista de personas.
	 */
	public TreeMap<String, Persona> getPersonas() {
		return Lista.personas;
	}

	/** Lista con todas las asignaturas del sistema. */

	private static TreeMap<String, Asignaturas> asignaturas = new TreeMap<String, Asignaturas>();

	/**
	 * M�todo que devuelve todas las asignaturas del sistema.
	 * 
	 * @return asignaturas Lista de asignaturas.
	 */
	public static TreeMap<String, Asignaturas> getAsignaturas() {
		return Lista.asignaturas;
	}

	/** Lista con todas las aulas del sistema. */

	private TreeMap<String, Aulas> aulas = new TreeMap<String, Aulas>();

	/**
	 * M�todo que devuelve todas las aulas del sistema.
	 * 
	 * @return aulas Lista de aulas.
	 */
	public TreeMap<String, Aulas> getAulas() {
		return this.aulas;
	}

	/** Lista con todos los profesores del sistema. */
	private static TreeMap<String, Profesores> profesores = new TreeMap<String, Profesores>();

	/**
	 * M�todo que devuelve todos los profesores del sistema.
	 * 
	 * @return profesores Lista de profesores.
	 */
	public TreeMap<String, Profesores> getProfesores() {
		return Lista.profesores;
	}

	/** Lista con todos los alumnos del sistema. */
	private static TreeMap<String, Alumnos> alumnos = new TreeMap<String, Alumnos>();

	/**
	 * M�todo que devuelve todos los alumnos del sistema.
	 * 
	 * @return alumnos Lista de alumnos.
	 */
	public TreeMap<String, Alumnos> getAlumnos() {
		return Lista.alumnos;
	}
}
