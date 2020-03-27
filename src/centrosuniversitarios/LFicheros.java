package centrosuniversitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Contiene las operaciones necesarias para leer todos los ficheros del sistema
 * y posteriormente volver a escribirlos.
 * 
 * @author Andrés Álvarez López
 * @author Jorge Combo Val
 */
public class LFicheros {

	/**
	 * Leemos todas las personas del sistema y las almacenamos en personas,
	 * profesores y alumnos.
	 * 
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 * @return retorna si hay error
	 */

	public static int leerPersonas(Lista lista_todo) {

		// VAMOS A LEER LOS ALUMNOS
		TreeMap<String, Persona> personas = lista_todo.getPersonas(); // Accedemos al treemap de personas
		TreeMap<String, Alumnos> alumnos = lista_todo.getAlumnos();
		TreeMap<String, Profesores> profesores = lista_todo.getProfesores();
		try {

			String nombreyapellidos;
			String dni;
			String fechadenacimiento;

			BufferedReader bf = new BufferedReader(new FileReader("alumnos.txt")); // Leemos el archivo de texto

			String cadena = " ";
			String bfRead;

			while ((bfRead = bf.readLine()) != null) // Leemos el archivo
			{
				if (bfRead.contains("*")) // Leemos todos los datos de un alumno
				{
					String[] datosSeparados = cadena.split("\\&");
					nombreyapellidos = datosSeparados[1].trim();
					dni = datosSeparados[0].trim();
					fechadenacimiento = datosSeparados[3].trim();
					guardarDatosAlumno(alumnos, cadena);
					Persona persona = new Persona(nombreyapellidos, dni, fechadenacimiento);
					personas.put(cogerReferencia(cadena), persona); // Almacenamos

					cadena = " "; // Reiniciamos la cadena para almacenar otro alumno distinto
					bfRead = bf.readLine();
				}
				cadena = cadena + bfRead + "&";

			}
			String[] datosSeparados = cadena.split("\\&");
			nombreyapellidos = datosSeparados[1].trim();
			dni = datosSeparados[0].trim();
			fechadenacimiento = datosSeparados[3].trim();
			guardarDatosAlumno(alumnos, cadena);
			personas.put(cogerReferencia(cadena), new Persona(nombreyapellidos, dni, fechadenacimiento)); // Cogemos la
																											// ultima
																											// persona
			bf.close();

		} catch (Exception excepcion)

		{
			System.err.println(excepcion + "Leer alumnos"); // Error si no existe el archivo
			return -1;
		}

		/*
		 * for (Entry<String, Alumnos> prueba : alumnos.entrySet()){ String clave =
		 * prueba.getKey(); TreeMap<Integer, Docencia> nombreasd =
		 * prueba.getValue().getlistaDocenciaRecibida();
		 * 
		 * for (Entry<Integer, Docencia> prueba2 : nombreasd.entrySet()){ int clave2 =
		 * prueba2.getKey(); char nombre2 =prueba2.getValue().gettipogrupo();
		 * System.out.println(clave+"--->"+clave2+"    "+nombre2); } }
		 */

		// VAMOS A LEER LOS PROFESORES

		try {

			BufferedReader bf = new BufferedReader(new FileReader("profesores.txt")); // Leemos el archivo de texto
			String nombreyapellidos;
			String dni;
			String fechadenacimiento;

			String cadena = " ";
			String bfRead;

			while ((bfRead = bf.readLine()) != null) // Leemos el archivo
			{
				if (bfRead.contains("*")) // Leemos todos los datos de un profesor
				{
					String[] datosSeparados = cadena.split("\\&");
					nombreyapellidos = datosSeparados[1].trim();
					dni = datosSeparados[0].trim();
					fechadenacimiento = datosSeparados[2].trim();
					personas.put(cogerReferencia(cadena), new Persona(nombreyapellidos, dni, fechadenacimiento));
					guardarDatosProfesor(profesores, cadena);
					cadena = " "; // Reiniciamos la cadena para almacenar otro profesor distinto
					bfRead = bf.readLine();
				}
				cadena = cadena + bfRead + "&";

			}
			String[] datosSeparados = cadena.split("\\&");
			nombreyapellidos = datosSeparados[1].trim();
			dni = datosSeparados[0].trim();
			fechadenacimiento = datosSeparados[2].trim();
			personas.put(cogerReferencia(cadena), new Persona(nombreyapellidos, dni, fechadenacimiento)); // Cogemos la
			guardarDatosProfesor(profesores, cadena); // ultima
			// persona
			bf.close();

		} catch (Exception excepcion)

		{
			System.err.println(excepcion + "LeerProfesores"); // Error si no existe el archivo
			return -1;
		}

		/*
		 * for (Entry<String, Profesores> prueba : profesores.entrySet()){ String clave
		 * = prueba.getKey(); TreeMap<Integer, Docencia> nombreasd =
		 * prueba.getValue().getListaGruposAsignados();
		 * 
		 * for (Entry<Integer, Docencia> prueba2 : nombreasd.entrySet()){ int clave2 =
		 * prueba2.getKey(); float nombre2 =prueba2.getValue().getIDGrupo();
		 * System.out.println(clave+"--->"+clave2+"    "+nombre2); } }
		 */

		return 0;
	}

