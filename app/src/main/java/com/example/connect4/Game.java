package com.example.connect4;

public class Game
{
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
