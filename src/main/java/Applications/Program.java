
package Applications;

import Model.dao.DaoFactory;
import Model.dao.SellerDao;
import Model.entities.Seller;
import java.util.Date;

public class Program {

    public static void main(String[] args){
       
        SellerDao selerDao = DaoFactory.creatSellerDao();
        
        Seller seller = selerDao.findById(2);
        System.out.println(seller);
        
    }
}