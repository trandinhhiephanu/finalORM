package fa.training.services;

import fa.training.entities.Account;
import fa.training.entities.EPassbook;
import fa.training.entities.enums.Term;
import fa.training.repositories.AccountRepository;
import fa.training.repositories.EPassbookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EPassbookService {

    private final EPassbookRepository ePassbookRepository = new EPassbookRepository();
    private final AccountRepository accountRepository = new AccountRepository();

    public EPassbook getEPassbookById(int id) {
        return ePassbookRepository.findById(id);
    }

    public List<EPassbook> getAllEPassbooks() {
        return ePassbookRepository.findAll();
    }

    public EPassbook createEPassbook(EPassbook ePassbook) {
        return ePassbookRepository.save(ePassbook);
    }

    public EPassbook updateEPassbook(EPassbook ePassbook) {
        return ePassbookRepository.update(ePassbook);
    }

    public void deleteEPassbook(int id) {
        ePassbookRepository.delete(id);
    }


    // Question a: create and save a new passbook
    public EPassbook createNewSavings(String accountNumber, double depositedAmount, int term) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null && account.getBalance() >= depositedAmount) {
            EPassbook ePassbook = new EPassbook();
            ePassbook.setAccount(account);
            ePassbook.setDepositedAmount(depositedAmount);
            ePassbook.setTerm(Term.values()[term]);
            ePassbook.setStartDate(LocalDate.now());
            ePassbook.setMaturityDate(LocalDate.now().plus(term, ChronoUnit.MONTHS));
            return ePassbookRepository.save(ePassbook);
        }
        return null;
    }


    // Question b: get details of a passbook by passbook id
    public List<Object[]> getPassbookDetail(int passbookId) {
        List<Object[]> passbookDetails = new ArrayList<>();
        EPassbook ePassbook = ePassbookRepository.findById(passbookId);
        if (ePassbook != null) {
            String accountNumber = ePassbook.getAccount().getAccountNumber();
            String fullName = ePassbook.getAccount().getLastName() + " " + ePassbook.getAccount().getFirstName();
            double depositedAmount = ePassbook.getDepositedAmount();
            LocalDate startDate = ePassbook.getStartDate();
            Term term = ePassbook.getTerm();
            LocalDate maturityDate = ePassbook.getMaturityDate();
            double estimatedInterest = calculateInterest(depositedAmount, term);
            passbookDetails.add(new Object[]{accountNumber, fullName, depositedAmount, startDate, term, maturityDate, estimatedInterest});
        }
        return passbookDetails;
    }

    private double calculateInterest(double depositedAmount, Term term) {
        double interestRate;
        switch (term) {
            case ONE:
                interestRate = 4.55;
                break;
            case TWO:
                interestRate = 4.65;
                break;
            case THREE:
                interestRate = 4.75;
                break;
            case SIX:
                interestRate = 6.2;
                break;
            case NINE:
                interestRate = 6.2;
                break;
            case TWELVE:
                interestRate = 6.4;
                break;
            case EIGHTEEN:
                interestRate = 6.7;
                break;
            case TWENTY_FOUR:
                interestRate = 6.7;
                break;
            case THIRTY_SIX:
                interestRate = 6.7;
                break;
            default:
                interestRate = 0;
        }
        return depositedAmount * (interestRate / 100) / 12 * term.getValue();
    }
}