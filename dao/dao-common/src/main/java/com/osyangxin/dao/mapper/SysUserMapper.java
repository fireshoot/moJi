package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.SysRole;
import com.osyangxin.dao.model.SysUser;
import com.osyangxin.dao.model.SysUserInfoView;
import com.osyangxin.moji.bean.Condition;
import com.osyangxin.moji.mapper.BaseMapper;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUserInfoView> getSysUserViewByConditionList(@Param("conditions") List<Condition> conditions);

    SysRole getRoleStatusByUserId(@Param("id") Integer id);

    int batchUpdateByUserId(@Param("list") List<Integer> list, @Param("status") Short status, @Param("updatedAt") Date updatedAt);
}