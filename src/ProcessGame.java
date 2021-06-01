import javax.swing.*;
import java.util.Objects;


public class ProcessGame extends JFrame {
    Player firstPlayer = new FirstPlayer("Человек");
    Player aiPlayer = new AiPlayer("Ai");


    public ProcessGame() {
        GameField gameField = new GameField(firstPlayer, aiPlayer, 3, 3);
        gameField.setVisible(true);
    }

    @Override
    public String toString() {
        return "ProcessGame{" +
                "firstPlayer=" + firstPlayer +
                ", aiPlayer=" + aiPlayer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessGame that = (ProcessGame) o;
        return Objects.equals(firstPlayer, that.firstPlayer) && Objects.equals(aiPlayer, that.aiPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPlayer, aiPlayer);
    }

}
