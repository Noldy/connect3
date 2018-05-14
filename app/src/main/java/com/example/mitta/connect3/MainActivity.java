package com.example.mitta.connect3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Player player = new Player();
    boolean isGameFinished = false;
    // Places the next counter on the board
    public void placeCounter(View view){

        TextView turnIndicator = findViewById(R.id.textView);
        ImageView counter = (ImageView) view;
        int counterPosition = Integer.parseInt(counter.getTag().toString());

        // Check if a counter is already placed
        while(counter.getDrawable() == null && !isGameFinished) {
            // Set counter on board,display turn message and update the board state
            if (player.getTurn().equals("Red")) {
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).setDuration(300);
                turnIndicator.setText("Yellow Player's Turn");
                player.updateBoardState(1,counterPosition);
            }
            else if (player.getTurn().equals("Yellow")) {
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).setDuration(300);
                turnIndicator.setText("Red Player's Turn");
                player.updateBoardState(2,counterPosition);
            }

            // Check for winner
            if(playerHasWon()){
                String winningToast = player.getTurn() + " player has won!";
                turnIndicator.setText(winningToast);
                //Toast.makeText(getApplicationContext(),winningToast, Toast.LENGTH_LONG).show();
                isGameFinished = true;
            }

            player.nextPlayer();
        }
    }

    // Clear the counter and restart all values to default
    public void restartGame(View view){

        ImageView cell1 = findViewById(R.id.imageView);
        ImageView cell2 = findViewById(R.id.imageView2);
        ImageView cell3 = findViewById(R.id.imageView3);
        ImageView cell4 = findViewById(R.id.imageView4);
        ImageView cell5 = findViewById(R.id.imageView5);
        ImageView cell6 = findViewById(R.id.imageView6);
        ImageView cell7 = findViewById(R.id.imageView7);
        ImageView cell8 = findViewById(R.id.imageView8);
        ImageView cell9 = findViewById(R.id.imageView9);

        cell1.setImageResource(0);
        cell2.setImageResource(0);
        cell3.setImageResource(0);
        cell4.setImageResource(0);
        cell5.setImageResource(0);
        cell6.setImageResource(0);
        cell7.setImageResource(0);
        cell8.setImageResource(0);
        cell9.setImageResource(0);

        TextView turnIndicator = findViewById(R.id.textView);
        turnIndicator.setText("Red goes first");
        player.setTurn("Red");

        for(int i = 0 ; i < 9; i++){
            player.updateBoardState(0,i);
        }

        isGameFinished = false;
    }

    // Checks if the a player has completed a connect 3
    public boolean playerHasWon(){

        boolean isConnect3 = false;
        int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int[] winningPosition:winningPositions){

            if(player.getBoardState(winningPosition[0]) == player.getBoardState(winningPosition[1]) &&
                    player.getBoardState(winningPosition[1]) == player.getBoardState(winningPosition[2]) &&
                        player.getBoardState(winningPosition[0]) != 0){
                isConnect3 = true;
                break;
            }

        }

        return isConnect3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player.setTurn("Red");
    }

}

