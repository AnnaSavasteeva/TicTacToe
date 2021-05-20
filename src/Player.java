import javax.swing.*;


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


//    @Override
//    public String toString() {
//        return this.getClass().getSimpleName() + "{" +
//                "name='" + name + '\'' +
//                ", sign='" + sign + '\'' +
//                '}';
//    }



}
