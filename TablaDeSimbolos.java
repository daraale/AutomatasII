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
	
	public static String RevisaAsignacion(Token tokenIzq, Token tokenAsig) {
		int tipoIdentificador1;
		int tipoIdentificador2;
		
		if(tokenIzq.kind != 51 && tokenIzq.kind != 53) {
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
		
		if(tokenAsig.kind == 52) {
			try {
				tipoIdentificador2 = (Integer)_tablaTokens.get(tokenAsig.image);
			}
			catch(Exception e) {
				return "Error: El identificador " + tokenAsig.image + " no ha sido declarado\r\nLinea: " + tokenAsig.beginLine;
			}
		}
		else if(tokenAsig.kind == 49 || tokenAsig.kind == 50 || tokenAsig.kind == 51 || tokenAsig.kind == 53 || tokenAsig.kind == 54 || tokenAsig.kind == 55) {
			tipoIdentificador2 = tokenAsig.kind;
		}
		else {
			tipoIdentificador2 = 0;
		}

		String mensaje = "";
		switch(tipoIdentificador1) {
			case 44:			
				if( _enteros.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + tokenAsig.image + " a Entero \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			case 45:
				if( _dobles.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + tokenAsig.image + " a Doble \r\nLinea: " + tokenIzq.beginLine;
				}
			case 46:
				validaChar++;
				if(validaChar < 2) {
					if( _caracteres.contains(tipoIdentificador2) ) {
						mensaje = " ";
					}
					else {
						mensaje = "Error: No se puede convertir " + tokenAsig.image + " a Caracter \r\nLinea: " + tokenIzq.beginLine;
					}
				}
				else {
					mensaje = "Error: No se puede asignar mas de un valor a un caracter \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			case 47:
				if( _cadenas.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + tokenAsig.image + " a Cadena \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			case 48:
				if( _booleanos.contains(tipoIdentificador2) ) {
					mensaje = " ";
				}
				else {
					mensaje = "Error: No se puede convertir " + tokenAsig.image + " a Booleano \r\nLinea: " + tokenIzq.beginLine;
				}
				break;
			default:
				mensaje = "El identificador " + tokenIzq.image + " no ha sido declarado \r\nLinea: " + tokenIzq.beginLine;
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
			return "Error: El identificador " + token.image + " no ha sido declarado \r\nLinea: " + token.beginLine;
		}
	}
}