package com.jimmychiu.artion.dto;

import com.jimmychiu.artion.enumType.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class RoleRequest {
    // 角色名稱：限制为 1-50 个字符
    @Schema(description = "角色名稱", example = "", maxLength = 20)
    @Size(max = 20)
    @NotBlank(message = "角色名稱不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private String name;

    // 權限：限制为 500 个字符
    @Size(max=500)
    @NotBlank(message = "權限不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private Set<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
