package org.example.daos;

import org.example.entities.Programme;

import java.util.List;

public interface ProgrammeDao {
    void add(Programme programme);
    void update(Programme programme);
    void delete(Programme programme);
    List<Programme> showAll();
    void showInfo();
    Programme getById(String output);
}
