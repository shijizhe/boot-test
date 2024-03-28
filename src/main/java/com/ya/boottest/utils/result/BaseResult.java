package com.ya.boottest.utils.result;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * <p>created time:2023/6/10 15:42</p>
 * <p>des:
 * 通用返回结果
 * </p>
 *
 * @author Ya Shi
 */
@Tag(name = "BaseResult", description = "通用返回结果")
@Data
public class BaseResult<T> {

    @Schema(description = "返回编码")
    public int code;
    @Schema(description = "返回信息")
    public String message;

    @Schema(description = "返回数据")
    public T data;

    private BaseResult() {
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    public static BaseResult<?> success() {
        return new BaseResult<>(ResultEnum.SUCCESS);
    }

    /**
     * 默认形式，无返回数据成功
     * @return BaseResult
     */
    public static <T> BaseResult<T> success(Class<T> clazz) {
        return new BaseResult<>(ResultEnum.SUCCESS);
    }

    /**
     * 默认形式，无返回数据失败
     * @return BaseResult
     */
    public static <T> BaseResult<T> fail(Class<T> clazz) {
        return new BaseResult<>(ResultEnum.FAILED);
    }


    /**
     * 附带数据的成功返回
     * @param data 数据
     * @return BaseResult
     */
    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(ResultEnum.SUCCESS,data);
    }

    /**
     * 附带成功message的成功返回
     * @param message 成功信息
     * @return BaseResult
     */
    public static BaseResult<?> successWithMessage(String message) {
        return new BaseResult<>(ResultEnum.SUCCESS.code, message);
    }

    /**
     * 携带信息，返回成功
     * @param message 成功信息
     * @param clazz 类型
     * @return BaseResult
     */
    public static <T> BaseResult<T> successWithMessage(String message, Class<T> clazz) {
        return new BaseResult<>(ResultEnum.SUCCESS.code,message);
    }

    public static BaseResult<?> fail(String message) {
        return new BaseResult<>(ResultEnum.FAILED.code, message);
    }

    /**
     * 携带信息，返回失败
     * @param message 失败信息
     * @param clazz 类型
     * @return BaseResult
     */
    public static <T> BaseResult<T> fail(String message, Class<T> clazz) {
        return new BaseResult<>(ResultEnum.FAILED.code,message);
    }


    public boolean judgeSuccess() {
        return ResultEnum.SUCCESS.code == this.code;
    }

    public static void main(String[] args) {
    }
}
