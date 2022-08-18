package com.juanp.mvisample

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanp.mvisample.ResultState.*
import com.juanp.mvisample.data.Company
import com.juanp.mvisample.data.database.RoomDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(AuthViewState())
    val viewState: StateFlow<AuthViewState>
        get() = _viewState
    private lateinit var roomDataSource: RoomDataSource
    var idCompany = 0

    fun init(context: Context) {
        roomDataSource = RoomDataSource(context)
    }

    fun save(name: String, address: String, nit: String, phone: String) {
        if (validFields(name, address, nit, phone))
            viewModelScope.launch {
                roomDataSource.saveCompany(Company(name, address, nit, phone))
                _viewState.value = AuthViewState(SUCCESS_SAVE, null)
            }
        else showError()
    }

    private fun showError() {
        _viewState.value = AuthViewState(ERROR, null)
    }

    private fun validFields(name: String, address: String, nit: String, phone: String) =
        name.isNotEmpty() && address.isNotEmpty() && nit.isNotEmpty() && phone.isNotEmpty()

    fun getCompanies() {
        viewModelScope.launch {
            roomDataSource.getPopularCompany().collect {
                _viewState.value = AuthViewState(null, it)
            }
        }
    }

    fun edit(name: String, address: String, nit: String, phone: String) {
        if (validFields(name, address, nit, phone))
            viewModelScope.launch {
                roomDataSource.update(Company(name, address, nit, phone, idCompany))
                _viewState.value = AuthViewState(SUCCESS_EDIT, null)
            }
        else showError()
    }

    fun delete() {
        viewModelScope.launch {
            roomDataSource.delete(idCompany)
            _viewState.value = AuthViewState(SUCCESS_DELETE, null)
        }
    }
}