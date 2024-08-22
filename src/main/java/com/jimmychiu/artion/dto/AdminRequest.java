package com.jimmychiu.artion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AdminRequest {

    // 帳號：限制为 5-20 个字符
    @Schema(description = "帳號", example = "johndoe", minLength = 5, maxLength = 20, required = true)
    @Size(min = 5, max = 20)
    @NotBlank(message = "帳號不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private String username;

    // 姓名：限制为 1-50 个字符
    @Schema(description = "姓名", example = "John Doe", maxLength = 50)
    @Size(max = 50)
    @NotBlank(message = "姓名不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private String name;

    // 員工編號
    @Schema(description = "員工編號", example = "AA00001", maxLength = 50)
    @Size(max = 50)
    @NotBlank(message = "員工編號不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private String personnelCode;

    //角色id
    @Schema(description = "角色id", example = "1")
    @NotNull(message = "角色ID不能為空值")
    private Integer roleId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
