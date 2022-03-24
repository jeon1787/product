package com.kh.product.domain.dao;

import com.kh.product.domain.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {

  /**
   * 등록
   * @param productDTO
   * @return
   */
  ProductDTO insert(ProductDTO productDTO);

  /**
   * 조회
   * @param productId 상품ID
   * @return 
   */
  ProductDTO selectOne(Long productId);

  /**
   * 전체조회
   * @return
   */
  List<ProductDTO> selectAll();

  /**
   * 수정
   * @param productDTO
   * @return
   */
  ProductDTO update(Long productId, ProductDTO productDTO);

  /**
   * 삭제
   * @param productId 상품ID
   * @return 
   */
  int delete(Long productId);
}
