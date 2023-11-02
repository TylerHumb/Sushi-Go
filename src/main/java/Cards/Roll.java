package Cards;

import Cards.Card;

public class Roll extends Card {
    //use rollvalue to dictate if a roll is worth more than one point
    int RollValue;
    int SecondPoints;
    //TODO Implement a way for URAMAKI to work
    //if there is no second place point or last place point, just leave as zero
    public Roll(String name,int rollvalue,int points,int secondPoints){
        Name = name;
        RollValue =rollvalue;
        Points = points;
        SecondPoints = secondPoints;
    }
    public void setRollValue(int rollValue){
        RollValue = rollValue;
    }
    public int getRollValue(){
        return RollValue;
    }

    public int getSecondPoints() {
        return SecondPoints;
    }


    @Override
    public String toString() {
        return "Roll{" +
                "RollValue=" + RollValue +
                ", SecondPoints=" + SecondPoints +
                ", Name='" + Name + '\'' +
                ", Points=" + Points +
                ", Value=" + Value +
                '}';
    }
}
