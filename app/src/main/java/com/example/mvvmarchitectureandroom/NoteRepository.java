package com.example.mvvmarchitectureandroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    //member variables
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    // constructor to assign those variables
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);

        //assigning NoteDao
        noteDao = database.noteDao();//abstract method that we create in our database class. so we call note dao and we will get it back
        allNotes = noteDao.getAllNotes();
    }

    // Database Operations
    //this methods are the api repository exposes to the outside
    //so our view model will call this operations and it doesn't have to care what exactly is happening in here
    //and hold the data we fetched with AsyncTask
    public void insert(Note note) {
        //provide a method body
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    //we will automatically excute data from note dao.
    // room will automatically  excute database returns the live data from on the background threat
    // so we don't have to take care of it
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //but for the database operations we have to execute the code back on threat on our self's
    // Becouse room doesn't allow operations on the background threat
    //so we are using AsyncTask
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
