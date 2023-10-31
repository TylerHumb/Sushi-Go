package Cards;

public class Appetiser extends Card {
    //amount dictates how many of a card is needed to get points
    int Amount;
    int EelDeduction = 0;
    public Appetiser(String name,int amount,int points){
        Name = name;
        Amount = amount;
        Points = points;
    }
    public Appetiser(String name,int amount,int points,int eelDeduction){
        Name = name;
        Amount = amount;
        Points = points;
        EelDeduction = eelDeduction;
    }
    public Appetiser(String name){
        Name = name;
    }

    public int getAmount() {
        return Amount;
    }
    public int getEelDeduction(){
        return EelDeduction;
    }

    @Override
    public String toString() {
        return "Appetiser{" +
                "Amount=" + Amount +
                ", EelDeduction=" + EelDeduction +
                ", Name='" + Name + '\'' +
                ", Points=" + Points +
                ", Value=" + Value +
                '}';
    }
}
