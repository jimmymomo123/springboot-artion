package com.jimmychiu.artion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    //活動名稱
    private String name;

    //活動地點
    private String location;

    //活動票價
    private Integer ticketPrice;

    //活動內容
    private String content;

    //照片
    private String eventPic;

    //活動開始時間
    private LocalDate startDate;

    //活動結束時間
    private LocalDate endDate;

    //資料創建時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime createdTime;

    //資料修改時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime updatedTime;

    @JsonIgnoreProperties("events")
    @ManyToOne(fetch = FetchType.LAZY)  // 使用 LAZY 加载 Category
    @JoinColumn(name = "category_id")
    private Category category;


}
