package application.service;

import application.entity.Bid;
import application.entity.BidItem;
import application.entity.Sale;
import application.producer.Producer;
import application.repository.BidItemRepository;
import application.repository.BidRepository;
import application.repository.SaleRepository;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@ComponentScan(basePackages = "application.validator")
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private BidItemRepository bidItemRepository;

    public void add(Bid bid) throws InvalidDataException{
        bidRepository.save(bid);
    }

    public void start(long bidNumber) throws InvalidDataException{
        Bid bid = bidRepository.findByBidNumber(bidNumber);
        bid.setStatus(true);
        bidRepository.save(bid);
        Producer producer = null;
        try {
            producer = new Producer("queue");
            HashMap message = new HashMap();
            message.put("BID", bid);
            producer.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(long bidNumber) throws InvalidDataException{
        Bid bid = bidRepository.findByBidNumber(bidNumber);
        bid.setStatus(false);
        bidRepository.save(bid);

        Sale sale = new Sale();
        sale.setProduct(bid.getItem().getProduct());
        sale.setPrice(bid.getItem().getPrice());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        sale.setDate(dateFormat.format(date));
        sale.setUser(bid.getItem().getBidders().get(bid.getItem().getBidders().size() - 1));
        sale.setSaleNumber(bid.getBidNumber()+32);
        saleRepository.save(sale);
    }

    public void delete(long bidNumber) throws InvalidDataException{
        Bid bid = bidRepository.findByBidNumber(bidNumber);
        bidRepository.delete(bid);
    }

    public Bid getBid(long bidNumber) throws InvalidDataException{
        return bidRepository.findByBidNumber(bidNumber);
    }

    public void addBidItem(long bidNumber, long bidItemId) throws InvalidDataException{
        BidItem bidItem = bidItemRepository.findOne(bidItemId);
        Bid bid = bidRepository.findByBidNumber(bidNumber);
        bid.setItem(bidItem);
        bidRepository.save(bid);
    }
}
