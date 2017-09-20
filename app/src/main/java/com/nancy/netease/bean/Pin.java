package com.nancy.netease.bean;

/**
 * Created by robot on 2017/9/14.
 */

public class Pin {
    public String id;
    public String name;
    public boolean state;

    public Pin( String name, boolean state) {

        this.name = name;
        this.state = state;
    }

    public Pin() {
    }
}
