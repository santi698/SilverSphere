package board;

import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;

import cell.*;
import cell.Character;

/** 
 * Clase que representa el tablero de juego
 * 
 * @author santi698
 *
 */
public class Board implements Serializable{

	private static final long serialVersionUID = -6426728114234690441L;

	/**
	 * El contenido del tablero de modela con una matriz de listas, 
	 * donde cada elemento es una capa del tablero
	 */
	public final int rows, columns;
	private Cell[][] dataMatrix;
	private Cell charCell;
	private Cell targetCell;
	
	Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		dataMatrix = new Cell[rows][columns];
	}
	//TODO No se que falta, pero algo debe faltar.
	public Board(String[] s) throws InvalidLevelException {
		this(s.length -1 ,s[0].length()); //FIXME Parche feo 
		int chCount = 0, dCount = 0, ibCount = 0, intCount = 0;
		for (int i = 0; i < rows; i++) {
			if (s[i].length() != columns)
				throw new InvalidLevelException("El tablero debe ser rectangular.");
			for (int j = 0; j < columns; j++) {
				char c = s[i].charAt(j);
				Cell actualCell = charToCell(c);
				dataMatrix[i][j] = actualCell;
				switch (c) {
				case '@': chCount++; charCell = actualCell; break;
				case 'G': dCount++; targetCell = actualCell; break;
				case 'C': ibCount++; break;
				case 'K': intCount++; break;
				}
			}
		}
		if (chCount != 1 || ibCount == 0 || dCount != 1 || intCount > 1)
			throw new InvalidLevelException("Debe haber exactamente un personaje, un destino" +
					", al menos un cubo de hielo, y no más de un interruptor." +
					"\n Personajes: " + chCount + ", Destinos: " + dCount + ", Cubos de hielo: " +
							"" + ibCount + ", Interruptores:" + intCount + ".");
		}
	private static Cell charToCell(char c) throws InvalidLevelException {
		switch (c) {
		case 'T': return new Tree();
		case '#': return new Water();
		case 'K': return new Interruptor();
		case 'C': return new EmptyCell(new IceBlock());
		case 'B': return new EmptyCell(new Box());
		case 'G': return new Destino();
		case '@': return new EmptyCell(new Character());
		case ' ': return new EmptyCell();
		default: throw new InvalidLevelException("Caracter Inválido.");
		}
	}
	public Cell getCell(int x, int y) {
		return dataMatrix[x][y];
	}
	public Cell getCell(Point p) {
		return getCell(p.x, p.y);
	}
	public Cell getCharacter() {
		return charCell;
	}
	public Cell getTarget() {
		return targetCell;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Cell[] row : dataMatrix) {
			s.append(Arrays.toString(row));
			s.append("\n");
		}
		return s.toString();
	}
	
}