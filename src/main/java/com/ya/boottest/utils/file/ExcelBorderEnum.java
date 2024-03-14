package com.ya.boottest.utils.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * Excel边框枚举
 * </p>
 *
 * @author Ya Shi
 * @since 2023/8/12 15:28
 */
@Getter
@AllArgsConstructor
public enum ExcelBorderEnum {
    BORDER_ALL("ALL", "全边框"),
    BORDER_SIDE("SIDE", "侧边框（左右）"),
    BORDER_NO("NO", "无边框");

    public final String borderType;
    public final String desc;
}
