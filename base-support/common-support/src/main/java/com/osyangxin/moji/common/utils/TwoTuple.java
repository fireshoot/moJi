package com.osyangxin.moji.common.utils;

/**
 * @Description:
 * @Author: guozongping
 * @CreateDate: 2020/7/3 10:33
 */
public class TwoTuple<A, B> {

    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }
}
