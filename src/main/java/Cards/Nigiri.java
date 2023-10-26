package Cards;

import Cards.Card;

public class Nigiri extends Card {
    public Nigiri(String name,int points){
        Name = name;
        Points = points;
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
