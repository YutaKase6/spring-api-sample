package com.example.springapi.application.controller;

import com.example.springapi.application.exception.NotFoundException;
import com.example.springapi.application.resource.ErrorResponse;
import com.example.springapi.application.resource.UserBody;
import com.example.springapi.domain.object.User;
import com.example.springapi.domain.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザ操作のコントローラ
 */
@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * ユーザ検索
     *
     * @param id 検索したいユーザID
     * @return ユーザ
     */
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable("id") String id) {
        // 検索しようとしたIDが存在しない場合はNotFoundExceptionをthrow
        return this.userService.findById(id).orElseThrow(() -> new NotFoundException("", id));
    }

    /**
     * ユーザ作成、更新
     *
     * @param userBody リクエストボディ
     * @return 更新後のユーザ
     */
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated UserBody userBody) {
        return this.userService.save(userBody.toDomainUser());
    }

    /**
     * ユーザ削除
     *
     * @param id 削除したいユーザID
     */
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") String id) {
        try {
            this.userService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // 削除しようとしたIDが存在しない
            throw new NotFoundException(e.getMessage(), id);
        }
    }
}
