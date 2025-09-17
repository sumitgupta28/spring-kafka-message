package com.sg.kafka.entity;


import com.sg.kafka.constants.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;
    private int userId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;  // PENDING, APPROVED, REJECTED
    private String loanTransactionId;
}
