package application.service;

import application.entity.Sale;
import application.entity.User;
import application.repository.SaleRepository;
import application.validator.SaleValidator;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "application.validator")
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleValidator saleValidator;

    public void add(Sale sale) throws InvalidDataException{
        saleValidator.validate(sale);
        saleRepository.save(sale);
    }

    public void delete(Sale sale) throws InvalidDataException{
        saleValidator.validateDelete(sale);
        saleRepository.delete(sale);
    }

    public Sale getSale(long saleNumber) throws InvalidDataException{
        return saleRepository.findBySaleNumber(saleNumber);
    }

    public List<Sale> getSalesByUser(User user) throws InvalidDataException{
        return saleRepository.findByUser(user);
    }
}
