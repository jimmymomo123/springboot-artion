package com.jimmychiu.artion.service;

import com.jimmychiu.artion.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    void createEvent(Event event);
    void updateEvent(Long eventId, Event event);
    void deleteEvent(Long eventId);
}
