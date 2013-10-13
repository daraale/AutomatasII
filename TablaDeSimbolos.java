import java.util.*;

public class TablaDeSimbolos extends Object {
	public static int validaChar = 0;
	private static Hashtable _tablaTokens = new Hashtable();
	
	private static ArrayList<Integer> _enteros	= new ArrayList();
	private static ArrayList<Integer> _dobles	= new ArrayList();
	private static ArrayList<Integer> _cadenas	= new ArrayList();
	private static ArrayList<Integer> _caracteres = new ArrayList();
	private static ArrayList<Integer> _booleanos = new ArrayList();
	
	public static void InsertarSimbolo(Token identificador, int tipo) {
		try {
			int tipoIdentificador = (Integer)_tablaTokens.get(identificador.image);			
			System.out.println("Error: El identificador " + identificador.image + " ya ha sido declarado \t\tLinea: " + identificador.beginLine);
		}
		catch(Exception e) {
			_tablaTokens.put(identificador.image, tipo);
		}
	}
	
	public static void InicializaListaCompatibilidad() {
		_enteros.add(44);
		_enteros.add(51);
		
		_dobles.add(44);
		_dobles.add(45);
		_dobles.add(51);
		_dobles.add(53);
		
		_caracteres.add(46);
		_caracteres.add(55);
		
		_cadenas.add(47);
		_cadenas.add(54);

		_booleanos.add(48);
		_booleanos.add(49);
		_booleanos.add(50);
	}
	
	public static String RevisaAsignacion(Token token1, Token token2) {
		int tipoIdentificador1;
		int tipoIdentificador2;
		
		if(token1.kind != 49 && token1.kind != 50 && token1.kind != 51 && token1.kind != 53 && token1.kind != 54 && token1.kind != 55) {
			try {
				tipoIdentificador1 = (Integer)_tablaTokens.get(token1.image);
			}
			catch(Exception e) {
				return "Error: El identificador " + token1.image + " no ha sido declarado \t\tLinea: " + token1.beginLine;
			}
		}
		else {
			tipoIdentificador1 = 0;
		}
		
		if(token2.kind == 52) {
			try {
				tipoIdentificador2 = (Integer)_tablaTokens.get(token2.image);
			}
			catch(Exception e) {
				return "Error: El identificador " + token2.image + " no ha sido declarado \t\tLinea: " + token2.beginLine;
			}
		}
		else if(token2.kind == 49 || token2.kind == 50 || token2.kind == 51 || token2.kind == 53 || token2.kind == 54 || token2.kind == 55) {
			tipoIdentificador2 = token2.kind;
		}
		else {
			tipoIdentificador2 = 0;
		}

		String mensaje = "";
		switch( tipoIdentificador1 ) {
			case 44:			
				if( _enteros.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + token2.image + " a Entero \t\tLinea: " + token1.beginLine;
				}
				break;
			case 45:
				if( _dobles.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + token2.image + " a Doble \t\tLinea: " + token1.beginLine;
				}
			case 46:
				validaChar++;
				if(validaChar < 2) {
					if( _caracteres.contains(tipoIdentificador2) ) {
						mensaje = " ";
					}
					else {
						mensaje = "Error: No se puede convertir " + token2.image + " a Caracter \t\tLinea: " + token1.beginLine;
					}
				}
				else {
					mensaje = "Error: No se puede asignar mas de un valor a un caracter \t\tLinea: " + token1.beginLine;
				}
				break;
			case 47:
				if( _cadenas.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + token2.image + " a Cadena \t\tLinea: " + token1.beginLine;
				}
				break;
			case 48:
				if( _booleanos.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + token2.image + " a Booleano \t\tLinea: " + token1.beginLine;
				}
				break;
			default:
				mensaje = "El identificador " + token1.image + " no ha sido declarado \t\tLinea: " + token1.beginLine;
				break;
		}
		
		return mensaje;
	}

	public static String RevisaVariable(Token token) {
		try {
			int tipoIdentificador1 = (Integer)_tablaTokens.get(token.image);
			return " ";
		}
		catch(Exception ex) {
			return "Error: El identificador " + token.image + " no ha sido declarado \t\tLinea: " + token.beginLine;
		}
	}
}