package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvStudents: RecyclerView
    private lateinit var btnAdd: Button
    private val students = mutableListOf<SinhVien>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // ✅ đặt trước

        rvStudents = findViewById(R.id.rvStudents)
        btnAdd = findViewById(R.id.btnAdd)

        // Thêm demo dữ liệu
        students.add(SinhVien("Nguyễn Văn A", "20220000"))
        students.add(SinhVien("Trần Thị B", "20210000"))
        students.add(SinhVien("Lê Văn C", "20230000"))

        val adapter = StudentAdapter(students)
        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = adapter

        // Nút thêm sinh viên
        btnAdd.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_student, null)
            val etTen = dialogView.findViewById<EditText>(R.id.etTen)
            val etMSSV = dialogView.findViewById<EditText>(R.id.etMSSV)

            AlertDialog.Builder(this)
                .setTitle("Thêm sinh viên")
                .setView(dialogView)
                .setPositiveButton("Thêm") { _, _ ->
                    val ten = etTen.text.toString()
                    val mssv = etMSSV.text.toString()
                    if (ten.isNotBlank() && mssv.isNotBlank()) {
                        students.add(SinhVien(ten, mssv))
                        rvStudents.adapter?.notifyItemInserted(students.size - 1)
                    } else {
                        Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Huỷ", null)
                .show()
        }
    }
}
