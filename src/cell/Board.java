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
	private Cell[][] dataMatrix;
	//TODO toda la clase basicamente :P
	public Cell getCell(int x, int y) {
		return dataMatrix[x][y];
	}
}
