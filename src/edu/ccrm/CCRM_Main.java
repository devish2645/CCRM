package edu.ccrm;

import edu.ccrm.cli.MainMenu;

public class CCRM_Main {
    public static void main(String[] args) {
        // To enable assertions, run with the -ea flag.
        // For example: java -ea edu.ccrm.CCRM_Main
        MainMenu mainMenu = new MainMenu();
        mainMenu.display();
    }
}