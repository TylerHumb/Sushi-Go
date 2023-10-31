import Cards.Card;

import java.util.ArrayList;

public class Player {
    int Points;
    int Num;
    ArrayList<Card> PlayedCards;

    public Player(int num){
        Points = 0;
        Num = num;
        PlayedCards = new ArrayList<>();
    }
}
