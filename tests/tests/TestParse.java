package tests;

import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

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
		fail("Not yet implemented");
	}

}
