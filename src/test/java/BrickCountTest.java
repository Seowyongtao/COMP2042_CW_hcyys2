import Brick_Destroy.BrickCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickCountTest {

    BrickCount brickCount;

    @BeforeEach
    public void setup(){
        brickCount = new BrickCount(30);
    }

    @Test
    void brickCountDecrementTest() {
        int brickCountNum = brickCount.getBrickCount();
        brickCount.brickCountDecrement();
        assertEquals(brickCount.getBrickCount(), brickCountNum - 1);
    }

    @Test
    void setBrickCountTest() {
        brickCount.setBrickCount(33);
        assertEquals(brickCount.getBrickCount(), 33);

        brickCount.setBrickCount(25);
        assertEquals(brickCount.getBrickCount(), 25);
    }
}