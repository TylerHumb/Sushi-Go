import Cards.*;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> Deck;
    ArrayList<Card> PossibleCards;
    ArrayList<Card> UsedCards;

    public void GeneratePossible(){
        PossibleCards.add(new Nigiri("egg nigiri",1));
        PossibleCards.add(new Nigiri("salmon nigiri",1));
        PossibleCards.add(new Nigiri("squid nigiri",1));
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
        PossibleCards.add(new Dessert("green tea ice cream",4,10));
        PossibleCards.add(new Dessert("pudding",6,6));
    }
    public void GenerateUsed(Roll roll,Appetiser appetiser1,Appetiser appetiser2,Appetiser appetiser3,Special special1,Special special2, Dessert dessert){
        switch (roll.getName()){
            case "maki roll":
                AddMaki(roll);
            case "temaki":
                addcardtoused(roll,12);
        }
        if (!appetiser1.getName().equals("onigiri")){
            addcardtoused(appetiser1,8);
        } else
    }
    public void Round1(){
    }
    public void addcardtoused(Card card,int amount){
        for (int i = amount;i > 0;i--){
            UsedCards.add(card);
        }
    }
    public void AddMaki(Roll roll){
        roll.setRollValue(1);
        addcardtoused(roll,4);
        roll.setRollValue(2);
        addcardtoused(roll,5);
        roll.setRollValue(3);
        addcardtoused(roll,3);
    }
    public void AddOnigiri(Appetiser appetiser){
        //tobeadded
    }
}
