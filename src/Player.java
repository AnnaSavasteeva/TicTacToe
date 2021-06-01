import javax.swing.*;
import java.util.Objects;


public abstract class Player {
    private final String name;
    private final String sign;

    public Player(String name, String sign) {
        this.name = name;
        this.sign = sign;
    }


    public void turnPlayer(JButton btn) {
        btn.setText(this.sign);
    }

    public void turnPlayer(GameField gameField) {
        System.out.println(this.sign);
    }


    protected boolean isWinner(String sign, GameField gameField) {
        if (gameField.isLineRangeFull(sign)) {
            return true;
        } else {
            return gameField.isDiagonalRangeFull(sign);
        }
    }


    protected JButton getBtnWinner(String sign, GameField gameField) {
        for (JButton[] btnArray : gameField.getCells()) {
            for (JButton btn : btnArray) {
                if (gameField.isCellEmpty(btn)) {
                    btn.setText(sign);
                    if (this.isWinner(sign, gameField)) {
                        return btn;
                    }

                    btn.setText(GameField.getSignEmpty());
                }
            }
        }
        return null;
    }


    protected JButton getEmptyBtn(GameField gameField) {
        for (JButton[] btnArray : gameField.getCells()) {
            for (JButton btn : btnArray) {
                if (gameField.isCellEmpty(btn)) {
                    return btn;
                }
            }
        }
        return null;
    }


    public String getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(sign, player.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sign);
    }

}
