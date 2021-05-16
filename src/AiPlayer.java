import javax.swing.*;


public class AiPlayer extends Player {

    public AiPlayer(String name) {
        super(name, GameField.getSignO());
    }

    @Override
    public void turnPlayer(JButton btn) {
        JButton oWinner = this.getBtnWinner(this.getSign());
        if (oWinner != null) {
            super.turnPlayer(oWinner);
            return;
        }

        JButton xWinner = this.getBtnWinner(GameField.getSignX());
        if (xWinner != null) {
            super.turnPlayer(xWinner);
            return;
        }

        JButton emptyBtn = this.getEmptyBtn();
        if (emptyBtn != null) {
            super.turnPlayer(emptyBtn);
        }
    }

}
