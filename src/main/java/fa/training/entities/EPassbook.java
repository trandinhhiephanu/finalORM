package fa.training.entities;

import fa.training.entities.enums.Term;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "E_PASSBOOK")
public class EPassbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Account account;

    @Column(name = "deposited_amount", nullable = false, updatable = false)
    private double depositedAmount;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDate startDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "term", nullable = false, updatable = false)
    private Term term;

    @Column(name = "maturity_date", nullable = false, updatable = false)
    private LocalDate maturityDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

}