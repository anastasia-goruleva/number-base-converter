package converter;

import converter.userinterface.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new UserInterface(new Scanner(System.in)).run();
    }
}
