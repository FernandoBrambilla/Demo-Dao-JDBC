
package Applications;

import Model.dao.DaoFactory;
import Model.dao.SellerDao;
import Model.entities.Seller;

public class Program {

    public static void main(String[] args){
       
        SellerDao selerDao = DaoFactory.creatSellerDao();
        System.out.println("-------teste 1 --------findById");
        Seller seller = selerDao.findById(2);
        System.out.println(seller);
        
    }
}