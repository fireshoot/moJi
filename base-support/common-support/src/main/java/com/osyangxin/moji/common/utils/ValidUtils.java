package com.osyangxin.moji.common.utils;


import com.osyangxin.moji.common.bean.ValidData;
import com.osyangxin.moji.common.enums.BaseStubDynamic;
import com.osyangxin.moji.common.exception.ApplicationException;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;


import static com.osyangxin.moji.common.constants.GroupCheckFlag.DefaultFlag;
import static com.osyangxin.moji.common.constants.GroupCheckFlag.ValidDataFlag;


/**
 * @author yangxin
 * @类描述 :
 *          分组校验工具类，使用@class{ValidData.class}用来分组，当flag是1时，只校验不是ValidData标记的字段，
 *          当flag是0时，校验全部字段
 * @time 2019/12/5  14:21
 */
public class ValidUtils {

    private ValidUtils() {
    }

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Set<ConstraintViolation<T>> valid(T obj) {
        return validator.validate(obj);
    }

    public static <T> Set<ConstraintViolation<T>> valid(T obj, Class<?>... var2) {
        return validator.validate(obj, var2);
    }

    public static <T> void validDataByGroup(T form, int flag) {
        Set<ConstraintViolation<T>> mes = null;
        if (flag == DefaultFlag.getStatus()) {
            mes = ValidUtils.valid(form);
        } else if (flag == ValidDataFlag.getStatus()) {
            mes = ValidUtils.valid(form, ValidData.class, Default.class);
        } else {
            throw new ApplicationException(new BaseStubDynamic(401,
                    String.format("parameter type error -->[%s]", "GroupCheckFlag value 数据非法")));
        }

        if (!mes.isEmpty()) {
            throw new ApplicationException(new BaseStubDynamic(401,
                    String.format("parameter type error -->[%s]", getErrMsg(mes))));
        }

    }

    /**
     *  扔出错误信息
     * */
    private static <T> String getErrMsg(Set<ConstraintViolation<T>> mes) {
        Iterator<ConstraintViolation<T>> iterator = mes.iterator();
        return iterator.next().getMessageTemplate();
    }

}
