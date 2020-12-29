package com.osyangxin.moji.component.advice;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.osyangxin.moji.component.annotation.PageAble;
import com.osyangxin.moji.component.bean.ResultPageView;
import java.lang.reflect.Method;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 系统切面支持
 */
@Component
@Aspect
public class PageAbleAdvice {

    private static final Logger logger = LoggerFactory.getLogger(PageAbleAdvice.class);
    private static final String PAGE_ABLE = "@annotation(com.driver.demo.base.annotation.PageAble)";

    @Around(PAGE_ABLE)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("execute method : " + proceedingJoinPoint.getSignature().getName());
        try {
            prepare(proceedingJoinPoint);
            Object obj = proceedingJoinPoint.proceed();
            Object result = after(obj);
            return result;
        } catch (Throwable throwable) {
            logger.error("aspect execute error : ", throwable);
            throw throwable;
        } finally {
            PageHelper.clearPage();
        }
    }

    private void prepare(ProceedingJoinPoint point) throws Exception {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        PageAble pageAble = targetMethod.getAnnotation(PageAble.class);
        String numName = pageAble.pageNumName();
        String sizeName = pageAble.pageSizeName();
        int pageNo = pageAble.pageNum();
        int pageSize = pageAble.pageSize();
        Object[] paramValues = point.getArgs();
        String[] paramNames = methodSignature.getParameterNames();
        int length = paramNames.length;
        for (int i = 0; i < length; i++) {
            if (paramNames[i].equals(numName)) {
                pageNo = (Integer) paramValues[i];
            } else if (paramNames[i].equals(sizeName)) {
                pageSize = (Integer) paramValues[i];
            }
        }
        PageHelper.startPage(pageNo, pageSize);
    }

    private Object after(Object obj) {
        assert obj instanceof List;
        PageInfo<?> pageInfo;
        Page<Object> localPage = PageHelper.getLocalPage();
        long total = localPage.getTotal();
        int pageNum = localPage.getPageNum();
        int pages = localPage.getPages();
        List<?> list = (List<?>) obj;
        pageInfo = new PageInfo((list));
        ResultPageView<?> resultPageView;
        resultPageView = new ResultPageView<>(total, pageNum, pages, pageInfo.getList());
        PageHelper.clearPage();
        return resultPageView;
    }

}

