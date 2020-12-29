package com.osyangxin.moji.common.utils;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;


/**
 * @类描述
 * @time 2019/10/15  16:02
 */
public class ListUtils<T> {

    public void copyList(Object obj, List<T> list2, Class<T> classObj) throws IllegalAccessException, InstantiationException {
        if ((!Objects.isNull(obj)) && (!Objects.isNull(list2))) {
            List list1 = (List) obj;
            for (int i = 0; i < list1.size(); i++) {
                T data = classObj.newInstance();
                BeanUtils.copyProperties(list1.get(i), data);
                list2.add(data);
            }
        }
    }
}
