package com.example.springapi.domain.service;

import com.example.springapi.domain.exception.NotFoundException;
import com.example.springapi.domain.object.User;
import com.example.springapi.domain.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ユーザ操作のロジック
 */
@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    /**
     * ユーザ検索
     *
     * @param id 検索したいユーザID
     * @return ユーザ
     */
    public User findById(String id) {
        return this.userRepository.findById(id);
    }

    /**
     * ユーザ作成、更新
     *
     * @param user 作成、更新したユーザ
     * @return 更新後のユーザ
     */
    public User save(User user) {
        return this.userRepository.save(user);
    }

    /**
     * ユーザ削除
     *
     * @param id 削除したいユーザID
     */
    public void deleteById(String id) {
        this.userRepository.deleteById(id);
    }
}
