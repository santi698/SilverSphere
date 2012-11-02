package board;

import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;

import cell.Box;
import cell.Cell;
import cell.Character;
import cell.EmptyCell;
import cell.IceBlock;
import cell.IceBlockTarget;
import cell.MoveReturnValue;
import cell.Target;
import cell.Tree;
import cell.Water;

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
	private Character character;
	private Target targetCell;
	
	public Cell getTargetCell() {
		return targetCell;
	}
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
				case '@': 
					chCount++;
					character = (Character)actualCell.getContent();
					character.setPosition(new Point(j, i));
					break;
				case 'G': dCount++; targetCell = (Target) actualCell; break;
				case 'C': 
					ibCount++;
					actualCell.getContent().setPosition(new Point(j, i));
					break;
					
				case 'K': intCount++; break;
				case 'B': actualCell.getContent().setPosition(new Point(j, i));
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
		case 'K': return new IceBlockTarget();
		case 'C': return new EmptyCell(new IceBlock());
		case 'B': return new EmptyCell(new Box());
		case 'G': return new Target();
		case '@': return new EmptyCell(new Character());
		case ' ': return new EmptyCell();
		default: throw new InvalidLevelException("Caracter Inválido.");
		}
	}
	public Cell getCell(int x, int y) {
		if (x < columns && y < rows)
			return dataMatrix[y][x];
		return null;
	}
	public void setCell(int x, int y, Cell cell) {
		if (x < columns && y < rows)
			dataMatrix[y][x] = cell;
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
	public MoveReturnValue moveCharacter(Direction direction) {
		return character.move(this, direction);
	}
	public void setTargetVisible() {
		targetCell.setVisible();
		
	}
	
}
