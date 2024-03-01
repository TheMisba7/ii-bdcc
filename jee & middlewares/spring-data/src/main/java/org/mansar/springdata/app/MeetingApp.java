package org.mansar.springdata.app;

import org.mansar.springdata.dao.MeetingRepository;
import org.mansar.springdata.model.Meeting;
import org.springframework.stereotype.Service;

@Service
public class MeetingApp extends AbstractApp<MeetingRepository, Meeting>{
    public MeetingApp(MeetingRepository repository) {
        super(repository);
    }

    public Meeting update(long id, Meeting meeting) {
        return null;
    }
}
