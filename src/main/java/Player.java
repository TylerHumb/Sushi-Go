import Cards.Card;

import java.util.ArrayList;

public class Player {
    int Points;
    int Num;
    ArrayList<Card> PlayedCards;
    ArrayList<Card> Hand;

    public Player(int num){
        Points = 0;
        Num = num;
        PlayedCards = new ArrayList<>();
    }
    public void Assignhand(ArrayList<Card> hand){
        Hand = hand;
    }

    public ArrayList<Card> getHand() {
        return Hand;
    }

    public int getNum() {
        return Num;
    }
    public Card PlayCard(int index){
        PlayedCards.add(Hand.get(index));
        Card playedcard = Hand.get(index);
        Hand.remove(index);
        return playedcard;
    }
}
