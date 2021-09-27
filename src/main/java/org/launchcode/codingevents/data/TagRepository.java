package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Created in 18.5

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
}
