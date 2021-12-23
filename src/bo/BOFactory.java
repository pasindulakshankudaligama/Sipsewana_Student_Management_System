package bo;



public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case ITEM:
               // return new ItemBOImpl();
            case CUSTOMER:
                //return new CustomerBOImpl();
            case PURCHASE_ORDER:
                //return new PurchaseOrderBOImpl();
            case MANAGE_ORDER:
               // return new ManageOrderBOImpl();
            case USER:
               // return new UserBOImpl();
            case LOGIN:
               // return new LogInBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        CUSTOMER, ITEM, PURCHASE_ORDER,MANAGE_ORDER,USER,LOGIN
    }
}
