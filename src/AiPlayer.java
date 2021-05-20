import javax.swing.*;


public class AiPlayer extends Player {

    public AiPlayer(String name) {
        super(name, GameField.getSignO());
    }

    @Override
    public void turnPlayer(GameField gameField) {
        JButton oWinner = this.getBtnWinner(this.getSign(), gameField);
        if (oWinner != null) {
            super.turnPlayer(oWinner);
            return;
        }

        JButton xWinner = this.getBtnWinner(GameField.getSignX(), gameField);
        if (xWinner != null) {
            super.turnPlayer(xWinner);
            return;
        }

        JButton emptyBtn = this.getEmptyBtn(gameField);
        if (emptyBtn != null) {
            super.turnPlayer(emptyBtn);
        }
    }
}
