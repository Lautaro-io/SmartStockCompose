package com.chelo.smartstock.notification

import com.chelo.smartstock.data.local.repositories.ProductRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class NotificationCheckerUseCase @Inject constructor(
    private val productRepo: ProductRepository,
) {

    suspend fun checkExpireDate(): Boolean {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val products = productRepo.getAllProducts().first()
        val result = products.any {
            val date = LocalDate.parse(it.expireDate, formatter)
            val should = ChronoUnit.DAYS.between(today, date) in 1..30
            should
        }


        return result
    }
}