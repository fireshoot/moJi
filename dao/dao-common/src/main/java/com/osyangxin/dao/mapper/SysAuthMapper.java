package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.SysAuth;
import com.osyangxin.dao.model.SysAuthOrdinalView;
import com.osyangxin.moji.mapper.BaseMapper;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface SysAuthMapper extends BaseMapper<SysAuth> {
    List<SysAuthOrdinalView> getSysAuthOrdinal();

    List<SysAuthOrdinalView> getAuthByUserId(@Param("id") Integer id);

    int batchUpdateStatusByIds(@Param("status") Short status, @Param("updatedAt") Date updateAt, @Param("idList") List<Integer> idList);

    int batchDelByAuthIds(@Param("authIds") Set<Integer> authIds);

    int getMinOrdinalForDelIds(@Param("set") Set set);
}