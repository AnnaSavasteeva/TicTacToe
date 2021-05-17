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


    protected boolean isWinner(String sign) {
        if (GameField.isLineRangeFull(sign)) {
            return true;
        } else {
            return GameField.isDiagonalRangeFull(sign);
        }
    }


    protected JButton getBtnWinner(String sign) {
        for (JButton[] btnArray : GameField.getCells()) {
            for (JButton btn : btnArray) {
                if (GameField.isCellEmpty(btn)) {
                    btn.setText(sign);
                    if (this.isWinner(sign)) {
                        return btn;
                    }

                    btn.setText(GameField.getSignEmpty());
                }
            }
        }
        return null;
    }


    protected JButton getEmptyBtn() {
        for (JButton[] btnArray : GameField.getCells()) {
            for (JButton btn : btnArray) {
                if (GameField.isCellEmpty(btn)) {
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
