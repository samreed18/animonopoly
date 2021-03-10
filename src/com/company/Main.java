package com.company;

public class Main {

    public static void main(String[] args) {
        Animal test = new Animal();
        int[] testCostVisit = {50, 60, 80, 100};
        test.setAttributes("Test", testCostVisit, 50);
        test.printDetails();
    }
}
