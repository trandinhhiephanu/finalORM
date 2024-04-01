package fa.training.services;

import fa.training.entities.Account;
import fa.training.repositories.AccountRepository;

import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository = new AccountRepository();

    public Account getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account) {
        return accountRepository.update(account);
    }

    public void deleteAccount(int id) {
        accountRepository.delete(id);
    }

    // Question c: get all users with balance greater than a given number
    // Requirement is 100.000.000 will be tested in the test case
    public List<Account> getAccountByBalance(double minBalance) {
        return accountRepository.findByMinBalance(minBalance);
    }
}
