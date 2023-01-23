package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
o	Iterate through, setting the current coordinate to the proper token type (Red, Yellow, or Empty, which would be grey or white)
•	Swap text according to current player turn (15 minutes)
o	“It’s Player 1’s turn” <-> “It’s Player 2’s turn”
•	Check if game is over (45 minutes)
o	If it is, disable btncol1Place through btnCol7Place and enable/make visible btnReset and btnMenu. Also, set the text to “{PlayerName} has won!” and increment the score in the win box for the appropriate player
o	If it isn’t, continue as normal

 */

public class GameActivity extends AppCompatActivity {

    public ImageView[][] board;

    public Game game;

    public TextView lblTurn, lblP1, lblP1Wins, lblP2, lblP2wins;
    public ImageView ivOneOne, ivTwoOne, ivThreeOne, ivFourOne, ivFiveOne, ivSixOne,
            ivOneTwo, ivTwoTwo, ivThreeTwo, ivFourTwo, ivFiveTwo, ivSixTwo,
            ivOneThree, ivTwoThree, ivThreeThree, ivFourThree, ivFiveThree, ivSixThree,
            ivOneFour, ivTwoFour, ivThreeFour, ivFourFour, ivFiveFour, ivSixFour,
            ivOneFive, ivTwoFive, ivThreeFive, ivFourFive, ivFiveFive, ivSixFive,
            ivOneSix, ivTwoSix, ivThreeSix, ivFourSix, ivFiveSix, ivSixSix,
            ivOneSeven, ivTwoSeven, ivThreeSeven, ivFourSeven, ivFiveSeven, ivSixSeven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    public void init()
    {
        lblTurn = findViewById(R.id.lblTurn);
        lblP1Wins = findViewById(R.id.lblP1Wins);
        lblP2wins = findViewById(R.id.lblP2Wins);
        lblP1 = findViewById(R.id.lblP1);
        lblP2 = findViewById(R.id.lblP2);

        ivOneOne = findViewById(R.id.ivOneOne);
        ivTwoOne = findViewById(R.id.ivTwoOne);
        ivThreeOne = findViewById(R.id.ivThreeOne);
        ivFourOne = findViewById(R.id.ivFourOne);
        ivFiveOne = findViewById(R.id.ivFiveOne);
        ivSixOne = findViewById(R.id.ivSixOne);
        ivOneTwo = findViewById(R.id.ivOneTwo);
        ivTwoTwo = findViewById(R.id.ivTwoTwo);
        ivThreeTwo = findViewById(R.id.ivThreeTwo);
        ivFourTwo = findViewById(R.id.ivFourTwo);
        ivFiveTwo = findViewById(R.id.ivFiveTwo);
        ivSixTwo = findViewById(R.id.ivSixTwo);
        ivOneThree = findViewById(R.id.ivOneThree);
        ivTwoThree = findViewById(R.id.ivTwoThree);
        ivThreeThree = findViewById(R.id.ivThreeThree);
        ivFourThree = findViewById(R.id.ivFourThree);
        ivFiveThree = findViewById(R.id.ivFiveThree);
        ivSixThree = findViewById(R.id.ivSixThree);
        ivOneFour = findViewById(R.id.ivOneFour);
        ivTwoFour = findViewById(R.id.ivTwoFour);
        ivThreeFour = findViewById(R.id.ivThreeFour);
        ivFourFour = findViewById(R.id.ivFourFour);
        ivFiveFour = findViewById(R.id.ivFiveFour);
        ivSixFour = findViewById(R.id.ivSixFour);
        ivOneFive = findViewById(R.id.ivOneFive);
        ivTwoFive = findViewById(R.id.ivTwoFive);
        ivThreeFive = findViewById(R.id.ivThreeFive);
        ivFourFive = findViewById(R.id.ivFourFive);
        ivFiveFive = findViewById(R.id.ivFiveFive);
        ivSixFive = findViewById(R.id.ivSixFive);
        ivOneSix = findViewById(R.id.ivOneSix);
        ivTwoSix = findViewById(R.id.ivTwoSix);
        ivThreeSix = findViewById(R.id.ivThreeSix);
        ivFourSix = findViewById(R.id.ivFourSix);
        ivFiveSix = findViewById(R.id.ivFiveSix);
        ivSixSix = findViewById(R.id.ivSixSix);
        ivOneSeven = findViewById(R.id.ivOneSeven);
        ivTwoSeven = findViewById(R.id.ivTwoSeven);
        ivThreeSeven = findViewById(R.id.ivThreeSeven);
        ivFourSeven = findViewById(R.id.ivFourSeven);
        ivFiveSeven = findViewById(R.id.ivFiveSeven);
        ivSixSeven = findViewById(R.id.ivSixSeven);

        board = new ImageView[][]{{ivOneOne, ivOneTwo, ivOneThree, ivOneFour, ivOneFive, ivOneSix, ivOneSeven},
                {ivTwoOne, ivTwoTwo, ivTwoThree, ivTwoFour, ivTwoFive, ivTwoSix, ivTwoSeven},
                {ivThreeOne, ivThreeTwo, ivThreeThree, ivThreeFour, ivThreeFive, ivThreeSix, ivThreeSeven},
                {ivFourOne, ivFourTwo, ivFourThree, ivFourFour, ivFourFive, ivFourSix, ivFourSeven},
                {ivFiveOne, ivFiveTwo, ivFiveThree, ivFiveFour, ivFiveFive, ivFiveSix, ivFiveSeven},
                {ivSixOne, ivSixTwo, ivSixThree, ivSixFour, ivSixFive, ivSixSix, ivSixSeven}};

        Intent intent = new Intent();
        game = new Game(intent.getStringArrayExtra("playerNames"),intent.getBooleanExtra("isAiGame",false));
    }

    public void updateBoard()
    {
        char[][] backendBoard = game.getBoard();
        for (int i = 0; i < backendBoard.length; i++)
        {
            for (int j = 0; j < backendBoard.length; j++)
            {
                char color = backendBoard[i][j];
                switch (color)
                {
                    case 'R':
                        board[i][j].setImageResource(R.drawable.red_token);
                        break;
                    case 'Y':
                        board[i][j].setImageResource(R.drawable.yellow_token);
                        break;
                    default:
                        board[i][j].setImageResource(R.drawable.empty_token);
                }
            }
        }
    }

    public void placePiece(int colNum)
    {
        game.doTurn(colNum);
        updateBoard();
    }

    @SuppressLint("NonConstantResourceId")
    public void onBtnClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnCol1Place:
                placePiece(1);
                break;
            case R.id.btnCol2Place:
                placePiece(2);
                break;
            case R.id.btnCol3Place:
                placePiece(3);
                break;
            case R.id.btnCol4Place:
                placePiece(4);
                break;
            case R.id.btnCol5Place:
                placePiece(5);
                break;
            case R.id.btnCol6Place:
                placePiece(6);
                break;
            case R.id.btnCol7Place:
                placePiece(7);
                break;
            case R.id.btnReset:
                reset();
                break;
            case R.id.btnMainMenu:
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void reset()
    {
        game.resetGame();
        updateBoard();
    }
}