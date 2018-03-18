package application.service;

import application.entity.Product;
import application.repository.ProductRepository;
import application.validator.ProductValidator;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "application.validator")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductValidator productValidator;

    public void add(Product product) throws InvalidDataException{
        productValidator.exists(product);
        productRepository.save(product);
    }

    public void update(long series, Product product) throws InvalidDataException{
        productValidator.exists(product);
        Product existing = productRepository.findBySeries(series);
        existing.setName(product.getName());
        existing.setProducer(product.getProducer());
        existing.setSpecs(product.getSpecs());

        productRepository.save(existing);
    }

    public void delete(Long series) throws InvalidDataException{
        Product product = productRepository.findBySeries(series);
        productValidator.notExists(product);
        productRepository.delete(product);
    }

    public List<Product> getProducts() throws InvalidDataException{
        return productRepository.findAll();
    }

    public Product getProduct(long series) throws InvalidDataException{
        return productRepository.findBySeries(series);
    }
}
