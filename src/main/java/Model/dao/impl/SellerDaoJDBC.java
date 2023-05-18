package Model.dao.impl;

import Model.dao.SellerDao;
import Model.entities.Seller;
import java.util.List;

public class SellerDaoJDBC implements SellerDao{

    @Override
    public void insert(Seller obj) {
       
    }

    @Override
    public void update(Seller obj) {
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public Seller findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Seller> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
