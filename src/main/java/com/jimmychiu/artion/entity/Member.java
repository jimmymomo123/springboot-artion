package com.jimmychiu.artion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    // 帳號：限制为 5-20 个字符
    @Schema(description = "帳號", example = "johndoe", minLength = 5, maxLength = 20, required = true)
    @Size(min = 5, max = 20)
    @Column(length = 20, nullable = false)
    private String username;

    // 密碼：限制为 8-20 个字符
    @Schema(description = "密碼", example = "password123", minLength = 8, maxLength = 20, required = true)
    @Size(min = 8, max = 20)
    @Column(length = 20, nullable = false)
    @JsonIgnore
    private String password;

    // 姓名：限制为 1-50 个字符
    @Schema(description = "姓名", example = "John Doe", maxLength = 50)
    @Size(max = 50)
    @Column(length = 50)
    private String name;

    // 信箱：限制为 5-100 个字符
    @Schema(description = "信箱", example = "johndoe@example.com", maxLength = 100, required = true)
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String email;

    // 電話號碼：限制为 10-15 個字符
    @Schema(description = "電話號碼", example = "+1234567890", minLength = 10, maxLength = 15)
    @Size(min = 10, max = 15)
    @Column(length = 15)
    private String phoneNum;

    // 性別：限制为 1 个字符 ('M', 'F', 'O' 等)
    @Schema(description = "性別", example = "M", maxLength = 1)
    @Size(max = 1)
    @Column(length = 1)
    private String gender;

    // 生日
    @Schema(description = "生日", example = "1990-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    //資料創建時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime createdTime;

    //資料修改時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime updatedTime;

    @JsonIgnoreProperties("members")
    @ManyToOne(fetch = FetchType.LAZY)  // 使用 LAZY 加载 Role
    @JoinColumn(name = "role_id")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
