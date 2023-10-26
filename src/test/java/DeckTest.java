import Cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;
    @BeforeEach
    void setup(){
        deck = new Deck(3);
    }

    @Test
    void generatePossible() {
        ArrayList<Card> possiblecards = deck.GeneratePossible();
        assertTrue(possiblecards.size() > 10);
    }

    @Test
    void generateUsed() {
        ArrayList<Card> p = deck.GeneratePossible();
        deck.GenerateUsed((Roll) p.get(0),(Appetiser) p.get(3),(Appetiser)p.get(4),(Appetiser)p.get(5),(Special) p.get(9),(Special) p.get(10),(Dessert) p.get(11));
        assertEquals(54,deck.UsedCards.size());

    }

    @Test
    void round1() {
        ArrayList<Card> p = deck.GeneratePossible();
        deck.GenerateUsed((Roll) p.get(0),(Appetiser) p.get(3),(Appetiser)p.get(4),(Appetiser)p.get(5),(Special) p.get(9),(Special) p.get(10),(Dessert) p.get(11));
        ArrayList<ArrayList<Card>> hands = deck.Round1();
        assertAll(
                () -> assertEquals(3,hands.size()),
                () -> assertEquals(10,hands.get(1).size())
        );
        int dessertcount = 0;
        for (ArrayList<Card> hand:hands){
            for (Card card: hand){
                if (card instanceof Dessert){
                    dessertcount += 1;
                }
            }
        }
        assertEquals(5,dessertcount + deck.RemainingDessert);
    }
}