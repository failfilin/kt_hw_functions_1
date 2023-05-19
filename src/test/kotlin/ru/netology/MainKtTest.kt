package ru.netology

import commisionAmount
import limitCheck
import org.intellij.lang.annotations.JdkConstants.FontStyle
import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun commisionAmountMaestroMasterOverALreadyLimit() { // лимит уже превышен
        val cardType = "Maestro" // тип карты/счета при переводе
        val alreadyPayAmmount = 600_000 //сумма переводов в это месяце
        val payAmmount = 1500 // размер платежа
        val result = commisionAmount(cardType, alreadyPayAmmount, payAmmount)
        assertEquals(29, result.toInt())
    }
    @Test
    fun commisionAmountMaestroMasterOverLimit() {// пересекается лимит при новом платеже
        val cardType = "Maestro" // тип карты/счета при переводе
        val alreadyPayAmmount = 74_000 //сумма переводов в это месяце
        val payAmmount = 2900 // размер платежа
        val result = commisionAmount(cardType, alreadyPayAmmount, payAmmount)
        assertEquals(31, result.toInt())
    }
    @Test
    fun commisionAmountMaestroMasterUnderLimit() {// пересекается лимит при новом платеже
        val cardType = "Maestro" // тип карты/счета при переводе
        val alreadyPayAmmount = 60_000 //сумма переводов в это месяце
        val payAmmount = 2900 // размер платежа
        val result = commisionAmount(cardType, alreadyPayAmmount, payAmmount)
        assertEquals(0, result.toInt())
    }

    @Test
    fun commisionAmountVisaMirOverMinCommision() {
        val cardType = "Visa" // тип карты/счета при переводе
        val alreadyPayAmmount = 6_000 //сумма переводов в это месяце
        val payAmmount = 6000 // размер платежа
        val result = commisionAmount(cardType, alreadyPayAmmount, payAmmount)
        assertEquals(45, result.toInt())
    }
    @Test
    fun commisionAmountVisaMirUnderMinCommision() {
        val cardType = "Visa" // тип карты/счета при переводе
        val alreadyPayAmmount = 6_000 //сумма переводов в это месяце
        val payAmmount = 1500 // размер платежа
        val result = commisionAmount(cardType, alreadyPayAmmount, payAmmount)
        assertEquals(35, result.toInt())
    }

    @Test
    fun commisionAmountElse() {
        val cardType = "SomeElse" // тип карты/счета при переводе
        val alreadyPayAmmount = 6_000 //сумма переводов в это месяце
        val payAmmount = 1500 // размер платежа
        val result = commisionAmount(cardType, alreadyPayAmmount, payAmmount)
        assertEquals(0, result.toInt())
    }
    @Test
    fun limitCheckVkMonth(){
        val cardType = "VK Pay" // тип карты/счета при переводе
        val alreadyPayAmmount = 45_000 //сумма переводов в это месяце
        val payAmmount = 1500 // размер платежа
        val result = limitCheck(cardType, alreadyPayAmmount, payAmmount)
        assertEquals("Превышены лимиты", result)
    }
    @Test
    fun limitCheckVkDaily(){
        val cardType = "VK Pay" // тип карты/счета при переводе
        val alreadyPayAmmount = 5_000 //сумма переводов в это месяце
        val payAmmount = 16000 // размер платежа
        val result = limitCheck(cardType, alreadyPayAmmount, payAmmount)
        assertEquals("Превышены лимиты", result)
    }
    @Test
    fun limitCheckOtherMonth(){
        val cardType = "Some Other" // тип карты/счета при переводе
        val alreadyPayAmmount = 5_000 //сумма переводов в это месяце
        val payAmmount = 700_000 // размер платежа
        val result = limitCheck(cardType, alreadyPayAmmount, payAmmount)
        assertEquals("Превышены лимиты", result)
    }
    @Test
    fun limitCheckOtherDaily(){
        val cardType = "Some Other" // тип карты/счета при переводе
        val alreadyPayAmmount = 155_000 //сумма переводов в это месяце
        val payAmmount = 300_000 // размер платежа
        val result = limitCheck(cardType, alreadyPayAmmount, payAmmount)
        assertEquals("Превышены лимиты", result)
    }
    @Test
    fun limitCheckSuccess(){
        val cardType = "Мир" // тип карты/счета при переводе
        val alreadyPayAmmount = 10_000 //сумма переводов в это месяце
        val payAmmount = 30_000 // размер платежа
        val result = limitCheck(cardType, alreadyPayAmmount, payAmmount)
        assertEquals("Размер комиссии равен 225.0", result)
    }

}