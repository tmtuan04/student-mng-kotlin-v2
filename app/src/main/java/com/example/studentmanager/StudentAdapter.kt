package com.example.studentmanager

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

class StudentAdapter(private val students: List<SinhVien>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTen: TextView = itemView.findViewById(R.id.tvTen)
        val tvMSSV: TextView = itemView.findViewById(R.id.tvMSSV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sinhvien, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val sv = students[position]
        holder.tvTen.text = sv.ten
        holder.tvMSSV.text = sv.mssv
    }
    override fun getItemCount(): Int = students.size
}
