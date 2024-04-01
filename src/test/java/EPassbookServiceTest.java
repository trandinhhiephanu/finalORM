import fa.training.entities.Account;
import fa.training.entities.EPassbook;
import fa.training.entities.enums.Term;
import fa.training.repositories.AccountRepository;
import fa.training.repositories.EPassbookRepository;
import fa.training.services.EPassbookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EPassbookServiceTest {

    @Mock
    private EPassbookRepository ePassbookRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private EPassbookService ePassbookService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEPassbookById() {
        EPassbook ePassbook = new EPassbook();
        ePassbook.setId(1);
        when(ePassbookRepository.findById(1)).thenReturn(ePassbook);

        EPassbook result = ePassbookService.getEPassbookById(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testCreateEPassbook() {
        EPassbook ePassbook = new EPassbook();
        ePassbook.setId(1);
        when(ePassbookRepository.save(ePassbook)).thenReturn(ePassbook);

        EPassbook result = ePassbookService.createEPassbook(ePassbook);
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetAllEPassbooks() {
        List<EPassbook> ePassbooks = new ArrayList<>();
        when(ePassbookRepository.findAll()).thenReturn(ePassbooks);

        List<EPassbook> result = ePassbookService.getAllEPassbooks();
        assertEquals(ePassbooks, result);
    }

    @Test
    public void testUpdateEPassbook() {
        EPassbook ePassbook = new EPassbook();
        ePassbook.setId(1);
        when(ePassbookRepository.update(ePassbook)).thenReturn(ePassbook);

        EPassbook result = ePassbookService.updateEPassbook(ePassbook);
        assertEquals(1, result.getId());
    }

    @Test
    public void testDeleteEPassbook() {
        doNothing().when(ePassbookRepository).delete(1);
        ePassbookService.deleteEPassbook(1);
        verify(ePassbookRepository, times(1)).delete(1);
    }

    @Test
    public void testCreateNewSavings() {
        Account account = new Account();
        account.setAccountNumber("1234567890");
        account.setBalance(1000);

        EPassbook ePassbook = new EPassbook();
        ePassbook.setId(1);
        ePassbook.setAccount(account);
        ePassbook.setDepositedAmount(500);
        ePassbook.setTerm(Term.ONE);

        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account);
        when(ePassbookRepository.save(any(EPassbook.class))).thenReturn(ePassbook);

        EPassbook result = ePassbookService.createNewSavings("1234567890", 500, 1);
        assertNotNull(result, "The result should not be null");
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetPassbookDetail() {
        Account account = new Account();
        account.setAccountNumber("1234567890");
        account.setFirstName("Duy");
        account.setLastName("Nguyen");

        EPassbook ePassbook = new EPassbook();
        ePassbook.setId(1);
        ePassbook.setAccount(account);
        ePassbook.setDepositedAmount(500);
        ePassbook.setStartDate(LocalDate.now());
        ePassbook.setTerm(Term.ONE);
        ePassbook.setMaturityDate(LocalDate.now().plus(1, ChronoUnit.MONTHS));

        when(ePassbookRepository.findById(1)).thenReturn(ePassbook);

        List<Object[]> result = ePassbookService.getPassbookDetail(1);
        assertNotNull(result, "The result should not be null");
        assertEquals(1, result.size());
        assertEquals("1234567890", result.get(0)[0]);
        assertEquals("Nguyen Duy", result.get(0)[1]);
        assertEquals(500.0, result.get(0)[2]);
        assertEquals(LocalDate.now(), result.get(0)[3]);
        assertEquals(Term.ONE, result.get(0)[4]);
        assertEquals(LocalDate.now().plus(1, ChronoUnit.MONTHS), result.get(0)[5]);
    }
}