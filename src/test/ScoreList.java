package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Responsible for writing score into file and load the scores in order from the file to high score array list
 */
public class ScoreList {

    private ArrayList<Integer> scoreList;
    private ArrayList<Integer> highScoreList;

    /**
     * Constructor for ScoreList
     *
     * @param score Value of the score
     */
    public ScoreList(int score){
        scoreList = new ArrayList<Integer>();
        highScoreList = new ArrayList<Integer>();

        writeToLastLine(score);
        loadDatasToArray();

        Collections.sort(scoreList, Collections.reverseOrder());
        highScoreList = (ArrayList<Integer>) scoreList.stream().limit(3).collect(Collectors.toList());
    }

    /**
     * Write the score into last line of the file
     *
     * @param score Value of the score
     */
    public void writeToLastLine(int score){

        try
        {
            FileWriter fw = new FileWriter("scoreList.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            String scoreString = String.valueOf(score);
            bw.write(scoreString);
            bw.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    /**
     * Load the datas from file to scoreList array
     */
    public void loadDatasToArray(){

        try {

            File myObj = new File("scoreList.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                int data;

                try {
                    data = Integer.parseInt(myReader.nextLine());
                }
                catch (NumberFormatException e)
                {
                    data = 0;
                }
                scoreList.add(data);
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Return highScoreList array
     *
     * @return highScoreList array
     */
    public ArrayList<Integer> getHighScoreList(){
        return this.highScoreList;
    }
}

