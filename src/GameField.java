import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;


public class GameField extends JFrame {
//    Game field
    private static int DIMENSION = 0;
    private static int FULLNESS = 0;
//    Cells signs
    private static final String SIGN_X = "X";
    private static final String SIGN_O = "O";
    private static final String SIGN_EMPTY = "•";
//    Field setup
    private static JButton[][] CELLS;
    private static final Font FONT = new Font("Arial", Font.BOLD, 20);
    private boolean gameOver;


    public GameField(Player firstPlayer, Player secondPlayer, int dimension, int fullness) {
        DIMENSION = dimension;
        FULLNESS = fullness;
        CELLS = new JButton[DIMENSION][DIMENSION];

        final int gameFieldWidth = 300;
        final int gameFieldHeight = 300;

//        Main panel
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(gameFieldWidth, gameFieldHeight + 50);
        setAlwaysOnTop(true);


//        Game field panel
        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setSize(gameFieldWidth, gameFieldHeight);
        gameFieldPanel.setLayout(new GridLayout(DIMENSION, DIMENSION));
//        Add buttons
        for (int i = 0; i < CELLS.length; i++) {
            for(int j = 0; j < CELLS[i].length; j++) {
                CELLS[i][j] = new JButton(SIGN_EMPTY);
                CELLS[i][j].setFont(FONT);
                gameFieldPanel.add(CELLS[i][j]);
            }
        }
//        Add action listeners to buttons
        for (JButton[] currentBtnArray : CELLS) {
            for (JButton currentBtn : currentBtnArray) {
                currentBtn.addActionListener(e -> {
                    if (this.gameOver) {
                        return;
                    }

//                    First player turn
                    if (!isCellEmpty(currentBtn)) {
                        this.msgCellIsNotEmpty();
                        return;
                    }

                    firstPlayer.turnPlayer(currentBtn);

                    if (firstPlayer.isWinner(firstPlayer.getSign())) {
                        this.msgPlayerWon(firstPlayer);
                        gameOver = true;
                        return;
                    }

                    if (GameField.isGameFieldFull()) {
                        this.msgDraw();
                        gameOver = true;
                        return;
                    }

//                    Second Player turn
                    secondPlayer.turnPlayer(currentBtn);
                    if (secondPlayer.isWinner(secondPlayer.getSign())) {
                        this.msgPlayerWon(secondPlayer);
                        gameOver = true;
                        return;
                    }
                    if (GameField.isGameFieldFull()) {
                        this.msgDraw();
                        gameOver = true;
                    }

                });
            }
        }


//        Setup panel
        JPanel setupPanel = new JPanel();
        JButton clearBtn = new JButton("Начать заново");
        clearBtn.addActionListener(e -> {
            for (JButton[] btnArray : CELLS) {
                for(JButton btn : btnArray) {
                    btn.setText(SIGN_EMPTY);
                }
            }
            this.gameOver = false;
        });
        setupPanel.add(clearBtn, BorderLayout.CENTER);


//        Add panels
        add(gameFieldPanel, BorderLayout.CENTER);
        add(setupPanel, BorderLayout.PAGE_END);

    }



//    Check cells' state
    public static boolean isCellEmpty(JButton cell) {
        return cell.getText().equals(SIGN_EMPTY);
    }

    public static boolean isLineRangeFull(String sign) {
        for (int i = 0; i < DIMENSION; i++) {
            int rowSignsCounter = 0;
            int colSignsCounter = 0;

            for (int j = 0; j < DIMENSION; j++) {
                rowSignsCounter = (CELLS[i][j].getText().equals(sign)) ? rowSignsCounter + 1 : 0;
                colSignsCounter = (CELLS[j][i].getText().equals(sign)) ? colSignsCounter + 1 : 0;

                if(rowSignsCounter == FULLNESS || colSignsCounter == FULLNESS) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isDiagonalRangeFull(String sign) {
        int mainDiagonalCounter = 0;
        int addDiagonalCounter = 0;

        for (int i = 0; i < DIMENSION; i++) {
            mainDiagonalCounter = (CELLS[i][i].getText().equals(sign)) ? mainDiagonalCounter + 1 : 0;
            addDiagonalCounter = (CELLS[i][CELLS[i].length - 1 - i].getText().equals(sign)) ? addDiagonalCounter + 1 : 0;

            if (mainDiagonalCounter == FULLNESS || addDiagonalCounter == FULLNESS) {
                return true;
            }
        }

        return false;
    }

    public static boolean isGameFieldFull() {
        for (JButton[] curCellArray: GameField.getCells()) {
            for (JButton curBtn: curCellArray) {
                if (curBtn.getText().equals(GameField.getSignEmpty())) {
                    return false;
                }
            }
        }
        return true;
    }



//    Dialog boxes
    public void msgCellIsNotEmpty() {
        JDialog dialog = createDialog("Клетка занята!");
        dialog.setVisible(true);
    }

    public void msgPlayerWon(Player player) {
        JDialog dialog = createDialog(player.getName() + " победил!");
        dialog.setVisible(true);
    }

    public void msgDraw() {
        JDialog dialog = createDialog("Ничья!");
        dialog.setVisible(true);
    }

    private JDialog createDialog(String msgText) {
        int dialogBoxWidth = 250;
        int dialogBoxHeight = 125;

        JDialog dialog = new JDialog(this, "", true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(dialogBoxWidth, dialogBoxHeight);

        JLabel message = new JLabel(msgText);
        message.setFont(FONT);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        dialog.add(message, BorderLayout.CENTER);

        return dialog;
    }



//    Getters
    public static JButton[][] getCells() {
        return CELLS;
    }

    public static String getSignX() {
        return SIGN_X;
    }

    public static String getSignO() {
        return SIGN_O;
    }

    public static String getSignEmpty() {
        return SIGN_EMPTY;
    }



//    Info
    @Override
    public String toString() {
        return "GameField{" +
                "SIGN_X=" + SIGN_X +
                "SIGN_O=" + SIGN_O +
                "SIGN_EMPTY=" + SIGN_EMPTY +
                "DIMENSION=" + DIMENSION +
                "DIMENSION=" + FULLNESS +
                "CELLS=" + Arrays.toString(CELLS) +
                "gameOver=" + gameOver +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameField gameField = (GameField) o;
        return gameOver == gameField.gameOver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameOver);
    }

}
