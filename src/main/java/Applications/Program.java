
package Applications;

import Model.dao.DaoFactory;
import Model.dao.SellerDao;
import Model.entities.Department;
import Model.entities.Seller;
import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args){
       
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        
        System.out.println("-------teste 1 --------findById");
        Seller sellerTest1 = sellerDao.findById(2);
        System.out.println(sellerTest1);
        
        System.out.println("-------teste 2 --------findByDpt");
        Department dpt = new Department(4, null);
        List <Seller> sellerList = sellerDao.findByDepartment(dpt);
        for(Seller obj:sellerList){
            System.out.println(obj);
        }
          
        System.out.println("-------teste 3 --------findByAll");
        sellerList = sellerDao.findAll();
        for(Seller obj:sellerList){
            System.out.println(obj);
        }
        
        System.out.println("-------teste 4 --------sellerInsert");
        Seller sellerTest4 = new Seller(null, "Maria", "maria@email.com", new Date(), 2000.0, dpt);
        sellerDao.insert(sellerTest4);
        System.out.println(sellerTest4 + "id = "+ sellerTest4.getId());
                
        System.out.println("-------teste 5 --------sellerUpdate");
        Seller sellerTest5 = sellerDao.findById(2);
        sellerTest5.setName("Maria Salete");
        sellerDao.update(sellerTest5);
        System.out.println(sellerTest5);
        
        System.out.println("-------teste 6 --------sellerDelete");
        sellerDao.deleteById(1);
        System.out.println("Registro deletado com Sucesso");
        
        
        
    }
}