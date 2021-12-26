package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.impl.ProgramDAOImpl;
import dto.ProgramDTO;
import dto.StudentDTO;
import entity.Program;

import java.util.ArrayList;
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
    public List<ProgramDTO> find() {
        List<Program> list = programDAO.find();
        ArrayList<ProgramDTO> dtoArrayList = new ArrayList<>();

        ProgramDTO programDTO = null;

        for (Program program: list
             ) {
            dtoArrayList.add(new ProgramDTO(
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
}
