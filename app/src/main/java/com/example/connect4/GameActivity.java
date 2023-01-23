package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/*
•	Swap text according to current player turn (15 minutes)
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

    public Button btnCol1Place, btnCol2Place, btnCol3Place, btnCol4Place,
            btnCol5Place, btnCol6Place, btnCol7Place, btnMainMenu, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    @SuppressLint("SetTextI18n")
    public void init()
    {
        lblTurn = findViewById(R.id.lblTurn);
        lblP1Wins = findViewById(R.id.lblP1Wins);
        lblP2wins = findViewById(R.id.lblP2Wins);
        lblP1 = findViewById(R.id.lblP1);
        lblP2 = findViewById(R.id.lblP2);

        btnCol1Place = findViewById(R.id.btnCol1Place);
        btnCol2Place = findViewById(R.id.btnCol2Place);
        btnCol3Place = findViewById(R.id.btnCol3Place);
        btnCol4Place = findViewById(R.id.btnCol4Place);
        btnCol5Place = findViewById(R.id.btnCol5Place);
        btnCol6Place = findViewById(R.id.btnCol6Place);
        btnCol7Place = findViewById(R.id.btnCol7Place);
        btnMainMenu = findViewById(R.id.btnMainMenu);
        btnReset = findViewById(R.id.btnReset);

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
        lblP1.setText(game.getPlayerNames()[1]);
        lblP2.setText(game.getPlayerNames()[2]);
        lblTurn.setText("It's " + game.getCurrentPlayer() + "'s Turn");

        toggleButtons(true,true,true,true,true,true,true,false,false);
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
        String currentPlayer = game.getCurrentPlayer();
        int turnCode = game.doTurn(colNum);
        updateBoard();
        switch(turnCode)
        {
            case 0:
                lblTurn.setText("It's " + game.getCurrentPlayer() + "'s Turn");
                break;
            case 1:
                break;
            case 2:
                lblTurn.setText(currentPlayer + " Wins!");
                break;
            case 3:
                lblTurn.setText("It was a tie");
                break;
        }
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

    public void toggleButtons(boolean col1Btn, boolean col2Btn, boolean col3Btn, boolean col4Btn, boolean col5Btn, boolean col6Btn, boolean col7Btn, boolean reset, boolean home)
    {
        if (col1Btn) {
            btnCol1Place.setVisibility(View.VISIBLE);
        } else {
            btnCol1Place.setVisibility(View.GONE);
        }

        if (col2Btn) {
            btnCol2Place.setVisibility(View.VISIBLE);
        } else {
            btnCol2Place.setVisibility(View.GONE);
        }

        if (col3Btn) {
            btnCol4Place.setVisibility(View.VISIBLE);
        } else {
            btnCol4Place.setVisibility(View.GONE);
        }

        if (col4Btn) {
            btnCol4Place.setVisibility(View.VISIBLE);
        } else {
            btnCol4Place.setVisibility(View.GONE);
        }

        if (col5Btn) {
            btnCol5Place.setVisibility(View.VISIBLE);
        } else {
            btnCol5Place.setVisibility(View.GONE);
        }

        if (col6Btn) {
            btnCol6Place.setVisibility(View.VISIBLE);
        } else {
            btnCol6Place.setVisibility(View.GONE);
        }

        if (col7Btn) {
            btnCol7Place.setVisibility(View.VISIBLE);
        } else {
            btnCol7Place.setVisibility(View.GONE);
        }

        if (home) {
            btnMainMenu.setVisibility(View.VISIBLE);
        } else {
            btnMainMenu.setVisibility(View.GONE);
        }

        if (reset) {
            btnReset.setVisibility(View.VISIBLE);
        } else {
            btnReset.setVisibility(View.GONE);
        }
    }


    public void reset()
    {
        game.resetGame();
        updateBoard();
    }
}