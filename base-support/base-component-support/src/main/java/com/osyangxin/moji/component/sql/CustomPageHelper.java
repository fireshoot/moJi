package com.osyangxin.moji.component.sql;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class CustomPageHelper extends PageHelper {

    @Override
    public void afterAll() {
        Page<Object> localPage = getLocalPage();
        super.afterAll();
        setLocalPage(localPage);
    }
}
