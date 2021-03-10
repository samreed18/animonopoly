package com.company;

public class Deck
{
    private int i;
    // Array used to print off user financial situation
    private String money {"+ £100","- £20","+200","- £350","+ £80","- £45","+ £1","- £5","+ £75","- £50","+ £450","- £375","+ £205","- £210","+ £410","- £715","+ £670","- £600","+ £720","- £420"};
    Cards c = new Cards();
    for(i =0; i<=20; i++;)
    {
        //prints here
        money[i]
        System.out.println("This is your Scenario:\n"+c.getRandomScenario()"\nThis is your outcome:"+money[i]);

    }

}
// Cards class

