import javax.swing.*;
import java.awt.*;


public class GameField extends JFrame {
//    Game field
    private final int dimension;
    private final int fullness;
//    Cells signs
    private static final String SIGN_X = "X";
    private static final String SIGN_O = "O";
    private static final String SIGN_EMPTY = "•";
//    Field setup
    private final JButton[][] cells;
    private static final Font FONT = new Font("Arial", Font.BOLD, 20);
    private boolean gameOver;


    public GameField(Player firstPlayer, Player secondPlayer, int dimension, int fullness) {
        this.dimension = dimension;
        this.fullness = fullness;
        this.cells = new JButton[this.dimension][this.dimension];

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
        gameFieldPanel.setLayout(new GridLayout(this.dimension, this.dimension));
//        Add buttons
        for (int i = 0; i < this.cells.length; i++) {
            for(int j = 0; j < this.cells[i].length; j++) {
                this.cells[i][j] = new JButton(SIGN_EMPTY);
                this.cells[i][j].setFont(FONT);
                gameFieldPanel.add(this.cells[i][j]);
            }
        }
//        Add action listeners to buttons
        for (JButton[] currentBtnArray : this.cells) {
            for (JButton currentBtn : currentBtnArray) {
                currentBtn.addActionListener(e -> {
                    if (this.gameOver) {
                        return;
                    }

//                    First player turn
                    if (!this.isCellEmpty(currentBtn)) {
                        this.msgCellIsNotEmpty();
                        return;
                    }

                    firstPlayer.turnPlayer(currentBtn);

                    if (firstPlayer.isWinner(firstPlayer.getSign(), this)) {
                        this.msgPlayerWon(firstPlayer);
                        this.gameOver = true;
                        return;
                    }

                    if (this.isGameFieldFull()) {
                        this.msgDraw();
                        this.gameOver = true;
                        return;
                    }

//                    Second Player turn
                    secondPlayer.turnPlayer(this);
                    if (secondPlayer.isWinner(secondPlayer.getSign(),this)) {
                        this.msgPlayerWon(secondPlayer);
                        this.gameOver = true;
                        return;
                    }
                    if (this.isGameFieldFull()) {
                        this.msgDraw();
                        this.gameOver = true;
                    }

                });
            }
        }


//        Setup panel
        JPanel setupPanel = new JPanel();
        JButton clearBtn = new JButton("Начать заново");
        clearBtn.addActionListener(e -> {
            for (JButton[] btnArray : this.cells) {
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
    public boolean isCellEmpty(JButton cell) {
        return cell.getText().equals(SIGN_EMPTY);
    }

    public boolean isLineRangeFull(String sign) {
        for (int i = 0; i < this.dimension; i++) {
            int rowSignsCounter = 0;
            int colSignsCounter = 0;

            for (int j = 0; j < this.dimension; j++) {
                rowSignsCounter = (this.cells[i][j].getText().equals(sign)) ? rowSignsCounter + 1 : 0;
                colSignsCounter = (this.cells[j][i].getText().equals(sign)) ? colSignsCounter + 1 : 0;

                if(rowSignsCounter == this.fullness || colSignsCounter == this.fullness) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isDiagonalRangeFull(String sign) {
        int mainDiagonalCounter = 0;
        int addDiagonalCounter = 0;

        for (int i = 0; i < this.dimension; i++) {
            mainDiagonalCounter = (this.cells[i][i].getText().equals(sign)) ? mainDiagonalCounter + 1 : 0;
            addDiagonalCounter = (this.cells[i][this.cells[i].length - 1 - i].getText().equals(sign)) ? addDiagonalCounter + 1 : 0;

            if (mainDiagonalCounter == this.fullness || addDiagonalCounter == this.fullness) {
                return true;
            }
        }

        return false;
    }

    private boolean isGameFieldFull() {
        for (JButton[] curCellArray: this.cells) {
            for (JButton curBtn: curCellArray) {
                if (curBtn.getText().equals(GameField.getSignEmpty())) {
                    return false;
                }
            }
        }
        return true;
    }



//    Dialog boxes
    private void msgCellIsNotEmpty() {
        JDialog dialog = createDialog("Клетка занята!");
        dialog.setVisible(true);
    }

    private void msgPlayerWon(Player player) {
        JDialog dialog = createDialog(player.getName() + " победил!");
        dialog.setVisible(true);
    }

    private void msgDraw() {
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
    public JButton[][] getCells() {
        return this.cells;
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


}
