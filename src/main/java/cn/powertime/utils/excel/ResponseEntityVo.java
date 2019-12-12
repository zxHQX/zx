package cn.powertime.utils.excel;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ZYW
 * @version 1.0
 * @date 2018-12-15 20:40
 */
@Data
public class ResponseEntityVo<T> implements Serializable {
    private Integer code;

    private String message;

    private Long timestamp;

    private T data;
}
