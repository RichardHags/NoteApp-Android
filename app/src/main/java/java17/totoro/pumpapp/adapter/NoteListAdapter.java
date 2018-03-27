package java17.totoro.pumpapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import java17.totoro.pumpapp.R;
import java17.totoro.pumpapp.shared.Note;

/**
 * Created by Totoro on 2018-03-19.
 */

public class NoteListAdapter extends ArrayAdapter<Note> {

    private Context nContext;
    private int nResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView title;
        TextView description;
        TextView date;
    }

    public NoteListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> objects) {
        super(context, resource, objects);
        nContext = context;
        nResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String Description = getItem(position).getDescription();
        String date = getItem(position).getDate();
        String noteText = getItem(position).getNoteText();

        Note note = new Note(title, Description, date, noteText);

        //Create the view result for showing the animation
        final View result;

        //Viewholder object
        ViewHolder holder;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(nContext);
            convertView = inflater.inflate(nResource, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.note_tvTitle);
            holder.description = (TextView) convertView.findViewById(R.id.note_tvDescription);
            holder.date = (TextView) convertView.findViewById(R.id.note_tvDate);

            result = convertView;

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation anim = AnimationUtils.loadAnimation(nContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(anim);
        lastPosition = position;

        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.date.setText(note.getDate());

        /*TextView tvTitle = (TextView) convertView.findViewById(R.id.note_tvTitle);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.note_tvDescription);
        TextView tvDate = (TextView) convertView.findViewById(R.id.note_tvDate);

        tvTitle.setText(title);
        tvDescription.setText(Description);
        tvDate.setText(date);*/

        return convertView;
    }

}


















