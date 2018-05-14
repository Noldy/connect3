package com.example.mitta.connect3;

public class Player {

    private String turn = new String();
    private int[] boardState = {0,0,0,0,0,0,0,0,0};


    public void setTurn(String turn){
        this.turn = turn;
    }
    public String getTurn(){
        return this.turn;
    }

    public void updateBoardState(int playerNumber, int position){
        this.boardState[position] = playerNumber;
    }
    public int getBoardState(int position){
        return this.boardState[position];
    }


    public void nextPlayer(){

        if(this.turn.equals("Red")) {
            this.turn = "Yellow";
        }
        else if(this.turn.equals("Yellow")){
            this.turn = "Red";
        }
    }


}
