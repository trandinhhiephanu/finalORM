package fa.training.repositories;

import fa.training.entities.EPassbook;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EPassbookRepository {

    public EPassbook findById(int id) {
        try (Session session = HibernateUtils.getInstance().openSession()) {
            return session.get(EPassbook.class, id);
        }
    }

    public List<EPassbook> findAll() {
        try (Session session = HibernateUtils.getInstance().openSession()) {
            return session.createQuery("FROM EPassbook", EPassbook.class).list();
        }
    }

    public EPassbook save(EPassbook ePassbook) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            session.save(ePassbook);
            transaction.commit();
            return ePassbook;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public EPassbook update(EPassbook ePassbook) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            session.update(ePassbook);
            transaction.commit();
            return ePassbook;
        } catch (Exception e) {
            if (transaction != null) {
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
            EPassbook ePassbook = session.get(EPassbook.class, id);
            if (ePassbook != null) {
                session.delete(ePassbook);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}