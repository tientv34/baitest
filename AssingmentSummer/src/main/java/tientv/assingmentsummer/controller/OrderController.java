package tientv.assingmentsummer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tientv.assingmentsummer.common.ResponseObject;
import tientv.assingmentsummer.dto.OrderDTO;
import tientv.assingmentsummer.entity.Orders;
import tientv.assingmentsummer.exception.CustomExceptionHandler;
import tientv.assingmentsummer.service.IOrderService;

import java.util.List;

@Controller
@RequestMapping("api/v1/oder")
@CrossOrigin
public class OrderController {
    @Autowired
    private IOrderService orderService;

    //Api get all list order
    @GetMapping("/lst_oder")
    public ResponseEntity<ResponseObject> getAllOrders(){
        try {
            List<Orders> lst_order = orderService.getAllOrders();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(
                            "OK", "Get all order successfully", lst_order
                    )
            );
        } catch (CustomExceptionHandler ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "FAILED", ex.getMessage(), null
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "FAILED", "system error", null
                    )
            );
        }
    }

    //API get one order by ID
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getOrderById(@PathVariable Long id){
        try {
            Orders orders = orderService.getOrderByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query Order successfully", orders)
            );
        } catch (CustomExceptionHandler ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", ex.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "FAILED", "system error", null
                    )
            );
        }
    }

    //API add one 1 Order
    @PostMapping("/add_orders")
    ResponseEntity<ResponseObject> addOrder(@RequestBody OrderDTO orderRequest){
        try {
            Orders orders1 = orderService.createOder(orderRequest);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Create Order successfully", orders1)
            );
        } catch (CustomExceptionHandler ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", ex.getMessage() ,null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "FAILED", "system error", null
                    )
            );
        }
    }

    //API update one order and orderdetails
    @PutMapping("/update_orders/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody OrderDTO orderRequest){
        try {
            Orders result = orderService.updateOrder(id, orderRequest);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Update orders successfully", result)
            );
        } catch (CustomExceptionHandler ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", ex.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "FAILED", "system error", null
                    )
            );
        }
    }

    //API Search order by id order or name customer
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchProduct(@RequestParam(name = "keyword") String keyword){
        try {
            List<Orders> lst_orders = orderService.getOrdersByIDOrName(keyword);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "OK", "Get Order successfully", lst_orders
                    )
            );
        } catch (CustomExceptionHandler ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "FAILED", ex.getMessage(), ""
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "FAILED", "system error", null
                    )
            );
        }
    }
}
