package application.repository;

import application.entity.Sale;
import application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Sale findBySaleNumber(long saleNumber);
    List<Sale> findByUser(User user);
}
