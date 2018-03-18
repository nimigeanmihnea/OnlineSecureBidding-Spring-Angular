package application.validator;

import application.entity.BidItem;
import application.repository.BidItemRepository;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidItemValidator {

    @Autowired
    private BidItemRepository bidItemRepository;

    private static final String BID_ITEM_NOT_VALID = "Not a valid bid item!";
    private static final String BID_ITEM_NOT_EXISTS = "The bid item does not exist";
    private static final String BID_ITEM_INVALID_PRICE = "The bid item's new price is lower or equals with the old price!";

    public BidItemValidator(){}

    public void validate(BidItem bidItem) throws InvalidDataException{
        if(bidItem.getProduct() == null || bidItem.getPrice() <= 0)
            throw new InvalidDataException(BID_ITEM_NOT_VALID);
    }

    public void exists(BidItem bidItem) throws InvalidDataException{
        if(bidItemRepository.findByProduct(bidItem.getProduct()) == null)
            throw new InvalidDataException(BID_ITEM_NOT_EXISTS);
    }

    public void price(BidItem bidItem, float price) throws InvalidDataException{
        if(bidItem.getPrice() >= price)
            throw new InvalidDataException(BID_ITEM_INVALID_PRICE);
    }
}
