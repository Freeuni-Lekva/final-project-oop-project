package com.quizzetta.DAOs;

import com.quizzetta.Model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    private Connection myConn;

    public NoteDAO(Connection myConn) {
        this.myConn = myConn;
    }

    public void addNote (Note note) throws SQLException {
        PreparedStatement stm;
        try {
            stm = myConn.prepareStatement("INSERT INTO notes (note_text, from_id, to_id, sent_time)" +
                    "VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, note.getNoteText());
            stm.setLong(2, note.getFromUserId());
            stm.setLong(3, note.getToUserId());
            stm.setTimestamp(4, note.getSentTime());
            stm.executeUpdate();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long noteId = res.getLong(1);
            note.setId(noteId);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Note getNote (long noteId) throws SQLException {
        PreparedStatement stm;

        try {
            stm = myConn.prepareStatement("SELECT * FROM notes WHERE id = ?");
            stm.setLong(1, noteId);
            ResultSet res = stm.executeQuery();
            res.next();
            return new Note(res.getLong("id"), res.getString("note_text"),
                    res.getLong("from_id"), res.getLong("to_id"),
                    res.getTimestamp("sent_time"));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Note> getChat (long user1, long user2) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM notes WHERE (from_id = ? AND to_id = ?)" +
                                                        " OR (to_id = ? AND from_id = ?) ORDER BY sent_time");
        stm.setLong(1, user1);
        stm.setLong(2, user2);
        stm.setLong(3, user1);
        stm.setLong(4, user2);
        ResultSet res = stm.executeQuery();
        List<Note> chat = new ArrayList<>();
        while (res.next()) {
            chat.add(getNote(res.getLong("id")));
        }
        return chat;
    }

    public void removeNote (long noteId) {
        try {
            PreparedStatement stm = myConn.prepareStatement("DELETE FROM notes WHERE id = ?");
            stm.setLong(1, noteId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
