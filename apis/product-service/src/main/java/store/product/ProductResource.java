package store.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<ProductOut> create(ProductIn productIn) {
        Product created_product = productService.create(ProductParser.to(productIn));
        return ResponseEntity.ok()
            .body(ProductParser.to(created_product));
    }

    @Override
    public ResponseEntity<List<ProductOut>> findAll() {
        return ResponseEntity
            .ok()
            .body(productService.findAll().stream().map(ProductParser::to).toList());
    }

    @Override
    public ResponseEntity<ProductOut> findById(String idProduct) {
        return ResponseEntity.ok().body(ProductParser.to(productService.findById(idProduct)));
    }
    @Override
    public ResponseEntity<Void> deleteById(String idProduct) {
        productService.deleteById(idProduct);
        return ResponseEntity.noContent().build();
    }
    
}
