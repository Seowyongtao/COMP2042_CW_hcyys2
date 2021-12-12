import Brick_Destroy.Ball;
import Brick_Destroy.Block;
import Brick_Destroy.RubberBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    Block block;

    @BeforeEach
    public void setup(){
        block = new Block(120, 190, 80,10);;
    }

    @Test
    void setInnerColorTest() {
        block.setInnerColor(Color.red);
        assertEquals(block.getInnerColor(), Color.red);

        block.setInnerColor(Color.green);
        assertEquals(block.getInnerColor(), Color.green);
    }

    @Test
    void setBorderColorTest() {
        block.setBorderColor(Color.red);
        assertEquals(block.getBorderColor(), Color.red);

        block.setBorderColor(Color.green);
        assertEquals(block.getBorderColor(), Color.green);
    }

    @Test
    void impactTest() {
        Ball ball = new RubberBall(new Point(120,190));
        block.setVisible();
        assertTrue(block.impact(ball));
    }

}