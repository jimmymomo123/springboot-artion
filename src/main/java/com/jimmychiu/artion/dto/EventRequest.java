package com.jimmychiu.artion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EventRequest {
    // 活動名稱
    @Schema(description = "活动名称，限制为 1-100 个字符", example = "Summer Festival", maxLength = 100)
    @NotBlank(message = "活动名称不能为空")
    @Size(max = 100, message = "活动名称不能超过100个字符")
    private String name;

    // 活動地點
    @Schema(description = "活动地点，限制为 1-200 个字符", example = "Central Park", maxLength = 200)
    @NotBlank(message = "活动地点不能为空")
    @Size(max = 200, message = "活动地点不能超过200个字符")
    private String location;

    // 活動票價
    @Schema(description = "活动票价", example = "50")
    @NotNull(message = "活动票价不能为空")
    private Integer ticketPrice;

    // 活動內容
    @Schema(description = "活动内容，限制为 1-2000 个字符", example = "A fun-filled summer festival with music, food, and games.", maxLength = 2000)
    @NotBlank(message = "活动内容不能为空")
    @Size(max = 2000, message = "活动内容不能超过2000个字符")
    private String content;

    // 照片
    @Schema(description = "活动照片的 URL", example = "https://example.com/event.jpg")
    @NotBlank(message = "活动照片的 URL 不能为空")
    private String eventPic;

    // 活動開始時間
    @Schema(description = "活动开始时间", example = "2024-08-21")
    @NotNull(message = "活动开始时间不能为空")
    private LocalDate startDate;

    // 活動結束時間
    @Schema(description = "活动结束时间", example = "2024-08-22")
    @NotNull(message = "活动结束时间不能为空")
    private LocalDate endDate;

    @Schema(description = "分類id", example = "1")
    @NotNull(message = "分類ID不能為空值")
    private Integer categoryId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
