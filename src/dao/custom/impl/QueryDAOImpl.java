package dao.custom.impl;

import Util.FactoryConfiguration;
import dao.custom.QueryDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Program> findProgramDetails(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select p.* from Student_Program sp inner join Program p ON sp.programList_programID = p.programID where sp.studentList_regNumber=?1";
        NativeQuery nativeQuery = session.createNativeQuery(sql).addEntity(Program.class);
        nativeQuery.setParameter(1, value);
        List list = nativeQuery.list();
        transaction.commit();
        session.close();
        return list;
    }
}