	/**
	 * Función para leer todas las Asignaturas del sistema
	 * 
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 * @return retorna si hay error
	 */
	public static int leerAsignaturas(Lista lista_todo) {

		// VAMOS A LEER LOS ASIGNATURAS
		TreeMap<String, Asignaturas> asignaturas = Lista.getAsignaturas();// Accedemos al treemap de personas

		try {

			BufferedReader bf = new BufferedReader(new FileReader("asignaturas.txt")); // Leemos el archivo de texto
			String cadena = "";
			String bfRead;

			while ((bfRead = bf.readLine()) != null) // Leemos el archivo
			{

				if (bfRead.contains("*")) {

					guardarAsignaturas(asignaturas, cadena);

					cadena = " "; // Reiniciamos la cadena para almacenar otro alumno distinto

					bfRead = bf.readLine();

				}

				cadena = cadena + bfRead + "&";

			}
			// ultima
			guardarAsignaturas(asignaturas, cadena); // Recogemos la ultima asignatura // persona
			bf.close();

		} catch (Exception excepcion)

		{
			System.err.println(excepcion + "LeerAsignaturas"); // Error si no existe el archivo
			return -1;
		}

		/*
		 * for (Entry<String, Asignaturas> prueba : asignaturas.entrySet()){ String
		 * clave = prueba.getKey(); TreeMap<Integer, Grupo> valor =
		 * prueba.getValue().getListaGruposTeoricos(); for(Entry<Integer, Grupo>
		 * prueba2: valor.entrySet()) { String Aula=prueba2.getValue().getAula();
		 * System.out.println(clave+"  ->  "+Aula); }
		 * 
		 * }
		 */

		return 0;
	}

	/**
	 * Función para leer todas las Aulas del sistema.
	 * 
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 * @return retorna si hay errores
	 */
	public static int leerAulas(Lista lista_todo) {

		// VAMOS A LEER LOS ASIGNATURAS
		TreeMap<String, Aulas> aulas = lista_todo.getAulas(); // Accedemos al treemap de personas
		try {

			BufferedReader bf = new BufferedReader(new FileReader("aulas.txt")); // Leemos el archivo de texto

			String cadena = " ";
			String bfRead;

			while ((bfRead = bf.readLine()) != null) // Leemos el archivo
			{
				if (bfRead.contains("*")) // Leemos todos los datos de un alumno
				{
					aulas.put(cogerReferencia(cadena), new Aulas(cadena)); // Almacenamos una persona con el
																			// dni como identificador
					cadena = " "; // Reiniciamos la cadena para almacenar otro alumno distinto
					bfRead = bf.readLine();
				}
				cadena = cadena + bfRead + "&";

			}
			aulas.put(cogerReferencia(cadena), new Aulas(cadena)); // Cogemos la ultima persona
			bf.close();

		} catch (Exception excepcion)

		{
			System.err.println(excepcion); // Error si no existe el archivo
			return -1;
		}

		return 0;
	}

	/**
	 * Función para recoger el dni de una persona.
	 * 
	 * @param datos
	 *            datos para coger la referencia
	 * @return retorna el dni
	 */
	public static String cogerReferencia(String datos) {
		String dni = datos.split("\\&")[0].trim();
		return dni;

	}

	/**
	 * Función para leer el archivo de ejecución y procesar los datos para después
	 * proceder con la ejecución de las instrucciones
	 * 
	 * @param personas
	 *            contiene a las personas del sistema
	 */

