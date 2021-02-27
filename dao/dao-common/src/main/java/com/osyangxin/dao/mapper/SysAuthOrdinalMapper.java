package com.osyangxin.dao.mapper;


import com.osyangxin.dao.model.SysAuthOrdinal;
import com.osyangxin.moji.mapper.BaseMapper;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAuthOrdinalMapper extends BaseMapper<SysAuthOrdinal> {

    int updateOrdinalForAdd(@Param("id") Integer id, @Param("ordinal") Integer ordinal, @Param("updatedAt") Date updatedAt);

    int updateOrdinalForSub(@Param("minOrdinalForDelIds") Integer minOrdinalForDelIds, @Param("updatedAt") Date updatedAt, @Param("size") Integer size);

    int delAllAuthOrdinal();

    int insertAuthOrdinal(@Param("list") List<SysAuthOrdinal> authOrdinalList, @Param("now") Date date);

    Integer getMaxOrdinalValue();

    SysAuthOrdinal getByAuthId(@Param(value = "authId") Integer authId);

    void updateOrdinalAddOne(@Param(value = "join2SqlInStr") String join2SqlInStr);
}