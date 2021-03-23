public class Animal {
    private final String nameSpecies;
    private int level;
    private final int[] costVisit;  //4 levels different costs
    private final int costBuy;
    private Player owner;

    public Animal(String speciesName, int level0Cost, int level1Cost, int level2Cost, int level3Cost, int buyCost){
        this.nameSpecies = speciesName;
        this.costVisit = new int[4];
        costVisit[0] = level0Cost;
        costVisit[1] = level1Cost;
        costVisit[2] = level2Cost;
        costVisit[3] = level3Cost;
        this.costBuy = buyCost;
        this.level =0;
    }

    public void visitBy(Player buyer){
        if(owner != null){
            System.out.println("Visit cost " + costVisit[level]);
            buyer.decreaseMoney(costVisit[level]);
            owner.increaseMoney(costVisit[level]);
        } else {
            System.out.println("No owner");
        }
    }

    public int getCostBuy(){
        return costBuy;
    }

    public void purchaseBy(Player player){
        if(owner == null){
            owner = player;
            owner.decreaseMoney(costBuy);
            System.out.println("Purchased for " + costBuy);
        } else {
            System.out.println("Already owned by " + owner);
        }
    }

    public int getIncreaseLevelCost(){
        double value = Math.round(costBuy * 1.0 * ((level * 1.0 + 2)/7));
        int returnedvalue = (int)value;
        return returnedvalue;
    }

    public void increaseLevel(){
        double value = Math.round(costBuy * 1.0 * ((level * 1.0 + 2)/7));
        int returnedvalue = (int)value;
        if((level < 4) && (owner != null)){
            owner.decreaseMoney(returnedvalue);
            level = level + 1;
        }
    }

    public String getName(){
        return nameSpecies;
    }

    public boolean hasOwner(){
        return owner != null;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString(){

        String ownerMessage = (owner==null)?"\n| Owner: No owner "+adjustSpaces("| Owner: No owner "):"\n| Owner: " + owner.getID()+adjustSpaces("| Owner: " + owner.getID());
        return "_________________________\n"+"| Species: " + nameSpecies+adjustSpaces("| Species: " + nameSpecies)+ ownerMessage+"\n| Buy: " + costBuy+adjustSpaces("| Buy: " + costBuy)+"\n| Visit: " + costVisit[level]+adjustSpaces("| Visit: " + costVisit[level])+"\n| Level: " + level+adjustSpaces("| Level: " + level)+"\n|_______________________|\n";
    }

    private String adjustSpaces(String text){
        int numSpaces = 24 - text.length();
        return " ".repeat(numSpaces)+"|";
    }
}
