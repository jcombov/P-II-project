package centrosuniversitarios;

import java.io.FileWriter;
import java.util.Map.Entry;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

/**
 * Contiene todas las funciones que realiza el programa.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */

public class Funciones {

	/**
	 * inserta una persona en el sistema, dependiendo si es alumno o profesor, lo
	 * hará de distinta manera. Se guardará en la lista anteriormente generada de
	 * personas.
	 * 
	 * @param nya
	 *            nombre y apellidos de la persona
	 * @param dni
	 *            dni de la persona
	 * @param perfil
	 *            perfil del profesor
	 * @param fechaNac
	 *            fecha de nacimiento
	 * @param fechaIng
	 *            fecha de ingreso del alumno
	 * @param categoria
	 *            categoria a la que pertenece el profesor
	 * @param departamento
	 *            departamento al que pertenece el profesor
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void insertaPersona(String nya, String dni, String perfil, String fechaNac, String fechaIng,
			String categoria, String departamento, Lista lista_todo) {
		TreeMap<String, Persona> personas = lista_todo.getPersonas();
		TreeMap<String, Alumnos> alumnos = lista_todo.getAlumnos();
		TreeMap<String, Profesores> profesores = lista_todo.getProfesores();

		if (dni.length() != 9) { // Error en el dni
			new Avisos("IP", "Dni incorrecto");
			return;
		}

		String[] datosSeparados = fechaNac.trim().split("/");
		Calendar fecha1 = GregorianCalendar.getInstance();
		int anonac = Integer.parseInt(datosSeparados[2]);
		int dianac = Integer.parseInt(datosSeparados[0]);
		int mesnac = Integer.parseInt(datosSeparados[1]);
		fecha1.set(anonac, dianac, mesnac);

		int control = fechabien((GregorianCalendar) fecha1);// control de si las fechas fueron bien introducidas
		if (control == 1) {
			new Avisos("IP", "Fecha incorrecta");
			return;
		}

		if (anonac > 2017 || anonac < 1960) {
			new Avisos("IP", "Fecha incorrecta");
			return;
		}

		if (perfil.trim().equals("alumno")) {
			for (Entry<String, Alumnos> prueba : alumnos.entrySet()) {
				if (dni.trim().equals(prueba.getKey())) {
					new Avisos("IP", "Alumno ya existente");
					return;
				}
			}
			String[] datosSeparadoss = fechaIng.split("/");
			int anoing = Integer.parseInt(datosSeparadoss[2]); // Retoque Futuro
			if ((anoing - anonac) < 16 || (anoing - anonac) > 60) {
				new Avisos("IP", "Fecha  de  ingreso incorrecta");
				return;
			}
			personas.put(dni, new Persona(nya, dni, fechaNac));
			alumnos.put(dni, new Alumnos(nya, dni, fechaNac, " ", fechaIng));
		}

		else {
			for (Entry<String, Profesores> prueba : profesores.entrySet()) {
				if (dni.trim().equals(prueba.getKey())) {
					new Avisos("IP", "Profesor ya existente");
					return;
				}
			}
			personas.put(dni, new Persona(nya, dni, fechaNac));
			profesores.put(dni, new Profesores(nya, dni, fechaNac, categoria, departamento));

		}

	}

	/**
	 * Le asigna un grupo a una persona, profesor o alumno. El alumno ha de cumplir
	 * ciertos prerrequisitos y deberá estar matriculado en la asignatura. Al
	 * profesor se le podrá asignar un grupo que pertenezca a su pod, mientras que
	 * no supere las horas maximas de docencia.
	 * 
	 * @param dni
	 *            dni de la persona
	 * @param siglas
	 *            siglas de la asignatura a matricular
	 * @param perfil
	 *            perfil del profesor
	 * @param tipo
	 *            tipo del grupor
	 * @param grupo
	 *            id del grupo
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void asignaGrupo(String perfil, String dni, String siglas, String tipo, String grupo,
			Lista lista_todo) {
		TreeMap<String, Persona> personas = lista_todo.getPersonas();
		TreeMap<String, Alumnos> alumnos = lista_todo.getAlumnos();
		TreeMap<String, Profesores> profesores = lista_todo.getProfesores();
		TreeMap<String, Asignaturas> asignaturas = Lista.getAsignaturas();
		TreeMap<String, Aulas> aulas = lista_todo.getAulas();

		int error;
		char tipe = tipo.charAt(0);
		int id = Integer.parseInt(grupo);
		char diaux = 0;
		int horaaux = 0;
		int cuatriaux = 0;
		int gente = 0, ocup = 0;
		float idgrupaux = 0;
		char tipgrupaux = ' ';
		String cursoaux = "";
		String aulaux = "";
		String ocups = "";
		@SuppressWarnings("unused")
		String asigaux = "";
		int subocup;
		error = 1;
		for (Entry<String, Persona> prueba : personas.entrySet()) {
			if (dni.trim().equals(prueba.getKey())) {
				error = 0;
				break;
			}

		}
		if (error == 1) {
			new Avisos("AGRUPO", "Alumno/profesor inexistente");
			return;
		}

		if (perfil.trim().equals("alumno")) {

		} else {

		}
		error = 1;
		for (Entry<String, Asignaturas> prueba : asignaturas.entrySet()) {
			if (siglas.trim().equals(prueba.getKey())) {
				error = 0;
				break;
			}

		}
		if (error == 1) {
			new Avisos("AGRUPO", "Asignatura inexistente"); // Aviso Modificado el archivo ejecucion
			return;
		}

		if (tipe != 'A' && tipe != 'B') {
			new Avisos("AGRUPO", "Tipo de grupo incorrecto"); // Aviso
			return;
		}

		if (tipe == 'A')
			{subocup = 40;}
		else
			{subocup = 20;}

		error = 1;
		for (Entry<String, Asignaturas> prueba : asignaturas.entrySet()) {
			TreeMap<Integer, Grupo> gruposA = prueba.getValue().getListaGruposTeoricos();
			TreeMap<Integer, Grupo> gruposB = prueba.getValue().getListaGruposPracticos();

			if (tipe == 'A') {
				for (Entry<Integer, Grupo> prueba2 : gruposA.entrySet()) {
					if (id == prueba2.getKey() && siglas.trim().equals(prueba.getKey())) {
						error = 0;
						break;
					}
				}
			} else {
				for (Entry<Integer, Grupo> prueba2 : gruposB.entrySet()) {
					if (id == prueba2.getKey() && siglas.trim().equals(prueba.getKey())) {
						error = 0;
						break;

					}
				}
			}
		}
		if (error == 1) {
			new Avisos("AGRUPO", "Grupo inexistente"); // Aviso Modificado el archivo ejecucion
			return;
		}

		for (Entry<String, Asignaturas> prueba : asignaturas.entrySet()) {
			TreeMap<Integer, Grupo> gruposA = prueba.getValue().getListaGruposTeoricos();
			TreeMap<Integer, Grupo> gruposB = prueba.getValue().getListaGruposPracticos();
			if (siglas.trim().equals(prueba.getKey())) {
				cuatriaux = prueba.getValue().getcuatrimestre();
				cursoaux = prueba.getValue().getcurso();
				if (tipe == 'A') {
					for (Entry<Integer, Grupo> prueba2 : gruposA.entrySet()) {
						if (id == prueba2.getValue().getIDGrupo()) {
							diaux = prueba2.getValue().getDia();
							horaaux = prueba2.getValue().getHora();
							aulaux = prueba2.getValue().getAula();
							break;
						}
					}
				} else {
					for (Entry<Integer, Grupo> prueba2 : gruposB.entrySet()) {
						if (id == prueba2.getValue().getIDGrupo()) {
							diaux = prueba2.getValue().getDia();
							horaaux = prueba2.getValue().getHora();
							aulaux = prueba2.getValue().getAula();
							break;
						}
					}
				}
			}

		}

		for (Entry<String, Aulas> prueba : aulas.entrySet()) {
			if (aulaux.trim().equals(prueba.getKey())) {
				ocups = prueba.getValue().getcapacidad();
			}
		}
		ocup = Integer.parseInt(ocups);

		error = 1;
		if (perfil.trim().equals("profesor")) {
			for (Entry<String, Profesores> prueba : profesores.entrySet()) {
				TreeMap<Integer, Docencia> gruposprof = prueba.getValue().getListaGruposAsignados();
				TreeMap<Integer, POD> pods = prueba.getValue().getListaCoordinacion();
				for (Entry<Integer, Docencia> prueba2 : gruposprof.entrySet()) {
					if (tipe == prueba2.getValue().gettipogrupo() && id == prueba2.getValue().getIDGrupo()
							&& siglas.trim().equals(prueba2.getValue().getsiglas())) {
						new Avisos("AGRUPO", "Grupo ya asignado"); // Aviso
						return;
					}
				}

				if (dni.trim().equals(prueba.getKey())) {
					for (Entry<Integer, POD> prueba2 : pods.entrySet()) {
						if (siglas.trim().equals(prueba2.getValue().getsiglas())
								&& tipe == prueba2.getValue().gettipogrupo()) {
							error = 0;
						}
					}
					if (error == 1) {
						new Avisos("AGRUPO", "Asignatura/tipo-grupo no presente en POD del profesor "); // Aviso
						return;
					}
				}
			}

			for (Entry<String, Profesores> prueba : profesores.entrySet()) {
				TreeMap<Integer, Docencia> gruposprof = prueba.getValue().getListaGruposAsignados();
				TreeMap<Integer, POD> pods = prueba.getValue().getListaCoordinacion();

				float cont = 1;
				if (dni.trim().equals(prueba.getKey())) {
					for (Entry<Integer, Docencia> prueba2 : gruposprof.entrySet()) {
						if (siglas.trim().equals(prueba2.getValue().getsiglas())
								&& tipe == prueba2.getValue().gettipogrupo()) {
							cont++;
							for (Entry<Integer, POD> prueba3 : pods.entrySet()) {
								if (siglas.trim().equals(prueba3.getValue().getsiglas())
										&& tipe == prueba3.getValue().gettipogrupo())
									if (cont > prueba3.getValue().getnGrupos()) {
										new Avisos("AGRUPO", "Número de grupos superior al contemplado en POD"); // Aviso
										return;
									}

							}

						}

						idgrupaux = prueba2.getValue().getIDGrupo();
						tipgrupaux = prueba2.getValue().gettipogrupo();

						for (Entry<String, Asignaturas> prueba3 : asignaturas.entrySet()) {
							TreeMap<Integer, Grupo> gruposA = prueba3.getValue().getListaGruposTeoricos();
							TreeMap<Integer, Grupo> gruposB = prueba3.getValue().getListaGruposPracticos();

							if (prueba3.getValue().getcuatrimestre() == cuatriaux) {

								if (tipgrupaux == 'A') {
									for (Entry<Integer, Grupo> prueba4 : gruposA.entrySet()) {
										if (prueba4.getValue().getDia() == diaux
												&& prueba4.getValue().getHora() == horaaux
												&& prueba4.getValue().getIDGrupo() == idgrupaux) {
											new Avisos("AGRUPO", "Solape de profesor"); // Aviso
											return;
										}
									}
								} else {
									for (Entry<Integer, Grupo> prueba4 : gruposB.entrySet()) {
										if (prueba4.getValue().getDia() == diaux
												&& prueba4.getValue().getHora() == horaaux
												&& prueba4.getValue().getIDGrupo() == idgrupaux) {
											new Avisos("AGRUPO", "Solape de profesor"); // Aviso
											return;
										}
									}
								}

							}
						}
					}

				}

			}
		}

		error = 1;
		if (perfil.trim().equals("alumno")) {
			for (Entry<String, Alumnos> prueba : alumnos.entrySet()) {
				TreeMap<Integer, Docencia> asigalum = prueba.getValue().getlistaDocenciaRecibida();

				if (dni.trim().equals(prueba.getKey())) {
					for (Entry<Integer, Docencia> prueba2 : asigalum.entrySet()) {
						if (siglas.trim().equals(prueba2.getValue().getsiglas())) {
							error = 0;
							break;
						}

						idgrupaux = prueba2.getValue().getIDGrupo();
						tipgrupaux = prueba2.getValue().gettipogrupo();

						for (Entry<String, Asignaturas> prueba3 : asignaturas.entrySet()) {
							TreeMap<Integer, Grupo> gruposA = prueba3.getValue().getListaGruposTeoricos();
							TreeMap<Integer, Grupo> gruposB = prueba3.getValue().getListaGruposPracticos();

							if (prueba3.getValue().getcuatrimestre() == cuatriaux
									&& cursoaux.trim().equals(prueba3.getValue().getcurso())) {
								if (tipgrupaux == 'A') {
									for (Entry<Integer, Grupo> prueba4 : gruposA.entrySet()) {
										if (prueba4.getValue().getDia() == diaux
												&& prueba4.getValue().getHora() == horaaux
												&& prueba4.getValue().getIDGrupo() == idgrupaux) {
											new Avisos("AGRUPO", "Solape de alumno"); // Aviso
											return;
										}
									}
								} else {
									for (Entry<Integer, Grupo> prueba4 : gruposB.entrySet()) {
										if (prueba4.getValue().getDia() == diaux
												&& prueba4.getValue().getHora() == horaaux
												&& prueba4.getValue().getIDGrupo() == idgrupaux) {
											new Avisos("AGRUPO", "Solape de alumno"); // Aviso
											return;
										}
									}
								}

							}
						}
					}
					if (error == 1) {
						new Avisos("AGRUPO", "Alumno no matriculado"); // Aviso
						return;
					}

				}
				for (Entry<Integer, Docencia> prueba4 : asigalum.entrySet()) {
					idgrupaux = prueba4.getValue().getIDGrupo();
					tipgrupaux = prueba4.getValue().gettipogrupo();
					asigaux = prueba4.getValue().getsiglas();

					for (Entry<String, Asignaturas> prueba2 : asignaturas.entrySet()) {
						TreeMap<Integer, Grupo> gruposA = prueba2.getValue().getListaGruposTeoricos();
						TreeMap<Integer, Grupo> gruposB = prueba2.getValue().getListaGruposPracticos();

						if (tipgrupaux == 'A') {

							for (Entry<Integer, Grupo> prueba3 : gruposA.entrySet()) {
								if (prueba3.getValue().getIDGrupo() == idgrupaux && prueba3.getValue().getDia() == diaux
										&& prueba3.getValue().getHora() == horaaux
										&& aulaux.trim().equals(prueba3.getValue().getAula())) {
									gente++;
								}
							}
						} else if (tipgrupaux == 'B') {
							for (Entry<Integer, Grupo> prueba3 : gruposB.entrySet()) {
								if (prueba3.getValue().getIDGrupo() == idgrupaux && prueba3.getValue().getDia() == diaux
										&& prueba3.getValue().getHora() == horaaux
										&& aulaux.trim().equals(prueba3.getValue().getAula())) {
									gente++;
								}
							}
						}

					}
				}
				if (gente + 1 > ocup || gente + 1 > subocup) {
					new Avisos("AGRUPO", "Aula completa");
					return;
				}
			}

		}
		if (perfil.trim().equals("alumno")) {
			for (Entry<String, Alumnos> prueba : alumnos.entrySet()) {
				if (prueba.getValue().getdni().equals(dni)) {
					int correcto = 0;
					TreeMap<Integer, Docencia> grupoalum = prueba.getValue().getlistaDocenciaRecibida();
					
					for (Entry<Integer, Docencia> lecturaaux : grupoalum.entrySet()) {
						if (tipe == lecturaaux.getValue().gettipogrupo()
								&& siglas.trim().equals(lecturaaux.getValue().getsiglas())) {
							correcto=1;
							lecturaaux.getValue().setIDGrupo(id);
							lecturaaux.getValue().settipogrupo(tipe);
							
							break;
						}

						if (lecturaaux.getValue().getsiglas().equals(siglas)
								&& lecturaaux.getValue().getIDGrupo() == 0) {
							correcto = 1;
							lecturaaux.getValue().setIDGrupo(id);
							lecturaaux.getValue().settipogrupo(tipe);
							break;

						}

					}
					if (correcto == 0) {
						grupoalum.put(grupoalum.size() + 1, new Docencia('R', siglas, tipe, id));
					}
					
				}
			}
		} else {
			for (Entry<String, Profesores> prueba : profesores.entrySet()) {
				if (prueba.getValue().getdni().equals(dni)) {
					int correcto = 0;
					TreeMap<Integer, Docencia> grupoprof = prueba.getValue().getListaGruposAsignados();
					for (Entry<Integer, Docencia> lecturaaux : grupoprof.entrySet()) {
						if (lecturaaux.getValue().getsiglas().equals(siglas)
								&& lecturaaux.getValue().getIDGrupo() == 0) {
							correcto = 1;
							lecturaaux.getValue().setIDGrupo(id);
							lecturaaux.getValue().settipogrupo(tipe);

						}
					}
					if (correcto == 0) {
						grupoprof.put(grupoprof.size() + 1, new Docencia('I', siglas, tipe, id));
					}
				}
			}
		}
	}

	/**
	 * Crea un nuevo grupo de una asignatura. Para ello habrá que buscar que no se
	 * solapen los horarios ni las aulas. Se guardará en la lista anteriormente
	 * generada de asignaturas.
	 * 
	 * @param siglas
	 *            siglas de la asignatura
	 * @param tipo
	 *            grupo A o B
	 * @param ID
	 *            id del nuevo grupo
	 * @param dia
	 *            dia de la semana en el que se impartirá la asignatura.
	 * @param hora
	 *            hora de comienzo de la clase
	 * @param aula
	 *            aula en la que se impartirá la asignatura.
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void crearGrupo(String siglas, String tipo, String ID, String dia, String hora, String aula,
			Lista lista_todo) {

		TreeMap<String, Asignaturas> asignaturas = Lista.getAsignaturas();
		TreeMap<String, Aulas> aulas = lista_todo.getAulas();

		int error;
		char tipe = tipo.charAt(0);
		char date = dia.charAt(0);
		int ide = Integer.parseInt(ID);
		int hour = Integer.parseInt(hora);

		for (Entry<String, Asignaturas> prueba : asignaturas.entrySet()) {
			TreeMap<Integer, Grupo> gruposA = prueba.getValue().getListaGruposTeoricos();
			TreeMap<Integer, Grupo> gruposB = prueba.getValue().getListaGruposPracticos();

			error = 1;
			for (Entry<String, Asignaturas> prueba2 : asignaturas.entrySet()) {
				if (siglas.trim().equals(prueba2.getKey())) {
					error = 0;
					break;
				}

			}
			if (error == 1) {
				new Avisos("CGA", "Asignatura inexistente"); // Aviso Modificado el archivo ejecucion
				return;
			}

			if (tipe != 'A' && tipe != 'B') {
				new Avisos("CGA", "Tipo de grupo incorrecto"); // Aviso
				return;
			}
			if (tipe == 'A') {
				for (Entry<Integer, Grupo> prueba2 : gruposA.entrySet()) {
					if (ide == prueba2.getKey() && siglas.trim().equals(prueba.getKey())) {
						new Avisos("CGA", "Grupo ya existente"); // Aviso
						return;
					}
				}
			} else {
				for (Entry<Integer, Grupo> prueba2 : gruposB.entrySet()) {
					if (ide == prueba2.getKey() && siglas.trim().equals(prueba.getKey())) {
						new Avisos("CGA", "Grupo ya existente"); // Aviso
						return;

					}
				}
			}

			if (date != 'L' && date != 'M' && date != 'X' && date != 'J' && date != 'V') {
				new Avisos("CGA", "Dia incorrecto"); // Aviso
				return;
			}

			error = 1;
			for (Entry<String, Aulas> prueba2 : aulas.entrySet()) {
				if (aula.trim().equals(prueba2.getKey())) {
					error = 0;
					break;
				}

			}
			if (error == 1) {
				new Avisos("CGA", "Aula no existente"); // Aviso Modificado el archivo ejecucion
				return;
			}

			for (Entry<Integer, Grupo> prueba2 : gruposA.entrySet()) {
				if (aula.trim().equals(prueba2.getValue().getAula()) && date == prueba2.getValue().getDia()
						&& hour == prueba2.getValue().getHora()) {
					new Avisos("CGA", "Solape de Aula"); // Aviso
					return;
				}
			}

			for (Entry<Integer, Grupo> prueba2 : gruposB.entrySet()) {
				if (aula.trim().equals(prueba2.getValue().getAula()) && date == prueba2.getValue().getDia()
						&& hour == prueba2.getValue().getHora()) {
					new Avisos("CGA", "Solape de Aula"); // Aviso
					return;
				}
			}
		}

		for (Entry<String, Asignaturas> prueba : asignaturas.entrySet()) {
			TreeMap<Integer, Grupo> gruposA = prueba.getValue().getListaGruposTeoricos();
			TreeMap<Integer, Grupo> gruposB = prueba.getValue().getListaGruposPracticos();

			if (siglas.trim().equals(prueba.getKey())) {

				if (tipe == 'A')
					gruposA.put(ide, new Grupo(0, tipe, ide, date, hour, aula));
				else
					gruposB.put(ide, new Grupo(0, tipe, ide, date, hour, aula));

			}
		}

	}

	/**
	 * Matricula a un alumno en una asignatura cuyos prerrequisitos han sido
	 * cumplidos. Se guardará en la lista anteriormente generada de alumnos.
	 * 
	 * @param dni
	 *            dni del alumno
	 * @param siglas
	 *            siglas de la asignatura
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void matriculaAlumno(String dni, String siglas, Lista lista_todo) {

		TreeMap<String, Alumnos> alumn = lista_todo.getAlumnos();
		TreeMap<String, Asignaturas> asignaturas = Lista.getAsignaturas();

		if (!alumn.containsKey(dni)) {
			new Avisos("MAT", "Alumno inexistente");
			return;
		}
		if (!asignaturas.containsKey(siglas)) {
			new Avisos("MAT", "Asignatura inexistente");
			return;
		}

		for (Entry<String, Alumnos> lectura : alumn.entrySet()) // Recorremos los alumnos
		{
			if (dni.equals(lectura.getValue().getdni())) { // Buscamos que coincida el dni
				TreeMap<Integer, Docencia> listaDocenciaRecibida = lectura.getValue().getlistaDocenciaRecibida();
				TreeMap<String, AsignaturasSuperadas> listaAsigSup = lectura.getValue().getListaAsignaturasSuperadas();

				for (Entry<String, Asignaturas> lectura3 : asignaturas.entrySet()) // Recorremos las asignaturas
				{
					String[] prerrequisitos = lectura3.getValue().getprerequisitos();
					if (siglas.equals(lectura3.getValue().getsiglas())) { // Miramos que coincidan las asignaturas con
																			// las que se pasaron por ejecucion
						if (prerrequisitos[0].isEmpty())
							break; // Si no hay requisitos pasamos a la siguiente comprobacion
						else {
							int tamaño = listaAsigSup.size();
							int contador = 0;
							for (int i = 0; i < prerrequisitos.length; i++) // Recogemos los prerrequisitos
							{

								if (listaAsigSup.containsKey(prerrequisitos[i])) {
									contador++;
								}

							}
							if (contador != tamaño) {
								new Avisos("MAT", "No cumple requisitos");
								return;
							}
						}

					}
				}

				for (Entry<Integer, Docencia> lectura2 : listaDocenciaRecibida.entrySet()) {
					String siglasdocencia = lectura2.getValue().getsiglas();
					if (siglasdocencia.equals(siglas)) {
						new Avisos("MAT", "Ya es alumno de la asignatura indicada");
						return;
					}
				}

				Docencia docencia = new Docencia('R', siglas, '\0', 0);
				listaDocenciaRecibida.put(listaDocenciaRecibida.size() + 1, docencia);

			}

		}

	}

	/**
	 * Genera un archivo de texto con el expediente de un alumno. Este expediente
	 * contiene las asignaturas aprobadas por orden de curso y alfabéticamente.
	 * 
	 * @param dni
	 *            dni del alumno
	 * @param salida
	 *            nombre del fichero de salida
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void expedienteAlumno(String dni, String salida, Lista lista_todo) {

		TreeMap<String, Alumnos> alumn = lista_todo.getAlumnos();
		TreeMap<String, Asignaturas> asig = Lista.getAsignaturas();
		if (!alumn.containsKey(dni)) {
			new Avisos("EXP", "Alumno inexistente");
			return;
		}

		FileWriter fichero = null;
		try {

			fichero = new FileWriter(salida);

			// Escribimos linea a linea en el fichero
			for (Entry<String, Alumnos> linea : alumn.entrySet()) {
				if (dni.equals(linea.getKey())) {

					TreeMap<String, AsignaturasSuperadas> AsigSup = linea.getValue().getListaAsignaturasSuperadas();

					for (int i = 1; i < 5; i++) // Recorremos los años
					{
						for (Entry<String, AsignaturasSuperadas> linea2 : AsigSup.entrySet()) // Recorremos asignaturas
																								// superadas
						{
							for (Entry<String, Asignaturas> linea3 : asig.entrySet()) {
								if (linea3.getKey().equals(linea2.getKey())) {

									String curso = linea3.getValue().getcurso();

									if (i == Integer.parseInt(curso)) {
										fichero.write(linea3.getValue().getcurso() + ";" + linea2.getValue().getsiglas()
												+ ";" + linea2.getValue().getnota() + ";"
												+ linea2.getValue().getcursoacademico() + "\r\n");
									}
								}
							}
						}
					}
				}
			}

			fichero.close();

		} catch (Exception ex) {
			new Avisos("EXP", "Mensaje de la excepción: " + ex.getMessage());
		}

		return;
	}

	/**
	 * Genera un horario de la ocupación semanal de las aulas por aquellos grupos
	 * impartidos por algún profesor. Se puede escoger entre mostrar solo un aula o
	 * todas las aulas del sistema.
	 * 
	 * @param aula
	 *            aula que se quiere mostrar
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void ocupacionAulas(String aula, Lista lista_todo) {

		TreeMap<String, Asignaturas> aulaasig = Lista.getAsignaturas();
		TreeMap<String, Profesores> prof = lista_todo.getProfesores();
		TreeMap<String, Aulas> aulas = lista_todo.getAulas();

		if (!aula.contains("*")) {
			String[][] ocupacionSemanal = new String[12][6];
			String[][] inicialesprof = new String[12][6];
			for (Entry<String, Asignaturas> lectura2 : aulaasig.entrySet()) {

				TreeMap<Integer, Grupo> listaGruposPracticos = lectura2.getValue().getListaGruposPracticos();
				TreeMap<Integer, Grupo> listaGruposTeoricos = lectura2.getValue().getListaGruposTeoricos();

				for (Entry<Integer, Grupo> lista : listaGruposPracticos.entrySet()) {
					if (lista.getValue().getAula().equals(aula)) {
						char diaSemana = lista.getValue().getDia();
						String[] aux = null;
						for (Entry<String, Profesores> lectura4 : prof.entrySet()) {
							TreeMap<Integer, Docencia> docenciaimp = lectura4.getValue().getListaGruposAsignados();
							for (Entry<Integer, Docencia> lectura5 : docenciaimp.entrySet()) {
								if ((lectura2.getValue().getsiglas().equals(lectura5.getValue().getsiglas()))
										&& (lista.getValue().getIDGrupo() == lectura5.getValue().getIDGrupo())) {

									aux = lectura4.getValue().getnombreyapellidos().trim().split(",");
									String[] aux2 = aux[0].trim().split(" ");

									String iniciales = Character.toString(aux[1].trim().charAt(0))
											+ Character.toString(aux2[0].trim().charAt(0))
											+ Character.toString(aux2[1].trim().charAt(0));

									int j = 0;
									if (diaSemana == 'L')
										j = 0; // Cada dia corresponde con una columna de mi matriz
									if (diaSemana == 'M')
										j = 1;
									if (diaSemana == 'X')
										j = 2;
									if (diaSemana == 'J')
										j = 3;
									if (diaSemana == 'V')
										j = 4;

									int posicion = lista.getValue().getHora() - 9; // Las 9 corresponden con la fila 0
																					// en mi matriz

									for (int k = 0; k < lista.getValue().getDuracionGrupo(); k++) {
										ocupacionSemanal[posicion][j] = lectura2.getValue().getsiglas() + "-"
												+ lista.getValue().getGrupoAB() + lista.getValue().getIDGrupo();
										inicialesprof[posicion][j] = iniciales;
										posicion++;
									}
								}
							}
						}

					}

				}

				for (Entry<Integer, Grupo> lista : listaGruposTeoricos.entrySet()) {
					if (lista.getValue().getAula().equals(aula)) {
						char diaSemana = lista.getValue().getDia();
						String[] aux = null;
						for (Entry<String, Profesores> lectura4 : prof.entrySet()) {
							TreeMap<Integer, Docencia> docenciaimp = lectura4.getValue().getListaGruposAsignados();
							for (Entry<Integer, Docencia> lectura5 : docenciaimp.entrySet()) {
								if ((lectura2.getValue().getsiglas().equals(lectura5.getValue().getsiglas()))
										&& (lista.getValue().getIDGrupo() == lectura5.getValue().getIDGrupo())) {

									aux = lectura4.getValue().getnombreyapellidos().trim().split(",");
									String[] aux2 = aux[0].trim().split(" ");

									String iniciales = Character.toString(aux[1].trim().charAt(0))
											+ Character.toString(aux2[0].trim().charAt(0))
											+ Character.toString(aux2[1].trim().charAt(0));

									int j = 0;
									if (diaSemana == 'L')
										j = 0; // Cada dia corresponde con una columna de mi matriz
									if (diaSemana == 'M')
										j = 1;
									if (diaSemana == 'X')
										j = 2;
									if (diaSemana == 'J')
										j = 3;
									if (diaSemana == 'V')
										j = 4;

									int posicion = lista.getValue().getHora() - 9; // Las 9 corresponden con la fila 0
																					// en mi matriz

									for (int k = 0; k < lista.getValue().getDuracionGrupo(); k++) {
										ocupacionSemanal[posicion][j] = lectura2.getValue().getsiglas() + "-"
												+ lista.getValue().getGrupoAB() + lista.getValue().getIDGrupo();
										inicialesprof[posicion][j] = iniciales;
										posicion++;
									}
								}
							}
						}

					}

				}
			}
			for (int a = 0; a < 10; a++)
				for (int b = 0; b < 5; b++)
					if (ocupacionSemanal[a][b] == null)
						ocupacionSemanal[a][b] = "\0";
			for (int a = 0; a < 10; a++)
				for (int b = 0; b < 5; b++)
					if (inicialesprof[a][b] == null)
						inicialesprof[a][b] = "\0";
			System.out.println("		AULA: " + aula);
			System.out.println("");
			printAula(ocupacionSemanal, inicialesprof);
		}

		else {
			for (Entry<String, Aulas> listaaulas : aulas.entrySet()) {
				String[][] ocupacionSemanal = new String[12][6];
				String[][] inicialesprof = new String[12][6];
				for (Entry<String, Asignaturas> lectura2 : aulaasig.entrySet()) {

					TreeMap<Integer, Grupo> listaGruposPracticos = lectura2.getValue().getListaGruposPracticos();
					TreeMap<Integer, Grupo> listaGruposTeoricos = lectura2.getValue().getListaGruposTeoricos();

					for (Entry<Integer, Grupo> lista : listaGruposPracticos.entrySet()) {
						if (lista.getValue().getAula().equals(listaaulas.getValue().getsiglas())) {
							char diaSemana = lista.getValue().getDia();
							String[] aux = null;
							for (Entry<String, Profesores> lectura4 : prof.entrySet()) {
								TreeMap<Integer, Docencia> docenciaimp = lectura4.getValue().getListaGruposAsignados();
								for (Entry<Integer, Docencia> lectura5 : docenciaimp.entrySet()) {
									if ((lectura2.getValue().getsiglas().equals(lectura5.getValue().getsiglas()))
											&& (lista.getValue().getIDGrupo() == lectura5.getValue().getIDGrupo())) {

										aux = lectura4.getValue().getnombreyapellidos().trim().split(",");
										String[] aux2 = aux[0].trim().split(" ");

										String iniciales = Character.toString(aux[1].trim().charAt(0))
												+ Character.toString(aux2[0].trim().charAt(0))
												+ Character.toString(aux2[1].trim().charAt(0));

										int j = 0;
										if (diaSemana == 'L')
											j = 0; // Cada dia corresponde con una columna de mi matriz
										if (diaSemana == 'M')
											j = 1;
										if (diaSemana == 'X')
											j = 2;
										if (diaSemana == 'J')
											j = 3;
										if (diaSemana == 'V')
											j = 4;

										int posicion = lista.getValue().getHora() - 9; // Las 9 corresponden con la fila
																						// 0
																						// en mi matriz

										for (int k = 0; k < lista.getValue().getDuracionGrupo(); k++) {
											ocupacionSemanal[posicion][j] = lectura2.getValue().getsiglas() + "-"
													+ lista.getValue().getGrupoAB() + lista.getValue().getIDGrupo();
											inicialesprof[posicion][j] = iniciales;
											posicion++;
										}
									}
								}
							}

						}

					}

					for (Entry<Integer, Grupo> lista : listaGruposTeoricos.entrySet()) {
						if (lista.getValue().getAula().equals(listaaulas.getValue().getsiglas())) {
							char diaSemana = lista.getValue().getDia();
							String[] aux = null;
							for (Entry<String, Profesores> lectura4 : prof.entrySet()) {
								TreeMap<Integer, Docencia> docenciaimp = lectura4.getValue().getListaGruposAsignados();
								for (Entry<Integer, Docencia> lectura5 : docenciaimp.entrySet()) {
									if ((lectura2.getValue().getsiglas().equals(lectura5.getValue().getsiglas()))
											&& (lista.getValue().getIDGrupo() == lectura5.getValue().getIDGrupo())) {

										aux = lectura4.getValue().getnombreyapellidos().trim().split(",");
										String[] aux2 = aux[0].trim().split(" ");

										String iniciales = Character.toString(aux[1].trim().charAt(0))
												+ Character.toString(aux2[0].trim().charAt(0))
												+ Character.toString(aux2[1].trim().charAt(0));

										int j = 0;
										if (diaSemana == 'L')
											j = 0; // Cada dia corresponde con una columna de mi matriz
										if (diaSemana == 'M')
											j = 1;
										if (diaSemana == 'X')
											j = 2;
										if (diaSemana == 'J')
											j = 3;
										if (diaSemana == 'V')
											j = 4;

										int posicion = lista.getValue().getHora() - 9; // Las 9 corresponden con la fila
																						// 0
																						// en mi matriz

										for (int k = 0; k < lista.getValue().getDuracionGrupo(); k++) {
											ocupacionSemanal[posicion][j] = lectura2.getValue().getsiglas() + "-"
													+ lista.getValue().getGrupoAB() + lista.getValue().getIDGrupo();
											inicialesprof[posicion][j] = iniciales;
											posicion++;
										}
									}
								}
							}

						}

					}
				}
				System.out.println("");
				System.out.println("");
				for (int a = 0; a < 10; a++)
					for (int b = 0; b < 5; b++)
						if (ocupacionSemanal[a][b] == null)
							ocupacionSemanal[a][b] = "\0";
				for (int a = 0; a < 10; a++)
					for (int b = 0; b < 5; b++)
						if (inicialesprof[a][b] == null)
							inicialesprof[a][b] = "\0";
				System.out.println("		AULA: " + listaaulas.getValue().getsiglas());
				System.out.println("");
				printAula(ocupacionSemanal, inicialesprof);
			}

		}

		return;
	}

	/**
	 * Evalua a los alumnos de una asignatura y se actualizará la información tanto
	 * si se ha aprobado la asignatura como si se ha suspendido. Los datos de esos
	 * alumnos se encuentran en los ficheros introducidos
	 * 
	 * @param asignatura
	 *            asignatura a evaluar
	 * @param ficheroA
	 *            nombre del fichero de teoria
	 * @param ficheroB
	 *            nombre del fichero de prácticas
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void evaluaAlumno(String asignatura, String ficheroA, String ficheroB, Lista lista_todo) {

		TreeMap<String, Asignaturas> asignaturas = Lista.getAsignaturas();
		TreeMap<String, Alumnos> alumnos = lista_todo.getAlumnos();
		TreeMap<String, Float> notasA = new TreeMap<String, Float>();
		TreeMap<String, Float> notasB = new TreeMap<String, Float>();
		TreeMap<String, Float> notastotal = new TreeMap<String, Float>();
		TreeMap<String, Float> notasSuperadas = new TreeMap<String, Float>();
		TreeMap<String, Float> notasNoSuperadas = new TreeMap<String, Float>();
		float notatotal = 0;
		String cursoacademico = "";

		cursoacademico = LFicheros.leerCurso(cursoacademico); // Cogemos el curso academico

		if (!asignaturas.containsKey(asignatura)) {
			new Avisos("EVALUA", "Asignatura inexistente");
			return;
		} // No existe la asignatura del archivo

		for (Entry<String, Alumnos> lectura2 : alumnos.entrySet()) {
			TreeMap<String, AsignaturasSuperadas> asignaturasEvaluadas = lectura2.getValue()
					.getListaAsignaturasSuperadas();

			for (Entry<String, AsignaturasSuperadas> lectura3 : asignaturasEvaluadas.entrySet()) {

				if (lectura3.getValue().getsiglas().equals(asignatura)
						&& lectura3.getValue().getcursoacademico().equals(cursoacademico)) {
					new Avisos("EVALUA", "Asignatura ya evaluada en este curso academico");
					return;
				} // Si ya se ha evaluado la asignatura en este año academico
			}

		}

		LFicheros.leerNotas(notasA, notasB, ficheroA, ficheroB);

		for (Entry<String, Float> prueba : notasA.entrySet()) {

			for (Entry<String, Alumnos> lectura : alumnos.entrySet()) {
				if (prueba.getValue() > 5 || prueba.getValue() < 0) {
					int linea = LFicheros.buscarLinea(prueba.getKey());
					new Avisos("EVALUA", "Error en la linea " + linea + " del fichero A: Nota grupo A incorrecta");
					break;
				}
				if (!alumnos.containsKey(prueba.getKey())) {
					int linea = LFicheros.buscarLinea(prueba.getKey());
					new Avisos("EVALUA",
							"Error en la linea " + linea + " del fichero A: Alumno inexistente: " + prueba.getKey());
					break;

				}

				if (lectura.getValue().getdni().trim().equals(prueba.getKey().trim())) {
					TreeMap<Integer, Docencia> asigCursadas = lectura.getValue().getlistaDocenciaRecibida();
					@SuppressWarnings("unused")
					TreeMap<String, AsignaturasSuperadas> listaAsigSuperadas = lectura.getValue()
							.getListaAsignaturasSuperadas();

					int contador = 0;
					for (Entry<Integer, Docencia> doc : asigCursadas.entrySet()) {
						if (doc.getValue().getsiglas().equals(asignatura)) {
							contador++;
						}

					}
					if (!(contador >= 1)) {
						int linea = LFicheros.buscarLinea(prueba.getKey());
						new Avisos("EVALUA", "Error en la linea " + linea + " del fichero A: Alumno no matriculado");
						break;
					} // Leemos las asignaturas superadas, si el contador es mayor que 1 es que ha
						// superado la asignatura

					notastotal.put(prueba.getKey(), prueba.getValue());
				}
			}
		}

		for (Entry<String, Float> prueba : notasB.entrySet()) {

			for (Entry<String, Alumnos> lectura : alumnos.entrySet()) {
				if (prueba.getValue() > 5 || prueba.getValue() < 0) {
					int linea = LFicheros.buscarLinea(prueba.getKey());
					new Avisos("EVALUA", "Error en la linea " + linea + " del fichero B: Nota grupo B incorrecta");
					break;
				}
				if (!alumnos.containsKey(prueba.getKey())) {
					int linea = LFicheros.buscarLinea(prueba.getKey());
					new Avisos("EVALUA",
							"Error en la linea " + linea + "del fichero B: Alumno inexistente: " + prueba.getKey());
					break;

				}

				if (lectura.getValue().getdni().trim().equals(prueba.getKey().trim())) {
					TreeMap<Integer, Docencia> asigCursadas = lectura.getValue().getlistaDocenciaRecibida();
					@SuppressWarnings("unused")
					TreeMap<String, AsignaturasSuperadas> listaAsigSuperadas = lectura.getValue()
							.getListaAsignaturasSuperadas();

					int contador = 0;
					for (Entry<Integer, Docencia> doc : asigCursadas.entrySet()) {
						if (doc.getValue().getsiglas().equals(asignatura)) {
							contador++;
						}

					}
					if (!(contador >= 1)) {
						int linea = LFicheros.buscarLinea(prueba.getKey());
						new Avisos("EVALUA", "Error en la linea " + linea + " del fichero B: Alumno no matriculado");
						break;
					} // Leemos las asignaturas superadas, si el contador es mayor que 1 es que ha
						// superado la asignatura

					for (Entry<String, Float> lectura4 : notastotal.entrySet()) {
						if (lectura4.getKey().equals(prueba.getKey())) {
							notatotal = lectura4.getValue() + prueba.getValue();

							if (notatotal >= 5) {
								notasSuperadas.put(lectura4.getKey(), notatotal);
							} else {
								notasNoSuperadas.put(lectura4.getKey(), notatotal); // Guardamos las notas en su
																					// correspondiente TreeMap
							}
						}
					}
				}
			}
		}

		for (Entry<String, Float> sad : notasSuperadas.entrySet()) {
			for (Entry<String, Alumnos> prueba2 : alumnos.entrySet()) {
				if (prueba2.getValue().getdni().equals(sad.getKey())) // Buscamos el alumno
				{
					TreeMap<Integer, Docencia> listaDocencia = prueba2.getValue().getlistaDocenciaRecibida();
					TreeMap<String, AsignaturasSuperadas> AsigSuperada = prueba2.getValue()
							.getListaAsignaturasSuperadas();
					int a = 1;
					for (int i = 1; i < listaDocencia.size() + a; i++) // Recorremos la listaDocencia ya que tiene como
																		// key ints de 1 a su tamaño
					{
						Docencia docencia = listaDocencia.get(i); // Recogemos la docencia correspondiente a su key

						if (docencia.getsiglas().equals(asignatura)) // Si las siglas de docencia son iguales a las de
																		// la asignatura a eliminar
						{
							listaDocencia.remove(i);
							a++; // Le sumamos 1 a "a" porque el tamaño de la lista se reduce en 1
							AsignaturasSuperadas superada = new AsignaturasSuperadas(asignatura, cursoacademico,
									sad.getValue());
							AsigSuperada.put(asignatura, superada); // Guardamos la asignatura superada

						}
					}

				}
			}
		}

		for (Entry<String, Float> sad : notasNoSuperadas.entrySet()) {
			for (Entry<String, Alumnos> prueba2 : alumnos.entrySet()) {
				if (prueba2.getValue().getdni().equals(sad.getKey())) // Buscamos el alumno
				{
					TreeMap<Integer, Docencia> listaDocencia = prueba2.getValue().getlistaDocenciaRecibida();
					int a = 1;
					for (int i = 1; i < listaDocencia.size() + a; i++) // Recorremos la listaDocencia ya que tiene como
																		// key ints de 1 a su tamaño
					{
						Docencia docencia = listaDocencia.get(i); // Recogemos la docencia correspondiente a su key

						if (docencia.getsiglas().equals(asignatura)) // Si las siglas de docencia son iguales a las de
																		// la asignatura a eliminar
						{
							listaDocencia.remove(i);
							a++; // Le sumamos 1 a "a" porque el tamaño de la lista se reduce en 1

						}
					}

				}
			}
		}

		return;
	}

	/**
	 * Da forma al horario
	 * 
	 * @param ocupacionSemanal
	 *            matriz con las asignaturas ordenadas
	 * @param inicialesProf
	 *            matriz con las iniciales de los profesores que imparten la
	 *            asignatura
	 */

