package com.kh.product.domain.svc;

import com.kh.product.domain.dao.ProductDAO;
import com.kh.product.domain.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

  //주입
  private final ProductDAO productDAO;

  //등록
  @Override
  public ProductDTO write(ProductDTO productDTO) {
    return productDAO.insert(productDTO);
  }

  //조회
  @Override
  public ProductDTO findByProductId(Long productId) {
    return productDAO.selectOne(productId);
  }

  //전체조회
  @Override
  public List<ProductDTO> findAll() {
    return productDAO.selectAll();
  }

  //수정
  @Override
  public ProductDTO modify(Long productId, ProductDTO productDTO) {
    return productDAO.update(productId, productDTO);
  }

  //삭제
  @Override
  public int remove(Long productId) {
    return productDAO.delete(productId);
  }
}
