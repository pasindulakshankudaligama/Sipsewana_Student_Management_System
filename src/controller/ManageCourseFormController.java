package controller;

import bo.BOFactory;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class ManageCourseFormController {
    public AnchorPane cmContext;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtSearch;
    public JFXTextField txtProgramId;
    public JFXTextField txtProgramName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public TableView tblProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;

    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

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
        Stage window = (Stage) cmContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        System.out.println("");
    }

    public void saveProgramOnAction(ActionEvent actionEvent) {

    }

    public void removeProgramOnAction(ActionEvent actionEvent) {
    }

    public void updateProgramOnAction(ActionEvent actionEvent) {
    }


    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void keyEvent(KeyEvent keyEvent) {
    }
}
