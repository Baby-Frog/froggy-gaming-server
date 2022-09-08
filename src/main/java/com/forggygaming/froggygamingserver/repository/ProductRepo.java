package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Page<Product> findAll(Pageable pageable);

    Product findProductByProName(String proName);

    Product findProductByProId(Long proId);

    @Query("SELECT pro FROM Product pro ORDER BY pro.proName ASC")
    List<Product> ascSortProductsByProName();

    @Query("SELECT pro FROM Product pro ORDER BY pro.proName DESC")
    List<Product> descSortProductsByProName();

    @Query("SELECT pro FROM Product pro ORDER BY pro.proPrice ASC")
    List<Product> ascSortProductsByProPrice();

    @Query("SELECT pro FROM Product pro ORDER BY pro.proPrice DESC")
    List<Product> descSortProductsByProPrice();

    @Query("SELECT pro FROM Product pro ORDER BY pro.createdAt ASC")
    List<Product> ascSortProductsByCreatedAt();

    @Query("SELECT pro FROM Product pro ORDER BY pro.createdAt DESC ")
    List<Product> descSortProductsByCreatedAt();

    @Query("SELECT pro FROM Product pro WHERE pro.proPrice >= ?1 AND pro.proPrice <= ?2")
    List<Product> findProductsInProPriceZone(Double proPriceMin, Double proPriceMax);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LIKE %?1%")
    Page<Product> findProductsByProName(String proName, Pageable pageable);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LiKE %?1% ORDER BY pro.proPrice ASC")
    List<Product> getProductsListAndAscSortByProPrice(String proName);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LiKE %?1% ORDER BY pro.proPrice DESC")
    List<Product> getProductsListAndDescSortByProPrice(String proName);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LiKE %?1% ORDER BY pro.createdAt ASC")
    List<Product> getProductsListAndAscSortByProDate(String proName);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LiKE %?1% ORDER BY pro.createdAt DESC")
    List<Product> getProductsListAndDescSortByProDate(String proName);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LiKE %?1% ORDER BY pro.proName ASC")
    List<Product> getProductsListAndAscSortByProName(String proName);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LiKE %?1% ORDER BY pro.proName DESC")
    List<Product> getProductsListAndDescSortByProName(String proName);

    @Query("SELECT pro FROM Product pro WHERE pro.category.cateId = ?1")
    List<Product> productListByCateId(Long cateId);

    @Query("SELECT pro FROM Product pro WHERE pro.brand.id = ?1")
    List<Product> productListByBrandId(Long brandId);
}
