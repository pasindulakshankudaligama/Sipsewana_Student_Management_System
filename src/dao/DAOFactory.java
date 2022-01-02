package dao;


import dao.custom.impl.ProgramDAOImpl;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public UltraSuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, PROGRAM,QUERY
    }
}
