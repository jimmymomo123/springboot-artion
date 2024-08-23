package com.jimmychiu.artion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {

    // 分类名称
    @Schema(description = "分类名称，限制为 1-100 个字符", example = "Art Events", maxLength = 100)
    @NotBlank(message = "分类名称不能为空")
    @Size(max = 100, message = "分类名称不能超过100个字符")
    private String name;

    // 创建管理员名称
    @Schema(description = "创建管理员名称，限制为 1-50 个字符", example = "admin", maxLength = 50)
    @NotBlank(message = "创建管理员名称不能为空")
    @Size(max = 50, message = "创建管理员名称不能超过50个字符")
    private String createAdminName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateAdminName() {
        return createAdminName;
    }

    public void setCreateAdminName(String createAdminName) {
        this.createAdminName = createAdminName;
    }
}
