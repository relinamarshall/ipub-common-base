package com.ipub.common.web.entity;

import com.ipub.common.base.model.BaseModel;
import lombok.Data;

/**
 * User
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Data
public class User extends BaseModel {
    private String acct;
    private String nm;
    private String sex;
    private String email;
    private String address;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User inst) {
            return this.acct.equals(inst.acct);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.acct.hashCode();
    }
}
