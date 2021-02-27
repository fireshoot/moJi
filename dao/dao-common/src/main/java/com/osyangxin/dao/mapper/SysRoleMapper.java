package com.osyangxin.dao.mapper;


import com.osyangxin.dao.model.SysAuthOrdinalView;
import com.osyangxin.dao.model.SysRole;
import com.osyangxin.moji.mapper.BaseMapper;
import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysAuthOrdinalView> getAuthListDefault(Integer roleId);
}