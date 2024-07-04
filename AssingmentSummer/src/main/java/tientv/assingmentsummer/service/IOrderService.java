package tientv.assingmentsummer.service;

import tientv.assingmentsummer.dto.OrderDTO;
import tientv.assingmentsummer.entity.Orders;
import tientv.assingmentsummer.exception.CustomExceptionHandler;

import java.util.List;

//Declare interface functions
public interface IOrderService {
    Orders createOder(OrderDTO orderDTO) throws CustomExceptionHandler;

    Orders getOrderByID(Long idOrder) throws CustomExceptionHandler;

    Orders updateOrder(Long id, OrderDTO orderDTO) throws CustomExceptionHandler;

    List<Orders> getAllOrders() throws CustomExceptionHandler;

    List<Orders> getOrdersByIDOrName(String text) throws CustomExceptionHandler;
}
