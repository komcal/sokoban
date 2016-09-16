package sokoban;

public class GameBoard {
	 
    private int height;
    private int width;
    private String[] baseBoard;
 
    private int playerRow;
    private int playerCol;
 
    private int numBoxes;
    private int[] boxRows;
    private int[] boxCols;
    
    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }
    public GameBoard(String[] map) {
        loadBoard(map);
    }
 
    public void loadBoard(String[] map) {
        height = map.length;
        width = map[0].length();
        numBoxes = 0;
        boxRows = new int[height*width];
        boxCols = new int[height*width];
 
        baseBoard = new String[height];
        for(int r = 0; r < height; r++) {
            baseBoard[r] = "";
            for(int c = 0; c < width; c++) {
                char mch = map[r].charAt(c);
                char sch = '.';
                switch(mch) {
                case 'A': 
                    playerRow = r;
                    playerCol = c;
                    break;
                case 'O':
                    boxRows[numBoxes] = r;
                    boxCols[numBoxes] = c;
                    numBoxes++;
                    break;
                default:
                    sch = mch;
                }
                baseBoard[r] += sch;
            }
        }
    }
 
    public int getHeight() {
        return height;
    }
 
    public int getWidth() {
        return width;
    }
 
    public int getPlayerRow() {
        return playerRow;
    }
 
    public int getPlayerCol() {
        return playerCol;
    }
 
    public void setPlayerPosition(int r, int c) {
        playerRow = r;
        playerCol = c;
    }
 
    public int getNumBoxes() {
        return numBoxes;
    }
 
    public int[] getBoxPosition(int i) {
        return new int[] { 
                boxRows[i],
                boxCols[i]
        };
    }
 
    public void setBoxPosition(int i, int r, int c) {
        boxRows[i] = r;
        boxCols[i] = c;
    }
 
    public boolean hasPlayerAt(int r, int c) {
        return (playerRow == r) && (playerCol == c);
    }
 
    public boolean hasBoxAt(int r, int c) {
        for(int i = 0 ; i < numBoxes ; i++){
            if (boxRows[i] == r && boxCols[i] == c) {
            	return true;
            }
        }
        return false;
    }
 
    public boolean hasExitAt(int r, int c) {

    	return getBaseBoardType(r, c).equals("*");
    }
    public String getBaseBoardType(int r, int c) {
    	if (isPositionOutOfBoard(r, c)) {
    		return "XX";
    	} else {
    		String row = baseBoard[r];
    		return Character.toString(row.charAt(c));
    	}
    }
    public String toString() {
    	String stringBoard = "";
        for (int i = 0 ; i < baseBoard.length ; i++) {
        	String row = baseBoard[i];
        	for (int j = 0 ; j < row.length() ; j++) {
        		if (hasBoxAt(i, j)) {
        			stringBoard += "O";
        		}
        		else if (hasPlayerAt(i, j)) {
        			stringBoard += "A";
        		}
        		else {
        			stringBoard += getBaseBoardType(i, j);
        		}
        	}
        	stringBoard += "\n";
        }
        return stringBoard;
    }
    public boolean isPositionOutOfBoard(int r, int c){
    	if(r < 0 || c < 0 || c >= baseBoard[0].length() || r >= baseBoard.length){
    		return true;
    	}
    	return false;
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
    public char getBoardNextItem(int r, int c, Direction dir) {
    	r += getRowDiff(dir);
    	c +=  getColDiff(dir);
    	if (hasBoxAt(r, c)) {
    		return 'O';
    	}
    	else if (hasExitAt(r, c)) {
    		return '*';
    	}
    	else if (hasPlayerAt(r, c)) {
    		return 'A';
    	}
    	else {
    		String row = baseBoard[r];
    		return row.charAt(c);
    	}
    }
}