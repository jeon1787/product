package com.kh.product.domain.svc;

import com.kh.product.domain.dto.ProductDTO;

import java.util.List;

public interface ProductSVC {

  //등록
  ProductDTO write(ProductDTO productDTO);

  //조회
  ProductDTO findByProductId(Long productId);

  //전체조회
  List<ProductDTO> findAll();

  //수정
  ProductDTO modify(Long productId, ProductDTO productDTO);

  //삭제
  int remove(Long productId);
}
