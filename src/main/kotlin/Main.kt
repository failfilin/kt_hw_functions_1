fun main() {
    val cardType = "Maestro" // тип карты/счета при переводе
    val alreadyPayAmmount = 600_000 //сумма переводов в это месяце
    val payAmmount = 1500 // размер платежа
    val vkOnesLimit = 15_000// размер лимита на разовый платеж ВК
    val vkMonthLimit = 40_000// размер на месячные платежи для вк
    val otherOnesLimit = 150_000 // размер на разовый платеж для всех систем
    val otherMonthLimit = 600_000 // размер на месячные платежи для остальных систем
    when {
        cardType == "VK Pay" && (alreadyPayAmmount + payAmmount) > vkMonthLimit || cardType == "VK Pay" && payAmmount > vkOnesLimit -> println(
            "Превышены лимиты"
        )

        cardType != "VK Pay" && (alreadyPayAmmount + payAmmount) > otherMonthLimit || cardType != "VK Pay" && payAmmount > otherOnesLimit -> println(
            "Превышены лимиты"
        )

        else -> println("Размер комиссии равен ${commisionAmount(cardType, alreadyPayAmmount, payAmmount)}")
    }

}

fun commisionAmount(cardType: String = "VK Pay", alreadyPayAmount: Int = 0, payAmount: Int): Double {
    val mirVisaComission = 0.0075 // процент комиссии для карт мир и виза
    val mirVisaMinComision = 35.0 // минимальный размер комиссии для карт мир и виза
    val masterMaestroComision = 0.006 //  процент комиссии ля мастеркард и маестро
    val masterMaestroAdditionalComision = 20.0 // постоянная надавка к комиссии для карт маестро и мастеркард
    val masterMaestroLimit = 75_000.0
    return when {
        cardType in arrayOf(
            "Mastercard",
            "Maestro"
        ) && (alreadyPayAmount + payAmount) > masterMaestroLimit -> if (alreadyPayAmount > masterMaestroLimit) payAmount * masterMaestroComision + masterMaestroAdditionalComision else (alreadyPayAmount + payAmount - masterMaestroLimit) * masterMaestroComision + masterMaestroAdditionalComision

        cardType in arrayOf(
            "Visa",
            "Мир"
        ) -> if (payAmount * mirVisaComission < mirVisaMinComision) mirVisaMinComision else payAmount * mirVisaComission

        else -> 0.0
    }
}