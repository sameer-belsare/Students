package com.students;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

/**
 * Created by sameer.belsare on 8/2/17.
 */

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Student> students;
    private Context mContext;
    private View.OnClickListener mItemClickListener;

    public StudentsAdapter(List<Student> studentList, Context context,
                           View.OnClickListener clickListener) {
        students = studentList;
        mContext = context;
        mItemClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.student_item, parent,
                false);
        view.setOnClickListener(mItemClickListener);
        return new StudentsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudentsListViewHolder viewHolder = (StudentsListViewHolder) holder;
        Student student = students.get(position);
        String name = student.getFirstName();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(name.substring(0,1), R.color.colorAccent);
        //Glide.with(mContext).load(/*Uri.parse(imgPath)*/drawable).asBitmap().placeholder(R.mipmap.ic_launcher).into(viewHolder.profileImage);
        viewHolder.profileImage.setImageDrawable(drawable);
        viewHolder.firstName.setText(student.getFirstName());
        viewHolder.lastName.setText(student.getLastName());
        viewHolder.itemView.setTag(student.getRollNumber());
    }

    @Override
    public int getItemCount() {
        return (students != null ? students.size() : 0);
    }

    private static class StudentsListViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImage;
        public TextView firstName;
        public TextView lastName;

        public StudentsListViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            firstName = (TextView) itemView.findViewById(R.id.firstName);
            lastName = (TextView) itemView.findViewById(R.id.lastName);
        }
    }

}
