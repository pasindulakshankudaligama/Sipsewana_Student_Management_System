package dao.custom.impl;

import Util.FactoryConfiguration;
import dao.custom.ProgramDAO;
import entity.Program;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import view.tm.ProgramTM;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean add(Program entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Program program = session.get(Program.class, s);
        session.delete(program);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Program> find() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Program");
        List<Program> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<Program> searchPrograms(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Program p WHERE p.programID LIKE ?1");
        query.setParameter(1,'%'+value+'%');
        List list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> getAllProgramIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT p.programID FROM Program p");
        List<String> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
