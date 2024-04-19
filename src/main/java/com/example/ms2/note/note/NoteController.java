package com.example.ms2.note.note;

import com.example.ms2.note.notebook.Notebook;
import com.example.ms2.note.notebook.NotebookReposiotry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/{notebookId}/notes")
public class NoteController {

    private final NoteRepository noteRepository;
    private final NotebookReposiotry notebookReposiotry;
    private final NoteService noteService;


    @PostMapping("/write")
    public String write(@PathVariable("notebookId") Long notebookId) {
        Notebook notebook = notebookReposiotry.findById(notebookId).orElseThrow();
        noteService.saveDefault(notebook);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("notebookId") Long notebookId, @PathVariable Long id) {
        Note note = noteRepository.findById(id).get();
        List<Notebook> notebookList = notebookReposiotry.findAll();
        Notebook targetNotebook = notebookReposiotry.findById(notebookId).get();

        model.addAttribute("targetNote", note);
        model.addAttribute("noteList", noteRepository.findAll());
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetNotebook", targetNotebook);

        return "main";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("notebookId") Long notebookId, @PathVariable("id") Long id, String title, String content) {
        Note note =noteRepository.findById(id).get();

        if (title.trim().length() == 0) {
            title = "제목 없음";
        }

        note.setTitle(title);
        note.setContent(content);

        noteRepository.save(note);

        return "redirect:/books/%d/notes/%d".formatted(notebookId, id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("notebookId") Long notebookId, @PathVariable("id") Long id) {
        noteRepository.deleteById(id);

        return "redirect:/";
    }


}
