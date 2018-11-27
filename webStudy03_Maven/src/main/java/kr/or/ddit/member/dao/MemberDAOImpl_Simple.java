package kr.or.ddit.member.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.ibatis.SampleDataMapper;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class MemberDAOImpl_Simple implements IMemberDAO{

   @Override
   public MemberVO selectMember(String mem_id) {
      MemberVO member = null;
      StringBuffer sql = new StringBuffer();
      sql.append("select                                                      ");
       sql.append("mem_id, mem_pass, mem_name, mem_regno1,                     ");
       sql.append("mem_regno2, to_char(mem_bir,'yyyy-mm-dd') mem_bir, mem_zip, mem_add1,                     ");
       sql.append("mem_add2, mem_hometel, mem_comtel, mem_hp,                  ");
       sql.append("mem_mail, mem_job, mem_like, mem_memorial, to_char(mem_memorialday,'yyyy-mm-dd') mem_memorialday, ");
       sql.append("mem_mileage, mem_delete                                     ");
       sql.append("from member                                                 ");
       sql.append("where mem_id=?                                              ");
      try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
         pstmt.setString(1, mem_id);
         ResultSet rs = pstmt.executeQuery();
         if(rs.next()) {
            member = (MemberVO)SampleDataMapper.resultSetToJavaObject(rs, MemberVO.class);
         }
         return member;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public int insertMember(MemberVO member) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public List<MemberVO> selectMemberList(PagingInfoVO pagingVO) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public int updateMember(MemberVO member) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int deleteMember(String mem_id) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public long selectTotalRecord(PagingInfoVO pagingVO) {
      // TODO Auto-generated method stub
      return 0;
   }

   
   
   
}