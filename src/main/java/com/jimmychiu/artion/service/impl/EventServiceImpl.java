package com.jimmychiu.artion.service.impl;

import com.jimmychiu.artion.entity.Category;
import com.jimmychiu.artion.entity.Event;
import com.jimmychiu.artion.repository.CategoryRepository;
import com.jimmychiu.artion.repository.EventRepository;
import com.jimmychiu.artion.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public void createEvent(Event event) {
        event.setCreatedTime(LocalDateTime.now());
        event.setUpdatedTime(LocalDateTime.now());
        eventRepo.save(event);
    }

    @Transactional
    @Override
    public void updateEvent(Long eventId, Event event) {
        // 查找现有的事件
        Event existingEvent = eventRepo.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));

        // 更新时间
        existingEvent.setUpdatedTime(LocalDateTime.now());

        // 更新事件信息
        if (event.getName() != null) {
            existingEvent.setName(event.getName());
        }
        if (event.getLocation() != null) {
            existingEvent.setLocation(event.getLocation());
        }
        if (event.getTicketPrice() != null) {
            existingEvent.setTicketPrice(event.getTicketPrice());
        }
        if (event.getContent() != null) {
            existingEvent.setContent(event.getContent());
        }
        if (event.getEventPic() != null) {
            existingEvent.setEventPic(event.getEventPic());
        }
        if (event.getStartDate() != null) {
            existingEvent.setStartDate(event.getStartDate());
        }
        if (event.getEndDate() != null) {
            existingEvent.setEndDate(event.getEndDate());
        }

        // 保存更新后的事件
        eventRepo.save(existingEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
        // 查找现有的事件
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));
        eventRepo.delete(event);
    }


}
