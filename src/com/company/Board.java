package com.company;

import java.util.ArrayList;
public class Board {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    public Board(ArrayList<Player> playerArrayList){//, ArrayList<Animal> animalArrayList){
        players = playerArrayList;
        //animals = animalArrayList;
    }

    public String toString(){
        String[][][] grid = new String[7][8][2];   //row~ column ~top/bottom half
        String gridString="_";
        for (int column=0;column<8;column++){
            gridString+="_____";
        }
        gridString+="\n";
        /*for (int row=0;row<7;row++){
            for (int column=0;column<8;column++){
                grid[row][column]="|    ";
                gridString+=grid[row][column];
            }
            gridString+="|\n";
            for (int column=0;column<8;column++){
                grid[row][column]="|____";
                gridString+=grid[row][column];
            }
            gridString+="|\n";
        } */
        //fills the whole grid with squares
        for (int row=0;row<7;row++){
            for (int column=0;column<8;column++){
                if (row==0){                       //adding in numbers for the top row
                    grid[0][column][0]= "|"+column+"   ";
                }
                else if (row==6){   //adding in numbers for the bottom row
                    int spaceNumber = 20-column;   //two digit
                    grid[6][column][0]="|"+spaceNumber+"  ";
                }
                else if (column==0){          //adds numbers for left hand side
                    int spaceNumber = 26- row;
                    grid[row][0][0]= "|" +spaceNumber+"  ";
                }
                else if(column==7){           //adds numbers for right hand column
                    int spaceNumber = row+7;
                    //grid[row][7][0] = "|"+spaceNumber+new String(new char[4-String.valueOf(spaceNumber).length()]).replace("\0"," ");
                    grid[row][7][0] = "|"+spaceNumber+" ".repeat(4-String.valueOf(spaceNumber).length());     //accounts for number of spaces for single/double digits
                }
                else{
                    grid[row][column][0]="|    ";       //top half of each box
                }
            }
            for (int column=0;column<8;column++){
                grid[row][column][1]="|____";       //bottom half
            }
        }

        //big rectangle space in the middle
        for (int row=1;row<5;row++){
            grid[row][1][0]="|    ";   //inner left side edging
            grid[row][1][1]="|    ";
            for (int column=2;column<7;column++){
                grid[row][column][0]="     ";
                grid[row][column][1]="     ";
            }
        }
        //inner bottom edging
        for (int column=2; column<7;column++){
            grid[5][column][0]="     ";
            grid[5][column][1]="_____";
        }

        grid[0][0][0]= "|GO  ";  //STRT
        grid[6][7][0]="|JAIL";
        //add players;
        for (Player player : players){
            char symbol = player.getPlayingPiece();
            int space = player.getLocation();
            int row = convertSpaceToGridCoordinates(space)[0];
            int column = convertSpaceToGridCoordinates(space)[1];
            //String spaceString = grid[row][column][1];
            char[] spaceCharsArray = grid[row][column][1].toCharArray();
            int nextEmptySpace = grid[row][column][1].indexOf("_");
            //grid[row][column][1]= "|"+symbol+"___";
            spaceCharsArray[nextEmptySpace]=symbol;
            grid[row][column][1]=String.valueOf(spaceCharsArray);
        }

        //adds everything to one string, separated by line breakers
        for (int row=0;row<7;row++){
            for (int column=0;column<8;column++){
                gridString+=grid[row][column][0];
            }
            gridString+="|\n";
            for (int column=0;column<8;column++){
                gridString+=grid[row][column][1];
            }
            gridString+="|\n";
        }
        return gridString;
    }
    private int[] convertSpaceToGridCoordinates(int spaceNumber){
        int[] coordinates = new int[2];   //row ~ column
        if (spaceNumber<=7){
            coordinates[0]= 0;
            coordinates[1] = spaceNumber;
        }
        else if (spaceNumber<=13){
            coordinates[0]= spaceNumber-7;
            coordinates[1] = 7;
        }
        else if (spaceNumber<=20){
            coordinates[0] = 6;
            coordinates[1] = 20-spaceNumber;
        }
        else if (spaceNumber<=25){
            coordinates[0]= 26-spaceNumber;
            coordinates[1]= 0;
        }
        return coordinates;
    }
    public void removePlayer( Player player){
        players.remove(player);
    }
    public Player getLastPlayer(){
        if (players.size()==1){
            return players.get(0);
        }
        else{
            return null;
        }
    }
}
