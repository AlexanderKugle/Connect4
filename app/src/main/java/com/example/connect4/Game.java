package com.example.connect4;

import java.util.Arrays;

public class Game
{
    private final byte MAX_COL = 7;
    private final byte MAX_ROW = 6;
    private char[][] board = new char[MAX_COL][MAX_ROW]; //indexing in by column then by row
    private String[] playerNames;
    private char[] playerTokens = new char[] {'R', 'Y'};
    private boolean isAiGame;

    private int turn;

    public Game(String[] playerNames, boolean isAiGame){
        this.playerNames = playerNames;
        this.isAiGame = isAiGame;
        turn = (int)(Math.random() * 2);
        clearBoard();
    }

    /**
     * Main gameplay loop. Takes in the user input, finds the row number from that,
     * places the token, checks for tie and win, and does the AI turn if necessary
     * @param colNum 0-based number the user chose in the UI
     * @return Integer representing the result of the turn.
     * <br />
     *     0 = Turn succeeded, Game continues<br/>
     *     1 = Turn failed<br/>
     *     2 = Game Over. A player won.<br/>
     *     3 = Game Over. The game was a tie.
     * <br />
     * Codes 2 and 3 do not change the turn counter
     */
    public int doTurn(int colNum){
        //0 = success, continue as normal | 1 = failed, some error occurred or bad input was provided |
        //2 = A player won | 3 = The game is a tie
        int turnCode = 0;
        try{
            boolean isGameOver = false;
            boolean isATie = false;

            //place token
            turnCode = doTokenPlacement(colNum);

            //check for win

            for(int colIter = 0; colIter < board.length; colIter++){
                for(int rowIter = 0; rowIter < board[colIter].length; rowIter++){
                    isGameOver = checkForWin(4, colIter, rowIter, 1, 0);
                    if(!isGameOver){
                        isGameOver = checkForWin(4, colIter, rowIter, 0, 1);
                        if(!isGameOver){
                            isGameOver = checkForWin(4, colIter, rowIter, 1, 1);
                            if(!isGameOver){
                                isGameOver = checkForWin(4, colIter, rowIter, -1, 0);
                                if(!isGameOver){
                                    isGameOver = checkForWin(4, colIter, rowIter, 0, -1);
                                    if(!isGameOver){
                                        isGameOver = checkForWin(4, colIter, rowIter, -1, -1);
                                    }
                                }
                            }
                        }

                    }
                }
            }

            //If the game isn't won, check for a tie
            if(!isGameOver){
                isATie = checkForTie();
            }

            //If the game hasn't met an end condition, switch turns.
            if(!isGameOver && !isATie){
                turn = (turn + 1) % 2;
                //If applicable, do an AI turn and swap the counter back to the Human player
                if(isAiGame){
                    doAiTurn();
                    turn = (turn + 1) % 2;
                }
            } else {
                //if the game has met an end condition, set the turnCode to the appropriate value (as defined above)
                turnCode = isATie ? 3 : 2;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            turnCode = 1;
        }


        return turnCode;
    }

    /**
     * Increments the turn counter (since it wasn't incremented at the end of the game) and clears the board
     */
    public void resetGame(){
        turn = (turn + 1) % 2;
        clearBoard();
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
     * Places a token in the board on the lowest row of a given column
     * @param colNum number of column ot place the token
     * @return Code representing the success / failure of the placement (0 = Success, 1 = Failure)
     */
    public int doTokenPlacement(int colNum){
        int placeCode = 0;
        //get the row num
        int rowNum = getNextRowNum(colNum);
        if(rowNum == -1){
            placeCode = 1;
            return placeCode;
        }

        //place the token
        board[colNum][rowNum] = playerTokens[turn];

        return placeCode;
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
        //iterate through the column provided to find the first empty row from the bottom
        for(int rowIter = board[colNum].length; rowIter > -1; rowIter--){
            if(board[colNum][rowIter] == ' '){
                rowNum = rowIter;
                break;
            }
        }
        return rowNum;
    }

    /**
     * Checks the top row of the board to see if it is full.
     * The only time it should be entirely full is when there is no more valid spaces to play
     * @return Boolean representing whether or no the top row is completely full, thus meaning the game is eligible for a tie
     */
    private boolean checkForTie(){
        boolean isATie = true;
        //iterates through the top row of the board
        for (char[] chars : board) {
            //if it finds an empty space, change the flag value (isATie) to false
            if (chars[0] == ' ') {
                isATie = false;
                break;
            }
        }

        return isATie;
    }

    public void doAiTurn(){

    }

    //These getters should be used by the view to update the GUI board and have the player names available to the view

    public char[][] getBoard() {
        return board;
    }

    public String getCurrentPlayer(){
        return playerNames[turn];
    }

    public String[] getPlayerNames(){
        return playerNames;
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
