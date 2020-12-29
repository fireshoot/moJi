package com.osyangxin.moji.component.mapper;



import com.osyangxin.moji.component.bean.BaseModel;
import com.osyangxin.moji.component.bean.Condition;
import com.osyangxin.moji.component.bean.Sort;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 *  基本数据库操作统一mapper
 */
public interface BaseMapper<T extends BaseModel> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> getByConditions(@Param("conditions") Map<String, Object> conditions);

    List<T> getByConditionList(@Param("conditions") List<Condition> conditions);

    List<T> getSortedResultByConditionList(@Param("conditions") List<Condition> conditions,
                                           @Param("sorter") Sort sort);

    /**
     * 方法实现说明
     *
     * <choose>
     *       <when test="sorter !=null and sorter.size() > 0">
     *         order by
     *         <foreach item="item" collection="sorter"
     *                  open="" separator="," close="">
     *           ${item.field} ${item.sortType}
     *         </foreach>
     *       </when>
     * </choose>
     *
     * @author      yangxin
     * @date        2019/12/25 10:05
    */
    List<T> getSortedResultByConditionLists(@Param("conditions") List<Condition> conditions,
                                            @Param("sorter") List<Sort> sort);

    List<T> getByIn(@Param("field") String field, @Param("set") Set<Object> set);


}
