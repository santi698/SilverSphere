package cell;

import java.util.HashMap;
import java.lang.Character;
import board.InvalidLevelException;

/**
 * Clase que se encarga de la creación de las celdas.
 *
 */
public class CellFactory {
	HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
	public CellFactory() {
		initializeCounts();
	}
	private void initializeCounts() {
		counts.put('T', 0);
		counts.put('#', 0);
		counts.put('K', 0);
		counts.put('C', 0);
		counts.put('B', 0);
		counts.put('G', 0);
		counts.put('@', 0);
		counts.put(' ', 0);
	}
	/**
	 * Metodo que crea los nuevos objetos del tablero serún el parametro que recibe. 
	 * Ademas mantiene una cuenta de cuantos elementos crea de cada objeto.
	 * @param id es un Char que representa alguno de los posibles elementos del juego.
	 * @return una nueva instancia del objeto pasado.
	 * @throws InvalidLevelException en caso de que el caracter no corresponda a alguno de los validos por el juego.
	 */
	public Cell createCell(char id) throws InvalidLevelException {
		counts.put(id, counts.get(id)+1);
		switch (id) {
		case 'T': return new Tree();
		case '#': return new Water();
		case 'K': return new IceBlockTarget();
		case 'C': return new ContainerCell(new IceBlock());
		case 'B': return new ContainerCell(new Box());
		case 'G': return new Target();
		case '@': return new ContainerCell(new cell.Character());
		case ' ': return new ContainerCell();
		default: throw new InvalidLevelException("Caracter InvÃ¡lido.");
		}
	}
	/**
	 * Metodo que devuelve la cantidad de objetos creados segun el i{@code d} recibido
	 * @param id 
	 * @return
	 */
	public int getCount(char id) {
		return counts.get(id);
	}
}