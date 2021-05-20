import javax.swing.*;


public class ProcessGame extends JFrame {
    Player firstPlayer = new FirstPlayer("Человек");
    Player aiPlayer = new AiPlayer("Ai");


    public ProcessGame() {
        GameField gameField = new GameField(firstPlayer, aiPlayer, 3, 3);
        gameField.setVisible(true);
    }




}
