package com.ly.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 定义系统返回值VO对象
 *
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 200表示正确  201表示失败
     */
    private Integer status;
    /**
     * 服务器返回提示信息
     */
    private String msg;
    /**
     * 服务器返回业务数据
     */
    private Object data;

    public static SysResult success() {
        return new SysResult(200, "服务器执行成功!!!", null);
    }

    public static SysResult success(String msg, Object data) {
        return new SysResult(200, msg, data);
    }

    public static SysResult success(Object data) {
        return new SysResult(200, "服务器执行成功!!!", data);
    }

    public static SysResult fail() {
        return new SysResult(201, "服务器处理失败!!!", null);
    }

    public static SysResult success(String name) {
        return new SysResult(200, name, null);
    }
}
