package com.example.addemployee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {
    Context context;
    ArrayList<Employee> employeeList;
    private FirebaseServices fbs;

    public EmployeeAdapter(Context context, ArrayList<Employee> employeeList ){
        this.context = context;
        this.employeeList = employeeList;
        this.fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.employee_row,parent,false);
        return  new EmployeeAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.tvName.setText(employee.getName());
        holder.tvId.setText(employee.getId());
        holder.tvPhone.setText(employee.getPhone());
    }

    @Override
    public int getItemCount(){
        return employeeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPhone, tvId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameEmployeerow);
            tvId = itemView.findViewById(R.id.tvIdEmployeerow);
            tvPhone = itemView.findViewById(R.id.tvPhoneEmployeerow);
        }
    }
}
