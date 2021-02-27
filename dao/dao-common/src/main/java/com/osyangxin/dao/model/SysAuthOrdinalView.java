package com.osyangxin.dao.model;

import lombok.Data;

@Data
public class SysAuthOrdinalView {
    private Integer authId;

    private String name;

    private String uri;

    private Short status;

    private Integer ordinal;

    private Integer level;

    private Short checked;

    private Short type;

    private String description;

    private Integer parentId;

    private String parentName;
}
