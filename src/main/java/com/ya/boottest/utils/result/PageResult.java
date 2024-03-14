package com.ya.boottest.utils.result;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * <p>created time:2023/6/15 21:02</p>
 * <p>des:
 * 分页返回结果 要放在BaseResult中返回
 * </p>
 *
 * @author Ya Shi
 */

@Tag(name = "PageResult", description = "分页返回结果")
@Data
public class PageResult <T> {

    @Schema(description = "总记录数")
    public long total;

    @Schema(description = "数据列表")
    public T records;

    public PageResult() {
        this.total = 0L;
        this.records = null;
    }
    public PageResult(long total, T records) {
        this.total = total;
        this.records = records;
    }
}
