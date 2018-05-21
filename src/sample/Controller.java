package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    public TextField txtData;
    @FXML
    Button btnResult;
    private Calculator calculator;
    private boolean isSecondNumber;

    public Controller() {
        txtData = new TextField();
        calculator = new Calculator();
        isSecondNumber = false;
        btnResult = new Button();
    }


//      new Alert(Alert.AlertType.CONFIRMATION, txtData.getText()).show();


    public void btnNumberClick(ActionEvent actionEvent) {
        String btnText = ((Button) actionEvent.getSource()).getText();
        txtData.setText(txtData.getText().equals("0") || (isSecondNumber == true) ? btnText : txtData.getText() + btnText);
        isSecondNumber = false;
    }

    public void btnCommaClick(ActionEvent actionEvent) {
        txtData.setText(txtData.getText() + (!txtData.getText().contains(".") ? "." : ""));
    }

    public void btnClearClick(ActionEvent actionEvent) {
        txtData.setText("0");
    }

    public void btnSignClick(ActionEvent actionEvent) {
        // Delete last zero
        String result = String.valueOf(-1 * Double.parseDouble(txtData.getText()));
        result = result.endsWith("0") ? result.substring(0, result.length() - 2) : result;
        txtData.setText(result);
    }

    public void btnPairOperationClick(ActionEvent actionEvent) {
        btnResult.setDisable(true); //// !!!!!!!

        try {
            calculator.setNumber1(Double.parseDouble(txtData.getText()));
            switch (((Button) actionEvent.getSource()).getText()) {
                case "+":
                    calculator.setOperation((byte) 1);
                    break;
                case "-":
                    calculator.setOperation((byte) 2);
                    break;
                case "*":
                    calculator.setOperation((byte) 3);
                    break;
                case "/":
                    calculator.setOperation((byte) 4);
                    break;
                case "x^y":
                    calculator.setOperation((byte) 5);
                    break;
                case "√":
                    calculator.setOperation((byte) 6);
                    calculator.calculate();
                    txtData.setText(calculator.getResult());
                    //txtData.setAlignment(Pos.CENTER_LEFT);
                    break;
                case "M+":
                    calculator.setSavedResult(txtData.getText());
                    break;
                case "M-":
                    txtData.setText(calculator.getSavedResult());
                    break;
                default:
                    calculator.setOperation((byte) 0);
            }
            isSecondNumber = true;
            //txtData.setText("0");
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }

    }

    public void btnResultClick(ActionEvent actionEvent) {

        try {
            calculator.setNumber2(Double.parseDouble(txtData.getText()));
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }

        calculator.calculate();
        txtData.setText(calculator.getResult());
        btnResult.setDisable(true); //// !!!!!!!
    }


}
