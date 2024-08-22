package com.jimmychiu.artion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    // 帳號：限制为 5-20 个字符
    @Schema(description = "帳號", example = "johndoe", minLength = 5, maxLength = 20)
    @Column(length = 20, nullable = false)
    private String username;

    // 密碼：限制为 8-20 个字符
    @Schema(description = "密碼", example = "password123", minLength = 8, maxLength = 20)
    @Column(length = 255, nullable = false)
    @JsonIgnore
    private String password;

    // 姓名：限制为 1-50 个字符
    @Schema(description = "姓名", example = "John Doe", maxLength = 50)
    @Column(length = 50)
    private String name;

    // 員工編號
    @Schema(description = "員工編號", example = "John Doe", maxLength = 50)
    @Column(nullable = false, unique = true, length=50)
    private String personnelCode;

    //資料創建時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime createdTime;

    //資料修改時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime updatedTime;

    @JsonIgnoreProperties("admins")
    @ManyToOne(fetch = FetchType.LAZY)  // 使用 LAZY 加载 Role
    @JoinColumn(name = "role_id")
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
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
