package com.bc.app.entity.info;

/**
 * 纺织品价格趋势
 *
 * @author zhou
 */
public class WeavePrice {

    private String id;
    private String type;
    private String name;
    private String lastTrade;
    private String unit;
    private String change;
    private String date;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastTrade() {
        return lastTrade;
    }

    public void setLastTrade(String lastTrade) {
        this.lastTrade = lastTrade;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
