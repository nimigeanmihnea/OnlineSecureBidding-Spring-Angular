package application.service;

import application.entity.Account;
import application.entity.User;
import application.repository.AccountRepository;
import application.validator.AccountValidator;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = "application.validator")
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountValidator accountValidator;

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
    private static final int ACCOUNT_DIGITS = 12;

    public void add(User user) throws InvalidDataException{
        Account account = new Account();
        account.setBidPoints(0);
        account.setUser(user);
        account.setAccountNumber(random(ACCOUNT_DIGITS));

        accountValidator.exists(account);

        accountRepository.save(account);
    }

    public void delete(User user) throws InvalidDataException{
        Account account = accountRepository.findByUser(user);
        accountValidator.notExitst(account);
        accountRepository.delete(account);
    }

    public void withdraw(User user, float sum) throws InvalidDataException{
        Account account = accountRepository.findByUser(user);
        accountValidator.notExitst(account);
        accountValidator.validateWithdraw(account, sum);
        account.setBidPoints(account.getBidPoints() - sum);
        accountRepository.save(account);
    }

    public void deposit(User user, float sum) throws InvalidDataException{
        Account account = accountRepository.findByUser(user);
        accountValidator.notExitst(account);
        accountValidator.validateDeposit(account, sum);
        account.setBidPoints(account.getBidPoints() + sum);
        accountRepository.save(account);
    }

    private static String random(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
