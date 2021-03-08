package com.company;

public class Animals {
    private String nameSpecies;
    private int level = 0;
    private int[] costVisit;
    private int costBuy;
    private Player owner;

    public void setAttributes(String speciesName, int[] visitCost, int buyCost){
        nameSpecies = speciesName;
        costVisit = visitCost;
        costBuy = buyCost;
    }

    public void visit(Player buyer){
        if(owner != null){
            System.out.println("Visited for " + costVisit[level]);
            buyer.decreaseMoney(costVisit[level]);
            owner.increaseMoney(costVisit[level]);
        } else {
            System.out.println("No owner");
        }
    }

    public int getCostBuy(){
        return costBuy;
    }

    public void purchase(Player player){
        if(owner == null){
            owner = player;
            owner.decreaseMoney(costBuy);
            System.out.println("Purchased for " + costBuy);
        } else {
            System.out.println("Already owned by " + owner);
        }
    }

    public void increaseLevel(){
        if(level < 4){
            owner.decreaseMoney(costBuy);
            level = level + 1;
        }
    }

    public String getName(){
        return nameSpecies;
    }

    public boolean hasOwner(){
        return owner != null;
    }

    public void printDetails(){
        System.out.println("-----------------------");
        System.out.println("|Species: " + nameSpecies);
        System.out.println("|Owner: " + owner);
        System.out.println("|Buy: " + costBuy);
        System.out.println("|Visit: " + costVisit[level]);
        System.out.println("|Level: " + level);
        System.out.println("-----------------------");
    }
}
