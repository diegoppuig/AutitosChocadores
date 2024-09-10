package autitoschocadores;

import java.util.Random;

public class Car { // It's better to start class names with a capital letter
    private int carPosition;
    private final char[][] carUp = {
            {' ', 'o', 'o', ' '},
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
    };
    private final char[][] carDown = {
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
            {' ', '*', '*', ' '},
            {' ', 'o', 'o', ' '},
    };
    private final char[][] carLeft = {
            {' ', ' ', ' ', ' '},
            {'o', '*', '*', '*'},
            {'o', '*', '*', '*'},
            {' ', ' ', ' ', ' '},
    };
    private final char[][] carRight = {
            {' ', ' ', ' ', ' '},
            {'*', '*', '*', 'o'},
            {'*', '*', '*', 'o'},
            {' ', ' ', ' ', ' '},
    };
    private final char[][] emptyCell = {
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '}
};

    // Assuming Tablero is your board class, it might be better not used as an
    // inherited class unless specifically needed.
    // private Tablero board;

    private char[][] currentOrientation;
    private String carColor; // To store the ANSI color code for '*'
    
    // Accessor methods
    public char[][] getCarUp() {
        return carUp;
    };

    public char[][] getCarDown() {
        return carDown;
    };

    public char[][] getCarLeft() {
        return carLeft;
    };

    public char[][] getCarRight() {
        return carRight;
    };

    public int getCarPosition() {
        return carPosition;
    };

    public Car(int startPosition) {
        this.carPosition = startPosition;
        this.currentOrientation = carUp; // Default orientation
        setRandomColor(); // Assign a random color at instantiation
    }

//ROTATE CLOCKWISE AND GET ORIENTATION;
    public void rotateClockwise() {
        char[][] newOrientation = emptyCell; //changed from null

        // Determine the current orientation and set the new orientation accordingly
        if (currentOrientation == carUp) {
            newOrientation = carRight;
        } else if (currentOrientation == carRight) {
            newOrientation = carDown;
        } else if (currentOrientation == carDown) {
            newOrientation = carLeft;
        } else if (currentOrientation == carLeft) {
            newOrientation = carUp;
        } else if (currentOrientation == emptyCell) {
            newOrientation = emptyCell;
        }

        // Update the current orientation
        currentOrientation = newOrientation;
    }

    

    // Method to return the current orientation of the car
    public char[][] getOrientation() {
        return currentOrientation;
    }

    public String getCarDirection() {
        if (currentOrientation == carUp) {
            return "carUp";
        } else if (currentOrientation == carRight) {
            return "carRight";
        } else if (currentOrientation == carDown) {
            return "carDown";
        } else if (currentOrientation == carLeft) {
            return "carLeft";
        }
        return "error";
    }
    
    public void setOrientation(int direction) {
        switch (direction) {
            case 0: // Up
                currentOrientation = carUp;
                break;
            case 1: // Right
                currentOrientation = carRight;
                break;
            case 2: // Down
                currentOrientation = carDown;
                break;
            case 3: // Left
                currentOrientation = carLeft;
                break;
            case 4: // empty
                currentOrientation = emptyCell;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
   // Method to apply random colors to asterisks and yellow highlight to 'o'
    
    public String getCarColor() {
        return carColor;
    }
        
    private void setRandomColor() {
        Random random = new Random();
        String[] colors = {
            "\033[31m", // Red
            "\033[32m", // Green
            "\033[34m", // Blue
            "\033[35m", // Magenta
            "\033[36m"  // Cyan
        };
        this.carColor = colors[random.nextInt(colors.length)];
    }
    
    
   public void applyRandomColors() {
    Random random = new Random();

    for (int i = 0; i < currentOrientation.length; i++) {
        for (int j = 0; j < currentOrientation[i].length; j++) {
            char currentChar = currentOrientation[i][j];
            if (currentChar == '*') {
                // Generate a random color for the asterisk
                int colorIndex = random.nextInt(7) + 31; // ANSI color codes for text colors
                System.out.print("\u001B[" + colorIndex + "m" + currentChar); // Set color and print asterisk
            } else if (currentChar == 'o') {
                // Apply yellow highlight to 'o'
                System.out.print("\u001B[33m" + currentChar); // Set yellow color and print 'o'
            } else {
                // Print other characters as they are
                System.out.print(currentChar);
            }
        }
        System.out.println(); // Move to the next line after printing each row
    }
    System.out.print("\u001B[0m"); // Reset color at the end
}
}
////Diego Pereira Puig - 329028
//Davit Dostourian Erbe, 281665