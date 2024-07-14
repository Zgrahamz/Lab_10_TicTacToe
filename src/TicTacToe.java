import java.util.Scanner;
public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    public static void main(String[] args) {

        /*This program will:
        1. Clear the board and set the player to X (X moves first)
        2. Get the coordinates for the player's move (1 – 3 for the row and col)
        3. Convert each move's coordinates to array indices (0 – 2) by subtracting 1
        4. Ensure each move is valid by using the SafeInput library and a bulletproofed loop
        5. Check for a win or a tie if it is possible at any point in the game
        6. If there is, prompt the user to play again or stop
        7. Switch turns by toggling the player (X becomes O, O becomes X)*/

        Scanner sca = new Scanner(System.in);

        String trash = "";

        boolean validatingMove = false;

        String promptX1 = "Player X, please enter the row coordinates for your move.";
        String promptX2 = "Player X, please enter the column coordinates for your move.";

        int pXrow = 0;
        int pXcol = 0;

        String promptO1 = "Player O, please enter the row coordinates for your move.";
        String promptO2 = "Player O, please enter the column coordinates for your move.";

        int pOrow = 0;
        int pOcol = 0;

        String wXPrompt = "X wins! Play again?";
        String wOPrompt = "O wins! Play again?";
        String tXOPrompt = "It's a tie! Play again?";
        String player = "";

        //Step 1
        clearBoard();

        do {
            System.out.println("Current state of the board: " + "\n");
            player = "X";
            display();

            //Step 2
            pXrow = SafeInput.getRangedInt(sca, promptX1, 1, 3);
            pXcol = SafeInput.getRangedInt(sca, promptX2, 1, 3);

            //Step 3
            pXrow = pXrow - 1;
            pXcol = pXcol - 1;

            //Step 4
            do {
                if(isValidMove(pXrow, pXcol)) {
                    for(int row = 0; row < ROW; row++) {
                        for(int col = 0; col < COL; col++) {
                            board[pXrow][pXcol] = "X";
                            validatingMove = true;
                        }
                    }
                } else {
                    trash = sca.nextLine();
                    System.out.println("Not a valid move. Please try another move.");
                    sca.nextLine();
                    pXrow = SafeInput.getRangedInt(sca, promptX1, 1, 3);
                    pXcol = SafeInput.getRangedInt(sca, promptX2, 1, 3);
                }
            } while (!validatingMove);

            validatingMove = false;

            //Steps 5 and 6
            if (isWin(player)) {
                display();
                SafeInput.getYNConfirm(sca, wXPrompt);
            } else if (isTie(player)) {
                display();
                SafeInput.getYNConfirm(sca, tXOPrompt);
            } else {
                System.out.println("Current state of the board: " + "\n");
                display();
            }

                //Step 7
                player = "O";

                pOrow = SafeInput.getRangedInt(sca, promptO1, 1, 3);
                pOcol = SafeInput.getRangedInt(sca, promptO2, 1, 3);

                pOrow = pOrow - 1;
                pOcol = pOcol - 1;

                do {
                    if(isValidMove(pOrow, pOcol)) {
                        for(int row = 0; row < ROW; row++) {
                            for(int col = 0; col < COL; col++) {
                                board[pOrow][pOcol] = "O";
                                validatingMove = true;
                            }
                        }
                    } else {
                        trash = sca.nextLine();
                        System.out.println("Not a valid move. Please try another move.");
                        sca.nextLine();
                        pOrow = SafeInput.getRangedInt(sca, promptO1, 1, 3);
                        pOcol = SafeInput.getRangedInt(sca, promptO2, 1, 3);
                    }
                } while (!validatingMove);

                validatingMove = false;

                if (isWin(player)) {
                    display();
                    SafeInput.getYNConfirm(sca, wOPrompt);
                } else if (isTie(player)) {
                    display();
                    SafeInput.getYNConfirm(sca, tXOPrompt);
                }
        } while (!isWin(player));
    }

    //****************************************************************************************//

    private static void clearBoard() {
        for(int row=0; row < ROW; row++) {
            for(int col=0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        for(int row=0; row < ROW; row++) {
            for(int col=0; col < COL; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static boolean isValidMove(int row, int col) {
        boolean retVal = false;
        if (board[row][col].equals(" ")) {
            retVal = true;
        }
        return retVal;
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        return ((!board[0][0].equals(" ") && !board[1][1].equals(" ") && !board[2][2].equals(" "))) || (!board[0][2].equals(" ") && !board[1][1].equals(" ") && !board[2][0].equals(" "));
    }

    private static boolean isTie(String player) {
        return !(isColWin(player) || isRowWin(player) || isDiagnalWin(player)) && isFull();
    }

    private static boolean isFull() {
        for (int i = 0; i < ROW; i++) {
            for(int j = 0; j < COL; j++) {
                if(board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}