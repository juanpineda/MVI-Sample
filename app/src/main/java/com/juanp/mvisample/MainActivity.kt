package com.juanp.mvisample

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.juanp.mvisample.ResultState.*
import com.juanp.mvisample.common.setupAdapter
import com.juanp.mvisample.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vm by viewModels<MainViewModel>()
    private lateinit var coursesAdapter: SelectCoursesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            vm.init(this@MainActivity)
            vm.getCompanies()
            coursesAdapter = SelectCoursesAdapter(ArrayList()) { company ->
                username.setText(company.name)
                address.setText(company.address)
                nit.setText(company.nit)
                phone.setText(company.phone)
                vm.idCompany = company.id
                showEditionButtons()
            }
            save.setOnClickListener {
                vm.save(
                    username.text.toString(),
                    address.text.toString(),
                    nit.text.toString(),
                    phone.text.toString()
                )
            }
            edit.setOnClickListener {
                vm.edit(
                    username.text.toString(),
                    address.text.toString(),
                    nit.text.toString(),
                    phone.text.toString()
                )
            }
            delete.setOnClickListener {
                vm.delete()
            }
            cancel.setOnClickListener {
                hideEditionButtons()
                clearFields()
            }
            coursesRecyclerView.setupAdapter(this@MainActivity)
            coursesRecyclerView.adapter = coursesAdapter
        }

        addRepeatingJob(Lifecycle.State.STARTED) {
            vm.viewState.collect(::render)
        }
    }

    private fun render(authViewState: AuthViewState) = with(binding) {
        when (authViewState.error) {
            SUCCESS_SAVE -> {
                clearFields()
                showMessage(getString(R.string.saved))
            }
            SUCCESS_EDIT -> {
                hideEditionButtons()
                clearFields()
                showMessage(getString(R.string.edited))
            }
            ERROR -> showMessage(getString(R.string.incompleteFields))
            SUCCESS_DELETE -> {
                hideEditionButtons()
                clearFields()
                showMessage(getString(R.string.deleted))
            }
        }
        authViewState.result?.let {
            coursesAdapter.swapCourses(it)
        }
    }

    private fun showMessage(message: String) =
        Toast.makeText(
            this@MainActivity,
            message,
            LENGTH_LONG
        ).show()

    private fun showEditionButtons() = with(binding) {
        edit.visibility = VISIBLE
        save.visibility = GONE
        cancel.visibility = VISIBLE
        delete.visibility = VISIBLE
    }

    private fun hideEditionButtons() = with(binding) {
        edit.visibility = GONE
        save.visibility = VISIBLE
        cancel.visibility = GONE
        delete.visibility = GONE
    }

    private fun clearFields() = with(binding) {
        username.setText("")
        address.setText("")
        nit.setText("")
        phone.setText("")
    }
}