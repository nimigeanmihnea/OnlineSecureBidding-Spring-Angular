package application.validator;

import application.entity.Account;
import application.entity.User;
import application.repository.AccountRepository;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountValidator {

    private static final float ACCOUNT_MIN = 0;
    private static final float ACCOUNT_MAX = 150000;
    private static final String ACCOUNT_EXISTS = "Account already exitsts!";
    private static final String ACCOUNT_NOT_EXISTS = "Account already exitsts!";
    private static final String ACCOUNT_DEPOSIT_OVERRUN = "You can't deposit this sum, try a lower sum!";
    private static final String ACCOUNT_WITHDRAW_OVERRUN = "You can't withdraw this sum, try lesser!";

    @Autowired
    private AccountRepository accountRepository;

    public AccountValidator(){}

    public void validateDeposit(Account account, float sum) throws InvalidDataException{
        if(account.getBidPoints() + sum > ACCOUNT_MAX)
            throw new InvalidDataException(ACCOUNT_DEPOSIT_OVERRUN);
    }

    public void validateWithdraw(Account account, float sum) throws InvalidDataException{
        if(account.getBidPoints() - sum < ACCOUNT_MIN)
            throw new InvalidDataException(ACCOUNT_WITHDRAW_OVERRUN);
    }

    public void exists(Account account) throws InvalidDataException{
        if(accountRepository.findByAccountNumber(account.getAccountNumber()) != null)
            throw new InvalidDataException(ACCOUNT_EXISTS);
    }

    public void notExitst(Account account) throws InvalidDataException{
        if(accountRepository.findByAccountNumber(account.getAccountNumber()) == null)
            throw new InvalidDataException(ACCOUNT_NOT_EXISTS);
    }
}
