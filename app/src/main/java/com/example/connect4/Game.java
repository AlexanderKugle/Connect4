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

}
