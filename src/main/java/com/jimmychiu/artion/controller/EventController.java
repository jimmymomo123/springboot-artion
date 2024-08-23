package com.jimmychiu.artion.controller;

import com.jimmychiu.artion.dto.EventRequest;
import com.jimmychiu.artion.dto.Result;
import com.jimmychiu.artion.entity.Category;
import com.jimmychiu.artion.entity.Event;
import com.jimmychiu.artion.repository.CategoryRepository;
import com.jimmychiu.artion.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping
    public Result<List<Event>> list() {
        List<Event> events = eventService.getAllEvents(); // 获取所有事件
        return Result.success(events);
    }

    @PostMapping
    public Result createEvent(@Valid @RequestBody EventRequest request) {
        Event event = convertToEntity(request); // 将 DTO 转换为实体
        eventService.createEvent(event);
        return Result.success();
    }

    @PutMapping("/{eventId}")
    public Result updateEvent(@PathVariable Long eventId, @Valid @RequestBody EventRequest request) {
        Event event = convertToEntity(request); // 将 DTO 转换为实体
        eventService.updateEvent(eventId, event);
        return Result.success();
    }

    @DeleteMapping("/{eventId}")
    public Result deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return Result.success();
    }

    private Event convertToEntity(EventRequest dto) {
        Event event = new Event();
        BeanUtils.copyProperties(dto, event);

        Category category = categoryRepo.findById(dto.getCategoryId()).orElseThrow();
        event.setCategory(category);
        return event;
    }
}
