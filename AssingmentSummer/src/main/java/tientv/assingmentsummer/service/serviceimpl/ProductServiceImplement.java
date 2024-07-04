package tientv.assingmentsummer.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tientv.assingmentsummer.entity.OrderDetails;
import tientv.assingmentsummer.entity.Product;
import tientv.assingmentsummer.exception.CustomExceptionHandler;
import tientv.assingmentsummer.repository.OrderDetailsRepository;
import tientv.assingmentsummer.repository.ProductRepository;
import tientv.assingmentsummer.service.IProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Execute service functions IProductService
@Service
public class ProductServiceImplement implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    //Method create Product
    @Override
    public Boolean addProduct(Product product) throws CustomExceptionHandler {
        if (product == null){
            throw new CustomExceptionHandler("Product is not null");
        }
        if (product.getPrice() <= 0){
            throw new CustomExceptionHandler("Product price cannot be less than 0");
        }
        productRepository.addProduct(product.getName(), product.getDescription(), product.getPrice(), product.getStockQuantity());
        return true;
    }

    @Override
    public Product getProductById(Long id) throws CustomExceptionHandler {
        if (id < 0){
            throw new CustomExceptionHandler("Invalid product id");
        }
        Product product = new Product();
        product = productRepository.findProductById(id);
        if (product == null){
            throw new CustomExceptionHandler("Can't find a valid product");
        }
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws CustomExceptionHandler {
        if (id < 0){
            throw new CustomExceptionHandler("Invalid product id");
        }
        Product oldProduct = getProductById(id);
        if (oldProduct == null) {
            throw new CustomExceptionHandler("Can't find a valid product");
        }
        //Setup for update product
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStockQuantity(product.getStockQuantity());
        productRepository.updateProduct(product.getId(), product.getDescription(), product.getPrice(), product.getStockQuantity());
        return oldProduct;
    }

    @Override
    public Boolean deleteProduct(Long id) throws CustomExceptionHandler {
        if (id < 0){
            throw new CustomExceptionHandler("Invalid product id");
        }
        Product oldProduct = getProductById(id);
        if (oldProduct == null) {
            throw new CustomExceptionHandler("Can't find a valid product");
        }
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findByProductId(id);
        if (orderDetails.get() != null){
            throw new CustomExceptionHandler("Can't delete it because it already exists somewhere else");
        }
        productRepository.deleteProductById(oldProduct.getId());
        return true;
    }

    @Override
    public List<Product> getAllProducts() throws CustomExceptionHandler {
        List<Product> lst_products = new ArrayList<>();
        lst_products = productRepository.findAll();
        return lst_products;
    }

    @Override
    public List<Product> getProductsByName(String name) throws CustomExceptionHandler {
        List<Product> lst_products = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            return getAllProducts();
        }
        lst_products = productRepository.searchProductsByNameOrDescription(name);
        return lst_products;
    }
}
