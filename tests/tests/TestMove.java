package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import board.Board;
import board.Direction;
import board.InvalidLevelException;
import board.Position;
import cell.Box;
import cell.Character;


	public class TestMove {

		@Test
		public void rightMove() throws InvalidLevelException{
			String[] sArr = new String[4];
			sArr[0]= "B  G";
			sArr[1]= "@B  ";
			sArr[2]= "T  C";
			sArr[3]= "   K";
			
			Board board1 = new Board(sArr);
			Direction dir1 = Direction.RIGHT;
			ArrayList<Position> positionList = board1.moveCharacter(dir1);
			int x = 0; // falta definir x e y en funci�n de 
			int y = 1; //
			assertTrue(board1.getCell(x + 1, y + 0).getContent() instanceof Character);
			assertTrue(positionList.contains(new Position(1 + x, y + 0))); 
			assertTrue(board1.getCell(x + 2, y + 0).getContent() instanceof Box); 
			}
		
		@Test
		public void wrongMove() throws InvalidLevelException{
			String[] sArr2 = new String[3];
			sArr2[0]= "B G";
			sArr2[1]= "@T ";
			sArr2[2]= "C K";
		

			
			Board board2 = new Board(sArr2);
			Direction dirOutBoard = Direction.LEFT;
			Direction dirIce = Direction.DOWN;
			Direction dirInmobileBox = Direction.UP;
			Direction dirTree = Direction.RIGHT;

			
			/*ver como funciona bien la clase Box y Ice. Especialmente el movimiento,
			 *para analizar si hay casos que puedan fallar, para verificarlos puntualmente.
			 *ademas veri si hay que verificar alguna excepcion.
			 */
			
			
			
			assertTrue(board2.moveCharacter(dirOutBoard).isEmpty()); 
			assertTrue(board2.moveCharacter(dirTree).isEmpty());
			assertTrue(board2.moveCharacter(dirInmobileBox).isEmpty());
			assertTrue(board2.moveCharacter(dirIce).isEmpty());	
		}
}
