package com.kh.product.domain.dto;

import lombok.Data;

@Data
public class ProductDTO {
  private Long productId; //product_id  number,       --상품ID
  private String name;    //name        varchar2(50), --상품명
  private Long quantity;  //quantity    number,       --상품수량
  private Long price;     //price       number(10)    --상품가격
}
