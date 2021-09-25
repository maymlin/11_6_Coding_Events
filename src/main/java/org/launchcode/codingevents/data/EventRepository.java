package org.launchcode.codingevents.data;

// https://www.youtube.com/watch?v=YAISqYMOIAw
// Added in 17.2 Accessing Data
// Could also be called EventDAO

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

}
