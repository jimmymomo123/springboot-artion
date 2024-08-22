package com.jimmychiu.artion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "活动的唯一标识符", example = "1")
    private Long id;

    // 活動名稱
    @Schema(description = "活动名称，限制为 1-100 个字符", example = "Summer Festival", maxLength = 100)
    @Column(length = 100, nullable = false)
    private String name;

    // 活動地點
    @Schema(description = "活动地点，限制为 1-200 个字符", example = "Central Park", maxLength = 200)
    @Column(length = 200, nullable = false)
    private String location;

    // 活動票價
    @Schema(description = "活动票价", example = "50")
    private Integer ticketPrice;

    // 活動內容
    @Schema(description = "活动内容，限制为 1-2000 个字符", example = "A fun-filled summer festival with music, food, and games.", maxLength = 2000)
    @Column(length = 2000)
    private String content;

    // 照片
    @Schema(description = "活动照片的 URL", example = "https://example.com/event.jpg")
    private String eventPic;

    // 活動開始時間
    @Schema(description = "活动开始时间", example = "2024-08-21")
    private LocalDate startDate;

    // 活動結束時間
    @Schema(description = "活动结束时间", example = "2024-08-22")
    private LocalDate endDate;

    // 資料創建時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @Schema(description = "数据创建时间", example = "2024-08-20 12:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdTime;

    // 資料修改時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @Schema(description = "数据修改时间", example = "2024-08-21 12:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedTime;

    @JsonIgnoreProperties("events")
    @ManyToOne(fetch = FetchType.LAZY)  // 使用 LAZY 加载 Category
    @JoinColumn(name = "category_id")
    @Schema(description = "活动所属类别", example = "1")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEventPic() {
        return eventPic;
    }

    public void setEventPic(String eventPic) {
        this.eventPic = eventPic;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
