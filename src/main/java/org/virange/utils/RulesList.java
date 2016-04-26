package org.virange.utils;


import java.util.ArrayList;

public class RulesList {

    private String rule;
    private String reason;
    private String punish;
    private String message;
    private String time;

    public RulesList(ArrayList<String> list) {
        this.rule = list.get(0);
        this.reason = list.get(1);
        this.punish = list.get(2);
        this.message = list.get(3);
        this.time = list.get(4);
    }

    public String getRule() {
        return this.rule;
    }

    public String getReason() {
        return this.reason;
    }

    public String getPunish() {
        return this.punish;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTime() {
        return this.time;
    }
}
