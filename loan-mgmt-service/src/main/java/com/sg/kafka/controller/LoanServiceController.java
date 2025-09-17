package com.sg.kafka.controller;


import com.sg.kafka.entity.LoanDO;
import com.sg.kafka.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Slf4j
public class LoanServiceController {

    private final LoanService loanService;

    @PostMapping("/process")
    public ResponseEntity<String> processLoan(@RequestBody LoanDO loanDO) {
        log.info("LoanController::processLoan received loan application request for userId: {}", loanDO.getUserId());
        LoanDO processLoanApplication = loanService.processLoanApplication(loanDO);
        String responseMessage = String.format("Loan application received, credit check in progress. Transaction ID: %s && Loan ID: %s", processLoanApplication.getLoanTransactionId(), processLoanApplication.getLoanId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseMessage);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getLoanStatus(@RequestParam(required = true) Long loanId) {
        Optional<LoanDO> loanStatus = loanService.getLoanStatusById(loanId);
        return loanStatus.map(loan -> ResponseEntity.ok().body(loan.getStatus()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
