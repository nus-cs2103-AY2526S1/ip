package com.arnavjhajharia.penguin.ui;

import java.util.Scanner;

public class ConsoleUi implements Ui {
    private final Scanner scanner;

    public ConsoleUi(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showIntro() {
        String penguinStart =
                "          .--.                    \n" +
                        "         |o_o |   PENGUIN v0.1\n" +
                        "         |:_/ |   chill. simple. smooth.\n" +
                        "        //   \\\\ \\\\   \n" +
                        "       (|     | )   🥤  [Oreo Shake Inside]\n" +
                        "      /'\\\\_   _/`\\\\\n" +
                        "      \\\\___)=(___/\n";
        System.out.print(penguinStart);
        showDivider();
        showText("Let's start!");
        showDivider();
    }

    @Override
    public void showDivider() {
        System.out.println("--------------------------------");
    }

    @Override
    public void showText(String text) {
        System.out.println(text);
    }

    @Override
    public void showError(String errorText) {
        showDivider();
        System.out.println("Error: " + errorText);
        showDivider();
    }

    @Override
    public String readLine() {
        try { return scanner.nextLine(); }
        catch (Exception e) { return null; }
    }

    @Override
    public void showExit() {
        String penguinExit = """
                ======================================
                        Thanks for using PENGUIN
                          Have a chill day 🐧🥤
                ======================================
                
                          .--.                    
                         |o_o |   
                        |:_/ |   Goodbye!
                        //   \\ \\   
                       (|     | )  
                      /'\\_   _/`\\
                      \\___)=(___/
                """;
        System.out.print(penguinExit);
    }
}
