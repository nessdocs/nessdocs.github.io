/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechess;

/**
 *
 * @author Vanessah
 */

// Import the Scanner class, Arrays class, and Formatter class from Java Util package.
import java.util.Scanner;

public class SimpleChess {
// Allows the player to move chess pieces on a board.
    
    /**
     * Accepts user-input via the console to move chess pieces around a board.
     *
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize scanner.
        Scanner input = new Scanner(System.in);
        
        // Initialize the chess board.
        String [][] board = new String [8][8];
        // Setup the board with empty (-) values.
        int row; int column; // Initialize for loop variables.
        for (row = 0; row < board.length; row++){
            for (column = 0; column < board.length; column++){
                board[row][column] = "-";
            }
        }
        // Setup the black pieces.
        board [0][0] = "BR"; board [0][1] = "BKn"; board [0][2] = "BB"; board [0][3] = "BQ"; // First row, part 1.
        board [0][4] = "BK"; board [0][5] = "BB"; board [0][6] = "BKn"; board [0][7] = "BR"; // First row, part 2.
        board [1][0] = "BP"; board [1][1] = "BP"; board [1][2] = "BP"; board [1][3] = "BP"; // Second row, part 1.
        board [1][4] = "BP"; board [1][5] = "BP"; board [1][6] = "BP"; board [1][7] = "BP"; // Second row, part 2.
        // Setup the white pieces.
        board [6][0] = "WP"; board [6][1] = "WP"; board [6][2] = "WP"; board [6][3] = "WP"; // Seventh row, part 1.
        board [6][4] = "WP"; board [6][5] = "WP"; board [6][6] = "WP"; board [6][7] = "WP"; // Seventh row, part 2.
        board [7][0] = "WR"; board [7][1] = "WKn"; board [7][2] = "WB"; board [7][3] = "WQ"; // Eighth row, part 1.
        board [7][4] = "WK"; board [7][5] = "WB"; board [7][6] = "WKn"; board [7][7] = "WR"; // Eighth row, part 2.
        
        
        // Ask the user what color they'd like to play as.
        String blackSelect = "black"; String whiteSelect = "white"; boolean startWhite = false; boolean startError = false;
        System.out.println("Welcome to Simple Chess!");
        System.out.println("Type your starting color: Black or White. Press Enter to confirm.");
        String colorSelect = input.nextLine();
        if (colorSelect.equalsIgnoreCase(blackSelect)){
            startWhite = false;
        } else if (colorSelect.equalsIgnoreCase(whiteSelect)){
            startWhite = true;
        } else {
            startError = true;
        }
        while (startError == true){
            System.out.println("You entered an invalid starting color.");
            System.out.println("Type your starting color: Black or White. Press Enter to confirm.");
            colorSelect = input.nextLine();
            if (colorSelect.equalsIgnoreCase(blackSelect)){
                startWhite = false;
                startError = false;
            } else if (colorSelect.equalsIgnoreCase(whiteSelect)){
                startWhite = true;
                startError = false;
            } else {
                startError = true;
            }
        }
        
        // Print the player color and chess board into the console.
        printBoard (board);
        if (startWhite == false){
            System.out.println("Player Color: Black");
        } else if (startWhite == true) {
            System.out.println("Player Color: White");
        }
        
        // Start the game by invoking the movePiece method.
        selectPiece (board, startWhite);

        // Call menu, which calls method to continue or end game.
    }
    
    public static void printBoard (String[][] board){
        // Prints the board in its current state.
        int row; int column; // Initialize for loop variables.
        for (row = 0; row < board.length; row++){
            for (column = 0; column < board.length; column++){
                System.out.printf("%-5s", board[row][column]);
                if (column < 7) { // If the end of the row hasn't been reached, print a separator.
                    System.out.printf("%-4s", "|");
                }
                if (column == 7) { // When reaching the end of the row, print a blank line to separate.
                    System.out.println(""); System.out.println("");
                }
            }
        }
    }
    
    public static void selectPiece (String[][] board, boolean moveWhite){
        // Use Start (piece) and End (move) variables. Either like... "x, y" or enter separately through Row and Column input var?
        // Initialize Scanner and moving variables.
        Scanner input = new Scanner(System.in);
        int startRow = 0; int startColumn = 0;
   
        
        // Ask user to select a piece.
        System.out.println("Please select the piece you would like to move. Numbering of rows and columns starts with '0' and ends with '7'.");
        System.out.println("Type the row where the piece is located. Press Enter to confirm.");
        startRow = input.nextInt();
        System.out.println("Type the column where the piece is located. Press Enter to confirm.");
        startColumn = input.nextInt();
        validatePiece (board, moveWhite, startRow, startColumn);
        
    }
    
    public static void validatePiece (String[][] board, boolean moveWhite, int startRow, int startColumn){        
        // Initialize variable.
       boolean validPiece = false;
        
        // Check for chess piece in user-inputted location.
        if (board [startRow][startColumn].matches("-")){
            validPiece = false;
            System.out.println("That is not a valid chess piece. Please try again.");
            selectPiece (board, moveWhite);
        } else {
            validPiece = true;
            selectMove (board, moveWhite, startRow, startColumn);
        }
        
        }
    
    public static void selectMove (String[][] board, boolean moveWhite, int startRow, int startColumn){
        // Initialize Scanner and moving variables.
        Scanner input = new Scanner(System.in);
        int endRow = 0; int endColumn = 0;
        
        // Ask user to select a location.
        System.out.println("Please select an empty square on the board where you would like to move. Numbering of rows and columns starts with '0' and ends with '7'.");
        System.out.println("Type the row where you want to move. Press Enter to confirm.");
        endRow = input.nextInt();
        System.out.println("Type the column where you want to move. Press Enter to confirm.");
        endColumn = input.nextInt();
        validateMove (board, moveWhite, startRow, startColumn, endRow, endColumn);
        
    }
    
    public static void validateMove (String[][] board, boolean moveWhite, int startRow, int startColumn, int endRow, int endColumn){
        // Initialize variable.
       boolean validMove = false;
        
        // Check for chess piece in user-inputted location.
        if (board [endRow][endColumn].matches("-")){
            validMove = true;
            movePiece (board, moveWhite, startRow, startColumn, endRow, endColumn);
        } else {
            validMove = false;
            System.out.println("That is not a valid location. Please try again.");
            selectMove (board, moveWhite, startRow, startColumn);
        }
        
    }
    
    public static void movePiece (String[][] board, boolean moveWhite, int startRow, int startColumn, int endRow, int endColumn){
        // Copy the old data in the selected location into a swap variable.
        String oldDataSwap = board [endRow][endColumn];
        
        // Move the chess piece into the selected location.
        board [endRow][endColumn] = board [startRow][startColumn];
        
        // Move the old data into the previous location.
        board [startRow][startColumn] = oldDataSwap;
        
        // Switch the moveWhite variable to the opposite color and then call the menu.
        if (moveWhite == true) {
            moveWhite = false;
            menu (board, moveWhite);
        } else if (moveWhite == false){
            moveWhite = true;
            menu (board, moveWhite);
        }
        
    }
    
    public static void menu (String[][] board, boolean moveWhite){
        // Print the board and current color to the console.
        System.out.println("");
        printBoard (board);
        if (moveWhite == false){
            System.out.println("Current Color: Black");
        } else if (moveWhite == true) {
            System.out.println("Current Color: White");
        }
        
        // Ask the user if they'd like to continue playing or quit.
        System.out.println("");
        System.out.println("Would you like to continue playing?");
        Scanner input = new Scanner(System.in);
        boolean validMenu = false; // Set up the boolean for the menu loop.
        while (validMenu == false) {
        System.out.println("Continue Playing (Y)");
        System.out.println("Exit Program (N)");
        System.out.println("");
        System.out.println("Type the letter of your desired menu option. Press Enter to confirm.");
        String menuOption = input.nextLine(); // Assigns the user's input to a string variable.
        // Check if the selection is a valid menu option.
            if (menuOption.equalsIgnoreCase("Y")) {
                validMenu = true; // Stop menu loop.
                // Invoke selectPiece method to restart gameplay loop.
                selectPiece (board, moveWhite);
            } else if (menuOption.equalsIgnoreCase("N")) {
                // Exit the program.
                System.out.println("");
                System.out.println("Thank you for playing Simple Chess. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("");
                System.out.println("You have entered an invalid command.");
                System.out.println("");
                validMenu = false; // Menu should loop through the options and selection process.
            }
        }
    }
    
}