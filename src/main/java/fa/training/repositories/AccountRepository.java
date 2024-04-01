package fa.training.repositories;

import fa.training.entities.Account;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AccountRepository {

    public Account findById(int id) {
        try (Session session = HibernateUtils.getInstance().openSession()) {
            return session.get(Account.class, id);
        }
    }

    public List<Account> findAll() {
        try (Session session = HibernateUtils.getInstance().openSession()) {
            return session.createQuery("FROM Account", Account.class).list();
        }
    }

    public Account findByAccountNumber(String accountNumber) {
        try (Session session = HibernateUtils.getInstance().openSession()) {
            return session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();
        }
    }

    public List<Account> findByMinBalance(double minBalance) {
        try (Session session = HibernateUtils.getInstance().openSession()) {
            return session.createQuery("FROM Account WHERE balance >= :minBalance", Account.class)
                    .setParameter("minBalance", minBalance)
                    .list();
        }
    }

    public Account save(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            return account;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public Account update(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            session.update(account);
            transaction.commit();
            return account;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, id);
            if (account != null) {
                session.delete(account);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}