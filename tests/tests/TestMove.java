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
import cell.FloatingBox;


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
			String[] sArr = {
					"BBG",
					"@T ",
					"C K"
					};
			
			Board board = new Board(sArr);
			Direction dirOutBoard = Direction.LEFT;
			Direction dirIce = Direction.DOWN;
			Direction dirInmobileBox = Direction.UP;
			Direction dirTree = Direction.RIGHT;

			
			/*ver como funciona bien la clase Box y Ice. Especialmente el movimiento,
			 *para analizar si hay casos que puedan fallar, para verificarlos puntualmente.
			 *ademas veri si hay que verificar alguna excepcion.
			 */
			
			
			
			assertTrue(board.moveCharacter(dirOutBoard).isEmpty()); 
			assertTrue(board.moveCharacter(dirTree).isEmpty());
			assertTrue(board.moveCharacter(dirInmobileBox).isEmpty());
			assertTrue(board.moveCharacter(dirIce).isEmpty());	
			assertTrue(board.getCell( 0 , 0 ).getContent().move(board, Direction.RIGHT).isEmpty());	
		}
		
		@Test
		public void testWater() throws InvalidLevelException {
			//Asegura que se detecte cuando el jugador cae al agua
			String[] sArr = 
				{ "###C",
				  "#@#G",
				  "### "
				};
			for (Direction d : Direction.values()) {
				Board b = new Board(sArr);
				MoveRes res = b.checkMove(b.moveCharacter(d));
				assertEquals(res, MoveRes.WATER_REACHED);
			}
			//Asegura que las cajas se conviertan en cajas 
			//flotantes al caer al agua
			String[] sArr2 = {
					"#####",
					"##B##",
					"#B@B#",
					"  BCG",
					"#####"
			};
			for (Direction d : Direction.values()) {
				Board b = new Board(sArr2);
				Position boxPos = new Position(2, 2).next(d).next(d);
				ArrayList<Position> changed = b.moveCharacter(d);
				assertTrue(changed.contains(boxPos));
			}
			
		}
		@Test
		public void testWin() throws InvalidLevelException{
			
			String[] sArr1 = new String[4];
			sArr1[0]= "B BB";
			sArr1[1]= "@# B";
			sArr1[2]= "B CK";
			sArr1[3]= "# G ";	
			
			Board board1 = new Board(sArr1);
			Board board2 = new Board(sArr1);
			ArrayList<Position> changed;
			
			//Revisar las direcciones.
			
			board1.moveCharacter(Direction.DOWN);
			assertTrue(board1.getCell( 0 , 3 ) instanceof FloatingBox);
			board1.moveCharacter(Direction.RIGHT);
			board1.moveCharacter(Direction.RIGHT);
			assertTrue(board1.getTargetCell().isVisible());
			changed = board1.moveCharacter(Direction.DOWN);
			assertEquals(board1.checkMove(changed), MoveRes.PLAYER_WON);
			
			changed = board2.moveCharacter(Direction.RIGHT);
			assertEquals(board2.checkMove(changed), MoveRes.WATER_REACHED);
			board1.moveCharacter(Direction.DOWN);
			board1.moveCharacter(Direction.RIGHT);
			board1.moveCharacter(Direction.UP);			
			
			
			
		}
}
