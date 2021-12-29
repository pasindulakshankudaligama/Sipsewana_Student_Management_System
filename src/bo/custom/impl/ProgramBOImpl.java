package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.impl.ProgramDAOImpl;
import dto.ProgramDTO;
import entity.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.ProgramTM;

import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAOImpl programDAO = (ProgramDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean add(ProgramDTO programDTO) {
        return programDAO.add(new Program(
                programDTO.getProgramID(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }

    @Override
    public ObservableList<ProgramTM> find() {
        List<Program> list = programDAO.find();
        ObservableList<ProgramTM> dtoArrayList = FXCollections.observableArrayList();

        ProgramDTO programDTO = null;

        for (Program program : list
        ) {
            dtoArrayList.add(new ProgramTM(
                    program.getProgramID(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()
            ));

        }
        return dtoArrayList;
    }

    @Override
    public boolean delete(String id) {
        return programDAO.delete(id);
    }

    @Override
    public boolean update(ProgramDTO programDTO) {
        return programDAO.update(new Program(
                programDTO.getProgramID(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }

    @Override
    public ObservableList<ProgramTM> search(String value) {
        List<Program> list = programDAO.searchPrograms(value);
        ObservableList<ProgramTM> dtoArrayList = FXCollections.observableArrayList();
        for (Program program : list
        ) {
            dtoArrayList.add(new ProgramTM(
                    program.getProgramID(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()));
        }
        return dtoArrayList;
    }

    @Override
    public List<String> getAllProgramIds() {
        return programDAO.getAllProgramIds();
    }

    @Override
    public ProgramDTO getProgramDetails(String id) {
        return programDAO.getProgramList(id);
    }
}
