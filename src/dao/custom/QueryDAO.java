package dao.custom;

import dao.UltraSuperDAO;
import entity.Program;
import javafx.collections.ObservableList;

import java.util.List;

public interface QueryDAO extends UltraSuperDAO {
    ObservableList<Program> findProgramDetails();
}