	public static void leerEjecucion(Lista personas) {
		Scanner punteroLectura = null;
		String comandoAEjecutar = "";

		Scanner punteroPrueba = null;

		try {
			punteroLectura = new Scanner(new FileInputStream("ejecucion.txt"));
			punteroPrueba = new Scanner(new FileInputStream("ejecucion.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("Fichero inexistente");
			System.exit(-1);
		}

		while (punteroLectura.hasNextLine() && punteroPrueba.hasNextLine()) {
			comandoAEjecutar = "";
			punteroLectura.useDelimiter(" ");

			String lecturaPrueba = punteroPrueba.nextLine();
			if (lecturaPrueba.charAt(0) == '*')
				punteroLectura.nextLine();

			else if (lecturaPrueba.length() == 0)
				return;

			else {

				comandoAEjecutar = punteroLectura.nextLine();
				seleccionarFuncion(comandoAEjecutar, punteroLectura, personas);
			}
		}

		punteroLectura.close();

	}

	/**
	 * Función para leer todos los datos sobre la evaluación de los alumnos
	 * presentes en el archivo de texto
	 * 
	 * @param ficheroA
	 *            fichero de notas teoría
	 * @param ficheroB
	 *            fichero de notas práctica
	 * @param notasA
	 *            treemap para almacenar las notas de teoría y los alumnos
	 * @param notasB
	 *            treemap para almacenar las notas de práctica y los alumnos
	 */

	public static void leerNotas(TreeMap<String, Float> notasA, TreeMap<String, Float> notasB, String ficheroA,
			String ficheroB) {

		guardarNotas(ficheroA + ".txt", notasA);
		guardarNotas(ficheroB + ".txt", notasB);

	}

	/**
	 * Función para leer el curso académico actual.
	 * 
	 * @param cursoacademico
	 *            curso actual
	 * @return cursoacademico devuelve el curso actual
	 */

	public static String leerCurso(String cursoacademico) {
		try {

			BufferedReader bf = new BufferedReader(new FileReader("Curso_Academico.txt")); // Leemos el archivo de texto

			cursoacademico = bf.readLine().trim();
			bf.close();
		} catch (Exception excepcion)

		{
			System.err.println(excepcion); // Error si no existe el archivo
			return null;
		}

		return cursoacademico;

	}

	/**
	 * Una vez leído el archivo de ejecución se procede a seleccionar la función que
	 * ha de ejecutar el programa.
	 * 
	 * @param comandoAEjecutar
	 *            linea leída con el comando que se quiere ejecutar
	 * @param punteroLectura
	 *            Puntero de lectura
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void seleccionarFuncion(String comandoAEjecutar, Scanner punteroLectura, Lista lista_todo) {

		comandoAEjecutar = comandoAEjecutar.replaceAll("\\s+", " ");
		String[] datosSeparados = comandoAEjecutar.split(" ");

		if (datosSeparados[1].trim().equals("InsertaPersona")) { // Primera funcion

			String nya = "";
			char comillas = '"';
			datosSeparados[4] = datosSeparados[4].replace(comillas, ' '); // Vamos a rescatar el nombre y a guardarlo

			nya += datosSeparados[4].trim(); // Quitamos las comillas

			nya += " " + datosSeparados[5];
			datosSeparados[6] = datosSeparados[6].replace(comillas, ' ');
			nya += " " + datosSeparados[6].trim(); // Nombre guardado
			String perfil = datosSeparados[2];
			String dni = datosSeparados[3];
			String fechaNac = datosSeparados[7];
			String fechaIng = " ";
			String categoria = " ";
			String departamento = " ";

			if (perfil.trim().equals("profesor")) {
				if (datosSeparados.length != 10) {
					new Avisos("IP", "Numero de argumentos incorrecto"); // Aviso
					return;
				}
				categoria = datosSeparados[8];
				departamento = datosSeparados[9];
			} else
				fechaIng = datosSeparados[8];

			Funciones.insertaPersona(nya, dni, perfil, fechaNac, fechaIng, categoria, departamento, lista_todo);

		}

		else if (datosSeparados[1].trim().equals("AsignaGrupo")) {
			String perfil = datosSeparados[2];
			String dni = datosSeparados[3];
			String siglas = datosSeparados[4];
			String tipo = datosSeparados[5];
			String grupo = datosSeparados[6];

			Funciones.asignaGrupo(perfil, dni, siglas, tipo, grupo, lista_todo);
		}

		else if (datosSeparados[1].trim().equals("CreaGrupoAsig")) {
			String siglas = datosSeparados[2];
			String tipo = datosSeparados[3];
			String ID = datosSeparados[4];
			String dia = datosSeparados[5];
			String hora = datosSeparados[6];
			String aula = datosSeparados[7];

			Funciones.crearGrupo(siglas, tipo, ID, dia, hora, aula, lista_todo);
		}

		else if (datosSeparados[1].trim().equals("Matricula")) // Funcion Matricular alumno
		{
			String dni = datosSeparados[2].trim();
			String siglas = datosSeparados[3].trim();

			Funciones.matriculaAlumno(dni, siglas, lista_todo);
			/*
			 * TreeMap<String, Alumnos> alumnos = lista_todo.getAlumnos(); for
			 * (Entry<String, Alumnos> prueba : alumnos.entrySet()){ String clave =
			 * prueba.getKey(); TreeMap<Integer, Docencia> nombreasd =
			 * prueba.getValue().getlistaDocenciaRecibida();
			 * 
			 * for (Entry<Integer, Docencia> prueba2 : nombreasd.entrySet()){ int clave2 =
			 * prueba2.getKey(); char nombre2 =prueba2.getValue().gettipogrupo();
			 * System.out.println(clave+"--->"+clave2+"    "+nombre2); } }
			 */
		}

		else if (datosSeparados[1].trim().equals("Expediente")) {
			String dni = datosSeparados[2].trim();
			String salida = datosSeparados[3].trim();
			Funciones.expedienteAlumno(dni, salida, lista_todo);

		}

		else if (datosSeparados[1].trim().equals("OcupacionAula")) {
			String aula = datosSeparados[2].trim();
			Funciones.ocupacionAulas(aula, lista_todo);

		}

		else if (datosSeparados[1].trim().equals("Evalua")) {
			String asignatura = datosSeparados[2].trim();
			String ficheroA = datosSeparados[3].trim();
			String ficheroB = datosSeparados[4].trim();

			Funciones.evaluaAlumno(asignatura, ficheroA, ficheroB, lista_todo);

		} else {
			new Avisos(datosSeparados[1]);
			return;
		}

	}

