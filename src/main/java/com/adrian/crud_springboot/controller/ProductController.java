package com.adrian.crud_springboot.controller;

import com.adrian.crud_springboot.models.Product;
import com.adrian.crud_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Esta anotación se aplica a una clase para marcarla como controlador de solicitudes.
@RestController
// La @RequestMappinganotación proporciona información de "enrutamiento".
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    // @Autowired se utiliza para la INYECCION DE DEPENDENCIAS AUTOMATICA
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /**
     * @GetMapping es una anotacion compuesta, se usa porque los metodos del controlador deben asignarse
     * a un método HTTP específico en lugar de usar @RequestMappingdel
     * */
    @GetMapping
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registerProduct(@RequestBody Product product){
        // @RequestBody indica que Spring debe deserializar el cuerpo de una solicitud en un objeto
        return this.productService.newProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        // @RequestBody indica que Spring debe deserializar el cuerpo de una solicitud en un objeto
        return this.productService.newProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") Long id){ // productId, pasará a ser "id" del parametro
        // @RequestBody indica que Spring debe deserializar el cuerpo de una solicitud en un objeto
        return this.productService.deleteProduct(id);
    }
}
