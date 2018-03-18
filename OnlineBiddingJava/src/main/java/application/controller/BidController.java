package application.controller;

import application.entity.Bid;
import application.entity.BidItem;
import application.service.BidService;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
@ComponentScan(basePackages = "application.service")
public class BidController {

    @Autowired
    private BidService bidService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/add_bid", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Bid bid){
        try {
            bidService.add(bid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/start", method = RequestMethod.POST)
    public ResponseEntity start(@RequestParam(value = "param") Long param){
        try {
            bidService.start(param);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/stop", method = RequestMethod.POST)
    public ResponseEntity stop(@RequestParam(value = "param") Long param){
        try {
            bidService.stop(param);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/delete_bid", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestParam(value = "param") Long param){
        try {
            bidService.delete(param);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/view_bid", method = RequestMethod.GET)
    public ResponseEntity get(@RequestParam(value = "param") Long param){
        try {
            return new ResponseEntity(bidService.getBid(param), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/bid/update_bid_items", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody List<Object> list){
        LinkedHashMap request = (LinkedHashMap) list.get(0);
        LinkedHashMap request2 = (LinkedHashMap) list.get(1);

        long param = Long.parseLong((String) request.get("bidNumber"));
        long id = Long.parseLong((String) request2.get("bidItemId"));

        try {
            bidService.addBidItem(param, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
