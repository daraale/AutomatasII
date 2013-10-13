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
		_tablaTokens.put(identificador.image, tipo);
	}
	
	public static void InicializaListaCompatibilidad() {
		_enteros.add(44);
		_enteros.add(48);
		
		_dobles.add(44);
		_dobles.add(45);
		_dobles.add(48);
		_dobles.add(50);
		
		_caracteres.add(46);
		_caracteres.add(52);
		
		_cadenas.add(47);
		_cadenas.add(51);

		_booleanos.add(54);
		_booleanos.add(55);
		_booleanos.add(56);
	}
	
	public static String RevisaAsignacion(Token tokenIzq, Token tokenAsig) {
		int tipoIdentificador1;
		int tipoIdentificador2;
		
		if(tokenIzq.kind != 48 && tokenIzq.kind != 50) {
			try {
				tipoIdentificador1 = (Integer)_tablaTokens.get(tokenIzq.image);
			}
			catch(Exception e) {
				return "Error: El identificador " + tokenIzq.image + " no ha sido declarado\r\nLinea: " + tokenIzq.beginLine;
			}
		}
		else {
			tipoIdentificador1 = 0;
		}
		
		if(tokenAsig.kind == 49) {
			try {
				tipoIdentificador2 = (Integer)_tablaTokens.get(tokenAsig.image);
			}
			catch(Exception e) {
				return "Error: El identificador " + tokenAsig.image + " no ha sido declarado\r\nLinea: " + tokenAsig.beginLine;
			}
		}
		else if(tokenAsig.kind == 48 || tokenAsig.kind == 50 || tokenAsig.kind == 51 || tokenAsig.kind == 52 || tokenAsig.kind == 55 || tokenAsig.kind == 56) {
			tipoIdentificador2 = tokenAsig.kind;
		}
		else {
			tipoIdentificador2 = 0;
		}

		switch(tipoIdentificador1) {
			case 44:			
				if( _enteros.contains(tipoIdentificador2) ) {
					return " ";
				}
				else {
					return "Error: No se puede convertir " + tokenAsig.image + " a Entero \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			case 45:
				if( _dobles.contains(tipoIdentificador2) ) {
					return " ";
				}
				else {
					return "Error: No se puede convertir " + tokenAsig.image + " a Doble \r\nLinea: " + tokenIzq.beginLine;
				}
			case 46:
				validaChar++;
				if(validaChar < 2) {
					if( _caracteres.contains(tipoIdentificador2) ) {
						return " ";
					}
					else {
						return "Error: No se puede convertir " + tokenAsig.image + " a Caracter \r\nLinea: " + tokenIzq.beginLine;
					}
				}
				else {
					return "Error: No se puede asignar mas de un valor a un caracter \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			case 47:
				if( _cadenas.contains(tipoIdentificador2) ) {
					return " ";
				}
				else {
					return "Error: No se puede convertir " + tokenAsig.image + " a Cadena \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			case 54:
				if( _booleanos.contains(tipoIdentificador2) ) {
					return " ";
				}
				else {
					return "Error: No se puede convertir " + tokenAsig.image + " a Booleano \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			default:
				return "El identificador " + tokenIzq.image + " no ha sido declarado \r\nLinea: " + tokenIzq.beginLine;
				break;
		}
	}

	public static String RevisaVariable(Token token) {
		try {
			int tipoIdentificador1 = (Integer)_tablaTokens.get(token.image);
			return " ";
		}
		catch(Exception ex) {
			return "Error: El identificador " + token.image + " no ha sido declarado \r\nLinea: " + token.beginLine;
		}
	}
}