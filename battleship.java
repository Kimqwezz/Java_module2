import java.util.Scanner;

public class BattleshipGame {
    private static final int BOARD_SIZE = 10;
    private static final int NUM_SHIPS = 5;

    private char[][] board;
    private int shipsRemaining;

    public BattleshipGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        shipsRemaining = NUM_SHIPS;
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        System.out.println(" ┌───────────────────┐");

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) ('A' + i) + "│");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("│");
        }

        System.out.println(" └───────────────────┘");
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    private void placeShips() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < NUM_SHIPS; i++) {
            System.out.print("Введите координаты для коробля " + (i + 1) + " (например, A5): ");
            String input = scanner.nextLine().toUpperCase();

            int row = input.charAt(0) - 'A';
            int col = Integer.parseInt(input.substring(1)) - 1;

            if (!isValidCoordinate(row, col) || board[row][col] != ' ') {
                System.out.println(" Ошибка, попробуйте снова ");
                i--;
                continue;
            }

            board[row][col] = '@';
        }
    }

    private boolean isGameOver() {
        return shipsRemaining == 0;
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            System.out.println();
            printBoard();

            System.out.print("Введите координаты для удара (например, A5): ");
            String input = scanner.nextLine().toUpperCase();

            int row = input.charAt(0) - 'A';
            int col = Integer.parseInt(input.substring(1)) - 1;

            if (!isValidCoordinate(row, col)) {
                System.out.println("Ошибка, повторите снова");
                continue;
            }

            if (board[row][col] == '@') {
                System.out.println("Hit!");
                board[row][col] = 'X';
                shipsRemaining--;
            } else if (board[row][col] == 'X') {
                System.out.println("Вы уже били по этим координатам, попробуйте снова");
            } else {
                System.out.println("Промах");
                board[row][col] = '-';
            }
        }

        System.out.println();
        printBoard();
        System.out.println("Поздравляю, вы потопил все коробли!");
    }

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру 'Морской бой' ");

        BattleshipGame game = new BattleshipGame();
        game.initializeBoard();
        game.placeShips();
        game.play();
    }
}