	/**
	 * Función que almacena los grupos teóricos y prácticos del archivo asignaturas.
	 * Se invoca varias veces desde la función leerAsignaturas para realizar lo
	 * anteriormente descrito.
	 * 
	 * @param asignaturas
	 *            treemap de asignaturas y así poder guardar los datos
	 * @param cadena
	 *            linea del archivo de texto
	 */

	public static void guardarAsignaturas(TreeMap<String, Asignaturas> asignaturas, String cadena) {
		String[] datosSeparados = cadena.split("\\&"); // DATOS ASIGNATURA
		String siglas = datosSeparados[0].trim();
		String nombre = datosSeparados[1].trim();
		String curso = datosSeparados[2].trim();
		int cuatrimestre = Integer.parseInt(datosSeparados[3].trim());
		String coord_dni = datosSeparados[4].trim();
		String prerequisitosaux = datosSeparados[5].trim();
		String[] prerequisitos = prerequisitosaux.split("\\;"); // Separamos los prerrequisitos en un array
		Asignaturas asignatura = new Asignaturas(nombre, siglas, curso, coord_dni, prerequisitos, cuatrimestre);
		asignaturas.put(cogerReferencia(cadena), asignatura);

		TreeMap<Integer, Grupo> listaGruposTeoricos = asignatura.getListaGruposTeoricos();
		TreeMap<Integer, Grupo> listaGruposPracticos = asignatura.getListaGruposPracticos();
		int duracionA = Integer.parseInt(datosSeparados[6].trim()); // DATOS DEL GRUPO DE LA ASIGNATURA
		int duracionB = Integer.parseInt(datosSeparados[7].trim());
		String GruposAaux = datosSeparados[8].trim();

		GruposAaux = GruposAaux.replaceAll("\\s+", " "); // Reemplazo todos los posibles dobles espacios por uno

		String[] GruposA = GruposAaux.split("\\;"); // Separo los grupos separados por punto y coma

		for (String v : GruposA) {

			String vaux = v.trim(); // En cada uno de esos grupos miro que no haya espacios en el principio o en el
									// final
			String[] GruposASeparado = vaux.split(" "); // Separo los datos de cada grupo en otro array

			// for(String o:GruposASeparado)System.out.println(o);;
			int IDGrupo = Integer.parseInt(GruposASeparado[0].trim()); // Recojo los datos de los grupos

			char Dia = GruposASeparado[1].trim().charAt(0);
			int Hora = Integer.parseInt(GruposASeparado[2].trim());
			String Aula = GruposASeparado[3];

			Grupo grupo = new Grupo(duracionA, 'A', IDGrupo, Dia, Hora, Aula); // Los guardo en la clase grupo
			listaGruposTeoricos.put(IDGrupo, grupo); // Le asigno a la asignatura los grupos teoricos

		}

		/*
		 * for (Entry<Integer, Grupo> prueba : listaGruposTeoricos.entrySet()){ int
		 * clave = prueba.getKey(); Grupo valor = prueba.getValue();
		 * System.out.println(clave+"  ->  "+valor.toString()); }
		 */

		asignatura.setListaGruposTeoricos(listaGruposTeoricos);

		// Ahora voy a guardar los grupos practicos

		String GruposBaux = datosSeparados[9].trim();

		GruposBaux = GruposBaux.replaceAll("\\s+", " "); // Reemplazo todos los posibles dobles espacios por uno

		String[] GruposB = GruposBaux.split("\\;");

		for (String v : GruposB) {

			String vaux = v.trim(); // En cada uno de esos grupos miro que no haya espacios en el principio o en el
									// final
			String[] GruposBSeparado = vaux.split(" "); // Separo los datos de cada grupo en otro array

			// for(String o:GruposASeparado)System.out.println(o);;
			int IDGrupo = Integer.parseInt(GruposBSeparado[0].trim()); // Recojo los datos de los grupos

			char Dia = GruposBSeparado[1].trim().charAt(0);
			int Hora = Integer.parseInt(GruposBSeparado[2].trim());
			String Aula = GruposBSeparado[3];

			Grupo grupo = new Grupo(duracionB, 'B', IDGrupo, Dia, Hora, Aula); // Los guardo en la clase grupo
			listaGruposPracticos.put(IDGrupo, grupo); // Le asigno a la asignatura los grupos teoricos

		}

		asignatura.setListaGruposPracticos(listaGruposPracticos);
		return;
	}

