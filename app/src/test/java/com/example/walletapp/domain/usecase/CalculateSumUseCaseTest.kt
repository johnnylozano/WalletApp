package com.example.walletapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculateListSumUseCaseTest{

    private val calculateSumUseCase = CalculateSumUseCase()

    @Test
    fun testResultWithEmptyListIsNull(){
        val result = calculateSumUseCase(emptyList())
        assertThat(result).isEqualTo(0f)
    }

    @Test
    fun testResultWithOneListNum(){
        val result = calculateSumUseCase(listOf(5.4f))
        assertThat(result).isEqualTo(5.4f)
    }

    @Test
    fun testResultWithMultipleListNumbers(){
        val result = calculateSumUseCase(listOf(5.4f,6.21f,5f))
        assertThat(result).isEqualTo(16.61f)
    }

    @Test
    fun testResultWithMultipleListNumbersWhichResultInANegativeNumber(){
        val result = calculateSumUseCase(listOf(5.4f,6.22f,-15f))
        assertThat(result).isEqualTo(-3.38f)
    }

}