package application.repository;

import application.entity.BidItem;
import application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidItemRepository extends JpaRepository<BidItem, Long>{

    BidItem findByProduct(Product product);
}
