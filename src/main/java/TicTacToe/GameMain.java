package TicTacToe;

import java.util.Scanner;

public class GameMain
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();

        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();

        Player player1 = new Player(player1Name, Symbol.X);
        Player player2 = new Player(player2Name, Symbol.O);

        TicTacToeGame game = new TicTacToeGame(player1, player2, 3);

        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            game.printBoard();
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");

            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();

            System.out.print("Enter column (0-2): ");
            int col = scanner.nextInt();

            boolean moveSuccess = game.playMove(row, col);
            if (!moveSuccess) {
                System.out.println("Invalid move, please try again.");
            }
        }

        game.printBoard();
        System.out.println("Game Over!");
        scanner.close();
    }
}
