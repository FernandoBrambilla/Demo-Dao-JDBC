
package Model.dao;

import Model.dao.impl.DepartmentDaoJDBC;
import Model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    
    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC(DataBaseConnection.DataBaseConnection.getConnection());
    }
    
    public static DepartmentDao creatDepartmentDao(){
        return new DepartmentDaoJDBC (DataBaseConnection.DataBaseConnection.getConnection());
    }
}
