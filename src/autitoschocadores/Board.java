package autitoschocadores;

public class Board {
    private Car[][] board;
    private int size;  
    
    public Board(int size) {
        this.size = size;
        this.board = new Car[size][size];
        initializeBoard();
    }

    // Initialize the board with empty cells
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = null;
            }
        }
    }

    // Place a car on the board at the specified position
    public void placeCar(int row, int col, Car car) {
        if (isValidPosition(row, col)) {
            board[row][col] = car;
        } else {
            System.out.println("Invalid position for placing car.");
        }
    }

    public Car getCarAt(int row, int col) {
        //char[][] emptyCellBlock = car.getEmptyCell;
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            return board[row][col];
        } else {
            //System.out.println("error: null at " + row + " "  + col);
            return null;
        }
    }

    // Display the current state of the board
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    displayCar(board[i][j]);
                } else {
                    System.out.print("- "); // Empty cell
                }
            }
            System.out.println();
        }
    }
    
    // Display the representation of the Autito
private void displayCar(Car car) {
    char[][] representation = car.getOrientation();
    String color = car.getCarColor();
    for (char[] row : representation) {
        for (char c : row) {
            if (c == '*') {
                System.out.print(color + c + "\033[0m" + " ");
            } else if (c == 'o') {
                System.out.print("\033[43m" + c + "\033[0m" + " "); // Yellow background for 'o'
            } else {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }
}

    // Check if the position is within the bounds of the board
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    // CHECK CAR DIRECTIONS TO GET NEXT POSITIONS
public boolean checkCarInDirections(int row, int col) {
    int[] relevantDirections;
    Car car = getCarAt(row, col);
    if(car == null){
        //System.out.println("Car is null, choose a new position");
        return false;
    }
    String carSavedPos = car.getCarDirection(); // save car pos

    // Determina las direcciones adyacentes relevantes según la orientación actual of the car
    switch (carSavedPos) {
        case "carUp":
            relevantDirections = new int[]{1, 2, 3}; // Derecha, abajo, izquierda
            break;
        case "carRight":
            relevantDirections = new int[]{2, 3, 0}; // Abajo, izquierda, arriba
            break;
        case "carDown":
            relevantDirections = new int[]{3, 0, 1}; // Izquierda, arriba, derecha
            break;
        case "carLeft":
            relevantDirections = new int[]{0, 1, 2}; // Arriba, derecha, abajo
            break;
        default:
            relevantDirections = new int[0]; // Sin direcciones relevantes
    }

    // Itera sobre las direcciones adyacentes relevantes y realiza las operaciones necesarias
    for (int direction : relevantDirections) {
        // Resto del código para verificar colisiones y realizar las operaciones necesarias en cada dirección
        
        // Get the initial position of the car
        int currentRow = row;
        int currentCol = col;

        // Move the car in the current direction until it reaches the end or finds another car
        while (true) {
            // Get the next position after moving in the current direction
            int[] nextPosition = getNextPosition(currentRow, currentCol, direction);
            int nextRow = nextPosition[0];
            int nextCol = nextPosition[1];

            // Check if the next position is within the bounds of the board
            if (!isValidPosition(nextRow, nextCol)) {
                break; // Reached the edge of the board
            }

            // Update the current position
            currentRow = nextRow;
            currentCol = nextCol;

            // Check if there's a car at the next position
            if (getCarAt(currentRow, currentCol) != null) {
                returnToOriginalPos(carSavedPos,row,col);
                return true; // There's a car in this direction
            }
          //  returnToOriginalPos(carSavedPos,row,col);
        }
    //    returnToOriginalPos(carSavedPos,row,col);
    }
    
    return false; // No Autito found in any direction
}



private void returnToOriginalPos(String pos, int row, int col){ //return cars to a saved pos
    Car car = getCarAt(row,col);
    switch (pos){
        case "carUp":
            car.setOrientation(0);
            break;
        case "carRight":
            car.setOrientation(1);
            break;
        case "carLeft":
            car.setOrientation(3);
            break;
        case "carDown":
            car.setOrientation(2);
            break;
        case "emptyCell":
            car.setOrientation(4);
            break;        
    }
        
    
}

   // Helper method to get the next position based on the current position and direction
public int[] getNextPosition(int row, int col, int direction) {
    int[] nextPosition = new int[2];
    
    switch (direction) {
        case 1: // Right
            nextPosition[0] = row;
            nextPosition[1] = col + 1;
            break;
        case 2: // Down
            nextPosition[0] = row + 1;
            nextPosition[1] = col;
            break;
        case 3: // Left
            nextPosition[0] = row;
            nextPosition[1] = col - 1;
            break;
        case 0: // Up
            nextPosition[0] = row - 1;
            nextPosition[1] = col;
            break;
        default:
            throw new IllegalArgumentException("Invalid direction");
    }
    
    return nextPosition;
}

}
//Diego Pereira Puig - 329028
//Davit Dostourian Erbe, 281665