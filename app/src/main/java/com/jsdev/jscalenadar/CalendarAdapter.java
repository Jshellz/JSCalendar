package com.jsdev.jscalenadar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<String> dayOFMonth;
    private final onItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> dayOFMonth, CalendarAdapter.onItemListener onItemListener)
    {
        this.dayOFMonth = dayOFMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.calendar_cell,parent,false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);

        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        holder.dayOfMonth.setText(dayOFMonth.get(position));

    }

    @Override
    public int getItemCount() {
        return dayOFMonth.size();
    }

    public interface onItemListener {

        void itemClick(int position, String dayText);

    }

}
