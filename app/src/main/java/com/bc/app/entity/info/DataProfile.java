package com.bc.app.entity.info;

import java.util.List;

/**
 * 数据信息
 *
 * @author zhou
 */
public class DataProfile {

    private HotExchange hotExchange;

    private List<WeavePrice> weavePriceList;

    public HotExchange getHotExchange() {
        return hotExchange;
    }

    public void setHotExchange(HotExchange hotExchange) {
        this.hotExchange = hotExchange;
    }

    public List<WeavePrice> getWeavePriceList() {
        return weavePriceList;
    }

    public void setWeavePriceList(List<WeavePrice> weavePriceList) {
        this.weavePriceList = weavePriceList;
    }

}
