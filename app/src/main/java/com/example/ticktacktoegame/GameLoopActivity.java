package com.example.ticktacktoegame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLoopActivity extends AppCompatActivity
{
    TextView txtGameStatus = null;
    private Button btn1 = null;
    private Button btn2 = null;
    private Button btn3 = null;
    private Button btn4 = null;
    private Button btn5 = null;
    private Button btn6 = null;
    private Button btn7 = null;
    private Button btn8 = null;
    private Button btn9 = null;
    private Button btnExit = null;

    //global vars that the user will edit when clicking buttons -> used in our game loop
    private int _userMove = 0;
    private int _aiMove = 0;
    private boolean _gameIsOver = false;
    private boolean _exitGame = false;
    private int _winner = 0; //0 = null; 1 = player; 2 = AI; 3 = draw;
    private char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Override
    protected void onCreate(Bundle instance)
    {
        super.onCreate(instance);
        controllerMethod();
    }
    private void controllerMethod()
    {
        showLayout();
        getReferenceToWidgets();
        createOnClickForButton();
        updateGameText(1);
    }
    private void showLayout()
    {
        setContentView(R.layout.game_screen);
    }
    private void getReferenceToWidgets()
    {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnExit = findViewById(R.id.btnExitGame);
        txtGameStatus = findViewById(R.id.txtGameStatus);
    }
    private void createOnClickForButton()
    {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(0))
                {
                    //want to load up the user's action
                    _userMove = 0;
                    handleGame();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(1))
                {
                    //want to load up the user's action
                    _userMove = 1;
                    handleGame();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(2))
                {
                    //want to load up the user's action
                    _userMove = 2;
                    handleGame();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(3))
                {
                    //want to load up the user's action
                    _userMove = 3;
                    handleGame();
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(4))
                {
                    //want to load up the user's action
                    _userMove = 4;
                    handleGame();
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(5))
                {
                    //want to load up the user's action
                    _userMove = 5;
                    handleGame();
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(6))
                {
                    //want to load up the user's action
                    _userMove = 6;
                    handleGame();
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(7))
                {
                    //want to load up the user's action
                    _userMove = 7;
                    handleGame();
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfTileIsOpen(8))
                {
                    //want to load up the user's action
                    _userMove = 8;
                    handleGame();
                }
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _exitGame = true;
            }
        });
    }

    ///
    /// Where our game logic actually begins:
    ///
    private void handleGame()
    {
        if(_winner==0) //no winner or Draw yet
        {
            updateGameText(2);
            gameLoop();
            return;
        }
        handleDraw();
    }
    private void gameLoop()
    {
        //handle player user moves
        handlePlayer();

        //handle AI moves
        handleAI();
    }
    private void handlePlayer()
    {
        if(!_gameIsOver)
        {
            //check user moves
            if(checkIfCanMoveThere(1)) //it's guaranteed that the player can move there, but we'll keep this in for robustness-sake
            {
                savePlayerMove(1);
                writePlayerMove(1);

                if(checkIfWasDrawOrWin(1))
                    return; //game ended
            }
            //reset the user's move
            _userMove = 0;
            return;
        }
        updateGameText(3);
    }

    private void handleAI()
    {
        if(!_gameIsOver)
        {
            calculateAIMove();
            if(checkIfCanMoveThere(2)) //it's guaranteed that the AI can move there, but we'll keep this in for robustness-sake
            {
                savePlayerMove(2);
                writePlayerMove(2);

                if(checkIfWasDrawOrWin(2))
                    return; //game ended
            }
            //reset the user's move
            _aiMove = 0;
            return;
        }
        updateGameText(3);
    }

    private boolean checkIfWasDrawOrWin(int player)
    {
        if(checkIfWin(player))
        {
            handleWinConditions(player);
            _gameIsOver = true;
            updateGameText(3); //draw will always be 3
            return true;
        }

        if(checkIfDraw())
        {
            handleDraw();
            return true;
        }
        return false;
    }

    //displays player and AI moves on the screen
    private void writePlayerMove(int player)
    {
        switch (player)
        {
            case 1: //normal player
                switch (_userMove+1)
                {
                    case 1:
                        btn1.setText("X");
                        break;
                    case 2:
                        btn2.setText("X");
                        break;
                    case 3:
                        btn3.setText("X");
                        break;
                    case 4:
                        btn4.setText("X");
                        break;
                    case 5:
                        btn5.setText("X");
                        break;
                    case 6:
                        btn6.setText("X");
                        break;
                    case 7:
                        btn7.setText("X");
                        break;
                    case 8:
                        btn8.setText("X");
                        break;
                    case 9:
                        btn9.setText("X");
                        break;
                }
                break;
            case 2: //AI
                switch (_aiMove+1)
                {
                    case 1:
                        btn1.setText("O");
                        break;
                    case 2:
                        btn2.setText("O");
                        break;
                    case 3:
                        btn3.setText("O");
                        break;
                    case 4:
                        btn4.setText("O");
                        break;
                    case 5:
                        btn5.setText("O");
                        break;
                    case 6:
                        btn6.setText("O");
                        break;
                    case 7:
                        btn7.setText("O");
                        break;
                    case 8:
                        btn8.setText("O");
                        break;
                    case 9:
                        btn9.setText("O");
                        break;
                }
                break;
        }
    }
    //returns false when tile is not open; true when it's available
    private boolean checkIfTileIsOpen(int playerTile)
    {
        if(board[playerTile] == 'X' || board[playerTile] == 'O')
            return false; //cant move there

        return true; //can move there
    }
    private void updateGameText(int id)
    {
        switch (id)
        {
            case 1:
                txtGameStatus.setText("Press a tile to start the game");
                break;
            case 2:
                txtGameStatus.setText("Game in progress");
                break;
            case 3:
                switch(_winner)
                {
                    case 1: //player won
                        txtGameStatus.setText("You win!");
                        break;
                    case 2: //player won
                        txtGameStatus.setText("The AI wins!");
                        break;
                    case 3: //player won
                        txtGameStatus.setText("It's a Draw.");
                        break;
                }
        }
    }
    private void handleDraw()
    {
        writePlayerMove(3);
        _gameIsOver = true;
        updateGameText(3);
    }
    private boolean checkIfCanMoveThere(int player)
    {
        switch(player)
        {
            case 1:
                return checkIfTileIsOpen(_userMove);

            case 2:
                // Check if the position is already occupied
                return checkIfTileIsOpen(_aiMove);
        }
        return false;
    }
    private void handleWinConditions(int player)
    {
        _winner = player; //1 = player; 2 = AI
        _gameIsOver = true;
        _exitGame = true;
    }
    private boolean checkIfWin(int player)
    {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == board[i + 1] && board[i + 1] == board[i + 2])
            {
                return true; // Row win
            }
        }

        // Check columns
        for (int i = 0; i < 3; ++i) {
            if (board[i] == board[i + 3] && board[i + 3] == board[i + 6])
            {
                return true; // Column win
            }
        }

        // Check diagonals
        if ((board[0] == board[4] && board[4] == board[8]) ||
            (board[2] == board[4] && board[4] == board[6]))
        {
            return true; // Diagonal win
        }

        return false; // No win yet
    }
    private boolean checkIfDraw()
    {
        for (int i = 0; i < 9; ++i) { //9 is the max number of moves we can make on a 3x3 board
            if (board[i] != 'X' && board[i] != 'O')
            {
                // If any position is not filled, the game is not a draw just yet
                return false;
            }
        }
        // All positions are filled, indicating a draw
        return true;
    }
    private void savePlayerMove(int player)
    {
        switch (player)
        {
            case 1: //player
                board[_userMove] = 'X';
                break;
            case 2: //AI
                board[_aiMove] = '0';
                break;
        }
    }
    private void calculateAIMove()
    {
        int lastValidMove = 0;

        for(int i = 0; i<9; i++)
        {
            if (checkIfTileIsOpen(i))
            {
                lastValidMove = i;
                Log.i("Ai move", "tile was available: " + i + " last valid move: " + lastValidMove);

                //save the first move in the set - we're going to recursively calculate all possibilities for this one, until we find a winning move
                char originalVal = board[i];
                board[i] = 'O';

                //find out if this move results in a win
                boolean moveResultsInWinOrDraw = isMoveResultsInWinOrDraw(2);

                //reset the board
                board[i] = originalVal;

                //just make the first move that gets us a win
                if(moveResultsInWinOrDraw)
                {
                    Log.i("Ai move", "Chosen move: " + lastValidMove);
                    _aiMove = i;
                    return;
                }
            }
        }

        //if there were absolutely no open slots, then we know that it's a draw
        if(lastValidMove <= 0)
        {
            handleDraw();
            Log.i("Ai move", "calculateAIMove: last valid move was empty, it's a draw ");
            return;
        }

        //no suitable moves were found, we need to pick a random move (we'll just take the last valid move we had)
        _aiMove = lastValidMove;
        Log.i("Ai move", "calculateAIMove: last valid move was: " + lastValidMove);
    }
    private boolean isMoveResultsInWinOrDraw(int depth)
    {
        if(checkIfWin(2))
            return true;
        if(checkIfDraw())
            return true;
        if(depth<=0)
            return false;

        for(int i = 0; i<9; i++)
        {
            if(checkIfTileIsOpen(i))
            {
                char originalVal = board[i];
                board[i] = 'O';

                //find out if this move results in a win
                boolean moveResultsInWinOrDraw = isMoveResultsInWinOrDraw(depth-1);

                //reset the board
                board[i] = originalVal;

                //just take the first set of moves that gets us a win or draw
                if(moveResultsInWinOrDraw)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
