package com.kh.product.web;

import com.kh.product.domain.dto.ProductDTO;
import com.kh.product.domain.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/api")
public class ProductRestController {

  private final ProductSVC productSVC;

  //등록
  @PostMapping
  public ApiResult<Object> save(@RequestBody ProductDTO productDTO){
    ProductDTO savedProduct = productSVC.write(productDTO);
    return new ApiResult<>("00","success",savedProduct);
  }

  //조회
  @GetMapping("/{productId}")
  public ApiResult<Object> findById(@PathVariable Long productId){
    return new ApiResult<>("00","success",productSVC.findByProductId(productId));
  }
  
  //전체조회
  @GetMapping
  public ApiResult<Object> findAll(){
    List<ProductDTO> list = productSVC.findAll();
    return new ApiResult<>("00","success",list);
  }
  
  //수정
  @PatchMapping("/{productId}")
  public ApiResult<Object> update(@PathVariable Long productId, @RequestBody ProductDTO productDTO){
    return new ApiResult<>("00","success",productSVC.modify(productId, productDTO));
  }
  
  //삭제
  @DeleteMapping("/{productId}")
  public ApiResult<Object> delete(@PathVariable Long productId){
    return new ApiResult<>("00","success",productSVC.remove(productId));
  }
}
