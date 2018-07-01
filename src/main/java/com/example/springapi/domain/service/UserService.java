package com.example.springapi.domain.service;

import com.example.springapi.domain.object.User;
import com.example.springapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ユーザ操作のロジック
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * ユーザ検索
     *
     * @param id 検索したいユーザID
     * @return ユーザ
     */
    public Optional<User> findById(String id) {
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
