package com.osyangxin.dao.mapper;


import com.osyangxin.dao.model.SysRoleAuth;
import com.osyangxin.moji.mapper.BaseMapper;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface SysRoleAuthMapper extends BaseMapper<SysRoleAuth> {
    int batchInsert(List<SysRoleAuth> list);

    int batchDeleteByIds(@Param("set") Set<Integer> set, @Param("roleId") Integer roleId);
}