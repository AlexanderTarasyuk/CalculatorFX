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
    private Calculator calculator;
    private boolean isSecondNumber;
    Button btnResult;


    public Controller() {
        txtData = new TextField();
        calculator = new Calculator();
        isSecondNumber = false;
        btnResult=new Button();
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
        try {
            calculator.number1 = Double.parseDouble(txtData.getText());
            switch (((Button) actionEvent.getSource()).getText()) {
                case "+":
                    calculator.operation = 1;
                    break;
                case "-":
                    calculator.operation = 2;
                    break;
                case "*":
                    calculator.operation = 3;
                    break;
                case "/":
                    calculator.operation = 4;
                    break;
                case "x^y":
                    calculator.operation = 5;
                    break;
                case "√":
                    calculator.operation = 6;
                    calculator.calculate();
                    txtData.setText(calculator.result);
                    //txtData.setAlignment(Pos.CENTER_LEFT);
                    break;
                case "M+":
                    calculator.result = txtData.getText();
                    break;
                case "M-":
                    txtData.setText(calculator.result);
                    break;
                default:
                    calculator.operation = 0;
            }
            isSecondNumber = true;
            //txtData.setText("0");
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }

    }

    public void btnResultClick(ActionEvent actionEvent) {
        btnResult.setDisable(false);  /// !!!!!
        try {
            calculator.number2 = Double.parseDouble(txtData.getText());
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }

        calculator.calculate();
        txtData.setText(calculator.result);
        btnResult.setDisable(true); //// !!!!!!!
    }


}
