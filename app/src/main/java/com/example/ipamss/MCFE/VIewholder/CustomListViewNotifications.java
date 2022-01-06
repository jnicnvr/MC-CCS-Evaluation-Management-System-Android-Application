package com.example.ipamss.MCFE.VIewholder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.ipamss.R;


public class CustomListViewNotifications extends ArrayAdapter<String> {

    private String[] course;
    private String[] code;
    private String[] subject;
    private String[] name;
    private String[] department;
    private String[] created_at;

    private Context context;

    public CustomListViewNotifications(Context context, String [] course, String [] code, String [] subject, String [] name, String [] department, String [] created_at) {
        super(context, R.layout.layout_notifications,course);

        Log.e("my_asset Custview","");

        this.context = context;
        this.course = course;
        this.code = code;
        this.subject = subject;
        this.name = name;
        this.department = department;
        this.created_at = created_at;
    }

    @NonNull
    @Override

    public android.view.View getView(int position, @NonNull android.view.View convertView, @NonNull ViewGroup parent) {

        android.view.View r = convertView;
        ViewHolder viewHolder = null;

        if (r == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            r = layoutInflater.inflate(R.layout.layout_notifications, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.tv_subject.setText(code[position]);
        viewHolder.tv_faculty.setText(name[position]);
        viewHolder.tv_course.setText(course[position]);
        viewHolder.tv_date.setText(created_at[position]);

        return r;
    }

    class ViewHolder {
        TextView tv_subject;
        TextView tv_faculty;
        TextView tv_course;
        TextView tv_date;

        ViewHolder(android.view.View v) {
            tv_subject = (TextView) v.findViewById(R.id.tv_subject);
            tv_faculty = (TextView) v.findViewById(R.id.tv_faculty);
            tv_course = (TextView) v.findViewById(R.id.tv_course);
            tv_date = (TextView) v.findViewById(R.id.tv_date);
        }
    }
}
