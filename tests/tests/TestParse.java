package tests;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import board.Board;
import board.InvalidLevelException;

public class TestParse {	
	
	private String [] readFileToStringArray (String file) throws IOException{
		FileReader inStream = null;
		char[] cbuf = new char[250];
		try {
			inStream = new FileReader (file);
			inStream.read(cbuf);
		} 
		finally {
			if (inStream != null) {
				inStream.close();
			}
		}
		String[] sArr = (new String(cbuf)).split("\n"); //WTF pone como ultimo string del arreglo al original completo o.O
		return sArr;
	}
	@Rule
	public ExpectedException e = ExpectedException.none();
	@Test
	public void testInvalid() throws IOException, InvalidLevelException {
		String[] files = {"./resources/levels/INV01.txt", "./resources/levels/INV02.txt",
				"./resources/levels/INV03.txt", "./resources/levels/INV04.txt",
				"./resources/levels/INV05.txt", "./resources/levels/INV06.txt",
				"./resources/levels/INV07.txt", "./resources/levels/INV08.txt",
				"./resources/levels/INV09.txt", "./resources/levels/INV10.txt",};
		for (String file : files) {
			System.out.println("File: " + file);
			String[] sArr = readFileToStringArray(file);
			Board b;
			e.expect(InvalidLevelException.class);
			b = new Board(sArr);
			System.out.println(b);
		}
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testValid() throws IOException, InvalidLevelException {
		String[] files = {"./resources/levels/v01.txt"};
		for (String file : files) {
			System.out.println("File: " + file);
			String[] sArr = readFileToStringArray(file);
			Board b;
			b = new Board(sArr);
			System.out.println(b);
		}
	
	}
	

}
