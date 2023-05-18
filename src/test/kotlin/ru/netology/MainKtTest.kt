package ru.netology

import commisionAmount
import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun commisionAmount() {
        val cardType = "Maestro" // тип карты/счета при переводе
        val alreadyPayAmmount = 600_000 //сумма переводов в это месяце
        val payAmmount = 1500 // размер платежа
        val result = commisionAmount(cardType, alreadyPayAmmount, payAmmount)
        assertEquals(29,result.toInt())
    }
}