import org.junit.Assert.*
import org.junit.Test

class MainKtTest {
    val mccard  = "Mastercard"
    val maestro = "Maestro"
    val visa = "Visa"
    val mir = "МИР"
    val vkpay = "VK Pay"
    val stringZeroCommission = "Комиссия составила 0 рублей"
    val stringDayLimitVk = "Превышен суточный лимит при оплате через VK Pay"
    val stringMonthLimitVk = "Превышен месячный лимит при оплате через VK Pay"
    val stringDayLimitCard = "Превышен суточный лимит при оплате картой"
    val stringMonthLimitCard = "Превышен месячный лимит при оплате картой"
    val zeroMonthLimit = 0


        //Тесты VK Pay
    @Test
    fun calculate_transactionVKPayZeroMonthLimit() {
        val result = calculate(vkpay, zeroMonthLimit, 10000)

        assertEquals(stringZeroCommission,result)
    }
    @Test
    fun calculate_transactionVKPayHalfMonthMonth() {
        val result = calculate(vkpay, 20000, 10000)

        assertEquals(stringZeroCommission,result)
    }

    @Test
    fun calculate_transactionVKPayFullMonthLimit() {
        val result = calculate(vkpay, 30000, 10000)

        assertEquals(stringZeroCommission,result)
    }

    @Test
    fun calculate_dailyLimitVKPay() {
        val result = calculate(vkpay, zeroMonthLimit, 15001)

        assertEquals(stringDayLimitVk,result)
    }

    @Test
    fun calculate_monthLimitVKPay() {
        val result = calculate(vkpay, 35001, 5000)

        assertEquals(stringMonthLimitVk, result)
    }

    //Тесты мастеркард
    @Test
    fun calculate_transactionMastercardNoCommisssionZeroMonth() {
        val result = calculate(mccard, zeroMonthLimit, 10000)

        assertEquals(stringZeroCommission,result)
    }
    @Test
    fun calculate_transactionMastercardNoCommisssionHalfMonthLimit() {
        val result = calculate(mccard, 35000, 10000)

        assertEquals(stringZeroCommission,result)
    }

    @Test
    fun calculate_transactionMastercardNoCommisssionFullMonth() {
        val result = calculate(mccard, 65000, 10000)

        assertEquals(stringZeroCommission,result)
    }

    @Test
    fun calculate_transactionMastercardWithCommisssionFullMonth() {
        val result = calculate(mccard, 75000, 10000)

        assertEquals("Комиссия составила 80 руб.", result)
    }

    @Test
    fun calculate_dailyLimitMastercard() {
        val result = calculate(mccard, zeroMonthLimit, 150001)

        assertEquals(stringDayLimitCard,result)
    }

    @Test
    fun calculate_monthLimitMastercard() {
        val transaction = 150000
        val monthLimit = 450001
        val result = calculate(mccard, monthLimit, transaction)

        assertEquals(stringMonthLimitCard, result)
    }

   //Maestro

    @Test
    fun calculate_transactionMaestroNoCommisssionZeroMonth() {
        val result = calculate(maestro, zeroMonthLimit, 10000)

        assertEquals(stringZeroCommission,result)
    }
    @Test
    fun calculate_transactionMaestroNoCommisssionHalfMonthLimit() {
        val result = calculate(maestro, 35000, 10000)

        assertEquals(stringZeroCommission,result)
    }

    @Test
    fun calculate_transactionMaestroNoCommisssionFullMonth() {
        val result = calculate(maestro, 65000, 10000)

        assertEquals(stringZeroCommission,result)
    }

    @Test
    fun calculate_transactionMaestroWithCommisssionFullMonth() {
        val result = calculate(maestro, 75000, 10000)

        assertEquals("Комиссия составила 80 руб.", result)
    }

    @Test
    fun calculate_dailyLimitMaestro() {
        val result = calculate(maestro, zeroMonthLimit, 150001)

        assertEquals(stringDayLimitCard,result)
    }

    @Test
    fun calculate_monthLimitMaestro() {
        val transaction = 150000
        val monthLimit = 450001
        val result = calculate(maestro, monthLimit, transaction)

        assertEquals(stringMonthLimitCard, result)
    }

    //Тесты Visa
    @Test
    fun calculate_commissionVisa() {

        val result = calculate(visa, zeroMonthLimit, 15000)

        assertEquals("Комиссия составила 112 руб.",result)
    }

    @Test
    fun calculate_minimalCommissionVisa() {

        val result = calculate(visa, zeroMonthLimit, 10)

        assertEquals("Комиссия составила 35 руб.",result)
    }
//Mir
    @Test
    fun calculate_commissionMir() {
        val result = calculate(mir, zeroMonthLimit, 15000)

        assertEquals("Комиссия составила 112 руб.",result)
    }

    @Test
    fun calculate_minimalCommissionMir() {
        val result = calculate(mir, zeroMonthLimit, 10)

        assertEquals("Комиссия составила 35 руб.",result)
    }

    //Error
    @Test
    fun calculate_error() {

        val result = calculate("", zeroMonthLimit, 10)

        assertEquals("Ошибка перевода!!!!",result)
    }


}