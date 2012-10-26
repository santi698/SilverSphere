package tests;

import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import cell.Board;

public class TestParse {
	
	

	@Test
	public void test() throws IOException {
		FileReader inStream = null;
		char[] cbuf = new char[250];
		try {
			inStream = new FileReader("/home/santi698/silversphere/SilverSphere/resources/levels/INV01.txt");
			inStream.read(cbuf);
			Board.parse(cbuf.toString());
		} 
		finally {
			if (inStream != null) {
				inStream.close();
			}
		}
		fail("In development");
	}

}
