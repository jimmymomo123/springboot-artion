package com.jimmychiu.artion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MemberRequest {

    // 姓名：限制为 1-50 个字符
    @Schema(description = "姓名", example = "John Doe", maxLength = 50)
    @Size(max = 50)
    @NotBlank(message = "姓名不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private String name;

    // 信箱：限制为 5-100 个字符
    @Schema(description = "信箱", example = "johndoe@example.com", maxLength = 100)
    @Size(max = 100)
    @NotBlank(message = "信箱不能是空白字符") // 仅当字段有值时，值不能是空字符串
    @Email
    private String email;

    // 電話號碼：限制为 10-15 個字符
    @Schema(description = "電話號碼", example = "+1234567890", minLength = 10, maxLength = 15)
    @Size(min = 10, max = 15)
    @NotBlank(message = "電話號碼不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private String phoneNum;

    // 性別：限制为 1 个字符 ('M', 'F', 'O' 等)
    @Schema(description = "性別", example = "M", maxLength = 1)
    @Size(max = 1)
    @NotBlank(message = "性別不能是空白字符") // 仅当字段有值时，值不能是空字符串
    private String gender;

    // 生日
    @Schema(description = "生日", example = "1990-01-01")
    private LocalDate birth;

    //角色id
    @Schema(description = "角色id", example = "1")
    private Integer roleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
