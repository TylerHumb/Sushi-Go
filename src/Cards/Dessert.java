package Cards;

public class Dessert extends Card {
    int Amount;
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
}
