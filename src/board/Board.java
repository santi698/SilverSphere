package board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import cell.Cell;
import cell.CellFactory;
import cell.Character;
import cell.Target;

/** 
 * Clase que representa el tablero de juego.
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
	
	/**
	 * Crea un tablero de las dimensiones especificadas.
	 * @param rows Cantidad de filas
	 * @param columns Cantidad de columnas
	 */
	Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		dataMatrix = new Cell[rows][columns];
	}
	/**
	 * @param s Un array de {@code String}s donde cada elemento es una fila del tablero
	 * @throws InvalidLevelException Cuando el nivel no respeta el formato estándar
	 */
	public Board(String[] s) throws InvalidLevelException {
		this(s.length, s[0].length());
		parse(s);
	}
	
	/**
	 * @param s
	 * @throws InvalidLevelException
	 */
	private void parse(String[] s) throws InvalidLevelException {
		CellFactory f = new CellFactory();
		for (int i = 0; i < rows; i++) {
			if (s[i].length() != columns)
				throw new InvalidLevelException("El tablero debe ser rectangular.");
			for (int j = 0; j < columns; j++) {
				char c = s[i].charAt(j);
				Cell actualCell = f.createCell(c);
				dataMatrix[i][j] = actualCell;
				switch (c) {
				case '@': 
					character = (Character)actualCell.getContent();
					character.setPosition(new Position(j, i));
					break;
				case 'G': 
					targetCell = (Target) actualCell;
					targetCell.setPosition(new Position(j, i));
					break;
				case 'C': 
					actualCell.getContent().setPosition(new Position(j, i));
					break;
					
				case 'B': actualCell.getContent().setPosition(new Position(j, i));
				}
			}
		}
		if (f.getCount('@') != 1 || f.getCount('C') == 0 ||
				f.getCount('G') != 1 || f.getCount('K') > 1)
			throw new InvalidLevelException("Debe haber exactamente un personaje, un destino" +
					", al menos un cubo de hielo, y no más de un interruptor." +
					"\n Personajes: " + f.getCount('@') + ", Destinos: " + f.getCount('G') +
					", Cubos de hielo: " + f.getCount('C') + 
					", Interruptores:" + f.getCount('K') + ".");
		if (f.getCount('K') == 0) {
			targetCell.setVisible();
		}
	}
	
	/**
	 * Retorna la celda en la posición (x, y)
	 * @param x 
	 * @param y
	 * @return la celda en la posición (x, y)
	 */
	public Cell getCell(int x, int y) {
		if (x >= 0 && x < columns && y >= 0 && y < rows)
			return dataMatrix[y][x];
		return null;
	}
	
	public Cell getCell(Position p) {
		return getCell(p.x, p.y);
	}
	
	/**
	 * Reemplaza el contenido de la celda en la posicion (x, y) por {@code cell}
	 * @param x
	 * @param y
	 * @param cell la celda que va a reemplazar a la que esta en la posición (x, y)
	 */
	public void setCell(int x, int y, Cell cell) {
		if (x < columns && y < rows)
			dataMatrix[y][x] = cell;
	}
	
	public void setCell(Position p, Cell cell) {
		setCell(p.x, p.y, cell);
	}
	
	/**
	 * Obtiene una referencia a la celda destino del tablero.
	 * @return La celda destino
	 * @see {@link Target}
	 */
	public Target getTargetCell() {
		return targetCell;
	}
	
	/**
	 * Para debugging
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Cell[] row : dataMatrix) {
			s.append(Arrays.toString(row));
			s.append("\n");
		}
		return s.toString();
	}
	
	/**
	 * Mueve al personaje en la direccion especificada. 
	 * @param direction La direccion en la que se debe mover el personaje
	 * @return Una lista con las posiciones del tablero que fueron modificadas. 
	 * @see {@link cell.MoveReturnValue}
	 * @see {@link Direction}
	 */
	public ArrayList<Position> moveCharacter(Direction direction) {
		return character.move(this, direction);
	}

	
}
