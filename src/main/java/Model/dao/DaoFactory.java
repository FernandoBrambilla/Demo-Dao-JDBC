
package Model.dao;

import Model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC();
    }
}
