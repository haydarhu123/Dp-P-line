package dao;

import domein.OVChipkaart;
import domein.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAOP {
    public boolean save(Product product);

    public boolean delete(Product product);

    public boolean update(Product product);

    public Product findByOVChipkaart(OVChipkaart ovchipkaart);

    public List<Product> findAll() throws SQLException;
}
