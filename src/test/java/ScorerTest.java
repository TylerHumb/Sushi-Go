import Cards.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScorerTest {

    @Test
    void scoreNigiri() {
        Nigiri nigiri = new Nigiri("squid nigiri",3);
        assertEquals(3,Scorer.scoreNigiri(nigiri,1));
        nigiri.activateWasabi();
        assertEquals(9,Scorer.scoreNigiri(nigiri,1));
    }

    @Test
    void scoreAppetiser() {
        Appetiser eel = new Appetiser("eel",2,7,3);
        Appetiser miso = new Appetiser("miso soup");
        Appetiser sashimi = new Appetiser("sashimi",3,10);
        Appetiser tempura = new Appetiser("tempura",2,5);
        Appetiser tofu = new Appetiser("tofu");
        Appetiser dumpling = new Appetiser("dumpling");
        assertAll(
                () -> assertEquals(-3,Scorer.scoreAppetiser(eel,1)),
                () -> assertEquals(7,Scorer.scoreAppetiser(eel,2)),
                () -> assertEquals(4,Scorer.scoreAppetiser(eel,3)),
                () -> assertEquals(6,Scorer.scoreAppetiser(miso,2)),
                () -> assertEquals(10,Scorer.scoreAppetiser(sashimi,3)),
                () -> assertEquals(0,Scorer.scoreAppetiser(sashimi,2)),
                () -> assertEquals(5,Scorer.scoreAppetiser(tempura,2)),
                () -> assertEquals(0,Scorer.scoreAppetiser(tempura,1)),
                () -> assertEquals(0,Scorer.scoreAppetiser(tofu,3)),
                () -> assertEquals(6,Scorer.scoreAppetiser(tofu,2)),
                () -> assertEquals(2,Scorer.scoreAppetiser(tofu,1)),
                () -> assertEquals(15,Scorer.scoreAppetiser(dumpling,6)),
                () -> assertEquals(6,Scorer.scoreAppetiser(dumpling,3)),
                () -> assertEquals(10,Scorer.scoreAppetiser(dumpling,4))
        );
    }

    @Test
    void scoreSpecial() {
        Special tea = new Special("tea");
        assertEquals(2,Scorer.scoreSpecial(tea,1,2));
    }

    @Test
    void scoreDessert() {
        Dessert green = new Dessert("green tea ice cream",4,12);
        assertEquals(0,Scorer.scoreDessert(green,3));
        assertEquals(12,Scorer.scoreDessert(green,4));
    }
// very annoying to test, but here we go, yes i know this test is long but whatever
    @Test
    void scoreRolls() {
        Roll maki = new Roll("maki roll",0,6,3,0);
        HashMap<Integer,Integer> playerscores = new HashMap<>();
        playerscores.put(1,1);
        playerscores.put(2,1);
        playerscores.put(3,1);
        playerscores.put(4,2);
        playerscores.put(5,2);
        String[] result = Scorer.scoreRolls(playerscores,maki);
        assertEquals("45",result[0]);
        assertEquals("123",result[1]);
        playerscores.clear();
        playerscores.put(1,1);
        playerscores.put(2,2);
        playerscores.put(3,3);
        result = Scorer.scoreRolls(playerscores,maki);
        assertEquals("3",result[0]);
        assertEquals("2",result[1]);
        Roll temaki = new Roll("temaki",1,4,0,-4);
        playerscores.clear();
        playerscores.put(1,1);
        playerscores.put(2,2);
        playerscores.put(3,3);
        result = Scorer.scoreRolls(playerscores,temaki);
        assertEquals("3",result[0]);
        assertEquals("1",result[1]);
    }
}