package Model.dao.impl;

import Model.dao.DepartmentDao;
import Model.entities.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao{
    
    //injeção de dependencia 
    private Connection connection;
        public DepartmentDaoJDBC(Connection connection){
            this. connection = connection;
        }

    @Override
    public void insert(Department obj) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                " insert into department "
                + "(`name`)"
                + " value  (?);",
                Statement.RETURN_GENERATED_KEYS);
                   
            pstmt.setString(1, obj.getName());
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0){
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    int  id = rs.getInt(1);
                    obj.setId(id);
                }
                DataBaseConnection.DataBaseConnection.closeResultSet(rs);
            } 
            else{
                throw new DataBaseConnection.DataBaseException("Erro inesperado! Nenhum registro afetado.");
            }
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
    public void update(Department obj) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                "UPDATE department "
                +" SET `name`=? "
                +" WHERE id= ?");     
            pstmt.setString(1, obj.getName());
            pstmt.setInt(2, obj.getId());
            pstmt.executeUpdate();
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
    public void deleteById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                "DELETE FROM department "
                +" WHERE id= ?");     
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
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
    public Department findById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                "SELECT * FROM department "
                +"WHERE Id = ?");
            pstmt.setInt(1, id);
            resultSet= pstmt.executeQuery();
            if(resultSet.next()){
                
                //cria um obj dept
                Department dept = instanceDept(resultSet);
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
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                "SELECT * FROM department "
                +"ORDER BY id");
            
            resultSet= pstmt.executeQuery();
            List <Department> departmentList = new ArrayList<>();
            Map <Integer, Department> map = new HashMap<>();
            while(resultSet.next()){
                
                //verifica se o depatamento ja existe na lista
                Department dept = map.get(resultSet.getInt("Id"));
                if(dept==null){
                    //caso não haja, cria um objeto departamento
                    dept= instanceDept(resultSet);
                    departmentList.add(dept);
                }
            }
            return departmentList;
            }
            catch(SQLException e){
                throw new DataBaseConnection.DataBaseException(e.getMessage());
        }
        finally{
            DataBaseConnection.DataBaseConnection.closeStatement(pstmt);
            DataBaseConnection.DataBaseConnection.closeResultSet(resultSet);
        } 
    }

    private Department instanceDept(ResultSet resultSet) throws SQLException {
        Department dept= new Department();
        dept.setId(resultSet.getInt("Id"));
        dept.setName(resultSet.getString("Name"));
        return dept;
    }
}
