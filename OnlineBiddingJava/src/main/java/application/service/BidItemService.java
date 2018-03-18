package application.service;

import application.entity.Bid;
import application.entity.BidItem;
import application.entity.Product;
import application.entity.User;
import application.repository.BidItemRepository;
import application.repository.BidRepository;
import application.validator.BidItemValidator;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan(basePackages = "application.validator")
public class BidItemService {

    @Autowired
    private BidItemRepository bidItemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidItemValidator bidItemValidator;

    public void add(Product product, float price) throws InvalidDataException{
        BidItem bidItem = new BidItem();
        bidItem.setProduct(product);
        bidItem.setPrice(price);
        bidItem.setBidders(null);
        bidItemValidator.validate(bidItem);
        bidItemRepository.save(bidItem);
    }

    public void delete(Long id) throws InvalidDataException{
        BidItem bidItem = bidItemRepository.findOne(id);
        bidItemValidator.exists(bidItem);
        bidItemRepository.delete(bidItem);
    }

    public void updatePrice(Long id, float price) throws InvalidDataException{
        Bid bid = bidRepository.findByBidNumber(id);
        BidItem bidItem = bid.getItem();
        bidItemValidator.exists(bidItem);
        bidItemValidator.price(bidItem, price);
        bidItem.setPrice(price);
        bidItemRepository.save(bidItem);
    }

    public void updateBidders(Long id, User user) throws InvalidDataException{
        Bid bid = bidRepository.findByBidNumber(id);
        BidItem bidItem = bid.getItem();
        List<User> users = bidItem.getBidders();
        List<User> newUsers = new ArrayList<User>();
        for (User aux:
             users) {
            newUsers.add(aux);
        }
        newUsers.add(user);

        bidItem.setBidders(newUsers);
        bidItemRepository.save(bidItem);
    }

    public List<BidItem> getItems() throws InvalidDataException{
        return bidItemRepository.findAll();
    }

}
