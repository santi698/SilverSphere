package board;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;



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
		} catch (Exception e1) {
		throw e1;
	}
	finally {
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
}
