package com.adrian.crud_springboot.repository;

import com.adrian.crud_springboot.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * La @Repository anotación es un marcador para cualquier clase que cumpla el rol de un repositorio
 * (también conocido como Objeto de Acceso a Datos o DAO).
 * */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /*
    * @JpaRepository es particularmente una extensión JPA específica para Repository . Tiene API CrudRepository y
    * PagingAndSortingRepository completos. Básicamente, Jpa Repository contiene las API para operaciones CRUD básicas,
    * las API para paginación y las API para clasificación.
    * */

    // Este metodo es personalizado
    Optional<Product> findProductByName(String name);
}
