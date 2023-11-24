package com.example.hobbybungae.domain.user.exception;

import com.example.hobbybungae.domain.common.DomainException;
import com.example.hobbybungae.global_exception.ErrorCode;
import com.example.hobbybungae.global_exception.ErrorDetail;

/* 새로 작성한 idName이 중복되는 경우 */
public class DuplicatedUserIdNameException extends DomainException {
    public DuplicatedUserIdNameException(String field, String value, String reason) {
        super(ErrorCode.DUPLICATED_USER, new ErrorDetail(field, value, reason));
    }
}
