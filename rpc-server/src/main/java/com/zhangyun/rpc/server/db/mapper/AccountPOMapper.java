package com.zhangyun.rpc.server.db.mapper;

import com.zhangyun.rpc.server.db.po.AccountPO;
import com.zhangyun.rpc.server.db.po.AccountPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface AccountPOMapper {
    @SelectProvider(type=AccountPOSqlProvider.class, method="countByExample")
    long countByExample(AccountPOExample example);

    @DeleteProvider(type=AccountPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(AccountPOExample example);

    @Delete({
        "delete from account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into account (username, password, ",
        "sex)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(AccountPO record);

    @InsertProvider(type=AccountPOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(AccountPO record);

    @SelectProvider(type=AccountPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT)
    })
    List<AccountPO> selectByExampleWithRowbounds(AccountPOExample example, RowBounds rowBounds);

    @SelectProvider(type=AccountPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT)
    })
    List<AccountPO> selectByExample(AccountPOExample example);

    @Select({
        "select",
        "id, username, password, sex",
        "from account",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT)
    })
    AccountPO selectByPrimaryKey(Long id);

    @UpdateProvider(type=AccountPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AccountPO record, @Param("example") AccountPOExample example);

    @UpdateProvider(type=AccountPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AccountPO record, @Param("example") AccountPOExample example);

    @UpdateProvider(type=AccountPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountPO record);

    @Update({
        "update account",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(AccountPO record);
}