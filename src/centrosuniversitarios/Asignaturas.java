package centrosuniversitarios;

import java.util.TreeMap;

/**
 * Contiene todas las asignaturas del centro y su datos. Quien es su
 * coordinador, cuales son sus grupos, donde se imparten...Es decir, datos sobre
 * la asignatura y su docencia.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Asignaturas {
	/** nombre de la asignatura. */
	String nombre;
	/** siglas de la asignatura. */
	String siglas;
	/** curso de la asignatura. */
	String curso;
	/** dni del coordinador de la asignatura. */
	String coord_dni;
	/** prerrequisitors para cursar la asignatura. */
	String[] prerequisitos;
	/** cuatrimestre en el que se imparte la asignatura. */
	int cuatrimestre;
	/** grupos teóricos de la asignatura. */
	private TreeMap<Integer, Grupo> listaGruposTeoricos = new TreeMap<Integer, Grupo>();
	/** grupos prácticos de la asignatura. */
	private TreeMap<Integer, Grupo> listaGruposPracticos = new TreeMap<Integer, Grupo>();

	// Constructores
	/**
	 * Constructor que genera una asignatura.
	 * 
	 * @param nombre
	 *            Nombre de la asignatura.
	 * @param siglas
	 *            Siglas de la asignatura.
	 * @param curso
	 *            Curso de la asignatura.
	 * @param coord_dni
	 *            DNI del coordinador de la asignatura.
	 * @param prerrequisitos
	 *            Prerrequisitos para cursar la asignatura.
	 * @param cuatrimestre
	 *            Cuatrimestre en la que se imparte la asignatura
	 */
	public Asignaturas(String nombre, String siglas, String curso, String coord_dni, String[] prerrequisitos,
			int cuatrimestre) {

		this.nombre = nombre;
		this.siglas = siglas;
		this.curso = curso;
		this.coord_dni = coord_dni;
		this.prerequisitos = prerrequisitos;
		this.cuatrimestre = cuatrimestre;

		// System.out.println(getsiglas()+ " " +getnombre()+ " " +getcurso()+"
		// "+getcoord_dni());

	}

	// Getters
	/**
	 * Devuelve las siglas de la asignatura
	 * 
	 * @return siglas
	 */
	public String getsiglas() {
		return this.siglas;
	}

	/**
	 * Devuelve el nombre de la asignatura
	 * 
	 * @return nombre
	 */
	public String getnombre() {
		return this.nombre;
	}

	/**
	 * Devuelve el curso de la asignatura
	 * 
	 * @return curso
	 */
	public String getcurso() {
		return this.curso;
	}

	/**
	 * Devuelve el dni del cordinador de la asignatura
	 * 
	 * @return coord_dni
	 */
	public String getcoord_dni() {
		return this.coord_dni;
	}

	/**
	 * Devuelve el cuatrimestre en el que se imparte la asignatura
	 * 
	 * @return cuatrimestre
	 */
	public int getcuatrimestre() {
		return this.cuatrimestre;
	}

	/**
	 * Devuelve los prerrequisitos de la asignatura
	 * 
	 * @return prerrequisitos
	 */
	public String[] getprerequisitos() {
		return this.prerequisitos;
	}

	/**
	 * Devuelve los grupos teoricos de la asignatura
	 * 
	 * @return lista grupos teoricos
	 */
	public TreeMap<Integer, Grupo> getListaGruposTeoricos() {
		return this.listaGruposTeoricos;
	}

	/**
	 * Devuelve los grupos practicos de la asignatura
	 * 
	 * @return grupos practicos
	 */
	public TreeMap<Integer, Grupo> getListaGruposPracticos() {
		return this.listaGruposPracticos;
	}

	// Setters
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
	 * Establecemos el nombre de las asignaturas
	 * 
	 * @param nombre
	 *            Nombre de la asignatura.
	 */
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Establecemos el curso en el que se imparten las asignaturas
	 * 
	 * @param curso
	 *            Curso de la asignatura.
	 */
	public void setcurso(String curso) {
		this.curso = curso;
	}

	/**
	 * Establecemos el DNI del coordinador de la asignatura
	 * 
	 * @param coord_dni
	 *            Coordinador de la asignatura.
	 */
	public void setcoord_dni(String coord_dni) {
		this.coord_dni = coord_dni;
	}

	/**
	 * Establecemos el cuatrimestre de la asignatura
	 * 
	 * @param cuatrimestre
	 *            Cuatrimestre de la asignatura.
	 */
	public void setcuatrimestre(int cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	/**
	 * Establecemos los prerrequisitos de las asignaturas
	 * 
	 * @param prerequisitos
	 *            Prerrequisitos de la asignatura.
	 */
	public void setprerequisitos(String[] prerequisitos) {
		this.prerequisitos = prerequisitos;
	}

	/**
	 * Establecemos los grupos teóricos de la asignatura
	 * 
	 * @param listaGruposTeoricos
	 *            de la asignatura.
	 */
	public void setListaGruposTeoricos(TreeMap<Integer, Grupo> listaGruposTeoricos) {
		this.listaGruposTeoricos = listaGruposTeoricos;
	}

	/**
	 * Establecemos los grupos practicos de la asignatura
	 * 
	 * @param listaGruposPracticos
	 *            practicos de la asignatura.
	 */
	public void setListaGruposPracticos(TreeMap<Integer, Grupo> listaGruposPracticos) {
		this.listaGruposPracticos = listaGruposPracticos;
	}

}
