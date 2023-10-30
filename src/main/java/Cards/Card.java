package Cards;

public abstract class Card {
    String Name;
    int Points;
    int Value;
    public void setValue(int value) {
        Value = value;
    }
    public void setPoints(int points) {
        Points = points;
    }
    public String getName(){
        return Name;
    }
}
