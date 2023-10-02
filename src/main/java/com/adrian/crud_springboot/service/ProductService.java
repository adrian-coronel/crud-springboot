package com.adrian.crud_springboot.service;

import com.adrian.crud_springboot.models.Product;
import com.adrian.crud_springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// Esta es mi capa de servicio
@Service
public class ProductService {

    HashMap<String, Object> datos;

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
        // COMPROBA SI el producto ya EXISTE
        Optional<Product> resp = productRepository.findProductByName(product.getName());
        datos = new HashMap<>();

        // Si el producto ya existe y no se está tratando de actualizar
        if (resp.isPresent() && product.getId() == null) {
            datos.put("error",  true);
            datos.put("message", "Ya existe un producto con ese nombre");
            return new ResponseEntity<>(
                      datos,
                    HttpStatus.CONFLICT // Estado: 409
            );
        }
        datos.put("message", "Se guardo con exito");

        if (product.getId() != null) // si se esta intentando ACTUALIZAR
            datos.put("message", "Se actualizó con exito");

        this.productRepository.save(product);
        datos.put("data",  product);
        return new ResponseEntity<>( datos, HttpStatus.CREATED );
    }

    public ResponseEntity<Object> deleteProduct(Long id){
        datos = new HashMap<>();
        boolean exists = this.productRepository.existsById(id);

        if (!exists){
            datos.put("error",  true);
            datos.put("message", "No existe el producto con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT // Estado: 409
            );
        }

        this.productRepository.deleteById(id);
        datos.put("message", "Producto eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED // Estado: 409
        );
    }

}
