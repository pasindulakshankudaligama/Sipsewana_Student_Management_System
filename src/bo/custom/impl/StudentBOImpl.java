package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentBOImpl studentBO = (StudentBOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(StudentDTO studentDTO) {
        return studentBO.add(new StudentDTO(
                studentDTO.getRegNumber(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNumber(),
                studentDTO.getAddress(),
                studentDTO.getDob(),
                studentDTO.getEmail(),
                studentDTO.getNic(),
                studentDTO.getGender()
        ));
    }

    @Override
    public List<StudentDTO> find() {
        List<StudentDTO> list = studentBO.find();
        ArrayList<StudentDTO> dtoArrayList = new ArrayList<>();

        StudentDTO studentDTO = null;

        for (StudentDTO student : list
        ) {
            dtoArrayList.add(new StudentDTO(
                    student.getRegNumber(),
                    student.getName(),
                    student.getAge(),
                    student.getContactNumber(),
                    student.getAddress(),
                    student.getDob(),
                    student.getEmail(),
                    student.getNic(),
                    student.getGender()
            ));

        }
        return dtoArrayList;
    }

    @Override
    public boolean delete(String id) {
        return studentBO.delete(id);
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return studentBO.update(new StudentDTO(
                studentDTO.getRegNumber(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNumber(),
                studentDTO.getAddress(),
                studentDTO.getDob(),
                studentDTO.getEmail(),
                studentDTO.getNic(),
                studentDTO.getGender()
        ));
    }
}
