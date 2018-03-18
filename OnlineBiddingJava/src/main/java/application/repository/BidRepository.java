package application.repository;

import application.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long>{

    Bid findByBidNumber(long bidNumber);
}
