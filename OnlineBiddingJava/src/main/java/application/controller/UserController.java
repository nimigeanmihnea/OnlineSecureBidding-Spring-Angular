package application.controller;

import application.entity.User;
import application.entity.UserData;
import application.service.UserService;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@ComponentScan(basePackages = "application.service")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void get(HttpServletRequest request){}

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user){
        User logged = null;
        try {
            logged = userService.search(user.getUsername());

            if(logged.getPassword().equals(user.getPassword()))
                return new ResponseEntity(logged, HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.FORBIDDEN);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody ArrayList<Object> list){
        try {
            LinkedHashMap requestUser = (LinkedHashMap) list.get(0);
            LinkedHashMap requestUserData = (LinkedHashMap) list.get(1);

            User user = new User();
            user.setUsername((String) requestUser.get("username"));
            user.setPassword((String) requestUser.get("password"));
            user.setRole("ROLE_USER");
            user.setBids(null);

            UserData userData = new UserData();
            userData.setPersonalId((String) requestUserData.get("personalId"));
            userData.setFirstName((String) requestUserData.get("firstName"));
            userData.setLastName((String) requestUserData.get("lastName"));
            userData.setEmail((String) requestUserData.get("email"));
            userData.setPhone((String) requestUserData.get("phone"));
            userData.setAddress((String) requestUserData.get("address"));
            userData.setUser(user);

            userService.add(user, userData);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/update_profile", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody UserData userData){
        try{
            userService.update(userData);
            return new ResponseEntity(HttpStatus.OK);
        }catch (InvalidDataException e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/delete_user", method = RequestMethod.POST)
    public ResponseEntity delete(){
        try{
            userService.delete();
            return new ResponseEntity(HttpStatus.OK);
        }catch (InvalidDataException e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/view_users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> view(){
        try {
            return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/view_users/details", method = RequestMethod.GET)
    public ResponseEntity viewUserDetails(@PathParam(value = "param") Long param){
        try {
            return new ResponseEntity(userService.getUserDetails(param), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/view_users/user", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam(value = "param") String param){
        try {
            return new ResponseEntity(userService.search(param), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
