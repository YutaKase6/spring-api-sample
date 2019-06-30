package com.example.springapi.infrastructure.repository;

import com.example.springapi.domain.exception.NotFoundException;
import com.example.springapi.domain.object.User;
import com.example.springapi.domain.repository.UserRepository;
import com.example.springapi.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 * 永続化の実装クラス
 * ドメインオブジェクトをEntityに変換してJPAをラップする
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public User findById(String id) {
        return this.userJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + " is not found."))
                .toDomainUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(User user) {
        return this.userJpaRepository.save(UserEntity.build(user))
                .toDomainUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        try {
            this.userJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // 削除しようとしたIDが存在しない
            throw new NotFoundException(e.getMessage());
        }
    }
}
