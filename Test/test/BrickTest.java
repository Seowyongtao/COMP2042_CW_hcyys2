package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {

    @Test
    void impactTest() {

        Brick clayBrick = new ClayBrick(new Point(0,0),new Dimension(3,1));
        int initialClayBrickStrength = clayBrick.getStrength();
        clayBrick.impact();
        assertTrue(clayBrick.isBroken());
        assertEquals(clayBrick.getStrength(), initialClayBrickStrength - 1);

        Brick cementBrick = new CementBrick(new Point(0,0),new Dimension(3,1));
        int initialCementBrickStrength = cementBrick.getStrength();
        cementBrick.impact();
        assertFalse(cementBrick.isBroken());
        assertEquals(cementBrick.getStrength(), initialCementBrickStrength - 1);
        cementBrick.impact();
        assertTrue(cementBrick.isBroken());
        assertEquals(cementBrick.getStrength(), initialCementBrickStrength - 2);
    }


    @Test
    void repairTest() {

        Brick clayBrick = new ClayBrick(new Point(0,0),new Dimension(3,1));
        clayBrick.impact();
        assertTrue(clayBrick.isBroken());
        int clayBrickStrengthAfterImpact = clayBrick.getStrength();
        clayBrick.repair();
        assertFalse(clayBrick.isBroken());
        assertEquals(clayBrick.getStrength(), clayBrickStrengthAfterImpact + 1);

        Brick cementBrick = new CementBrick(new Point(0,0),new Dimension(3,1));
        cementBrick.impact();
        cementBrick.impact();
        assertTrue(cementBrick.isBroken());
        int cementBrickStrengthAfterImpact = cementBrick.getStrength();
        cementBrick.repair();
        assertFalse(cementBrick.isBroken());
        assertEquals(cementBrick.getStrength(), cementBrickStrengthAfterImpact + 2);

        Brick steelBrick = new CementBrick(new Point(0,0),new Dimension(3,1));
        steelBrick.impact();
        int steelBrickStrengthAfterImpact = steelBrick.getStrength();
        if(steelBrick.isBroken()){
            steelBrick.repair();
            assertFalse(steelBrick.isBroken());
            assertEquals(steelBrick.getStrength(), steelBrickStrengthAfterImpact + 1);
        }
    }

}