import Cards.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    Player player = new Player(1);
    Scanner scan = new Scanner(System.in);
    Deck deck;
    int playerCount;
    ArrayList<Bot> bots = new ArrayList<>();
    Roll roll;
    Appetiser appetiser1;
    Appetiser appetiser2;
    Appetiser appetiser3;
    Special special1;
    Special special2;
    Dessert dessert;

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.gamesetup();
    }
    public void gamesetup(){
        System.out.println("Welcome to sushi go first iteration!");
        System.out.println("How many bots would you like to play with? (Max 7)");
        playerCount = playerInputCollector(7,1,scan) + 1;
        deck = new Deck(playerCount);
        for (int i = playerCount -1;i > 0;i--){
            bots.add(new Bot(i + 1));
        }
        selectSushi();
        deck.GenerateUsed(roll,appetiser1,appetiser2,appetiser3,special1,special2,dessert);
        round1();
    }
    public void round1(){
        ArrayList<ArrayList<Card>> hands = deck.Round1();
        player.Assignhand(hands.get(0));
        hands.remove(0);
        for (Bot bot:bots){
            bot.Assignhand(hands.get(0));
            hands.remove(0);
        }
        playOutCards();
    }


    public int playerInputCollector(int maxnum,int minnum, Scanner scnr){
        int response;
        try {
            System.out.println("Enter a number:");
            response = scnr.nextInt();
            if (response > maxnum || response < minnum){
                System.out.println("Invalid Input!");
                response = playerInputCollector(maxnum,minnum,scnr);
            }
        }catch (Exception e){
            System.out.println("Invalid Input!");
            response = playerInputCollector(maxnum,minnum,scnr);
        }
        return response;
    }
    public void selectSushi(){
        int selectionnum;
        System.out.println("Select what rolls to play with:");
        ArrayList<Card> rolllist = deck.RollList();
        selectionnum = printoptions(rolllist);
        roll =(Roll) rolllist.get(selectionnum -1);

        System.out.println("Select what appetisers to play with 1/3:");
        ArrayList<Card> appetiserList = deck.appetiserList();
        selectionnum = printoptions(appetiserList);
        appetiser1 = (Appetiser) appetiserList.get(selectionnum -1);
        appetiserList.remove(selectionnum -1);

        System.out.println("Select what appetisers to play with 2/3:");
        selectionnum = printoptions(appetiserList);
        appetiser2 =(Appetiser) appetiserList.get(selectionnum -1);
        appetiserList.remove(selectionnum -1);

        System.out.println("Select what appetisers to play with 3/3:");
        selectionnum = printoptions(appetiserList);
        appetiser3 = (Appetiser) appetiserList.get(selectionnum -1);

        System.out.println("Select what specials to play with 1/2");
        ArrayList<Card> specialList = deck.specialList();
        selectionnum = printoptions(specialList);
        special1 = (Special) specialList.get(selectionnum -1);
        specialList.remove(selectionnum-1);

        System.out.println("Select what specials to play with 2/2");
        selectionnum = printoptions(specialList);
        special2 = (Special) specialList.get(selectionnum -1);

        System.out.println("Select what dessert to play with:");
        ArrayList<Card> dessertList = deck.dessertList();
        selectionnum = printoptions(dessertList);
        dessert = (Dessert) dessertList.get(selectionnum -1);
    }
    public int printoptions(ArrayList<Card> list){
        for (int i = 0;i <list.size();i++){
            System.out.println(i+1 + "."+list.get(i).getName());
        }
         return playerInputCollector(list.size() +1,1,scan);
    }
    public int printforplaying(ArrayList<Card> list){
        for (int i = 0;i <list.size();i++){
            System.out.println(i+1 + "."+list.get(i).getName());
        }
        System.out.println("Enter 0 to see played cards");
        return playerInputCollector(list.size() +1,0,scan);
    }

    public void playOutCards(){
        while (player.getHand().size() != 0){
            Map<Integer,String> Playedcards = new HashMap<>();
            for (Bot bot:bots){
                Playedcards.put(bot.getNum(),bot.PlayCard().getName());
            }
            int playeroption = printforplaying(player.getHand()) -1;
            if (playeroption == -1){
                for (Map.Entry<Card,Integer> cards: Scorer.generatehashmap(player.PlayedCards).entrySet()){
                    System.out.println(cards.getKey().getName() + " amount: "+cards.getValue());
                }
                playeroption = playerInputCollector(player.getHand().size() +1,1,scan) -1;
            }
            Card playedcard = player.PlayCard(playeroption);
            System.out.println("Player Played " + playedcard.getName());
            for(Map.Entry<Integer,String> entry:Playedcards.entrySet()){
                System.out.println("Bot " + entry.getKey() + " Played "+entry.getValue());
            }
            if (appetiser1.getName().equals("miso soup")||appetiser2.getName().equals("miso soup")||appetiser3.getName().equals("miso soup")){
                misocheck(Playedcards,playedcard);
            }
            rotateHands();
        }
    }
    public void rotateHands(){
        ArrayList<Card> savedhand = player.getHand();
        for (Bot bot:bots){
            ArrayList<Card> temp = bot.getHand();
            bot.Assignhand(savedhand);
            savedhand = temp;
        }
        player.Assignhand(savedhand);
    }
    public void misocheck(Map<Integer,String> Playedcards,Card playercard){
        boolean miso = false;
        boolean failed = false;
        for(Map.Entry<Integer,String> entry:Playedcards.entrySet()){
            if (entry.getValue().equals("miso soup")&& !miso){
                miso = true;
            }else if (entry.getValue().equals("miso soup")){
                failed = true;
               for (Map.Entry<Integer,String> entry1:Playedcards.entrySet()){
                   if (entry1.getValue().equals("miso soup")){
                       removemiso(entry1.getKey());
                   }
                   break;
               }
            }
        }
        if (playercard.getName().equals("miso soup")&& miso){
            failed = true;
            for (int i = player.PlayedCards.size(); i > 0; i--){
                if (player.PlayedCards.get(i - 1).getName().equals("miso soup")){
                    player.PlayedCards.remove(i -1);
                    break;
                }
            }
        }
        if (failed){
            System.out.println("2 or more player's played miso soup!");
        }
    }
    public void removemiso(int botnum){
        for (Bot bot:bots){
            if (bot.getNum() == botnum){
                for (int i = bot.PlayedCards.size(); i > 0; i--){
                    if (bot.PlayedCards.get(i - 1).getName().equals("miso soup")){
                        bot.PlayedCards.remove(i -1);
                        break;
                    }
                }
            }
        }
    }
}
