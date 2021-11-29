package test;

public class BrickCount {

    private int brickCount;

    public BrickCount(int brickCount){

        this.brickCount = brickCount;
    }

    public int getBrickCount(){
        return brickCount;
    }

    public void brickCountDecrement(){
        brickCount--;
    }

    public void setBrickCount(int num){
        brickCount = num;
    }
}
