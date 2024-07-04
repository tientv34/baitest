package tientv.assingmentsummer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tientv.assingmentsummer.common.ResponseObject;
import tientv.assingmentsummer.entity.Product;
import tientv.assingmentsummer.exception.CustomExceptionHandler;
import tientv.assingmentsummer.service.IProductService;

import java.util.List;

@Controller
@RequestMapping("api/v1/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private IProductService productService;

    //API get all product
    @GetMapping("/lst_products")
    public ResponseEntity<ResponseObject> getAllProducts() throws CustomExceptionHandler {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "OK", "Get All Product successfully", products
                    )
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

    //API get product by ID
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query Product successfully", product)
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

    //Api add new 1 product
    @PostMapping("/add_product")
    ResponseEntity<ResponseObject> addProduct(@RequestBody Product product) {
        try {
            Boolean result = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Add Product successfully")
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

    //api update 1 product by ID and information product
    @PutMapping("/update_product/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product prd = productService.updateProduct(id, product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Update Product successfully", prd)
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

    //API delete 1 product by ID
    @DeleteMapping("/delete_prd/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) throws CustomExceptionHandler {
        try {
            Boolean result = productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Delete Product successfully " + id)
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

    //API search product by name or description
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchProduct(@RequestParam(name = "keyword") String keyword) {
        try {
            List<Product> products = productService.getProductsByName(keyword);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "OK", "Get All Product successfully", products
                    )
            );
        } catch (CustomExceptionHandler ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "OK", ex.getMessage(), ""
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
