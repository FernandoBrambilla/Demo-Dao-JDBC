
package Applications;

import Model.entities.Department;
import Model.entities.Seller;
import java.util.Date;

public class Program {

    public static void main(String[] args){
        Department dep = new Department(1, "books");
        System.out.println(dep);
        Seller seller = new Seller(21, "jose", "jose@email", new Date(), 4000.0, dep);
        System.out.println(seller);
}
}