package com.sg.kafka.repository;

import com.sg.kafka.entity.LoanDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanDO, Long> {
}
