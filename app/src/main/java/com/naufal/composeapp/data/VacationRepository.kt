package com.naufal.composeapp.data

import com.naufal.composeapp.model.Vacation
import com.naufal.composeapp.model.VacationData

class VacationRepository {
    fun getVacations():List<Vacation>{
        return VacationData.vacations
    }

    fun searchVacations(query: String ): List<Vacation>{
        return VacationData.vacations.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}