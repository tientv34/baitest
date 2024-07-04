/*

 */

package tientv.assingmentsummer.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tientv.assingmentsummer.entity.Product;

import java.util.List;

//Query into the database
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //Truy vấn thêm mới 1 sản phẩm
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Product (name, description, price, stock_quantity) VALUES (:name, :description, :price, :stockQuantity)", nativeQuery = true)
    void addProduct(String name, String description, Double price, Integer stockQuantity);

    //Tìm sản phẩm theo ID
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findProductById(Long id);

    //Update một sản phẩm.
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.description = :description, p.price = :price, p.stockQuantity = :stockQuantity WHERE p.id = :id")
    void updateProduct(Long id, String description, Double price, Integer stockQuantity);

    //Xóa 1 sản phẩm
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteProductById(Long id);

    //Lấy danh sách sản phẩm
    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    //Tìm kiếm sản phẩm theo name hoặc mô tả
    @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%', :keyword, '%')) OR lower(p.description) LIKE lower(concat('%', :keyword, '%'))")
    List<Product> searchProductsByNameOrDescription(String keyword);
}
