package org.mansar.springdata.api;

import org.mansar.springdata.app.MeetingApp;
import org.mansar.springdata.model.Meeting;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/meeting")
public class MeetingController {
    private final MeetingApp meetingApp;
    public MeetingController(MeetingApp meetingApp) {
        this.meetingApp = meetingApp;
    }

    @PostMapping("")
    public void post(@RequestBody final Meeting meeting) {
        meetingApp.add(meeting);
    }

    @GetMapping
    public List<Meeting> get() {
        return meetingApp.getAll();
    }
    @GetMapping(path = "/{id}")
    public Meeting get(@PathVariable(name = "id") final long id) {
        return meetingApp.get(id);
    }

    @PutMapping(path = "/{id}")
    public Meeting update(@PathVariable(name = "id") final long id,
                         @RequestBody final Meeting meeting) {
        return meetingApp.update(id, meeting);
    }
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final long id) {
        meetingApp.delete(id);
    }
}
