package com.osyangxin.moji.common.bean;

import com.alibaba.fastjson.JSONObject;
import com.osyangxin.moji.common.constants.WsCommand;
import lombok.Data;

@Data
public class WsMsg {
    private String type;
    private String cmd;
    private Integer cmdSn;
    private JSONObject data;

    public WsMsg(String type, WsCommand wsCommand, JSONObject data) {
        this.type = type;
        this.cmd = wsCommand.getDetail();
        this.cmdSn = wsCommand.getSn();
        this.data = data;
    }

    JSONObject toJSON() {
        return (JSONObject) JSONObject.toJSON(this);
    }
}
