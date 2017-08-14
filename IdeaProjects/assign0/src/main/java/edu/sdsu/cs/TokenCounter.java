/**
 * Created by Nicholas Lograsso
 * Assignment 0
 * CS310, Healey
 * 1/28/17
 */

package edu.sdsu.cs;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TokenCounter {
    public static void main(String [] args){

        //create an arraylist to hold tokens read from file
        ArrayList<String> tokenList = new ArrayList<String>();

        //create a token counter
        int tCounter = 0;

        //create temporary place holder for each new line read from file
        String readline;

        //verify that there is a valid amount of parameters
        if (args.length != 3){
            System.out.println("Must provide 3 arguments.");
            System.exit(0);
        }

        //give arguments recognizable names
        String inputFile = args[0];
        String outputFile = args[1];
        String inputToken = args[2].toLowerCase();


        //attempt to process input file
        try {
            //create file reader object for input file
            FileReader fileReader = new FileReader(inputFile);

            //create buffered reader wrapper object
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //read from file one line at a time
            while ((readline = bufferedReader.readLine()) != null) {

                //process the line read from the file first converting to lower case
                //and then splitting into an array of tokens
                String [] tokens = readline.toLowerCase().split("\\s+");

                //add tokens to tokenList
                Collections.addAll(tokenList, tokens);

            }

        }
        catch (FileNotFoundException fnfe) {
            System.out.println("The file " + inputFile + " does not exist.");
            System.exit(0);
        }
        catch (IOException ioe) {
            System.out.println("The file " + inputFile + " exists.");
            System.exit(0);
        }

        //create an iterator object to process the token list

        //iterate through the list and check if it matches the input token
        for (String aTokenList : tokenList) {

            if (aTokenList.equals(inputToken)) {
                tCounter++;
            }
        }

        //attempt to write token statistics to the output file
        try {
            //create file writer object for output file
            FileWriter fileWriter = new FileWriter(outputFile);

            //create buffered writer wrapper object
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //output results from token counter
            bufferedWriter.write("Nicholas Lograsso, ACCTXXXX");
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Tokens in file: %d", tokenList.size()));
            bufferedWriter.newLine();

            //output index of first and last tokens found in file
            if (tCounter == 0)
                bufferedWriter.write("First index: Token not found");
            else
                bufferedWriter.write(String.format("First index: %d", tokenList.indexOf(inputToken)));
                bufferedWriter.newLine();

            if (tCounter == 0)
                bufferedWriter.write("Last index: Token not found");
            else
                bufferedWriter.write(String.format("Last index: %d", tokenList.lastIndexOf(inputToken)));
                bufferedWriter.newLine();

            //output token counter results of specified token
            bufferedWriter.write(String.format("Count: %d", tCounter));
            bufferedWriter.newLine();

            //close the output file
            bufferedWriter.close();

        }

        catch (IOException ioe) {
            System.out.println("Error writing to file " + outputFile);
            System.exit(0);
        }


    } //main

} //class
