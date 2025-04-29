package com.ruoyi.disease.utils;

import java.util.HashMap;

public class Res extends HashMap<String,Object> {

    public static Res ok(){
        Res result = new Res();
        result.put("code", 200);
        result.put("msg", "操作成功");
        return result;
    }

    public static Res error(String msg){
        Res result = new Res();
        result.put("code", 500);
        result.put("msg", msg);
        return result;
    }

    @Override
    public Res put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
