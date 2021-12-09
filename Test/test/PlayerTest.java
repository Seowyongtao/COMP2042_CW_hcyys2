package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;

    @BeforeEach
    public void setup(){
        player = new Player(new Point(300,430),150,10, new Rectangle(0,0,600,450) );
    }

    @DisplayName("Test whether the change of the value of player's moveAmount is correct")
    @Test
    void moveAmountControlTest() {
        int initialMoveAmout = player.getMoveAmount();
        player.moveLeft();
        assertEquals(initialMoveAmout - player.getDefMoveAmount(), player.getMoveAmount());
        player.movRight();
        assertEquals(initialMoveAmout + player.getDefMoveAmount(), player.getMoveAmount());
        player.stop();
        assertEquals(0, player.getMoveAmount());
    }

    @Test
    void moveTest(){
        Point initialLocation = player.getBallPoint().getLocation();
        initialLocation.setLocation(player.getBallPoint().getX() + player.getMoveAmount(), player.getBallPoint().getY());
        player.move();
        assertEquals(initialLocation, player.getBallPoint().getLocation());
    }

    @Test
    void impactTest() {
        Ball ball = new RubberBall(new Point(300,430));
        assertTrue(player.impact(ball));
    }

    @Test
    void resetTest(){
        player.reset(new Point(300,430));

        assertEquals(player.getBallPoint().getLocation(), new Point(300,430));
    }

    @Test
    void setInnerColorTest() {
        player.setInnerColor(Color.red);
        assertEquals(player.getInnerColor(), Color.red);

        player.setInnerColor(Color.green);
        assertEquals(player.getInnerColor(), Color.green);
    }

    @Test
    void setBorderColorTest() {
        player.setBorderColor(Color.green);
        assertEquals(player.getBorderColor(), Color.green);
    }

}