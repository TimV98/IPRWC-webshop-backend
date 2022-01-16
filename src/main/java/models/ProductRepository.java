package models;

import models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Product findProductById(long id);

    Product saveProduct();
    
    Product editProduct(long id);

    void deleteById(long id);

}
