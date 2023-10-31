import Cards.Card;

public class Bot extends Player {
    String Bottype;

    public Bot(int num) {
        super(num);
    }
    //TODO Write bot logic for choosing cards
    public Card PlayCard(){
        PlayedCards.add(Hand.get(0));
        Card playedcard = Hand.get(0);
        Hand.remove(0);
        return playedcard;
    }
}
