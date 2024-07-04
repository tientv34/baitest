package tientv.assingmentsummer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tientv.assingmentsummer.entity.OrderDetails;

import java.util.Optional;

//Query into the database
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    Optional<OrderDetails> findByProductId(Long productId);
}
