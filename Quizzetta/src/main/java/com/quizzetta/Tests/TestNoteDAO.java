import com.quizzetta.DAOs.NoteDAO;
import com.quizzetta.Model.DatabaseConnector;
import com.quizzetta.Model.Note;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TestNoteDAO extends TestCase {

    private NoteDAO noteDao;
    private Note note1;
    private Note note2;
    private Note note3;
    private Note note4;
    private Note note5;

    public void setUp() throws SQLException, ClassNotFoundException {
        noteDao = new NoteDAO(DatabaseConnector.getConnection());
        note1 = new Note("text#1", 1, 2, new Timestamp(System.currentTimeMillis()));
        noteDao.addNote(note1);
        note2 = new Note("text#2", 2, 1, new Timestamp(System.currentTimeMillis()));
        noteDao.addNote(note2);
        note3 = new Note("text#3", 1, 2, new Timestamp(System.currentTimeMillis()));
        noteDao.addNote(note3);
        note4 = new Note("text#4", 2, 1, new Timestamp(System.currentTimeMillis()));
        noteDao.addNote(note4);
        note5 = new Note("text#5", 1, 2, new Timestamp(System.currentTimeMillis()));
        noteDao.addNote(note5);
    }

    public void testNote() throws SQLException {
        Note temp1 = noteDao.getNote(note1.getId());
        assertEquals(note1.getNoteText(), temp1.getNoteText());
        assertEquals(note1.getId(), temp1.getId());
        assertEquals(note1.getFromUserId(), temp1.getFromUserId());
        assertEquals(note1.getToUserId(), temp1.getToUserId());
        Note temp2 = noteDao.getNote(note2.getId());
        assertEquals(note2.getNoteText(), temp2.getNoteText());
        assertEquals(note2.getId(), temp2.getId());
        assertEquals(note2.getFromUserId(), temp2.getFromUserId());
        assertEquals(note2.getToUserId(), temp2.getToUserId());
        Note temp3 = noteDao.getNote(note3.getId());
        assertEquals(note3.getNoteText(), temp3.getNoteText());
        assertEquals(note3.getId(), temp3.getId());
        assertEquals(note3.getFromUserId(), temp3.getFromUserId());
        assertEquals(note3.getToUserId(), temp3.getToUserId());
        Note temp4 = noteDao.getNote(note4.getId());
        assertEquals(note4.getNoteText(), temp4.getNoteText());
        assertEquals(note4.getId(), temp4.getId());
        assertEquals(note4.getFromUserId(), temp4.getFromUserId());
        assertEquals(note4.getToUserId(), temp4.getToUserId());
        Note temp5 = noteDao.getNote(note5.getId());
        assertEquals(note5.getNoteText(), temp5.getNoteText());
        assertEquals(note5.getId(), temp5.getId());
        assertEquals(note5.getFromUserId(), temp5.getFromUserId());
        assertEquals(note5.getToUserId(), temp5.getToUserId());
    }

    public void testConversation() throws SQLException {
        List<Note> conversation = noteDao.getChat(1, 2);
        assertEquals(note1.getNoteText(), conversation.get(0).getNoteText());
        assertEquals(note2.getNoteText(), conversation.get(1).getNoteText());
        assertEquals(note3.getNoteText(), conversation.get(2).getNoteText());
        assertEquals(note4.getNoteText(), conversation.get(3).getNoteText());
        assertEquals(note5.getNoteText(), conversation.get(4).getNoteText());
    }
}
