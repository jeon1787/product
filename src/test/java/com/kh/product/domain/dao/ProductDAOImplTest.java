package com.kh.product.domain.dao;

import com.kh.product.domain.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

  //주입
  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("상품등록")
  void insert() {
    //등록 전
    int before = productDAO.selectAll().size();
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName("헤드폰");
    productDTO.setQuantity(20L);
    productDTO.setPrice(160000L);

    //등록 후 전체목록 수가 1증가했는지
    productDAO.insert(productDTO);
    int after = productDAO.selectAll().size();
    Assertions.assertThat(after - before).isEqualTo(1);
  }

  @Test
  @DisplayName("상품조회")
  void selectOne() {
    ProductDTO productDTO = productDAO.selectOne(1L);
    Assertions.assertThat(productDTO.getProductId()).isEqualTo(1);
    Assertions.assertThat(productDTO.getName()).isEqualTo("mouse");
    Assertions.assertThat(productDTO.getQuantity()).isEqualTo(50);
    Assertions.assertThat(productDTO.getPrice()).isEqualTo(10000);
  }

  @Test
  @DisplayName("전체조회")
  void selectAll() {
    Assertions.assertThat(productDAO.selectAll().size()).isEqualTo(3);
  }

  @Test
  @DisplayName("상품수정")
  void update() {
    //수정 전
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName("mouse(수정)");
    productDTO.setQuantity(120L);
    productDTO.setPrice(11000L);

    //수정 후
    ProductDTO modifiedProduct = productDAO.update(1L, productDTO);
    ProductDTO findedProduct = productDAO.selectOne(1L);
    Assertions.assertThat(findedProduct.getProductId()).isEqualTo(1L);
    Assertions.assertThat(findedProduct.getName()).isEqualTo("mouse(수정)");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(120L);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(11000L);
  }

  @Test
  @DisplayName("상품삭제")
  void delete() {
    //삭제 전
    int before = productDAO.selectAll().size();
    Long productId = 4L;
    
    //삭제 후 전체목록 수가 1감소했는지
    productDAO.delete(productId);
    int after = productDAO.selectAll().size();
    Assertions.assertThat(before - after).isEqualTo(1);
  }








//  @Test
//  @DisplayName("목록")
//  @Order(2)
//  void findAll() {
//    Assertions.assertThat(store.findAll().size()).isEqualTo(3);



}