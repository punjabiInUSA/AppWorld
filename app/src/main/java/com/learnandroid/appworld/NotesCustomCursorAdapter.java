package com.learnandroid.appworld;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotesCustomCursorAdapter extends CursorAdapter {


    public NotesCustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).
                inflate(R.layout.activity_custom_notes_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String textNote = cursor.getString(cursor.getColumnIndex(NotesDatabaseHelper.NOTE_TEXT));

        // 10 is the ascii value for a line feed character
        int pos = textNote.indexOf(10);

        if(pos !=  -1) {
            textNote = textNote.substring(0, pos) + "...";
        }

        TextView actualNoteTextView = (TextView) view.findViewById(R.id.tvCustomNote);
        actualNoteTextView.setText(textNote);
    }
}
