package ma.emsi.springbootinit.service;

import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProductImp implements ServiceProduct{
    @Autowired
    ProductRepo productRepo;
    @Override
    public Page<Product> getProducts(int page) {
        return productRepo
                .findAll(PageRequest.of(page,10));
    }

    @Override
    public Product getProduct(Long id) {
        //Verifier si le produit existe
        return productRepo.findById(id).get();
    }

    @Override
    public Product getProduct(String ref) {
        return productRepo.findByRef(ref);
    }

    @Override
    public Product getProductName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product editProductPrice(Long id, Float price) {
        Product prctToBeEdited
                = productRepo.findById(id).get();
        if(prctToBeEdited!=null) {
            prctToBeEdited.setPrice(price);
            return productRepo.save(prctToBeEdited);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product deleteProduct(String name) {
        return productRepo.deleteByName(name);
    }

    @Override
    public void saveProduct(Product product) {
        this.productRepo.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional <Product> optional = productRepo.findById(id);
        Product product = null;
        if(optional.isPresent()){
            product=optional.get();
        }else {
            throw new RuntimeException("Product not found ::"+ id);
        }
        return product;
    }

    @Override
    public List<Product> findallProducts(String keyword) {
        if (keyword != null){
            return productRepo.search(keyword);
        }
        return (List<Product>) productRepo.findAll();
    }
}
