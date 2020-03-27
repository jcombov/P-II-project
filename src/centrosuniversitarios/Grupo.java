package centrosuniversitarios;

/**
 * Dan forma a los grupos teóricos y prácticos que se imparten en el centro.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Grupo {
	/** ID del grupo. */
	private int IDGrupo;
	/** tipo del grupo. */
	private char grupoab;
	/** dia en el que se imparte la clase del grupo. */
	private char dia;
	/** hora a la que se imparte la clase del grupo. */
	private int hora;
	/** duracion de la clase */
	private int duraciongrupo;
	/** Aula en la que se imparte el grupo. */
	private String aula;

	/**
	 * Constructor que genera un profesor pasandole los datos pertinentes
	 * 
	 * @param grupoab
	 *            tipo del grupo, teórico o práctico.
	 * @param IDGrupo
	 *            ID del grupo.
	 * @param dia
	 *            dia en el que se imparte la clase del grupo.
	 * @param hora
	 *            hora a la que se imparte la clase del grupo.
	 * @param duraciongrupo
	 *            duracion de la clase.
	 * @param aula
	 *            en la que se imparte la clase
	 */

	public Grupo(int duraciongrupo, char grupoab, int IDGrupo, char dia, int hora, String aula) {
		this.grupoab = grupoab;
		this.IDGrupo = IDGrupo;
		this.dia = dia;
		this.hora = hora;
		this.duraciongrupo = duraciongrupo;
		this.aula = aula;
	}

	public Grupo() {
		// GETTERS
	}

	/**
	 * Obtenemos el tipo de grupo.
	 * 
	 * @return grupoab
	 */
	public char getGrupoAB() {
		return this.grupoab;
	}

	/**
	 * el ID del grupo.
	 * 
	 * @return IDGrupo
	 */
	public int getIDGrupo() {
		return this.IDGrupo;
	}

	/**
	 * Obtenemos el aula en la que se imparte la clase.
	 * 
	 * @return aula
	 */
	public String getAula() {
		return this.aula;
	}

	/**
	 * Obtenemos los dias en los que se imparte la clase.
	 * 
	 * @return dia
	 */
	public char getDia() {
		return this.dia;
	}

	/**
	 * Obtenemos las horas en las que se imparte la clase
	 * 
	 * @return hora
	 */
	public int getHora() {
		return this.hora;
	}

	/**
	 * Obtenemos la duracion de la clase.
	 * 
	 * @return duraciongrupo
	 */
	public int getDuracionGrupo() {
		return this.duraciongrupo;
	}

	// SETTERS
	/**
	 * Modificar el grupo del alumno
	 * 
	 * @param grupoab
	 *            Tipo del grupo.
	 */
	public void setGrupoAB(char grupoab) {
		this.grupoab = grupoab;
	}

	/**
	 * Modificar el ID del grupo
	 * 
	 * @param IDGrupo
	 *            ID del grupo.
	 */
	public void setIDGrupo(int IDGrupo) {
		this.IDGrupo = IDGrupo;
	}

	/**
	 * Modificar la duracion de la clase
	 * 
	 * @param duraciongrupo
	 *            Duracion de la clase.
	 */
	public void setDuracionGrupo(int duraciongrupo) {
		this.duraciongrupo = duraciongrupo;
	}

	/**
	 * Modificar el dia en el que se imparte la clase.
	 * 
	 * @param dia.
	 *            Día en el que se imparte la clase.
	 */
	public void setDia(char dia) {
		this.dia = dia;
	}

	/**
	 * Modificar la hora a la que empieza la clase
	 * 
	 * @param hora.
	 *            hora a la que empieza la clase.
	 */
	public void setHora(int hora) {
		this.hora = hora;
	}

	/**
	 * Modificar el el aula en la que se imparte la clase
	 * 
	 * @param aula.
	 *            Aula de la clase.
	 */
	public void setAula(String aula) {
		this.aula = aula;
	}

}
