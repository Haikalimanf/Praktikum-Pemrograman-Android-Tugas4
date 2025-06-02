package com.example.fromfgdmvvmprojects.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fromfgdmvvmprojects.model.AttendanceModel

class AttendanceViewModel: ViewModel() {

    private val _attendanceData = MutableLiveData<AttendanceModel>()
    val attendanceData: LiveData<AttendanceModel> get() = _attendanceData

    // Fungsi menyimpan data peserta
    fun setAttendanceData(model: AttendanceModel) {
        _attendanceData.value = model
    }


}