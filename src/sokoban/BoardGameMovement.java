package sokoban;

public class BoardGameMovement {
	public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }
	public int getColDiff(Direction dir) {
        switch(dir) {
        case LEFT:
            return -1;
        case RIGHT:
            return 1;
        default:
            return 0;
        }
    }
 
    public int getRowDiff(Direction dir) {
        switch(dir) {
        case UP:
            return -1;
        case DOWN:
            return 1;
        default:
            return 0;
        }
    }
}
