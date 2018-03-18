package application.controller;

import application.entity.BidItem;
import application.entity.User;
import application.service.AccountService;
import application.service.BidItemService;
import application.service.UserService;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
@ComponentScan(basePackages = "application.service")
public class BidItemController {

    @Autowired
    private BidItemService bidItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/delete_bid_item", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestParam(value = "param") Long param){
        try {
            bidItemService.delete(param);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/place_bid", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody List<Object> list){
        LinkedHashMap requestBidNumber = (LinkedHashMap) list.get(0);
        LinkedHashMap requestPrice = (LinkedHashMap) list.get(1);
        LinkedHashMap requestUsername = (LinkedHashMap) list.get(2);

        long bidNumber = Long.parseLong((String) requestBidNumber.get("bidNumber"));
        long price = Long.parseLong((String) requestPrice.get("price"));
        String username = (String) requestUsername.get("username");

        User user = null;
        try {
            user = userService.search(username);
            accountService.withdraw(user, price);
            bidItemService.updateBidders(bidNumber, user);
            bidItemService.updatePrice(bidNumber, price);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/view_bid_items", method = RequestMethod.GET)
    public ResponseEntity<List<BidItem>> view(){
        try {
            return new ResponseEntity(bidItemService.getItems(), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

}
