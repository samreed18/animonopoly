import java.util.ArrayList;
import java.util.Arrays;

public class Card {
    private final String scenario;
    private final int money;

    public Card(String scenario, int money){
        this.scenario = scenario;
        this.money = money;
    }

    @Override
    public String toString(){
        String myString = "_________________________\n";
        String[] scenarioWords = scenario.split(" ");
        ArrayList<String> scenarioWordList = new ArrayList<>(Arrays.asList(scenarioWords));

        while (scenarioWordList.size()!=0){
            String line ="|";
            Boolean lineFull = false;
            while (!lineFull && scenarioWordList.size()>0){
                String nextWord = scenarioWordList.get(0);
                if ( line.length() +nextWord.length() <24) {
                    line += nextWord+" ";
                    scenarioWordList.remove(0);
                }
                else{
                    lineFull=true;
                }
            }

            int numSpacesLeft = 24 - line.length();
            line+= " ".repeat(numSpacesLeft);
            line +="|\n";
            myString +=line;
        }
        myString+="|-----------------------|\n";
        if (this.money<0){
            String line="|Money owed ~ £"+Math.abs(money);
            myString+=line;
            myString +=" ".repeat(24- line.length());
            myString+="|\n";
        }
        else{
            String line="|Money earned ~ £"+money;
            myString+=line;
            myString +=" ".repeat(24- line.length());
            myString+="|\n";
        }

        myString+="|_______________________|\n";
        return myString;
    }

    public int getMoney() {
        return money;
    }
}
