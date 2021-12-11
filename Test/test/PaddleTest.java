package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PaddleTest {

    Paddle paddle;

    @BeforeEach
    public void setup(){
        paddle = new Paddle(new Point(300,430),150,10, new Rectangle(0,0,600,450) );
    }

    @DisplayName("Test whether the change of the value of paddle's moveAmount is correct")
    @Test
    void moveAmountControlTest() {
        int initialMoveAmount = paddle.getMoveAmount();
        paddle.moveLeft();
        assertEquals(initialMoveAmount - paddle.getDefMoveAmount(), paddle.getMoveAmount());
        paddle.movRight();
        assertEquals(initialMoveAmount + paddle.getDefMoveAmount(), paddle.getMoveAmount());
        paddle.stop();
        assertEquals(0, paddle.getMoveAmount());
    }

    @Test
    void moveTest(){
        Point initialLocation = paddle.getBallPoint().getLocation();
        initialLocation.setLocation(paddle.getBallPoint().getX() + paddle.getMoveAmount(), paddle.getBallPoint().getY());
        paddle.move();
        assertEquals(initialLocation, paddle.getBallPoint().getLocation());
    }

    @Test
    void impactTest() {
        Ball ball = new RubberBall(new Point(300,430));
        assertTrue(paddle.impact(ball));
    }

    @Test
    void resetTest(){
        paddle.reset(new Point(300,430));

        assertEquals(paddle.getBallPoint().getLocation(), new Point(300,430));
    }

    @Test
    void setInnerColorTest() {
        paddle.setInnerColor(Color.red);
        assertEquals(paddle.getInnerColor(), Color.red);

        paddle.setInnerColor(Color.green);
        assertEquals(paddle.getInnerColor(), Color.green);
    }

    @Test
    void setBorderColorTest() {
        paddle.setBorderColor(Color.green);
        assertEquals(paddle.getBorderColor(), Color.green);
    }

}