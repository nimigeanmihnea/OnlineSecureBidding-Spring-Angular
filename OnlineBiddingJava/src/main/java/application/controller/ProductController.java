package application.controller;

import application.entity.Product;
import application.service.BidItemService;
import application.service.ProductService;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ComponentScan(basePackages = "application.service")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BidItemService bidItemService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/add_product", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Product product, @RequestParam(value = "price") Float price){
        try {
            productService.add(product);
            bidItemService.add(product, price);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/delete_product", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestParam(value = "series") Long series){
        try{
            productService.delete(series);
            return new ResponseEntity(HttpStatus.OK);
        }catch (InvalidDataException e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/update_product", method = RequestMethod.POST)
    public ResponseEntity update(@RequestParam(value = "series") Long series, @RequestBody Product product){
        try{
            productService.update(series, product);
            return new ResponseEntity(HttpStatus.OK);
        }catch (InvalidDataException e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/view_products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> view(){
        try {
            return new ResponseEntity(productService.getProducts(), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin/view_product", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam(value = "param") Long param){
        try {
            return new ResponseEntity(productService.getProduct(param), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
