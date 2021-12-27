package dao.custom;

import dao.SuperDAO;
import entity.Program;
import javafx.collections.ObservableList;
import view.tm.ProgramTM;

import java.util.List;

public interface ProgramDAO extends SuperDAO<Program, String> {
    List<Program> searchPrograms(String value);
}
