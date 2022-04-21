package com.wsy.exam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: com.wsy.exam-> A
 * @description:
 * @author: wsy
 * @createDate: 2022-04-18 15:28
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
class A implements people, Serializable {

    private String id;

    @Override
    public boolean isName() {
        return true;

    }
}