import java.util.*;

public class BattleshipGame {
    public static final int NUM_OF_ROWS = 7;
    public static final int NUM_OF_COLUMNS = 7;
    public static final int NUM_OF_SHIPS = 3;
    public static int numOfGuesses = 0;
    public static char[][] gameBoard = new char[NUM_OF_ROWS][NUM_OF_COLUMNS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Battleship> battleships = setUpGame();
        displayBoard(gameBoard);

        boolean isAnyBattleshipLeft = !battleships.isEmpty();
        while (isAnyBattleshipLeft) {
            System.out.println("Try to guess hidden battleships");
            System.out.print("Input a coordinate (e.g. A1): ");
            String guessedCoordinate = scanner.nextLine();

            applyGuess(battleships, guessedCoordinate);
            numOfGuesses++;
            displayBoard(gameBoard);

            if (battleships.isEmpty()) {
                System.out.println("Well done! Your total number of guesses: " + numOfGuesses);
                break;
            }
        }

        scanner.close();
    }

    public static ArrayList<Battleship> setUpGame() {
        // initialize board
        for (char[] row : gameBoard) {
            java.util.Arrays.fill(row, BoardStatusType.UNKNOWN.getSymbol());
        }

        // create and place battleships
        ArrayList<Battleship> battleships = new ArrayList<>();
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            battleships.add(new Battleship());
        }

        placeShipsRandomly(battleships);

        return battleships;
    }

    public static void displayBoard(char[][] gameGrid) {
        System.out.print("  ");
        for (int i = 1; i <= NUM_OF_COLUMNS; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n --------------");

        int rowNameASCII = 65; // for A
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            System.out.print(Character.toString(rowNameASCII++) + "|");
            for (int j = 0; j < NUM_OF_COLUMNS; j++) {
                System.out.print(gameGrid[i][j] + " ");
            }
            System.out.println();
        }

        // display legend
        System.out.println();
        for (BoardStatusType feedback : BoardStatusType.values()) {
            System.out.println(feedback.getSymbol() + " - " + feedback.name());
        }
        System.out.println();
    }

    private static void updateBoard(String coordinate, BoardStatusType status) {
        // TODO: your code here
    }

    public static void applyGuess(ArrayList<Battleship> battleships, String guessedCoordinate) {
        // TODO: your code here
    }

    private static void placeShipsRandomly(ArrayList<Battleship> battleships) {
        Random rand = new Random();
        Set<String> reservedCoordinates = new LinkedHashSet<>();
        for (Battleship battleship : battleships) {
            boolean isShipPlaced = false;
            // TODO: your code here
        }
    }

    // TODO: potentially more methods here

    public static String getAlphaCoordsFromIndex(int rowIndex, int columnIndex) {
        int rowNameStartASCII = 65; // for A
        return Character.toString(rowNameStartASCII + rowIndex) + ++columnIndex;
    }
}