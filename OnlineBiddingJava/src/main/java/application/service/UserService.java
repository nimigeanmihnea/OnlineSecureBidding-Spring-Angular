package application.service;

import application.entity.User;
import application.entity.UserData;
import application.repository.UserDataRepository;
import application.repository.UserRepository;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "application.validator")
public class UserService implements UserDetailsService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = this.userRepository.findByUsername(username);
            user.isEnabled();
            return new User(user.getUsername(), user.getPassword(), user.getRole());
        }catch (UsernameNotFoundException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void add(User user, UserData userData) throws InvalidDataException {
        if(userRepository.findByUsername(user.getUsername()) == null
                && userDataRepository.findByPersonalId(userData.getPersonalId()) == null) {

            userRepository.save(user);
            userDataRepository.save(userData);
            accountService.add(user);
        }
    }

    public void update(UserData userData) throws InvalidDataException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);

        if(user != null){
            UserData data = userDataRepository.findByUser(user);
            data.setPhone(userData.getPhone());
            data.setPersonalId(userData.getPersonalId());
            data.setLastName(userData.getLastName());
            data.setFirstName(userData.getFirstName());
            data.setEmail(userData.getEmail());
            data.setAddress(userData.getAddress());

            userDataRepository.save(data);
        }else throw new InvalidDataException();
    }

    public void delete() throws InvalidDataException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);

        if(user != null){
            UserData data = userDataRepository.findByUser(user);
            if(data != null){
                userDataRepository.delete(data);
            }else throw new InvalidDataException();

            accountService.delete(user);
            userRepository.delete(user);
        }else throw new InvalidDataException();
    }

    public List<User> getUsers() throws InvalidDataException{
        return userRepository.findAll();
    }

    public UserData getUserDetails(Long id) throws InvalidDataException{
        User user = userRepository.findOne(id);
        return userDataRepository.findByUser(user);
    }

    public User search(String username) throws InvalidDataException{
        return userRepository.findByUsername(username);
    }
}
