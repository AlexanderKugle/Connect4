package com.example.connect4;

import java.util.Arrays;

public class Game
{

    private char[][] board = new char[7][6]; //indexing in by column then by row
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
            boolean isGameOver;
            boolean isATie = false;

            //get the row num
            int rowNum = getNextRowNum(colNum);
            if(rowNum == -1){
                turnCode = 1;
                return turnCode;
            }

            //place the token
            board[colNum][rowNum] = playerTokens[turn];

            //check for win
            isGameOver = checkForWin(0,0,0,0); //PLACEHOLDER

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
     *
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

    private boolean checkForWin(int xStart, int yStart, int xDir, int yDir){
        return false;
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
}
