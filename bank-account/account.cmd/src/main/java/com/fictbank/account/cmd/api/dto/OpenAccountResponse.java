package com.fictbank.account.cmd.api.dto;

import com.fictbank.account.common.dto.BaseResponse;
import lombok.Data;

@Data
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
