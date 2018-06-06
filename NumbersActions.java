package Boom;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NumbersActions {

    public int randNumber() {
        return (int)(Math.random()*100);
    }

    public void checkDigit(TextField textField, Task<Object> progressTask, Label answerLabel, int number) {
        if (Integer.parseInt(textField.getText()) == number) {
            textField.setText("");
            progressTask.cancel();
            answerLabel.setText("BRAWO!!!");
        } else {
            if (Integer.parseInt(textField.getText()) > number) {
                textField.setText("");
                answerLabel.setText("Za dużo!");
            } else {
                textField.setText("");
                answerLabel.setText("Za mało!");
            }
        }
    }
}