	/**
	 * Se invoca desde leerPersonas. Permite guardar los profesores en el treemap de
	 * profesores y sus datos específicos que no están incluídos en persona.
	 * 
	 * @param prof
	 *            treemap de profesores
	 * @param cadena
	 *            línea del archivo
	 */

	public static void guardarDatosProfesor(TreeMap<String, Profesores> prof, String cadena) {
		TreeMap<Integer, POD> listaCoordinacion = new TreeMap<Integer, POD>();
		TreeMap<Integer, Docencia> listaGruposAsignados = new TreeMap<Integer, Docencia>();

		String[] datosSeparados = cadena.split("&");

		String nombreyapellidos = datosSeparados[1].trim();
		String dni = datosSeparados[0].trim();
		String fechadenacimiento = datosSeparados[2].trim();
		String categoria = datosSeparados[3].trim();
		String departamento = datosSeparados[4].trim();
		if (datosSeparados.length == 6) {
			String gruposaux = datosSeparados[5].trim();
			gruposaux = gruposaux.replaceAll("\\s+", " ");
			String[] gruposSeparados = gruposaux.split(";");

			int i = 1;

			for (String v : gruposSeparados) {
				String vaux = v.trim();
				String datosGrupo[] = vaux.split(" ");
				String siglas = datosGrupo[0];
				char tipogrupo = datosGrupo[1].trim().charAt(0);
				int IDGrupo = Integer.parseInt(datosGrupo[2]);
				Docencia gruposAsignados = new Docencia('I', siglas, tipogrupo, IDGrupo); // I de docencia impartida
				listaGruposAsignados.put(i, gruposAsignados);
				i++;
				// System.out.println(nombreyapellidos + " " + dni+ " " + fechadenacimiento+ " "
				// + categoria+ " " + departamento+ " " + siglas+ " " + tipogrupo+ " " + IDGrupo
				// );

			} // Ya tengo todos los datos de profesor.txt

			// Ahora voy a recoger los del pod
		}
		try {

			BufferedReader bf = new BufferedReader(new FileReader("pod.txt")); // Leemos el archivo de texto

			String cadenapod = " ";
			String bfRead;

			while ((bfRead = bf.readLine()) != null) // Leemos el archivo
			{

				if (bfRead.trim().equals("*")) // Leemos todos los datos de un profesor
				{

					cadenapod = cadenapod.trim();

					String[] cadenapodseparada = cadenapod.split("&");

					if (cadenapodseparada[0].equals(dni)) {
						cadenapodseparada[1] = cadenapodseparada[1].replaceAll("\\s+", " ");
						String[] podseparado = cadenapodseparada[1].split(";");

						for (String o : podseparado) {
							o = o.trim();
							String[] datosind = o.split(" ");
							String siglaspod = datosind[0].trim();
							char tipogrupopod = datosind[1].trim().charAt(0);
							int IDGrupopod = Integer.parseInt(datosind[2].trim());

							POD pod = new POD(siglaspod, tipogrupopod, IDGrupopod);
							listaCoordinacion.put(IDGrupopod, pod);

						}
						break;

					}
					cadenapod = " ";
					bfRead = bf.readLine();

				}

				cadenapod = cadenapod + bfRead + "&";

			}
			cadenapod = cadenapod.trim();

			String[] cadenapodseparada = cadenapod.split("&");

			if (cadenapodseparada[0].equals(dni)) {
				cadenapodseparada[1] = cadenapodseparada[1].replaceAll("\\s+", " ");
				String[] podseparado = cadenapodseparada[1].split(";");

				for (String o : podseparado) {
					o = o.trim();
					String[] datosind = o.split(" ");
					String siglaspod = datosind[0].trim();
					char tipogrupopod = datosind[1].trim().charAt(0);
					int IDGrupopod = Integer.parseInt(datosind[2].trim());

					POD pod = new POD(siglaspod, tipogrupopod, IDGrupopod);
					listaCoordinacion.put(IDGrupopod, pod);
				}

			}
			bf.close();

		} catch (Exception e)

		{
			System.err.println("Fichero inexistente"); // Error si no existe el archivo
			return;
		}

		Profesores profesor = new Profesores(nombreyapellidos, dni, fechadenacimiento, categoria, departamento);
		profesor.setListaGruposAsignados(listaGruposAsignados);
		profesor.setListaCoordinacion(listaCoordinacion);
		prof.put(dni, profesor);

		return;

	}

