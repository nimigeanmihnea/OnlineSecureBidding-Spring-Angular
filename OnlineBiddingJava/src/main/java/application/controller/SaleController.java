package application.controller;

import application.entity.Sale;
import application.entity.User;
import application.service.SaleService;
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

@Controller
@ComponentScan(basePackages = "application.service")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/close_bid", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Sale sale){
        try {
            saleService.add(sale);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
           return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/delete_sale", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestParam(value = "param") Long param){
        Sale sale = null;
        try {
            sale = saleService.getSale(param);
            saleService.delete(sale);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/show_sales", method = RequestMethod.GET)
    public ResponseEntity show(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = null;
        try {
           user = userService.search(name);
           return new ResponseEntity(saleService.getSalesByUser(user),HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
