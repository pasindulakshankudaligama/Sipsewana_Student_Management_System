package dao;



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
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
            //    return new CustomerDAOImpl();
            case ITEM:
            //    return new ItemDAOImpl();
            case ORDER:
              //  return new OrderDAOImpl();
            case ORDER_DETAILS:
              //  return new OrderDetailDAOImpl();
            case QUERY:
              //  return new QueryDAOImpl();
            case USER:
               // return new UserDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAILS,QUERY,USER
    }
}
