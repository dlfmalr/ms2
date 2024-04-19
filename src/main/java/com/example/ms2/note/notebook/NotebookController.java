package com.example.ms2.note.notebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class NotebookController {
    @PostMapping("/books/write")
    public String write() {
        return null;
    }
}
