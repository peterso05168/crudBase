package app.controller;

import app.model.Note;
import app.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteService.createNote(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteService.getNoteById(noteId);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {
        return noteService.updateNote(noteId, noteDetails);
    }
}
