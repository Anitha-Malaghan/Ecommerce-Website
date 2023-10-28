package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Long> {
    Categories save(Categories categories);
    Categories findById(long id);


    //custom queries
    @Query(value = "SELECT c.name FROM Categories c WHERE c.id= :id")
    String findCategoryNameById(@Param("id") long id);
    //Another way
    @Query(value = "SELECT c.name FROM Categories c WHERE c.id=?1")
    String findCategoryNameByIdV1(long id);


}
