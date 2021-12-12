import Brick_Destroy.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    Score score;

    @BeforeEach
    public void setup(){
        score = new Score();
    }

    @Test
    void scoreIncrementTest() {
        int initialScore = score.getScore();
        score.scoreIncrement(10);
        assertEquals(score.getScore(), initialScore + 10);
    }

    @Test
    void scoreDecrementTest() {
        int initialScore = score.getScore();
        score.scoreDecrement(10);
        assertEquals(score.getScore(), initialScore - 10);
    }

    @Test
    void resetTest() {
        score.scoreDecrement(10);
        assertNotEquals(0, score.getScore());
        score.reset();
        assertEquals(0, score.getScore());
    }
}