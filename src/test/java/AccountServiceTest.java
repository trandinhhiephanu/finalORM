import fa.training.entities.Account;
import fa.training.repositories.AccountRepository;
import fa.training.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccountById() {
        Account account = new Account();
        account.setId(1);
        when(accountRepository.findById(1)).thenReturn(account);

        Account result = accountService.getAccountById(1);
        if (result != null) {
            assertEquals(1, result.getId());
        }
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> result = accountService.getAllAccounts();
        assertEquals(accounts, result);
    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account();
        account.setId(1);
        when(accountRepository.update(account)).thenReturn(account);

        Account result = accountService.updateAccount(account);
        if (result != null) {
            assertEquals(1, result.getId());
        }
    }

    // Test get account that balance greater than 100.000.000
    @Test
    public void testGetAccountByBalance() {
        List<Account> accounts = new ArrayList<>();
        when(accountRepository.findByMinBalance(100000000)).thenReturn(accounts);

        List<Account> result = accountService.getAccountByBalance(100000000);
        assertEquals(accounts, result);
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setId(1);
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.createAccount(account);
        assertEquals(1, result.getId());
    }

    @Test
    public void testCreateAccountWithMissingFields() {
        Account account = new Account();
        assertThrows(IllegalStateException.class, () -> accountService.createAccount(account));
    }

    @Test
    public void testDeleteAccount() {
        doNothing().when(accountRepository).delete(1);
        accountService.deleteAccount(1);
        verify(accountRepository, times(1)).delete(1);
    }
}