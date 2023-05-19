package Model.dao.impl;


import Model.dao.DepartmentDao;
import Model.entities.Department;
import Model.entities.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao{

    //injeção de dependencia 
    private Connection connection;
        public DepartmentDaoJDBC(Connection connection){
            this. connection = connection;
        }

    public DepartmentDaoJDBC() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void insert(Department obj) {
       
    }

    @Override
    public void update(Department obj) {
    }

    @Override
    public void delete(Integer id) {
    }

    //localizar seller por Id
    @Override
    public Department findById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                    "SELECT seller.*,department.name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentID = department.Id "
                    +"WHERE seller.Id = ?");
            pstmt.setInt(1, id);
            resultSet= pstmt.executeQuery();
            if(resultSet.next()){
                
                //cria um obj dept
                Department dept = instanceDept(resultSet);
                
                //cria um obj seller
                Seller seller = instanceSeller(resultSet, dept);
                
                return dept;
            }
            return null;
        }
        catch(SQLException e){
            throw new DataBaseConnection.DataBaseException(e.getMessage());
        }
        finally{
            DataBaseConnection.DataBaseConnection.closeStatement(pstmt);
            DataBaseConnection.DataBaseConnection.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Department instanceDept(ResultSet resultSet) throws SQLException {
        Department dept= new Department();
        dept.setId(resultSet.getInt("DepartmentId"));
        dept.setName(resultSet.getString("DepName"));
        return dept;
    }
    
    private Seller instanceSeller(ResultSet resultSet, Department dept) throws SQLException{
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setDepartment(dept);
        return seller;
    }
    
}
