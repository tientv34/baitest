package tientv.assingmentsummer.service;

import tientv.assingmentsummer.entity.Product;
import tientv.assingmentsummer.exception.CustomExceptionHandler;
import java.util.List;

//Declare interface functions
public interface IProductService {
    Boolean addProduct(Product product) throws CustomExceptionHandler;

    Product getProductById(Long id) throws CustomExceptionHandler;

    Product updateProduct(Long id, Product product) throws CustomExceptionHandler;

    Boolean deleteProduct(Long id) throws CustomExceptionHandler;

    List<Product> getAllProducts() throws CustomExceptionHandler;

    List<Product> getProductsByName(String name) throws CustomExceptionHandler;
}
