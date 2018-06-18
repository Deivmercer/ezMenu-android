package com.davemercer.ezmenu.bean;

public enum Currency {
    EUR("1"),
    USD("2"),
    GBP("3");

    private String code;

    Currency (String code){
        this.code = code;
    }

    public static Currency parse(String str) {
        values();
        for(Currency t : values()) {
            if(t.getCode().equals(str))
                return t;
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
