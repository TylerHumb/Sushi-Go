package Cards;

import Cards.Card;

public class Dessert extends Card {
    int Amount = 0;
    int LastPoints;
    //TODO find a way to implement Fruit as a dessert
    //below is for green tea ice cream
    public Dessert(String name, int amount,int points){
        Name = name;
        Amount = amount;
        Points = points;
    }
    //only a long to differentiate the two constructors so the neccessary one can be used
    //below is for pudding
    public Dessert(String name,int points,long lastPoints){
        Name = name;
        LastPoints = (int) lastPoints;
        Points = points;
    }
    public int getAmount(){
        return Amount;
    }

    @Override
    public String toString() {
        return "Dessert{" +
                "LastPoints=" + LastPoints +
                ", Name='" + Name + '\'' +
                ", Points=" + Points +
                ", Value=" + Value +
                '}';
    }
}
