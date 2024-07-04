package tientv.assingmentsummer.dto;

import jakarta.persistence.Column;
import tientv.assingmentsummer.entity.Orders;
import tientv.assingmentsummer.entity.Product;

import java.util.List;

// Order request from client
public class OrderDTO {
    private String customerName;
    private String address;
    private String email;
    private String phone;
    private String status;
    private Double totalAmount;

    private List<ProductDTO> lstProduct;

    public OrderDTO() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductDTO> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<ProductDTO> lstProduct) {
        this.lstProduct = lstProduct;
    }
}
