package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Long currentId = 1L;
    private HashMap<Long, TimeEntry> timeEntries = null;

    public InMemoryTimeEntryRepository(){
        timeEntries=new HashMap<>();
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()

        );
        timeEntries.put(id, newTimeEntry);
        return newTimeEntry;

    }

    @Override
    public TimeEntry find(Long id) {
        if(timeEntries.containsKey(id))
        {
            return timeEntries.get(id);
        }
        else return null;
    }

    @Override
    public List<TimeEntry> list()   {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (find(id)== null) return null;
        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntries.replace(id,updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntries.remove(id);

    }
}
