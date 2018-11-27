package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 상품관리를 위한 domain layer
 * 
 * **ibatis나 mybatis를 이용해 여러 테이블로 부터 데이터를 조회하는 방법
 * 1. 메인데이터를 중심으로 테이블간의 관계 파악
 * 	ex) 상품정보 상세조회(조회할 상품의 구매자 목록을 함께 조회)
 * 		상품(1) : 구매자(N)
 * 2. 각 테이블로 부터 데이터 조회시 사용할 VO를 구현
 * 	   각 VO간의 관계성을 설정.
 * 		상품(1) : 거래처(1) ->  ProdVO has a buyerVO
 * 		상품(1) : 구매자들(N) -> ProdVO has many MemberVO
 * 3-1. 테이블을 직접 조인한 쿼리작성(resultMap으로 결과 바인딩)
 * 4-1. NestedMap방식 = resultMap을 구현시 outerVO(ProdVO)와 innerVO(BuyerVO, MemberVO)역할의 resultMap들이 중첩되도록 설정
 * 	주의! 1:N 관계 조인시 여러건의 레코드에서 중복을 제거해야함(groupBy)
 * 3-2. 각 테이블로부터 데이터를 조회할 쿼리를 분리작성
 * 		ex) selectProd, selectCusomers
 * 4-2. Nested Select방식 : resultMap구현시 
 * 		outerVO 가 가진 특정 프로퍼티를 바인딩할 설정에서 select속성을 사용하여  추가 쿼리문을 실행함
 * 		ex) <result property="customers" select="Prod.selectCustomers" column="PROD_ID"/>
 * 	주의! 중첩된 select문에 인라인 파라미터가 사용된다면 result 설정의 column속성을 통해 값을 전달
 * 	장점 : lazyLordingEnabled="true"로 할경우 사용되지 않는 데이터는 조회하지 않는다
 */
@Data
@EqualsAndHashCode(of= {"prod_id", "prod_name"}) //2개가 같으면 하겠다
public class ProdVO implements Serializable{
	private long rnum; //추가
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String lprod_nm; //추가
	private String prod_buyer;
	private String buyer_name; //추가 단순히 값을 담기위한 그릇이므로 sql문에서 필요할시 넣어준다
	private Long prod_cost;
	private Long prod_price;
	private Long prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Long prod_totalstock;
	private String prod_insdate;
	private Long prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Long prod_qtyin;
	private Long prod_qtysale;
	private Long prod_mileage;
	
	private BuyerVO buyer; //has a 관계 핵심은 'a' 1:1관계이므로 
	
	private List<MemberVO> customers; //1:n은 has many관계이다.
}
