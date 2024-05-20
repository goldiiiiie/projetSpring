package ma.emsi.springbootinit.repositories;

import ma.emsi.springbootinit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProductRepo
        extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Product findByRef(String ref);
    Product deleteByName(String name);
    @Query("SELECT product FROM Product product WHERE CONCAT(product.id, ' ', product.code, ' ', product.name, ' ', product.price, ' ', product.prodDate, ' ', product.ref) LIKE %?1%")
    public List<Product> search(String keyword);


}
