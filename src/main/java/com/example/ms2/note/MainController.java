package com.example.ms2.note;

import com.example.ms2.note.note.Note;
import com.example.ms2.note.note.NoteRepository;
import com.example.ms2.note.note.NoteService;
import com.example.ms2.note.notebook.Notebook;
import com.example.ms2.note.notebook.NotebookReposiotry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NoteRepository noteRepository;
    private final NotebookReposiotry notebookReposiotry;
    private final NoteService noteService;

    @RequestMapping("/")
    public String main(Model model) {
        List<Note> noteList = noteRepository.findAll();
        List<Notebook> notebookList = notebookReposiotry.findAll();
        Notebook targetNotebook = notebookList.get(0);

        if (notebookList.isEmpty()) {
            Notebook notebook = new Notebook();
            notebook.setName("새노트");
            notebookReposiotry.save(notebook);

            return "redirect:/";
        }

        if (noteList.isEmpty()) {
            noteService.saveDefault(targetNotebook);
            return "redirect:/";
        }

        model.addAttribute("noteList", noteList);
        model.addAttribute("targetNote", noteList.get(0));
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetNotebook", targetNotebook);

        return "main";
    }
}
