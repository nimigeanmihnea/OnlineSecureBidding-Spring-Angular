package application.validator;

import application.entity.Product;
import application.repository.ProductRepository;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator {

    @Autowired
    private ProductRepository productRepository;

    private static final String PRODUCT_SERIES_EXISTS = "Product already exists!";
    private static final String PRODUCT_SERIES_NOT_EXISTS = "Product not exists!";

    public ProductValidator(){}

    public void exists(Product product) throws InvalidDataException{
        if(productRepository.findBySeries(product.getSeries()) != null)
            throw new InvalidDataException(PRODUCT_SERIES_EXISTS);
    }

    public void notExists(Product product) throws InvalidDataException{
        if(productRepository.findBySeries(product.getSeries()) == null)
            throw new InvalidDataException(PRODUCT_SERIES_NOT_EXISTS);
    }
}
