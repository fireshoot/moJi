package com.osyangxin.moji.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Cris
 * @description
 * @date 2019/8/19
 */
public class SimpleVehicleInfo {

    private Integer id;

    private String number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("number", number)
                .toString();
    }
}
