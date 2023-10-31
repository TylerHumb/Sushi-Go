package Cards;

import Cards.Card;

public class Nigiri extends Card {
    boolean wasabi = false;
    public Nigiri(String name,int points){
        Name = name;
        Points = points;
    }
    public void activateWasabi(){
        wasabi = true;
    }
    public boolean isWasabi(){
        return wasabi;
    }

    @Override
    public String toString() {
        return "Nigiri{" +
                "Name='" + Name + '\'' +
                ", Points=" + Points +
                ", Value=" + Value +
                '}';
    }
}