	static void printAula(String[][] ocupacionSemanal, String[][] inicialesprof) {
		System.out.format("%5s%10s%10s%10s%10s%10s", "HORA", "L", "M", "X", "J", "V");
		gotoxy(ocupacionSemanal, inicialesprof, 0, 0, "9-10");
		gotoxy(ocupacionSemanal, inicialesprof, 1, 0, "10-11");
		gotoxy(ocupacionSemanal, inicialesprof, 2, 0, "11-12");
		gotoxy(ocupacionSemanal, inicialesprof, 3, 0, "12-13");
		gotoxy(ocupacionSemanal, inicialesprof, 4, 0, "13-14");
		System.out.format("\n\n%5s%10s%10s%10s%10s%10s", "14-15", "X", "X", "X", "X", "X");
		System.out.println("");
		gotoxy(ocupacionSemanal, inicialesprof, 6, 0, "15-16");
		gotoxy(ocupacionSemanal, inicialesprof, 7, 0, "16-17");
		gotoxy(ocupacionSemanal, inicialesprof, 8, 0, "17-18");
		gotoxy(ocupacionSemanal, inicialesprof, 9, 0, "18-19");
		return;
	}

	/**
	 * Imprime linea a linea del horario segun los parametros que se le indiquen
	 * 
	 * @param ocupacionSemanal
	 *            matriz con las asignaturas ordenadas
	 * @param inicialesProf
	 *            matriz con las iniciales de los profesores que imparten la
	 *            asignatura
	 * @param a
	 *            fila de la matriz
	 * @param b
	 *            columnas de la matriz
	 */

	static void gotoxy(String[][] ocupacionSemanal, String[][] inicialesprof, int a, int b, String hora) {

		System.out.println(" ");
		System.out.println(" ");
		System.out.format("%5s%10s%10s%10s%10s%10s", hora, ocupacionSemanal[a][b], ocupacionSemanal[a][b + 1],
				ocupacionSemanal[a][b + 2], ocupacionSemanal[a][b + 3], ocupacionSemanal[a][b + 4]);
		System.out.println(" ");
		System.out.format("%5s%10s%10s%10s%10s%10s", " ", inicialesprof[a][b], inicialesprof[a][b + 1],
				inicialesprof[a][b + 2], inicialesprof[a][b + 3], inicialesprof[a][b + 4]);
		return;
	}

	/**
	 * Comprueba que se ha introducido un formato de fecha completa
	 * 
	 * @param calendar
	 *            fecha introducida
	 */

	static int fechabien(GregorianCalendar calendar) {
		try {
			calendar.setLenient(false);
			calendar.getTime();
		} catch (IllegalArgumentException err) {
			return 1;// retorna 1 si hay error y 0 si esta bien
		}
		return 0;
	}

}
