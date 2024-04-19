package com.example.ms2.note.note;

import com.example.ms2.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public Note saveDefault(Notebook notebook) {
        Note note = new Note();
        note.setTitle("new title");
        note.setContent("");
        note.setCreateDate(LocalDateTime.now());
        note.setNotebook(notebook);

        return noteRepository.save(note);
    }
}
