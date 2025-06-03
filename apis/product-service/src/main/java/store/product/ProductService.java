package store.product;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findById(String id) {
        return productRepository.findById(id).get().to();
    }

    public Product create(Product product) {
        ProductModel productModel = new ProductModel();
        // generating the id
        productModel.name(product.name());
        productModel.price(product.price());
        productModel.unit(product.unit());

        // Saving the product
        return productRepository.save(productModel).to();
    }



    public List<Product> findAll() {
        return StreamSupport
            .stream(productRepository.findAll().spliterator(), false)
            .map(ProductModel::to)
            .toList();
    }

    public void deleteById(String id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productRepository.deleteById(id);
    }
    
}
