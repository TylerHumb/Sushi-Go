import Cards.*;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> RollbackDeck;
    ArrayList<Card> PossibleCards;
    ArrayList<Card> UsedCards;
    int PlayerCount;
    //keeps track of what dessert the game is using, so it can be added outside generate used
    Card GamesDessert;
    int RemainingDessert;

    public Deck(int playercount){
        PlayerCount = playercount;
        PossibleCards = new ArrayList<>();
        UsedCards = new ArrayList<>();
    }

    public ArrayList<Card> GeneratePossible(){
        PossibleCards.add(new Roll("maki roll",0,6,3,0));
        PossibleCards.add(new Roll("temaki",1,4,0,-4));
        PossibleCards.add(new Appetiser("dumpling"));
        PossibleCards.add(new Appetiser("edamame"));
        PossibleCards.add(new Appetiser("eel",2,7,3));
        PossibleCards.add(new Appetiser("miso soup"));
        PossibleCards.add(new Appetiser("sashimi",3,10));
        PossibleCards.add(new Appetiser("tempura",2,5));
        PossibleCards.add(new Appetiser("tofu"));
        PossibleCards.add(new Special("tea"));
        PossibleCards.add(new Special("wasabi"));
        PossibleCards.add(new Dessert("green tea ice cream",4,12));
        PossibleCards.add(new Dessert("pudding",6,6));
        return PossibleCards;
    }
    public void GenerateUsed(Roll roll,Appetiser appetiser1,Appetiser appetiser2,Appetiser appetiser3,Special special1,Special special2, Dessert dessert){
        AddNigiri();
        if (roll.getName().equals("maki roll")) {
            AddMaki(roll);
        }else{
                addcardtoused(roll,12);
        }
        if (!appetiser1.getName().equals("onigiri")){
            addcardtoused(appetiser1,8);
        } else{
            AddOnigiri(appetiser1);
        }
        if (!appetiser2.getName().equals("onigiri")){
            addcardtoused(appetiser2,8);
        } else{
            AddOnigiri(appetiser2);
        }
        if (!appetiser3.getName().equals("onigiri")){
            addcardtoused(appetiser3,8);
        } else{
            AddOnigiri(appetiser3);
        }
        addcardtoused(special1,3);
        addcardtoused(special2,3);
        //doesnt add desserts as they are added on a round by round basis, but it is assigned here
        GamesDessert = dessert;
    }
    public ArrayList<ArrayList<Card>> Round1(){
        // a backup deck so that we can remove cards from the deck  and return it back after the round
        RollbackDeck = UsedCards;
        if (PlayerCount > 5){
            addcardtoused(GamesDessert, 7);
            RemainingDessert = 7;
        } else{
            addcardtoused(GamesDessert, 5);
            RemainingDessert = 5;
        }
        return DealHands();
    }
    private void addcardtoused(Card card,int amount){
        for (int i = amount;i > 0;i--){
            UsedCards.add(card);
        }
    }
    private void AddMaki(Roll roll){
        Roll maki1 = new Roll("maki roll",1,6,3,0);
        addcardtoused(maki1,4);
        Roll maki2 = new Roll("maki roll",2,6,3,0);
        addcardtoused(maki2,5);
        roll.setRollValue(3);
        addcardtoused(roll,3);
    }
    private void AddOnigiri(Appetiser appetiser){
        //tobeadded
    }
    private void AddNigiri(){
        addcardtoused(new Nigiri("egg nigiri",1),4);
        addcardtoused(new Nigiri("salmon nigiri",2),5);
        addcardtoused(new Nigiri("squid nigiri",3),3);
    }
    private ArrayList<ArrayList<Card>> DealHands() {
        int cardcount;
        ArrayList<ArrayList<Card>> hands = new ArrayList<>();
        if (PlayerCount == 2 || PlayerCount == 3) {
            cardcount = 10;
        } else if (PlayerCount == 4 || PlayerCount == 5) {
            cardcount = 9;
        } else if (PlayerCount == 6 || PlayerCount == 7) {
            cardcount = 8;
        } else cardcount = 7;
        for (int i = PlayerCount; i > 0; i--) {
            ArrayList<Card> hand = new ArrayList<>();
            for (int c = cardcount; c > 0; c--) {
                hand.add(DrawCard());
            }
            hands.add(hand);
        }
        return hands;
    }

    //Picks a card from the deck and removes it, doesnt ruin the deck as the card will be added back later
    private Card DrawCard(){
        Random rand = new Random();
        int index = rand.nextInt(UsedCards.size());
        Card chosencard = UsedCards.get(index);
        UsedCards.remove(index);
        // keeps track of how many desserts are left in the deck, so they can be added back in later
        if (chosencard instanceof Dessert){
            RemainingDessert -= 1;
        }
        return chosencard;
    }
}
