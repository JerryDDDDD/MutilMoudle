package com.layman.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品描述表
 * 
 * @author wcyong
 * 
 * @date 2018-11-25
 */
public class TbItemDesc  implements Serializable {
    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 商品描述
     */
    private String itemDesc;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
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