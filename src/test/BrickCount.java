package test;

/**
 * For getting and changing the value of brickCount
 */
public class BrickCount {

    private int brickCount;

    /**
     * Constructor for brickCount
     *
     * @param brickCount Value for initial brick count
     */
    public BrickCount(int brickCount){

        this.brickCount = brickCount;
    }

    /**
     * Get current value of brickCount
     *
     * @return Current value of brickCount
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * Decrement the brickCount by 1
     */
    public void brickCountDecrement(){
        brickCount--;
    }

    /**
     * Update the value for brickCount
     *
     * @param num New value for brickCount
     */
    public void setBrickCount(int num){
        brickCount = num;
    }
}
