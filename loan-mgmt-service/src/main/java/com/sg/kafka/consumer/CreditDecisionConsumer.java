package com.sg.kafka.consumer;

import com.sg.kafka.constants.LoanStatus;
import com.sg.kafka.entity.LoanDO;
import com.sg.kafka.events.CreditDecisionEvent;
import com.sg.kafka.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditDecisionConsumer {


    private final LoanRepository repository;


    @KafkaListener(topics = "credit-decision-topic", groupId = "credit-loan-group")
    public void consumeCreditDecisionEvent(CreditDecisionEvent event) {
        log.info("CreditDecisionConsumer::consumeCreditDecisionEvent received credit decision event {}", event);
        LoanDO loan = repository.findById(event.getLoanId()).orElse(null);
        if (loan != null) {
            if (event.isApproved()) {
                loan.setStatus(LoanStatus.APPROVED);
                log.info("CreditDecisionConsumer - LoanId: {} marked as APPROVED. Proceeding with disbursement logic.", loan.getLoanId());
            } else {
                loan.setStatus(LoanStatus.REJECTED);
                log.info("CreditDecisionConsumer - LoanId: {} marked as REJECTED. No further action required ", loan.getLoanId());

            }
            repository.save(loan);
        }
    }
}
