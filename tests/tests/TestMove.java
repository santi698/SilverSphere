package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import board.Board;
import board.Direction;
import board.InvalidLevelException;
import board.MoveRes;
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
			int x = 0; 
			int y = 1;
			assertTrue(board1.getCell(x + 1, y + 0).getContent() instanceof Character);
			assertTrue(positionList.contains(new Position(1 + x, y + 0))); 
			assertTrue(board1.getCell(x + 2, y + 0).getContent() instanceof Box); 
			}
		
		@Test
		public void wrongMove() throws InvalidLevelException{
			String[] sArr2 = new String[3];
			sArr2[0]= "BBG";
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
			assertTrue(board2.getCell( 0 , 0 ).getContent().move(board2, Direction.RIGHT).isEmpty());	
		}
		
		
		@Test
		public void terminalMove() throws InvalidLevelException{
			
			String[] sArr3 = new String[4];
			sArr3[0]= "B BG";
			sArr3[1]= "@#KB";
			sArr3[2]= "B CB";
			sArr3[3]= "#   ";	
			
			Board board3 = new Board(sArr3);
			
			
			//Revisar las direcciones.
			
			board3.moveCharacter(Direction.DOWN);
			assertEquals(board3.getCell( 0 , 3 ).toString(), "Floating Box");
			
			board3.moveCharacter(Direction.RIGHT);
			board3.moveCharacter(Direction.DOWN);
			board3.moveCharacter(Direction.RIGHT);
			board3.moveCharacter(Direction.UP);
			//assertEquals(board3.getCell( 2 , 1 ).toString(), "Interruptor +" + board3.getCell( 2 , 1 ).getContent() );
			
			//assertEquals(board3.checkMove(board3.moveCharacter(Direction.RIGHT)),  MoveRes.WATER_REACHED);

		
			/*
			 * Falta resolver los Tres comentarios de arriba. Después creo que todo está cubierto.
			 */
			
			
			
			
		}
}
