package dao.custom.impl;

import Util.FactoryConfiguration;
import dao.custom.QueryDAO;
import entity.Program;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ObservableList<Program> findProgramDetails() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        transaction.commit();
        session.close();
        return list;
    }
}
