import Cards.*;

import java.util.ArrayList;
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
}
