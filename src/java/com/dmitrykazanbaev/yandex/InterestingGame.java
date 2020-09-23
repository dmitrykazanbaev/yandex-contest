package com.dmitrykazanbaev.yandex;

import java.io.FileReader;
import java.util.Scanner;

public class InterestingGame {
    public static void main(String[] args) throws Exception {
        String file = "input.txt";
        Scanner scanner = new Scanner(new FileReader(file));

        String s = scanner.nextLine();
        String[] splitted = s.split(" ");
        short winCount = Short.parseShort(splitted[0]);
        int cardCount = Integer.parseInt(splitted[1]);

        int petya = 0;
        int vasya = 0;
        String winner = null;
        for (int i = 0; i < cardCount; i++) {
            short current = scanner.nextShort();
            if (current % 3 == 0 && current % 5 != 0) {
                petya++;
                if (petya == winCount) {
                    winner = "Petya";
                    break;
                }
            } else if (current % 3 != 0 && current % 5 == 0) {
                vasya++;
                if (vasya == winCount) {
                    winner = "Vasya";
                    break;
                }
            }
        }
        if (winner != null) {
            System.out.println(winner);
        } else {
            if (petya > vasya) {
                System.out.println("Petya");
            } else if (petya < vasya) {
                System.out.println("Vasya");
            } else {
                System.out.println("Draw");
            }
        }
    }
}