import java.util.*;

public class TablaDeSimbolos extends Object {
	private static Hashtable _tablaTokens = new Hashtable();
	
	private static ArrayList<Integer> _enteros	= new ArrayList();
	private static ArrayList<Integer> _dobles	= new ArrayList();
	private static ArrayList<Integer> _cadenas	= new ArrayList();
	private static ArrayList<Integer> _booleanos	= new ArrayList();
	
	public static void InsertarSimbolo(Token identificador, int tipo) {
		_tablaTokens.put(identificador.image, tipo);
	}
	
	public static void InicializaListaCompatibilidad() {
		_enteros.add(3);
		_enteros.add(10);
		
		_dobles.add(3);
		_dobles.add(4);
		_dobles.add(10);
		_dobles.add(11);
		
		_cadenas.add(12);
		
		_booleanos.add(25);
		_booleanos.add(26);
	}
	/*
	public static String RevisaAsignacion(Token tokenIzq, Token tokenAsig) {
		int tipoIdentificador1;
		int tipoIdentificador2;
		
		if(tokenIzq.kind != 10 && tokenIzq.kind != 11) {
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
		
		if(tokenAsig.kind == 42) {
			try {
				tipoIdentificador2 = (Integer)_tablaTokens.get(tokenAsig.image);
			}
			catch(Exception e) {
				return "Error: El identificador " + tokenAsig.image + " no ha sido declarado\r\nLinea: " + tokenAsig.beginLine;
			}
		}
		else if(tokenAsig.kind == 10 || tokenAsig.kind == 11 || tokenAsig.kind == 12) {
			tipoIdentificador2 = tokenAsig.kind;
		}
		else {
			tipoIdentificador2 = 0;
		}
	}*/
}