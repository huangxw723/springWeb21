package sprittr.dao;

import org.apache.ibatis.annotations.Select;

import sprittr.pojo.AsUser;


public interface AsUserMapper{


    int isAccountExists(Object conditions);

    /**
     * 检查会员账号是否已经存在
     *
     * @param account
     * @return
     */
    int accountMemberIsExist(String account);

    /**
     * 根据后台用户表ID反查账号表记录
     *
     * @param conditions
     * @return
     * @author yangyanchao
     * @date 2016年9月5日
     */
    @Select(" select * from as_user where type = #{type} and status=0 limit 1")
    AsUser findBySysUserIdd(String type);


    int insert(AsUser account);

}