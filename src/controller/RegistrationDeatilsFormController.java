package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class RegistrationDeatilsFormController {

    public AnchorPane rdContext;
    public Label lblDate;
    public Label lblTime;
    public TableView<ProgramTM> tblProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public TableView<StudentTM> tblStudent;
    public TableColumn colRegNum;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colNIC;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colEmail;
    public TableColumn colGender;

    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);
    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);

    public void initialize(){
        loadDateAndTime();

        try {
            showStudentsOnTable();
            showProgramsOnTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        Stage window = (Stage) rdContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
       // window.setResizable(false);
    }

    public void keyEvent(KeyEvent keyEvent) {
    }

    private void showStudentsOnTable() {
        ObservableList<StudentTM> list = studentBO.find();

        colRegNum.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblStudent.setItems(list);
    }

    public void showProgramsOnTable() throws SQLException, ClassNotFoundException {

       // ObservableList<ProgramTM> list = programBO.find();

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

       // tblProgram.setItems(list);
    }
}
