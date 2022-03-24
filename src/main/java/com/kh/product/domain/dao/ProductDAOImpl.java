package com.kh.product.domain.dao;

import com.kh.product.domain.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

  //주입
  private final JdbcTemplate jdbcTemplate;

  /**
   * 등록
   * @param productDTO
   * @return
   */
  @Override
  public ProductDTO insert(ProductDTO productDTO) {
    //sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into product (product_id, name, quantity, price) ");
    sql.append(" values (product_product_id_seq.nextval, ?, ?, ?) ");

    //sql 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();//키홀더 생성
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt =con.prepareStatement(
                sql.toString(),             //sql 문
                new String[]{"product_id"}  //키홀더로 반환할 값
        );

        pstmt.setString(1, productDTO.getName());
        pstmt.setLong(2, productDTO.getQuantity());
        pstmt.setLong(3, productDTO.getPrice());

        return pstmt;
      }
    }, keyHolder);

    //조회후 반환
    Long productId = Long.valueOf(keyHolder.getKeys().get("product_id").toString());
    return selectOne(productId);
  }

  /**
   * 조회
   * @param productId 상품ID
   * @return
   */
  @Override
  public ProductDTO selectOne(Long productId) {
    //sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" select product_id, name, quantity, price ");
    sql.append("   from product ");
    sql.append("  where product_id = ? ");

    //sql 실행
    List<ProductDTO> list = jdbcTemplate.query(
            sql.toString(), new BeanPropertyRowMapper<>(ProductDTO.class), productId);

    return (list.size() == 1) ? list.get(0) : null;
  }

  /**
   * 전체조회
   * @return
   */
  @Override
  public List<ProductDTO> selectAll() {
    //sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" select product_id, name, quantity, price ");
    sql.append("   from product ");
    sql.append("  order by product_id ");

    //sql 실행
    List<ProductDTO> list = jdbcTemplate.query(
            sql.toString(), new BeanPropertyRowMapper<>(ProductDTO.class));

    return list;
  }

  /**
   * 수정
   * @param productDTO
   * @return
   */
  @Override
  public ProductDTO update(Long productId, ProductDTO productDTO) {
    //sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" update product ");
    sql.append("    set name = ? , ");
    sql.append("        quantity = ? , ");
    sql.append("        price = ? ");
    sql.append("  where product_id = ? ");

    //sql 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
                sql.toString(),             //sql 문
                new String[]{"product_id"}  //키홀더로 반환할 값
        );

        pstmt.setString(1, productDTO.getName());
        pstmt.setLong(2, productDTO.getQuantity());
        pstmt.setLong(3, productDTO.getPrice());
        pstmt.setLong(4, productId);

        return pstmt;
      }
    },keyHolder);

    //조회후 반환
    Long returnedId = Long.valueOf(keyHolder.getKeys().get("product_id").toString());
    return selectOne(returnedId);
  }

  /**
   * 삭제
   * @param productId 상품ID
   * @return
   */
  @Override
  public int delete(Long productId) {
    //sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from product ");
    sql.append("  where product_id = ? ");

    //sql 실행
    int result = jdbcTemplate.update(sql.toString(), productId);

    return result;
  }
}
