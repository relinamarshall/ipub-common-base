package com.ipub.common.base.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 基础模型
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Setter
@Getter
public abstract class BaseModel {
    /**
     * 创建人
     */
    protected String crtBy;
    /**
     * 创建人名称
     */
    protected String crtNm;
    /**
     * 创建时间
     */
    protected Date crtTs;
    /**
     * 更新人
     */
    protected String updBy;
    /**
     * 更新人名称
     */
    protected String updNm;
    /**
     * 更新时间
     */
    protected Date updTs;
    /**
     * 删除标记位
     */
    protected String del;
}
