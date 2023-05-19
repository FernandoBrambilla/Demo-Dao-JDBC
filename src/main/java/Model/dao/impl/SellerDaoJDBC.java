package Model.dao.impl;

import Model.dao.SellerDao;
import Model.entities.Department;
import Model.entities.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao{

    //injeção de dependencia 
    private Connection connection;
        public SellerDaoJDBC(Connection connection){
            this. connection = connection;
        }

    public SellerDaoJDBC() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void insert(Seller obj) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                    " insert into seller "
                    + "(`name`, email, birthDate, baseSalary, departmentId)"
                    + " values  (?,?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
                   
            pstmt.setString(1, obj.getName());
            pstmt.setString(2, obj.getEmail());
            pstmt.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            pstmt.setDouble(4,obj.getBaseSalary());
            pstmt.setInt(5,obj.getDepartment().getId());
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
    public void update(Seller obj) {
    }

    @Override
    public void delete(Integer id) {
    }

    //localizar seller por Id
    @Override
    public Seller findById(Integer id) {
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
                
                return seller;
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
    public List<Seller> findAll() {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                    "SELECT seller.*,department.name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentID = department.Id "
                    +"ORDER BY name");
            resultSet= pstmt.executeQuery();
            List <Seller> sellerList = new ArrayList<>();
            Map <Integer, Department> map = new HashMap<>();
            while(resultSet.next()){
                
                //verifica se o depatamento ja existe na lista
                Department dept = map.get(resultSet.getInt("DepartmentId"));
                if(dept==null){
                    //caso não haja, cria um objeto departamento
                    dept= instanceDept(resultSet);
                }
                //cria um obj seller
                Seller seller = instanceSeller(resultSet, dept);
                sellerList.add(seller);
            }
            return sellerList;
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

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            pstmt= connection.prepareStatement(
                    "SELECT seller.*,department.name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentID = department.Id "
                    +"WHERE department.Id = ? "
                    +"ORDER BY name");
            pstmt.setInt(1, department.getId());
            resultSet= pstmt.executeQuery();
            List <Seller> sellerList = new ArrayList<>();
            Map <Integer, Department> map = new HashMap<>();
            while(resultSet.next()){
                
                //verifica se o depatamento ja existe na lista
                Department dept = map.get(resultSet.getInt("DepartmentId"));
                if(dept==null){
                    //caso não haja, cria um objeto departamento
                    dept= instanceDept(resultSet);
                }
                //cria um obj seller
                Seller seller = instanceSeller(resultSet, dept);
                sellerList.add(seller);
            }
            return sellerList;
        }
        catch(SQLException e){
            throw new DataBaseConnection.DataBaseException(e.getMessage());
        }
        finally{
            DataBaseConnection.DataBaseConnection.closeStatement(pstmt);
            DataBaseConnection.DataBaseConnection.closeResultSet(resultSet);
        }
    }
    
}
