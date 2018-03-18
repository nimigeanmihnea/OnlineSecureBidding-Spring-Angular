package application.validator;

import application.entity.Sale;
import application.repository.SaleRepository;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleValidator {

    private static final String SALE_PRICE_BELLOW_OR_ZERO = "The price of the sale must me greater than 0.";
    private static final String SALE_NUMBER_NOT_VALID = "The number of the sale must be unique!";

    @Autowired
    private SaleRepository saleRepository;

    public SaleValidator(){}

    public void validate(Sale sale) throws InvalidDataException{
        validateSalePrice(sale);
        validateSaleNumber(sale);
    }

    public void validateDelete(Sale sale) throws InvalidDataException{
        if(saleRepository.findOne(sale.getId()) == null)
            throw new InvalidDataException(SALE_NUMBER_NOT_VALID);
    }

    private void validateSalePrice(Sale sale) throws InvalidDataException{
        if(sale.getPrice() <= 0)
            throw new InvalidDataException(SALE_PRICE_BELLOW_OR_ZERO);
    }

    private void validateSaleNumber(Sale sale) throws InvalidDataException{
        if(saleRepository.findOne(sale.getId()) != null)
            throw new InvalidDataException(SALE_NUMBER_NOT_VALID);
    }
}
