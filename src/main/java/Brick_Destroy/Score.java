package Brick_Destroy;

/**
 * For getting and changing the value of score
 */
public class Score {

    private int score;

    /**
     * Constructor for score
     */
    public Score(){
        this.score = 0;
    }

    /**
     * Get the current score
     *
     * @return Current score
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Increment the score based on the given number
     *
     * @param num Number
     */
    public void scoreIncrement(int num){
        this.score = this.score + num;
    }

    /**
     * Decrement the score based on the given number
     *
     * @param num Number
     */
    public void scoreDecrement(int num){
        this.score = this.score - num;
    }

    /**
     * Set the score to 0
     */
    public void reset(){
        this.score = 0;
    }
}
