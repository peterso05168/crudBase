package app.service;

import java.util.List;
import app.model.Note;

public interface NoteService {

	List<Note> getAllNotes();
	
	Note createNote(Note note);
	
	Note getNoteById(Long noteId);
	
	Note updateNote(Long noteId, Note noteDetails);
}
