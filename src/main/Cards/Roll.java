package main.Cards;

public class Roll extends Card{
    //use rollvalue to dictate if a roll is worth more than one point
    int RollValue;
    int SecondPoints;
    int LastPoints;
    //TODO Implement a way for URAMAKI to work
    //if there is no second place point or last place point, just leave as zero
    public Roll(String name,int rollvalue,int points,int secondPoints, int lastPoints){
        Name = name;
        RollValue =rollvalue;
        Points = points;
        SecondPoints = secondPoints;
        LastPoints = lastPoints;
    }
    public void setRollValue(int rollValue){
        RollValue = rollValue;
    }
}
