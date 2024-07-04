package tientv.assingmentsummer.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tientv.assingmentsummer.entity.Orders;
import java.time.LocalDateTime;
import java.util.List;

//Query into the database
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `Order` (customer_name, address, email, phone, status, total_amount, order_date) " +
            "VALUES (:customerName, :address, :email, :phone, :status, :totalAmount, :orderDate)", nativeQuery = true)
    void insertOrder(@Param("customerName") String customerName,
                      @Param("address") String address,
                      @Param("email") String email,
                      @Param("phone") String phone,
                      @Param("status") String status,
                      @Param("totalAmount") Double totalAmount,
                      @Param("orderDate") LocalDateTime orderDate);

    @Modifying
    @Query("UPDATE Orders o SET o.customerName = :customerName, o.address = :address, o.email = :email, o.phone = :phoneNumber, o.status = :status, o.totalAmount = :totalAmount, o.orderDate = :orderDate WHERE o.id = :id")
    void updateOrder(@Param("id") Long id,
                     @Param("customerName") String customerName,
                     @Param("address") String address,
                     @Param("email") String email,
                     @Param("phoneNumber") String phoneNumber,
                     @Param("status") String status,
                     @Param("totalAmount") Double totalAmount,
                     @Param("orderDate") LocalDateTime orderDate);

    @Query("SELECT o FROM Orders o")
    List<Orders> findAllOrders();

    @Query("SELECT o FROM Orders o WHERE o.customerName LIKE %:customerName%")
    List<Orders> findByCustomerName(@Param("customerName") String customerName);

}
