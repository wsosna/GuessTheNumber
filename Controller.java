package Boom;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    Label timeLabel, answerLabel;
    @FXML
    TextField textField;
    @FXML
    Button checkButton;
    @FXML
    ProgressBar progressBar;

    public static final int VALUTE_TO_COMPLETE = 30;

    private Task<Object> progressTask;
    private int number;
    int value = 0;
    NumbersActions numberAct = new NumbersActions();

    @FXML
    public void initialize() {
        number = numberAct.randNumber();
        answerLabel.setWrapText(true);
        progressTask = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                String stan = "";
                while(!isCancelled()){
                    stan = "" + ++value;
                    updateMessage(value+" sek.");
                    updateProgress(value, VALUTE_TO_COMPLETE);
                    Thread.sleep(1000);
                    if (value >= VALUTE_TO_COMPLETE){
                        progressTask.cancel();
                        stan = "Czas uplynal";
                    }
                }
                return stan;
            }
        };


        progressBar.progressProperty().bind(progressTask.progressProperty());
        timeLabel.textProperty().bind(progressTask.messageProperty());

//        progressTask.messageProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                answerLabel.setText(newValue);
//            }
//        });

        Thread thread = new Thread(progressTask);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    public void play() {
        if (!textField.getText().isEmpty() && !(value>=VALUTE_TO_COMPLETE)) {
            numberAct.checkDigit(textField, progressTask, answerLabel, number);
        }else {
            if(value >= VALUTE_TO_COMPLETE)
                answerLabel.setText("CZAS UPLYNAL!!!");
        }
    }
}
