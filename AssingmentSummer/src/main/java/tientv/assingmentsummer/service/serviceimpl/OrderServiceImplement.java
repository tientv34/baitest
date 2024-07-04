package tientv.assingmentsummer.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tientv.assingmentsummer.dto.OrderDTO;
import tientv.assingmentsummer.dto.ProductDTO;
import tientv.assingmentsummer.entity.Orders;
import tientv.assingmentsummer.entity.OrderDetails;
import tientv.assingmentsummer.entity.Product;
import tientv.assingmentsummer.exception.CustomExceptionHandler;
import tientv.assingmentsummer.repository.OrderDetailsRepository;
import tientv.assingmentsummer.repository.OrderRepository;
import tientv.assingmentsummer.repository.ProductRepository;
import tientv.assingmentsummer.service.IOrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Execute service functions IOrderService
@Service
public class OrderServiceImplement implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    //Method add one Order
    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class}, timeout = 50)
    public Orders createOder(OrderDTO orderDTO) throws CustomExceptionHandler {
        Double totalAmount = 0.0;

        Orders order = new Orders();
        if (orderDTO == null) {
            throw new CustomExceptionHandler("Orders can't Null");
        }
        if (orderDTO.getCustomerName() == null || orderDTO.getAddress() == null || orderDTO.getPhone() == null) {
            throw new CustomExceptionHandler("Please fill in all necessary information");
        }
        //Format yyyy-MM-dd
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        LocalDate localDate = LocalDate.parse(formattedDate, formatter);
        LocalDateTime formattedLocalDateTime = localDate.atStartOfDay();
        //Setup create one Order
        order.setOrderDate(formattedLocalDateTime);
        order.setStatus("Order Success");
        order.setCustomerName(orderDTO.getCustomerName());
        order.setAddress(orderDTO.getAddress());
        order.setPhone(orderDTO.getPhone());
        order.setEmail(orderDTO.getEmail());
        order.setTotalAmount(totalAmount);
        Orders order1 = orderRepository.save(order);
        //Add product for oder details.
        if (order1 != null) {
            for (ProductDTO pDTO : orderDTO.getLstProduct()) {
                Product pEntity = productRepository.findProductById(pDTO.getId());
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrder(order1);
                orderDetails.setProduct(pEntity);
                orderDetails.setQuantity(pDTO.getQuantity());
                orderDetails.setUnitPrice(pEntity.getPrice());
                orderDetailsRepository.save(orderDetails);
                totalAmount += pDTO.getQuantity() * pDTO.getPrice();
            }
        }
        order1.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    @Override
    public Orders getOrderByID(Long idOrder) throws CustomExceptionHandler {
        if (idOrder < 0) {
            throw new CustomExceptionHandler("Invalid Orders id");
        }
        return orderRepository.findById(idOrder).get();
    }

    @Override
    public Orders updateOrder(Long id, OrderDTO orderRequest) throws CustomExceptionHandler {
        Orders order = getOrderByID(id);
        if (orderRequest == null) {
            throw new CustomExceptionHandler("Orders can't Null");
        }
        if (orderRequest.getCustomerName() == null || orderRequest.getAddress() == null || orderRequest.getPhone() == null) {
            throw new CustomExceptionHandler("Please fill in all necessary information");
        }
        //Format yyyy-MM-dd
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        LocalDate localDate = LocalDate.parse(formattedDate, formatter);
        LocalDateTime formattedLocalDateTime = localDate.atStartOfDay();

        //Setup update one Order
        order.setOrderDate(formattedLocalDateTime);
        order.setCustomerName(orderRequest.getCustomerName());
        order.setAddress(orderRequest.getAddress());
        order.setPhone(orderRequest.getPhone());
        order.setEmail(orderRequest.getEmail());
        order.setStatus(orderRequest.getStatus());
        Orders order1 = orderRepository.save(order);
        //Thêm sản phẩm vào oder details.
        Double totalAmount = 0.0;
        if (order1 != null) {
            for (ProductDTO pDTO : orderRequest.getLstProduct()) {
                Product pEntity = productRepository.findProductById(pDTO.getId());
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrder(order1);
                orderDetails.setProduct(pEntity);
                orderDetails.setQuantity(pDTO.getQuantity());
                orderDetails.setUnitPrice(pEntity.getPrice());
                orderDetailsRepository.save(orderDetails);
                totalAmount += pDTO.getQuantity() * pDTO.getPrice();
            }
        }
        order1.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> getAllOrders() throws CustomExceptionHandler{
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> getOrdersByIDOrName(String text) throws CustomExceptionHandler {
        if (text == null) {
            return getAllOrders();
        }
        List<Orders> lst_orders = new ArrayList<>();
        lst_orders = orderRepository.findByCustomerName(text);
        try {
            Long number = Long.parseLong(text);
            Optional<Orders> order = orderRepository.findById(number);
            lst_orders.add(order.get());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Orders id");
        }
        return lst_orders;
    }
}
