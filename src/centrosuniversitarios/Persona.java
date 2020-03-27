package centrosuniversitarios;

/**
 * La clase Persona contiene los datos en común de alumnos y profesores y los
 * almacena.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Persona {
	/** Nombre y apellidos de la persona. */
	String nombreyapellidos;
	/** DNI de la persona. */
	String dni;
	/** Fecha de nacimiento de la persona. */
	String fechadenacimiento;

	/**
	 * Constructor que genera un alumno pasandole los datos pertinentes
	 * 
	 * @param nombreyapellidos
	 *            Nombre y apellidos del alumno.
	 * @param dni
	 *            DNI del alumno.
	 * @param fechadenacimiento
	 *            Fecha de nacimiento.
	 */

	// Constructores
	public Persona(String nombreyapellidos, String dni, String fechadenacimiento) {

		this.nombreyapellidos = nombreyapellidos;
		this.dni = dni;
		this.fechadenacimiento = fechadenacimiento;

		// System.out.println(getnombreyapellidos() +" "+ getdni() +"
		// "+getFechaDeNacimiento());
	}

	// Getters

	/**
	 * Obtenemos el nombre y el apellido de la persona.
	 * 
	 * @return nombreyapellidos de la persona.
	 */
	public String getnombreyapellidos() {
		return this.nombreyapellidos;
	}

	/**
	 * Obtenemos el DNI de la persona.
	 * 
	 * @return DNI de la persona.
	 */

	public String getdni() {
		return this.dni;
	}

	/**
	 * Obtenemos la fecha de nacimiento de la persona.
	 * 
	 * @return Fecha de nacimiento de la persona.
	 */
	public String getFechaDeNacimiento() {
		return this.fechadenacimiento;
	}

	// Setters

	/**
	 * Establecemos nombre y apellidos de la persona
	 * 
	 * @param nombreyapellidos
	 *            Nombre y apellidos de la persona.
	 */
	public void setnombreyapellidos(String nombreyapellidos) {
		this.nombreyapellidos = nombreyapellidos;
	}

	/**
	 * Establecemos DNI de la persona
	 * 
	 * @param dni
	 *            DNI de la persona.
	 */

	public void setdni(String dni) {
		this.dni = dni;
	}

	/**
	 * Establecemos fecha de nacimiento de la persona
	 * 
	 * @param fechadenacimiento
	 *            fecha de nacimiento de la persona.
	 */

	public void setFechaDeNacimiento(String fechadenacimiento) {
		this.fechadenacimiento = fechadenacimiento;
	}

}
