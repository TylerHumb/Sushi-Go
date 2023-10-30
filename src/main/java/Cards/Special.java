package Cards;

import Cards.Card;

public class Special extends Card {
    public Special(String name){
        Name = name;
    }

    @Override
    public String toString() {
        return "Special{" +
                "Name='" + Name + '\'' +
                ", Points=" + Points +
                ", Value=" + Value +
                '}';
    }
}
