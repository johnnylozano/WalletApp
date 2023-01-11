package com.example.walletapp.domain.usecase

import org.junit.Assert.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class TransactionUseCaseTest {
    private val transactionValueInputUseCase = TransactionUseCase()

    @Test
    fun testZeroWithoutDecimalReturnsFalse() {
        val result = transactionValueInputUseCase("0")
        assertThat(result).isFalse()
    }

    @Test
    fun testZeroWithDecimalReturnsFalse() {
        val result = transactionValueInputUseCase("0.00")
        assertThat(result).isFalse()
    }

    @Test
    fun testMultipleZeroWithDecimalReturnsFalse() {
        val result = transactionValueInputUseCase("000.00")
        assertThat(result).isFalse()
    }

    @Test
    fun testZeroBeforeDecimalAndValueAfterDecimal() {
        val result = transactionValueInputUseCase("0.50")
        assertThat(result).isTrue()
    }

    @Test
    fun testZeroBeforeDecimalAndASecondValueAfterDecimalReturnsTrue(){
        val result = transactionValueInputUseCase("0.05")
        assertThat(result).isTrue()
    }

    @Test
    fun testNormalSingleValueWithoutDecimalReturnsTrue(){
        val result = transactionValueInputUseCase("5")
        assertThat(result).isTrue()
    }

    @Test
    fun testNormalValueWithOneDecimalValueAfterReturnsTrue(){
        val result = transactionValueInputUseCase("5.1")
        assertThat(result).isTrue()
    }

    @Test
    fun testNormalValueWithTwoDecimalValuesAfterReturnsTrue(){
        val result = transactionValueInputUseCase("5.76")
        assertThat(result).isTrue()
    }

    @Test
    fun testTooLongValueReturnsFalse(){
        val result = transactionValueInputUseCase("5.331")
        assertThat(result).isFalse()
    }

    @Test
    fun testNoValueReturnsFalse(){
        val result = transactionValueInputUseCase("5283a")
        assertThat(result).isFalse()
    }

    @Test
    fun testLeadingZeroValueReturnsTrue(){
        val result = transactionValueInputUseCase("05.54")
        assertThat(result).isTrue()
    }
}
