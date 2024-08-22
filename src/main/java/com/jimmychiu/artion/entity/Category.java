package com.jimmychiu.artion.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private String name;

    private String createAdminName;

    //資料創建時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime createdTime;

    //資料修改時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime updatedTime;

    @JsonIgnoreProperties("category")
    @OneToMany(mappedBy="category", fetch=FetchType.LAZY)
    @JsonBackReference
    private Set<Event> events;

}
