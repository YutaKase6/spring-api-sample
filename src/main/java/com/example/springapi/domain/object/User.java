package com.example.springapi.domain.object;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

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
