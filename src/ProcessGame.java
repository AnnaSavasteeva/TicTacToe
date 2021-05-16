import javax.swing.*;


public class ProcessGame extends JFrame {
    FirstPlayer firstPlayer = new FirstPlayer("Человек");
    AiPlayer aiPlayer = new AiPlayer("Ai");


    public ProcessGame() {
        GameField gameField = new GameField(firstPlayer, aiPlayer, 3, 3);
        gameField.setVisible(true);
    }

}
