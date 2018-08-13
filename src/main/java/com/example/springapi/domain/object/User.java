package com.example.springapi.domain.object;

import lombok.Builder;
import lombok.Data;

/**
 * ユーザ
 */
@Data
@Builder
public class User {

    /**
     * ユーザID
     */
    private String id;

    /**
     * ユーザ情報
     */
    private String value;
}
