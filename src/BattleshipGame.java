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
            String guessedCoordinate = scanner.nextLine().toUpperCase();

            if (!isValidCoordinate(guessedCoordinate)) {
                System.out.println("\nInvalid coordinate");
                continue;
            }

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
        int[] indexCord = getIndexCoordsFromAlpha(coordinate);

        gameBoard[indexCord[0]][indexCord[1]] = status.getSymbol();
    }

    public static void applyGuess(ArrayList<Battleship> battleships, String guessedCoordinate) {
        for (Battleship battleship : battleships) {
            boolean isCorrectlyGuessed = battleship.getShipCoordinates().contains(guessedCoordinate);
            if (!isCorrectlyGuessed) {
                updateBoard(guessedCoordinate, BoardStatusType.MISSED);
                continue;
            }

            battleship.hitShip(guessedCoordinate);
            updateBoard(guessedCoordinate, BoardStatusType.HIT);

            if (battleship.isDestroyed) {
                for (String shipCoordinate : battleship.getShipCoordinates().toArray(new String[0])) {
                    updateBoard(shipCoordinate, BoardStatusType.KILLED);
                }
                battleships.remove(battleship);
            }
            break;
        }
    }

    private static void placeShipsRandomly(ArrayList<Battleship> battleships) {
        Random rand = new Random();
        Set<String> reservedCoordinates = new LinkedHashSet<>();
        for (Battleship battleship : battleships) {
            boolean isShipPlaced = false;
            int no_attempts = 0;
            while (!isShipPlaced && no_attempts++ < 10) {
                int randRowIndex = rand.nextInt(NUM_OF_ROWS);
                int randColumnIndex = rand.nextInt(NUM_OF_COLUMNS);
                String randomCoordinate = getAlphaCoordsFromIndex(randRowIndex, randColumnIndex);

                if (reservedCoordinates.contains(randomCoordinate))
                    continue;

                Set<String> potentialCoordinates = new LinkedHashSet<>();
                potentialCoordinates.add(randomCoordinate);
                // !Collections.disjoint(reservedCoordinates, potentialCoordinates)
                boolean isHorizontal = rand.nextBoolean();
                for (int i = 1; i < battleship.getSizeOfShip(); i++) {
                    randomCoordinate = getAlphaCoordsFromIndex(randRowIndex + (isHorizontal ? 0 : i), randColumnIndex + (isHorizontal ? i : 0));
                    if (!isValidCoordinate(randomCoordinate)) {
                        potentialCoordinates.clear();
                        break;
                    }

                    potentialCoordinates.add(randomCoordinate);
                }

                // potential coordinates is not reserved, so place the ship
                if (!potentialCoordinates.isEmpty() && Collections.disjoint(reservedCoordinates, potentialCoordinates)) {
                    reservedCoordinates.addAll(potentialCoordinates);
                    battleship.setShipCoordinates(potentialCoordinates);
                    isShipPlaced = true;
                }
            }
        }
    }

    public static String getAlphaCoordsFromIndex(int rowIndex, int columnIndex) {
        int rowNameStartASCII = 65; // for A
        return Character.toString(rowNameStartASCII + rowIndex) + ++columnIndex;
    }

    public static int[] getIndexCoordsFromAlpha(String coordinate) {
        int rowNameASCII = 65; // for A
        int row = coordinate.charAt(0) - rowNameASCII;
        int column = Integer.parseInt(coordinate.substring(1)) - 1;

        return new int[]{row, column};
    }

    public static boolean isValidCoordinate(String coordinate) {
        int[] indexCord = getIndexCoordsFromAlpha(coordinate);
        if (indexCord[0] < 0 || indexCord[1] < 0) {
            return false;
        }

        // out of bound
        if (indexCord[0] > NUM_OF_ROWS - 1 || indexCord[1] > NUM_OF_COLUMNS - 1) {
            return false;
        }

        return true;
    }
}
