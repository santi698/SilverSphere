package tests;

import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import board.Board;


public class TestParse {
	
	

	@Test
	public void test() throws IOException {
		FileReader inStream = null;
		char[] cbuf = new char[250];
		try {
			inStream = new FileReader("./resources/levels/INV01.txt");
			inStream.read(cbuf);
		} 
		finally {
			if (inStream != null) {
				inStream.close();
			}
		}
		String[] sArr = (new String(cbuf)).split("\n"); //WTF pone como ultimo string del arreglo al original completo o.O
		Board b = new Board(sArr);
		System.out.println(b);
		fail("In development");
	}

}