	/**
	 * Se invoca desde leerPersonas. Permite guardar los alumnos en el treemap de
	 * alumnos y sus datos específicos que no están incluídos en persona.
	 * 
	 * @param alumnos
	 *            treemap de alumnos
	 * @param cadenaaux
	 *            línea del archivo
	 */

	public static void guardarDatosAlumno(TreeMap<String, Alumnos> alumnos, String cadenaaux) {
		String cadena;
		cadena = cadenaaux;

		if (cadenaaux.endsWith("&&")) {
			cadena = cadenaaux.concat("@");
		} // Lo hago para que siempre cree datosSeparados[6]

		String[] datosSeparados = cadena.trim().split("&");
		String nombreyapellidos = datosSeparados[1].trim();
		String dni = datosSeparados[0].trim();
		String fechadenacimiento = datosSeparados[3].trim();
		String email = datosSeparados[2].trim();
		String fechadeingreso = datosSeparados[4].trim();
		Alumnos alumno = new Alumnos(nombreyapellidos, dni, fechadenacimiento, email, fechadeingreso);
		String asigaux = datosSeparados[5].trim();
		asigaux = asigaux.replaceAll("\\s+", " ");
		String[] AsignaturasSuperadas = asigaux.split(";");

		String grupaux = datosSeparados[6].trim();
		grupaux = grupaux.replaceAll("\\s+", " ");
		String[] GruposMatriculados = grupaux.split(";");
		TreeMap<String, AsignaturasSuperadas> ASuperadas = alumno.getListaAsignaturasSuperadas();
		TreeMap<Integer, Docencia> Docencia = alumno.getlistaDocenciaRecibida();
		if (!datosSeparados[5].isEmpty()) { // Si existen asignaturas superadas
			for (String o : AsignaturasSuperadas) {
				o = o.trim();
				o = o.replaceAll("\\s+", " ");
				String[] AsigSupSeparadas = o.split(" ");
				String siglas = AsigSupSeparadas[0].trim();
				String cursoacademico = AsigSupSeparadas[1].trim();
				float nota = Float.parseFloat(AsigSupSeparadas[2]);
				AsignaturasSuperadas ASup = new AsignaturasSuperadas(siglas, cursoacademico, nota);
				ASuperadas.put(siglas, ASup);

				// System.out.println(dni +"-->"+siglas + " " + cursoacademico + " " + nota);
			}
		}

		if (!datosSeparados[6].isEmpty()) { // Si existen asignaturas matriculadas
			int i = 1;
			for (String v : GruposMatriculados) {
				v = v.trim();
				v = v.replaceAll("\\s+", " ");
				char docencia = 'R';
				String[] GrupMatSeparados = v.split(" ");
				String siglas = GrupMatSeparados[0].trim();

				if (GrupMatSeparados.length == 3) { // Matriculado en la asignatura pero sin grupo asignado
					char tipogrupo = GrupMatSeparados[1].trim().charAt(0);
					int IDGrupo = Integer.parseInt(GrupMatSeparados[2]);

					Docencia Doc = new Docencia(docencia, siglas, tipogrupo, IDGrupo);
					Docencia.put(i, Doc);

					i++;
					// System.out.println(dni +"-->"+siglas + " " + tipogrupo + " " + IDGrupo+"
					// "+docencia);
				} else {
					int IDGrupo = 0; // ID para indicar que no tiene grupo asignado
					char tipogrupo = '\0'; // No tiene grupo asignado
					Docencia Doc = new Docencia(docencia, siglas, tipogrupo, IDGrupo);
					Docencia.put(i, Doc);
				}

			}
		}
		alumno.setlistaDocenciaRecibida(Docencia);
		alumno.setlistaAsignaturasSuperadas(ASuperadas);
		alumnos.put(dni, alumno);

	}

