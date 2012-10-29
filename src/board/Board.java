package board;

import java.awt.Point;
import java.util.Arrays;

import cell.*;
import cell.Character;

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
	public final int rows, columns;
	private Cell[][] dataMatrix;
	
	Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		dataMatrix = new Cell[rows][columns];
	}
	//TODO toda la clase basicamente :P
	public Board(String[] s) {
		this(s.length -1 ,s[0].length()); //FIXME Parche feo
		for (int i = 0; i < rows; i++) {
			if (s[i].length() != columns)
				throw new RuntimeException(); //FIXME provisorio
			for (int j = 0; j < columns; j++) {
				char c = s[i].charAt(j);
				dataMatrix[i][j] = charToCell(c);
			}
		}
	}
	private static Cell charToCell(char c) {
		switch (c) {
		case 'T': return new Tree();
		case '#': return new Water();
		case 'K': return new Interruptor();
		case 'C': return new EmptyCell(new IceBlock());
		case 'B': return new EmptyCell(new Box());
		case 'G': return new Destino();
		case '@': return new EmptyCell(new Character());
		case ' ': return new EmptyCell();
		default: return null;
		}
	}
	public Cell getCell(int x, int y) {
		return dataMatrix[x][y];
	}
	public Cell getCell(Point p) {
		return getCell(p.x, p.y);
	}
	public Point getCharacterPosition(Board b) {
		for (int i = 0; i < rows; i++) 
			for (int j = 0; j < columns; j++) {
				if (dataMatrix[i][j].getContent() instanceof Character)
					return new Point (j, i);
			}
		// TODO Auto-generated method stub
		return null;
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
