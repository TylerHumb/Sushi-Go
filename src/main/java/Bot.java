import Cards.Card;
import Cards.Nigiri;

public class Bot extends Player {
    String Bottype;

    public Bot(int num) {
        super(num);
    }
    //TODO Write bot logic for choosing cards
    public Card PlayCard(){
        if (Hand.get(0).getName().contains("nigiri")){
            for (Card card:PlayedCards){
                if (card.getName().equals("wasabi")){
                    PlayedCards.remove(card);
                    Nigiri cardtoadd = (Nigiri) Hand.get(0);
                    cardtoadd.activateWasabi();
                    PlayedCards.add(cardtoadd);
                    Hand.remove(0);
                    return cardtoadd;
                }
            }
        }
        PlayedCards.add(Hand.get(0));
        Card playedcard = Hand.get(0);
        Hand.remove(0);
        return playedcard;
    }
}
