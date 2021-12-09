package test;

import org.junit.jupiter.api.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    Ball ball;

    @BeforeEach
    public void setup(){
        ball = new RubberBall(new Point(300,430));
    }

    @DisplayName("When the Ball object is created, its speedX must be within the range of -2 to 2")
    @RepeatedTest(value=5)
    void getSpeedXWithinCorrectRangeWhenBallObjectIsCreated() {
        assertTrue(ball.getSpeedX() <= 2  && ball.getSpeedX() >= -2);
    }

    @DisplayName("When the Ball object is created, its speedY must be within the range of 0 to -2")
    @RepeatedTest(value=5)
    void getSpeedYWithinCorrectRangeWhenBallObjectIsCreated() {
        assertTrue(ball.getSpeedY() <= 0 && ball.getSpeedY() >= -2);
    }

    @Test
    void reverseXTest(){
        int initialSpeedX = ball.getSpeedX();
        ball.reverseX();
        assertEquals(ball.getSpeedX(), initialSpeedX * -1);
    }

    @Test
    void reverseYTest(){
        int initialSpeedY = ball.getSpeedY();
        ball.reverseY();
        assertEquals(ball.getSpeedY(), initialSpeedY * -1);
    }

    @Test
    void resetTest(){
        ball.reset(new Point(300,430));
        assertEquals(ball.getPosition(), new Point(300,430));
        assertTrue(ball.getSpeedX() <= 2  && ball.getSpeedX() >= -2);
        assertTrue(ball.getSpeedY() <= 0 && ball.getSpeedY() >= -2);
        assertFalse(ball.getIsLost());
    }

    @Test
    void resetBallCountTest(){
        ball.resetBallCount();
        assertEquals(ball.getCount(), 3);
    }

    @Test
    void count_decrementTest(){
        int initialBallCount = ball.getCount();
        ball.count_decrement();
        assertEquals(ball.getCount(), initialBallCount - 1);
    }

    @Test
    void setInnerColorTest(){
        ball.setInnerColor(Color.red);
        assertEquals(ball.getInnerColor(), Color.red);

        ball.setInnerColor(Color.green);
        assertEquals(ball.getInnerColor(), Color.green);
    }

    @Test
    void setBorderColorTest(){
        ball.setBorderColor(Color.red);
        assertEquals(ball.getBorderColor(), Color.red);

        ball.setBorderColor(Color.green);
        assertEquals(ball.getBorderColor(), Color.green);
    }

}