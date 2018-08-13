package com.example.springapi.domain.repository;

import com.example.springapi.domain.object.User;

/**
 * インフラ層とのインタフェース
 */
public interface UserRepository {

    /**
     * ユーザ検索
     *
     * @param id 検索したいユーザID
     * @return ユーザ
     */
    User findById(String id);

    /**
     * ユーザ作成、更新
     *
     * @param user 作成、更新したユーザ
     * @return 更新後のユーザ
     */
    User save(User user);

    /**
     * ユーザ削除
     *
     * @param id 削除したいユーザID
     */
    void deleteById(String id);
}
