package com.example.springapi.application.controller;

import com.example.springapi.application.exception.NotFoundException;
import com.example.springapi.application.resource.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * コントローラからthrowされるExceptionをハンドルするクラス
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 400
     *
     * @param exception throwされたException
     * @return エラーレスポンス
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handle400(MethodArgumentNotValidException exception) {
        // validationに失敗したフィールドのリストを取得
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        // レスポンスの"Detail"に格納するために、validationに失敗したフィールドと失敗理由を連結
        StringBuilder errorDetailStr = new StringBuilder();
        fieldErrors.forEach(fieldError ->
                errorDetailStr.append(fieldError.getField())
                        .append(": ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ")
        );

        return new ErrorResponse("Bad Request.", errorDetailStr.toString(), "");
    }

    /**
     * 404
     *
     * @param exception throwされたException
     * @return エラーレスポンス
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ErrorResponse handle404(NotFoundException exception) {
        return new ErrorResponse(exception.getResource() + " is Not Found.", "", "");
    }

    /**
     * 405
     *
     * @param exception throwされたException
     * @return エラーレスポンス
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ErrorResponse handle405(NotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), "", "");
    }

    /**
     * 500
     *
     * @param exception throwされたException
     * @return エラーレスポンス
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ErrorResponse handle500(Exception exception) {
        return new ErrorResponse("Internal Server Error", "", "");
    }
}
