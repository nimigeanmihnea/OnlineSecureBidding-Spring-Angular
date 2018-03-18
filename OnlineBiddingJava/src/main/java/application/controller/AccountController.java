package application.controller;

import application.entity.User;
import application.service.AccountService;
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
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/withdraw", method = RequestMethod.POST)
    public ResponseEntity withdraw(@RequestParam(value = "sum") Float sum){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        try {
            User user = userService.search(name);
            accountService.withdraw(user, sum);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/deposit", method = RequestMethod.POST)
    public ResponseEntity deposit(@RequestParam(value = "sum") Float sum, @RequestBody User user){
        try {
            User aux = userService.search(user.getUsername());
            accountService.deposit(aux, sum);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
