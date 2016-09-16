package sokoban;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardGameMovementTest {
	static String smallBoardMap[] = {
            " #####",
            "#*O.A#",
            "#...O#",
            "##..*#",
            " #####"
        };
 
    private GameBoard smallBoard;
 
    @Before
    public void setUp() {
        smallBoard = new GameBoard(smallBoardMap);
    }
    @Test
    public void testGetBoardNextItem() {
        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.UP));
        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.LEFT));
        assertEquals('*', smallBoard.getBoardNextItem(2, 1, GameBoard.Direction.UP));
        assertEquals('A', smallBoard.getBoardNextItem(1, 3, GameBoard.Direction.RIGHT));
        assertEquals(' ', smallBoard.getBoardNextItem(1, 0, GameBoard.Direction.UP));
        assertEquals('.', smallBoard.getBoardNextItem(2, 1, GameBoard.Direction.RIGHT));
    }
}
