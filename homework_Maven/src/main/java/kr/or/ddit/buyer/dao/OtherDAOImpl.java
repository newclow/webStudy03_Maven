package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.ibstis.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OtherDAOImpl implements IOtherDAO {
	
	SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	@Override
	public Map<String, String> selectLprodList() {
		try {
			return sqlMapClient.queryForMap("Other.selectLprodList", null, "LPROD_GU", "LPROD_NM");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