	/**
	 * Permite guardar las notas y los alumnos de los archivos para evaluar.
	 * 
	 * @param nombrefichero
	 *            si se trata del fichero A o B
	 * @param notas
	 *            treemap de notas y de alumnos
	 */

	public static void guardarNotas(String nombrefichero, TreeMap<String, Float> notas) {

		try {

			BufferedReader bf = new BufferedReader(new FileReader(nombrefichero));

			String bfRead;

			while ((bfRead = bf.readLine()) != null) // Leemos el archivo
			{

				bfRead = bfRead.replaceAll("\\s+", " ");
				String[] datosSeparados = bfRead.trim().split(" ");
				String dni = datosSeparados[0].trim();
				float nota = Float.parseFloat(datosSeparados[1].trim());

				notas.put(dni, nota);

			}
			bf.close();

		} catch (Exception e)

		{
			System.err.println("Fichero inexistente"); // Error si no existe el archivo
			return;
		}

		return;
	}

	/**
	 * Busca la línea de los archivos de evaluación en los que se ha producido el
	 * error
	 * 
	 * @param cadena
	 *            línea del archivo
	 * @return retorna la línea del error
	 */

	public static int buscarLinea(String cadena) {
		int linea = 1;
		try {

			BufferedReader bf = new BufferedReader(new FileReader("Notas_A.txt"));

			String bfRead;

			while ((bfRead = bf.readLine()) != null) // Leemos el archivo
			{

				bfRead = bfRead.replaceAll("\\s+", " ");
				String[] datosSeparados = bfRead.trim().split(" ");
				String dni = datosSeparados[0].trim();
				if (dni.equals(cadena)) {
					bf.close();
					return linea;
				}
				linea++;
			}
			bf.close();
		} catch (Exception e)

		{
			System.err.println("Fichero inexistente"); // Error si no existe el archivo
			return 0;
		}
		return 0;
	}

	/**
	 * Función que permite guardar todos los datos después de la ejecución del
	 * programa. Para esto reescribe todos los archivos sujetos a cambios
	 * 
	 * @param lista_todo
	 *            lista que contiene todas las listas relativas al sistema
	 */

