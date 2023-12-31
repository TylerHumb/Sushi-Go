import Cards.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Scorer {
    static int EdamameCount;
    static int mostcards;
    public static HashMap<Card,Integer> generatehashmap(ArrayList<Card> playedcards){
        mostcards = 0;
        HashMap<Card,Integer> cardsByCard = new HashMap<>();
        //generates a hashmap showing what cards are present in the played cards and their amount
        for (Card card:playedcards){
            if (cardsByCard.containsKey(card)){
                int amount = cardsByCard.get(card) + 1;
                cardsByCard.put(card,amount);
                if (amount > mostcards) mostcards = amount;
            } else cardsByCard.put(card, 1);
        }
        return cardsByCard;
    }
    public static int nonFinalScore(ArrayList<Card> playedcards){
        int runningCount = 0;
        HashMap<Card,Integer> cardsByCard =generatehashmap(playedcards);
        for (Card card: cardsByCard.keySet()){
            if (card instanceof Nigiri){
                runningCount += scoreNigiri((Nigiri)card,cardsByCard.get(card));
                continue;
            }
            if (card instanceof Appetiser){
                runningCount += scoreAppetiser((Appetiser) card,cardsByCard.get(card));
            }
            if (card instanceof Special){
                runningCount += scoreSpecial((Special) card,cardsByCard.get(card),mostcards);
            }
        }
        return runningCount;
    }
    public static int finalScoring(ArrayList<Card> playedcards){
        int runningCount = nonFinalScore(playedcards);
        HashMap<Card,Integer> cardsByCard = generatehashmap(playedcards);
        for(Card card: cardsByCard.keySet()){
            if (card instanceof Dessert){
                runningCount += scoreDessert((Dessert) card,cardsByCard.get(card));
            }
        }
        return runningCount;
    }

    //TODO Check if this works for wasabi
    public static int scoreNigiri(Nigiri card, Integer amount){
        if (card.isWasabi()){
            return card.getPoints()*3;
        }
        return card.getPoints()*amount;
    }
    public static int scoreAppetiser(Appetiser card, Integer amount){
        //checks if the card is edamame, to be added later
        if (card.getName().equals("edamame")){
            EdamameCount += 1;
            return 0;
        }
        if (card.getAmount() != 0){
            int sets = amount / card.getAmount();
            if (amount% card.getAmount() != 0){
                return sets*card.getPoints() - card.getEelDeduction();
            }
            return sets*card.getPoints();
        }
        if (card.getName().equals("tofu")){
            return switch (amount) {
                case 1 -> 2;
                case 2 -> 6;
                default -> 0;
            };
        }
        if (card.getName().equals("dumpling")){
            return switch (amount) {
                case 1 -> 1;
                case 2 -> 3;
                case 3 -> 6;
                case 4 -> 10;
                default -> 15;
            };
        }
        //miso is just worth 3 each
        if (card.getName().equals("miso soup")){
            return amount*3;
        }
        throw new RuntimeException("Cannot score card");
    }

    public static int scoreSpecial(Special card, Integer amount,int mostcards){
        if (card.getName().equals("tea")){
            return mostcards*amount;
        }
        throw new RuntimeException("Cannot score card");
    }

    public static int scoreDessert(Dessert card, Integer amount){
        if (card.getAmount() != 0){
            int sets = amount / card.getAmount();
            return sets*card.getPoints();
        }
        if (card.getName().equals("pudding")){
            return 0;
        }
        throw new RuntimeException("Cannot score card");
    }
    //has to call roll scoring normally, returns a string of the players numbers
    public static String[] scoreRolls(HashMap<Integer,Integer> playerscores,Roll roll){
        int top = 0;
        int second = 0;
        //finds either the first and second place or first and last place, assigning the amounts to search for later
        if (roll.getSecondPoints() != 0){
            for (int i: playerscores.values()){
                if (i > top){
                    second = top;
                    top = i;
                }
            }
        } else {
            // sets the score real high because second will now represent who is coming last
            second = 100;
            for (int i: playerscores.values()){
                if (i > top){
                    top = i;
                }
                if(i < second){
                    second = i;
                }
            }
        }
        StringBuilder winners = new StringBuilder();
        StringBuilder losers = new StringBuilder();
        for (int i: playerscores.keySet()){
            if (playerscores.get(i) == top){
                winners.append(i);
            } else if (playerscores.get(i) == second) {
                losers.append(i);
            }
        }
        return new String[]{winners.toString(), losers.toString()};
    }
}
