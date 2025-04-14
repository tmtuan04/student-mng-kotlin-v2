package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.ui.theme.StudentManagerTheme

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = StudentAdapter(studentList) { student ->
            showDeleteDialog(student)
        }

        val rvStudents: RecyclerView = findViewById(R.id.rvStudents)
        rvStudents.adapter = adapter
        rvStudents.layoutManager = LinearLayoutManager(this)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_student, null)
        val edtName = dialogView.findViewById<EditText>(R.id.edtName)
        val edtId = dialogView.findViewById<EditText>(R.id.edtId)

        AlertDialog.Builder(this)
            .setTitle("Thêm sinh viên")
            .setView(dialogView)
            .setPositiveButton("Thêm") { _, _ ->
                val name = edtName.text.toString().trim()
                val id = edtId.text.toString().trim()

                if (name.isNotEmpty() && id.isNotEmpty()) {
                    val newStudent = Student(name, id)
                    studentList.add(0, newStudent)
                    adapter.notifyItemInserted(0)
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    private fun showDeleteDialog(student: Student) {
        AlertDialog.Builder(this)
            .setTitle("Xóa sinh viên")
            .setMessage("Bạn có muốn xoá ${student.name} (MSSV: ${student.studentId})?")
            .setPositiveButton("Xoá") { _, _ ->
                val index = studentList.indexOf(student)
                if (index != -1) {
                    studentList.removeAt(index)
                    adapter.notifyItemRemoved(index)
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}
