package test;

public class Score {

    private int score;

    public Score(){
        this.score = 0;
    }

    public int getScore(){
        return this.score;
    }

    public void scoreIncrement(int num){
        this.score = this.score + num;
    }

    public void scoreDecrement(int num){
        this.score = this.score - num;
    }

    public void reset(){
        this.score = 0;
    }
}
