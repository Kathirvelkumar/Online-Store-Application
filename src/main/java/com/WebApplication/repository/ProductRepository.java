package com.WebApplication.repository;

import com.WebApplication.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    @Query(value = "select p from Products p order by p.price desc limit 5" )
    List<Products> top5Products ();
}
