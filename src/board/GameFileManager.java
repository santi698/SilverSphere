package board;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Scanner;



public class GameFileManager {
	private Board board;


/**
 * Carga un juego guardado a partir del archivo f
 * @param f
 * @throws Exception 
 * @throws IOException
 */
public Board loadGame(File f) throws Exception {
	ObjectInputStream inStream = null;
	try {
		inStream = new ObjectInputStream(new FileInputStream(f));
		board = (Board) inStream.readObject();
		} finally {
		if (inStream != null)
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	return board;
}

public void save(File f) throws IOException {
	
	ObjectOutputStream outStream = null;
	if (f != null) {
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(f));
			outStream.writeObject(board);
			
		} catch (StreamCorruptedException e1) {
			throw e1;
		}
		finally {
			if (outStream != null)
				outStream.close();
		}
	}
}

/**
 * metodo que carga un nivel desde un archivo.
 * @param f
 * @return un tablero nuevo creado en funcion del nivel cargado
 * @throws IOException
 * @throws InvalidLevelException en caso de que el nivel que se intenta cargar no sea valido.
 */
public Board loadLevelFromFile(File f) throws IOException, InvalidLevelException {
	Scanner scanner = null;
	ArrayList<String> lines = new ArrayList<String>();
	try {
		scanner = new Scanner(new FileReader (f));
		scanner.useDelimiter("\n");
		while (scanner.hasNext()) {
			lines.add(scanner.nextLine());
		}
			
	} 
	finally {
		if (scanner != null) {
			scanner.close();
		}
	}
	String[] sArr = new String[lines.size()];
	lines.toArray(sArr);
	return new Board(sArr);
}
}
