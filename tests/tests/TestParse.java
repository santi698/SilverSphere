package tests;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import board.Board;
import board.InvalidLevelException;

public class TestParse {	
	
	private String [] readFileToStringArray (String file) throws IOException{
		Scanner scanner = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			scanner = new Scanner(new FileReader (file));
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
		return sArr;
	}
	@Test
	public void testInvalid() throws IOException {
		String[] files = {"test_levels/INV01.txt", "test_levels/INV02.txt",
				"test_levels/INV03.txt", "test_levels/INV04.txt",
				"test_levels/INV05.txt", "test_levels/INV06.txt",
				"test_levels/INV07.txt", "test_levels/INV08.txt",
				"test_levels/INV09.txt", "test_levels/INV10.txt",};
		for (String file : files) {
			boolean flag = false;
			System.out.println("File: " + file);
			String[] sArr = readFileToStringArray(file);
			Board b;
			try {
				b = new Board(sArr);
				System.out.println(b);
			} catch (InvalidLevelException e1) {
				System.out.println(e1.getMessage());
				flag = true;
			}
			assertTrue(flag);
		}
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testValid() throws IOException, InvalidLevelException {
		String[] files = {"./resources/levels/v01.txt",
				"./resources/levels/v02.txt", "./resources/levels/v03.txt",
				"./resources/levels/v04.txt"};
		for (String file : files) {
			System.out.println("File: " + file);
			String[] sArr = readFileToStringArray(file);
			Board b;
			b = new Board(sArr);
			System.out.println(b);
		}
	
	}
	

}
