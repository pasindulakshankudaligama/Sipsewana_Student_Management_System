package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class ManageStudentRegistrationFormController {
    public AnchorPane srContext;
    public Label lblDate;
    public Label lblTime;
    public TableView tblStudent;
    public TableColumn colRegNum;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colNIC;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colEmail;
    public TableColumn colGender;
    public JFXTextField txtRegNum;
    public JFXTextField txtName;
    public JFXTextField txtDOB;
    public JFXTextField txtContactNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtAge;
    public JFXTextField txtEmail;
    public JFXComboBox txtProgramId1;
    public JFXTextField txtPName1;
    public JFXTextField txtFee1;
    public JFXTextField txtDuration1;
    public JFXComboBox txtProgramId2;
    public JFXTextField txtPName2;
    public JFXTextField txtFee2;
    public JFXTextField txtDuration2;
    public JFXComboBox txtProgramId3;
    public JFXTextField txtPName3;
    public JFXTextField txtFee3;
    public JFXTextField txtDuration3;
    public JFXTextField txtNIC;
    public JFXRadioButton txtMale;
    public ToggleGroup gender;
    public RadioButton txtFemale;


    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);

    public void initialize(){
        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+ " : "+currentTime.getMinute()+ " : "+currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) srContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        //window.setResizable(false);
    }

    public void keyEvent(KeyEvent keyEvent) {
    }

    public void removeProgramOnAction(ActionEvent actionEvent) {
    }

    public void saveProgramOnAction(ActionEvent actionEvent) {
    }

    public void updateProgramOnAction(ActionEvent actionEvent) {
    }
}
