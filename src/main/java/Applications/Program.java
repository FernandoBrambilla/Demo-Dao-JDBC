
package Applications;

import Model.entities.Department;
import java.sql.Connection;
import java.sql.SQLException;

public class Program {

    public static void main(String[] args){
        Department dep = new Department(1, "books");
        System.out.println(dep);
}
}