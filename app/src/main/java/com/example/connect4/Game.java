package com.example.connect4;

import java.util.Arrays;
import java.util.Random;

public class Game
{
    private final byte MAX_COL = 7;
    private final byte MAX_ROW = 6;
    private char[][] board = new char[MAX_COL][MAX_ROW]; //indexing in by column then by row
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

    public void aiTurn() {
        int column = new Random().nextInt(MAX_COL + 1);
        System.out.println(column);
        doTurn(column);
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
     * checks for every possible win condition from where piece is placed
     * @param xStart
     * @param yStart
     * @return true if win was found false otherwise
     */
    public boolean checkForWin(int xStart, int yStart)
    {
        return checkForWin(4,xStart,yStart,1,0) || // right horizontal
                checkForWin(4,xStart,yStart,1,1) || // top right diagonal
                checkForWin(4,xStart,yStart,0,1) || // strait up vertical
                checkForWin(4,xStart,yStart,-1,1) || // left up diagonal
                checkForWin(4,xStart,yStart,-1,0) || // left horizontal
                checkForWin(4,xStart,yStart,-1,-1) || // left down diagonal
                checkForWin(4,xStart,yStart,0,-1) || // down vertical
                checkForWin(4,xStart,yStart,1,-1) || // down right diagonal
                checkForWinOffset(xStart, yStart);


    }

    /**
     * Will check if piece was placed in the middle to create the win condition
     * @param xStart
     * @param yStart
     * @return tru if win was found false otherwise
     */
    private boolean checkForWinOffset(int xStart, int yStart)
    {
        return checkForWin(4,xStart - 1,yStart,1,0) || // right horizontal left offset
                checkForWin(4,xStart - 1,yStart - 1,1,1) || // top right diagonal bottom left offset
                checkForWin(4,xStart,yStart - 1,0,1) || // strait up vertical down offset
                checkForWin(4,xStart + 1,yStart - 1,-1,1) || // left up diagonal bottom right offset
                checkForWin(4,xStart + 1,yStart,-1,0) || // left horizontal right offset
                checkForWin(4,xStart + 1,yStart + 1,-1,-1) || // left down diagonal top right offset
                checkForWin(4,xStart,yStart + 1,0,-1) || // down vertical up offset
                checkForWin(4,xStart - 1,yStart + 1,1,-1); // down right diagonal top left offset
    }

    /**
     * Given a start position, direction,and steps to go in the direction will check for a win in that direction.
     * @param steps
     * @param xStart
     * @param yStart
     * @param xDir
     * @param yDir
     * @return True if a win condition has been met false elsewise
     */
    private boolean checkForWin(int steps, int xStart, int yStart, int xDir, int yDir)
    {
        // Will continue to check for pieces if there are pieces required for a win
        if (steps > 0)
        {
            // Making sure next piece checked is not outside the board
            if (((xStart+xDir) < MAX_COL && (xStart+xDir) > 0) && ((yStart+yDir) < MAX_ROW && (yStart+yDir) > 0))
            {
                // Get player color that is needed to checked
                char checkColor = board[xStart][yStart];
                // Get next color in sequence from the board;
                char pieceColor = board[xStart + xDir][yStart + yDir];
                // If next piece is the same as current then continue going else there is not a win in this direction
                if (pieceColor == checkColor)
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
