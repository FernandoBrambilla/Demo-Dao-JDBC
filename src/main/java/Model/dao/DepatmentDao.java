package Model.dao;

import Model.entities.Department;
import java.util.List;

public interface DepatmentDao {
   
    void insert(Department obj);
    void update(Department obj);
    void delete(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
    
    
    
        
    
    
}
