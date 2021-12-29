package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.ProgramTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    public TableView<ProgramTM> tblProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;

    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);

    public void initialize() {
        loadDateAndTime();
        try {
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

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
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
        System.out.println();
    }

    public void saveProgramOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ProgramDTO programDTO = new ProgramDTO(
                txtProgramId.getText(),
                txtProgramName.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        if (programBO.add(programDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "ProgramDTO Add To Database").show();
            showProgramsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void showProgramsOnTable() throws SQLException, ClassNotFoundException {

        ObservableList<ProgramTM> list = programBO.find();

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tblProgram.setItems(list);
    }

    public void removeProgramOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ProgramTM selectedItem = tblProgram.getSelectionModel().getSelectedItem();
        String programId = selectedItem.getProgramID();

        if (programBO.delete(programId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            showProgramsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void updateProgramOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ProgramTM selectedItem = tblProgram.getSelectionModel().getSelectedItem();
        String programId = selectedItem.getProgramID();

        ProgramDTO program = new ProgramDTO(
                txtProgramId.getText(),
                txtProgramName.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        if (programBO.update(program)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated").show();
            showProgramsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtProgramId.clear();
        txtProgramName.clear();
        txtDuration.clear();
        txtFee.clear();
        showProgramsOnTable();
    }

    public void keyEvent(KeyEvent keyEvent) {
        ObservableList<ProgramTM> search = programBO.search(txtSearch.getText());
        tblProgram.setItems(search);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        try {
            ProgramTM selectedProgram = tblProgram.getSelectionModel().getSelectedItem();
            txtProgramId.setText(selectedProgram.getProgramID());
            txtProgramName.setText(selectedProgram.getProgramName());
            txtDuration.setText(selectedProgram.getDuration());
            txtFee.setText("" + selectedProgram.getFee());

        } catch (Exception e) {

        }
    }
}
