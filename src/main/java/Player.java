import Cards.Card;
import Cards.Nigiri;
import Cards.Special;

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
        if (Hand.get(index).getName().contains("nigiri")){
            for (Card card:PlayedCards){
                if (card.getName().equals("wasabi")){
                    PlayedCards.remove(card);
                    Nigiri cardtoadd = (Nigiri) Hand.get(index);
                    cardtoadd.activateWasabi();
                    PlayedCards.add(cardtoadd);
                    Hand.remove(index);
                    return cardtoadd;
                }
            }
        }
        PlayedCards.add(Hand.get(index));
        Card playedcard = Hand.get(index);
        Hand.remove(index);
        return playedcard;
    }
    public void addPoints(int points){
        Points += points;
    }
    public int getscore(){
        return Points;
    }
    //only for testing purposes
    public void addcardtoplayed(Card card){
        PlayedCards.add(card);
    }
}
