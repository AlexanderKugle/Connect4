package com.example.connect4;

import java.util.Arrays;

public class Game
{

    private char[][] board = new char[7][6]; //indexing in by column then by row
    private String[] playerNames;
    private char[] playerTokens = new char[] {'R', 'Y'};
    private boolean isAiGame;

    public Game(String[] playerNames, boolean isAiGame){
        this.playerNames = playerNames;
        this.isAiGame = isAiGame;
        clearBoard();
    }

    public void doTurn(int colNum){

    }

    /**
     * Fills the board with <code>' '</code> character literals.
     * Used to initialize the board and clear the board on a restart
     */
    private void clearBoard(){
        for(int colIter = 0; colIter < board.length; colIter++){
            Arrays.fill(board[colIter], ' ');
        }
    }

    /**
     * Loops through a single array in the <code>board</code> object
     * based on the <code>colNum</code> parameter
     * @param colNum 0-based column number to search for an open row in
     * @return The 0-based integer of the next open row, starting from the
     * bottom of the board and working its way up. If no open space is found, returns <code>-1</code>
     */
    private int getNextRowNum(int colNum){
        int rowNum = -1;

        for(int rowIter = board[colNum].length; rowIter > -1; rowIter--){
            if(board[colNum][rowIter] == ' '){
                rowNum = rowIter;
                break;
            }
        }
        return rowNum;
    }

    /**
     * Given a start position, direction,and steps to go in the direction will check for a win in that direction.
     * @param steps
     * @param xStart
     * @param yStart
     * @param xDir
     * @param yDir
     * @return
     */
    public boolean checkForWin(int steps, int xStart, int yStart, int xDir, int yDir)
    {
        // Will continue to check for pieces if there are pieces required for a win
        if (steps > 0)
        {
            // Get player color that is needed to checked
            String checkColor = board[xStart][yStart];
            // Get next color in sequence from the board;
            String pieceColor = board[xStart + xDir][yStart + yDir];
            // If next piece is the same as current then continue going else there is not a win in this direction
            if (pieceColor.equalsIgnoreCase(checkColor))
            {
                checkForWin(steps-1,xStart+xDir,yStart+yDir,xDir,yDir);
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
        return false;
    }
}
