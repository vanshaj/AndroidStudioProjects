package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase.db.Student
import com.example.roomdatabase.db.StudentDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isListItemClicked = false

    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: RecyclerViewAdapter

    private lateinit var selectedStudent: Student


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        val dao = StudentDatabase.getInstance(application).StudentDao
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)
        binding.btnSave.setOnClickListener {
            if (isListItemClicked) {
                updateStudentData()
                clearInput()
            } else {
                saveStudentData()
                clearInput()
            }
        }

        binding.btnClear.setOnClickListener {
            if (isListItemClicked) {
                deleteStudentData()
                clearInput()
            } else {
                clearInput()
            }
        }

        initRecyclerView()
    }

    private fun saveStudentData() {
        viewModel.insertStudent(
            Student(
                0,
                binding.etName.text.toString(),
                binding.etEmail.text.toString()
            )
        )
    }

    private fun updateStudentData() {
        viewModel.updateStudent(
            Student(
                selectedStudent.Id,
                binding.etName.text.toString(),
                binding.etEmail.text.toString()
            )
        )
        binding.btnSave.text = "Save"
        binding.btnClear.text = "Clear"
        isListItemClicked = false
    }

    private fun deleteStudentData() {
        viewModel.deleteStudent(
            Student(
                selectedStudent.Id,
                binding.etName.text.toString(),
                binding.etEmail.text.toString()
            )
        )
        binding.btnSave.text = "Save"
        binding.btnClear.text = "Clear"
        isListItemClicked = false
    }

    private fun clearInput() {
        binding.etName.setText("")
        binding.etEmail.setText("")
    }

    private fun initRecyclerView() {
        binding.rvStudent.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter{  selectedItem: Student ->
            listItemClicked(selectedItem)
        }
        binding.rvStudent.adapter = adapter

        displayStudentsList()
    }

    private fun displayStudentsList() {
        viewModel.students.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(student: Student) {
        selectedStudent = student
        binding.btnSave.text = "Update"
        binding.btnClear.text = "Delete"
        isListItemClicked = true
        binding.etName.setText(selectedStudent.name)
        binding.etEmail.setText(selectedStudent.email)
    }
}