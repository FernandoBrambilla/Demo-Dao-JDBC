
package Applications;

import Model.dao.DaoFactory;
import Model.dao.SellerDao;
import Model.entities.Department;
import Model.entities.Seller;
import java.util.List;

public class Program {

    public static void main(String[] args){
       
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        System.out.println("-------teste 1 --------findById");
        Seller seller = sellerDao.findById(2);
        System.out.println(seller);
        
        
       
        System.out.println("-------teste 2 --------findByDpt");
        Department dpt = new Department(2, null);
        List <Seller> sellerList = sellerDao.findByDepartment(dpt);
        for(Seller obj:sellerList){
            System.out.println(obj);
        }
        
         
        System.out.println("-------teste 3 --------findByAll");
       
        sellerList = sellerDao.findAll();
        for(Seller obj:sellerList){
            System.out.println(obj);
        }
        
        
        
    }
}