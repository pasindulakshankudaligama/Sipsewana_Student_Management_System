package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.StudentDTO;

import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean add(ProgramDTO programDTO);

    List<ProgramDTO> find();

    boolean delete(String id);

    boolean update(ProgramDTO programDTO);
}
