package com.example.springapi.application.resource;

import com.example.springapi.domain.object.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * リクエストボディのマッピング用クラス
 */
@Data
public class UserBody {

    @NotBlank
    @Size(max = 18)
    private String id;

    @NotBlank
    private String value;

    /**
     * ドメインオブジェクトへ変換
     *
     * @return ドメインオブジェクト
     */
    public User toDomainUser() {
        return User.builder()
                .id(this.id)
                .value(this.value)
                .build();
    }
}
