package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private long timeEntryId = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++timeEntryId);
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if(null != timeEntries.get(id)){
            timeEntry.setId(id);
            timeEntries.replace(id,timeEntry);
            return timeEntry;
        }else{
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        timeEntries.remove(id);
    }
}