	public static void escribirFich(Lista lista_todo) {

		TreeMap<String, Alumnos> alumnos = lista_todo.getAlumnos();
		TreeMap<String, Profesores> profesores = lista_todo.getProfesores();
		TreeMap<String, Asignaturas> asignaturas = Lista.getAsignaturas();

		File fichero = new File("alumnos.txt");
		fichero.delete();

		try {
			FileWriter punteroEscritura = new FileWriter(fichero);
			int contador = 1;
			for (Entry<String, Alumnos> prueba : alumnos.entrySet()) {
				TreeMap<Integer, Docencia> asigalum = prueba.getValue().getlistaDocenciaRecibida();
				TreeMap<String, AsignaturasSuperadas> asisuper = prueba.getValue().getListaAsignaturasSuperadas();

				punteroEscritura.write(prueba.getKey());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getnombreyapellidos());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().email());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getFechaDeNacimiento());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().FechaIngreso());
				punteroEscritura.write(System.getProperty("line.separator"));

				int cont = 0;
				int id;
				for (Entry<String, AsignaturasSuperadas> prueba2 : asisuper.entrySet()) {
					if (cont != 0)
						punteroEscritura.write("; ");
					punteroEscritura.write(prueba2.getValue().getsiglas() + " " + prueba2.getValue().getcursoacademico()
							+ " " + prueba2.getValue().getnota());
					cont++;
				}

				punteroEscritura.write(System.getProperty("line.separator"));

				cont = 0;
				for (Entry<Integer, Docencia> prueba2 : asigalum.entrySet()) {
					if (cont != 0)
						punteroEscritura.write("; ");
					id = Math.round(prueba2.getValue().getIDGrupo());
					if (id == 0)
						punteroEscritura.write(prueba2.getValue().getsiglas());
					else
						punteroEscritura.write(
								prueba2.getValue().getsiglas() + " " + prueba2.getValue().gettipogrupo() + " " + id);
					cont++;

				}
				if (contador < alumnos.size())
					punteroEscritura
							.write(System.getProperty("line.separator") + "*" + System.getProperty("line.separator"));
				contador++;
			}

			punteroEscritura.close();
		} catch (IOException e) {

		}

		fichero = new File("profesores.txt");
		fichero.delete();

		try {
			FileWriter punteroEscritura = new FileWriter(fichero);
			int contador = 1;
			for (Entry<String, Profesores> prueba : profesores.entrySet()) {
				TreeMap<Integer, Docencia> grupasig = prueba.getValue().getListaGruposAsignados();

				punteroEscritura.write(prueba.getKey());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getnombreyapellidos());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getFechaDeNacimiento());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getCategoria());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getDepartamento());
				punteroEscritura.write(System.getProperty("line.separator"));

				int cont = 0;
				int id;
				for (Entry<Integer, Docencia> prueba2 : grupasig.entrySet()) {
					if (cont != 0)
						punteroEscritura.write("; ");
					id = Math.round(prueba2.getValue().getIDGrupo());
					punteroEscritura
							.write(prueba2.getValue().getsiglas() + " " + prueba2.getValue().gettipogrupo() + " " + id);
					cont++;

				}

				if (contador < profesores.size())
					punteroEscritura
							.write(System.getProperty("line.separator") + "*" + System.getProperty("line.separator"));
				contador++;
			}

			punteroEscritura.close();
		} catch (IOException e) {

		}

		fichero = new File("asignaturas.txt");

		try {
			FileWriter punteroEscritura = new FileWriter(fichero);
			int contador = 1;
			for (Entry<String, Asignaturas> prueba : asignaturas.entrySet()) {

				TreeMap<Integer, Grupo> gruposA = prueba.getValue().getListaGruposTeoricos();
				TreeMap<Integer, Grupo> gruposB = prueba.getValue().getListaGruposPracticos();

				punteroEscritura.write(prueba.getKey());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getnombre());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getcurso());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getcuatrimestre());
				punteroEscritura.write(System.getProperty("line.separator") + prueba.getValue().getcoord_dni());

				punteroEscritura.write(System.getProperty("line.separator"));
				int cont = 0;
				for (int i = 0; i < prueba.getValue().getprerequisitos().length; i++) {
					if (cont != 0)
						punteroEscritura.write(";");
					punteroEscritura.write(prueba.getValue().getprerequisitos()[i]);
					cont++;

				}

				for (Entry<Integer, Grupo> prueba2 : gruposA.entrySet()) {
					punteroEscritura
							.write(System.getProperty("line.separator") + prueba2.getValue().getDuracionGrupo());
					break;
				}
				for (Entry<Integer, Grupo> prueba2 : gruposB.entrySet()) {
					punteroEscritura
							.write(System.getProperty("line.separator") + prueba2.getValue().getDuracionGrupo());
					break;
				}

				punteroEscritura.write(System.getProperty("line.separator"));

				cont = 0;
				for (Entry<Integer, Grupo> prueba2 : gruposA.entrySet()) {
					if (cont != 0)
						punteroEscritura.write("; ");
					punteroEscritura.write(prueba2.getValue().getIDGrupo() + " " + prueba2.getValue().getDia() + " "
							+ prueba2.getValue().getHora() + " " + prueba2.getValue().getAula());
					cont++;
				}

				punteroEscritura.write(System.getProperty("line.separator"));

				cont = 0;
				for (Entry<Integer, Grupo> prueba2 : gruposB.entrySet()) {
					if (cont != 0)
						punteroEscritura.write("; ");
					punteroEscritura.write(prueba2.getValue().getIDGrupo() + " " + prueba2.getValue().getDia() + " "
							+ prueba2.getValue().getHora() + " " + prueba2.getValue().getAula());
					cont++;
				}

				if (contador < asignaturas.size())
					punteroEscritura.write(System.getProperty("line.separator")
							+ "*************************************************************************"
							+ System.getProperty("line.separator"));
				contador++;
			}
			punteroEscritura.close();
		} catch (IOException e) {

		}
	}

}
