package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int roundAmount = 6;
        int requiredSize = 5;

        //Banco de palavras
        String[] dictionary = new String[]{
                "pouco", "porta", "chuva", "praia", "baixo", "touro", "bolsa",
                "calca", "blusa", "tenis", "pizza", "brisa", "vento", "roupa"
        };

        String randomWord = getRandomWord(dictionary);

        int currentRound = 0;
        boolean hasWinned = false;
        while (true) {
            System.out.println("Palavra:");
            String userInput = scanner.nextLine();

            if(!isInputValid(userInput, dictionary, requiredSize)){
                continue;
            }

            if (userInput.equals(randomWord)) {
                hasWinned = true;
                break;

            } else if (currentRound < roundAmount) {
                currentRound++;


                boolean[] rightPlaces = getRightPlaces(userInput, randomWord, requiredSize);

                paintCharacters(userInput, randomWord, rightPlaces);
            } else {
                break;
            }


            System.out.println();
        }

        if (hasWinned) {
            System.out.println("Ganhou!!!");
        } else {
            System.out.println("Perdeu :(");
        }
    }

    public static boolean[] getRightPlaces(String userInput, String randomWord, int requiredSize) {
        boolean[] rightPlaces = new boolean[requiredSize];
        for (int i = 0; i < userInput.length(); i++) {
            char inputChar = userInput.charAt(i);

            for (int j = 0; j < randomWord.length(); j++) {
                char wordChar = randomWord.charAt(j);

                if (inputChar == wordChar && i == j) {
                    rightPlaces[i] = true;

                    break;
                }
            }
        }

        return rightPlaces;
    }

    public static void paintCharacters(String userInput, String randomWord, boolean[] rightPlaces) {
        for (int i = 0; i < userInput.length(); i++) {
            Character currentChar = userInput.charAt(i);

            String finalChar;
            if (randomWord.contains(currentChar.toString()) && rightPlaces[i]) {
                finalChar = ANSI_PURPLE + currentChar;
            } else if (randomWord.contains(currentChar.toString())) {
                finalChar = ANSI_YELLOW + currentChar;
            } else {
                finalChar = Character.toString(currentChar);
            }
            System.out.print(finalChar + " ");
            System.out.println(ANSI_RESET);


        }
    }

    public static String getRandomWord(String[] dictionary){
        // Sortear uma palavra aleatoria
        Random random = new Random();
        int randomIndex = random.nextInt(dictionary.length);
        return dictionary[randomIndex];


    }

    public static boolean isInputValid(String userInput, String[] dictionary, int requiredSize){
        if (userInput.length() != requiredSize) {
            return false;
        }

        //Encontrar a palavra no dicionario
        boolean found = false;
        for (String word : dictionary) {
            if (userInput.equals(word)) {
                found = true;
                break;
            }
        }


        return found;
    }
}
