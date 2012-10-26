package cell;

import java.util.List;
/** 
 * Clase que representa el tablero de juego
 * 
 * @author santi698
 *
 */
public class Board {
	/**
	 * El contenido del tablero de modela con una matriz de listas, 
	 * donde cada elemento es una capa del tablero
	 */
	Board(String s) {
		dataMatrix = parse(s);
	}
	private Cell[][] dataMatrix;
	//TODO toda la clase basicamente :P
	public static Cell[][] parse (String s) {
		//TODO escribir
		return null;
	}
	public Cell getCell(int x, int y) {
		return dataMatrix[x][y];
	}
	
}
