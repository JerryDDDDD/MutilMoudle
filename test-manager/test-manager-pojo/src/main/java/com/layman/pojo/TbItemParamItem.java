package com.layman.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品规格和商品的关系表
 * 
 * @author wcyong
 * 
 * @date 2018-11-25
 */
public class TbItemParamItem  implements Serializable {
    private Long id;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 参数数据，格式为json格式
     */
    private String paramData;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}