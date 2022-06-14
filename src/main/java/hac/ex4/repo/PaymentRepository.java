package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The payment repository.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
