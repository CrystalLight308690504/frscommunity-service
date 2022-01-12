package priv.crystallightghost.frscommunity.exception;

import lombok.Getter;
import priv.crystallightghost.frscommunity.respond.ResultCode;

/**
 * 自定义异常
 */
@Getter
public class CommonException extends Exception {

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
