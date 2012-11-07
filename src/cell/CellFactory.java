package cell;

import java.util.HashMap;
import java.lang.Character;
import board.InvalidLevelException;

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
		default: throw new InvalidLevelException("Caracter Inválido.");
		}
	}
	public int getCount(char id) {
		return counts.get(id);
	}
}