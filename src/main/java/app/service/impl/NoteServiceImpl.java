package app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.exception.ResourceNotFoundException;
import app.model.Note;
import app.repository.NoteRepository;
import app.service.NoteService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Transactional(readOnly = true)
	@Override
	public List<Note> getAllNotes() {
    	log.info("getAllNotes() start");
    	List<Note> noteLst = noteRepository.findAll();
    	log.info("getAllNotes() end - returnVal: " + noteLst);
		return null;
	}

    @Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Note createNote(Note note) {
		log.info("createNote() start - note: " + note);
		Note returnNote = noteRepository.save(note);
		log.info("createNote() end - returnVal: " + returnNote);
		return returnNote;
	}

	@Transactional(readOnly = true)
	@Override
	public Note getNoteById(Long noteId) {
		log.info("getNoteById() start - noteId: " + noteId);
		Note returnNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
		log.info("getNoteById() end - returnVal: " + returnNote);
		return returnNote;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Note updateNote(Long noteId, Note noteDetails) {
		log.info("updateNote() start - noteId: " + noteId);
		Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        log.info("updateNote() end - returnVal: " + updatedNote);
		return updatedNote;
	}
}
