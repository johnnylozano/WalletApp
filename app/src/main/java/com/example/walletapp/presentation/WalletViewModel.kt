package com.example.walletapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.walletapp.domain.usecase.CalculateSumUseCase
import com.example.walletapp.domain.usecase.TransactionUseCase
import com.example.walletapp.presentation.state.TransactionDialogState
import com.example.walletapp.presentation.state.TransactionUiItem
import com.example.walletapp.presentation.types.TransactionType
import com.example.walletapp.ui.theme.green
import com.example.walletapp.ui.theme.redOrange
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class WalletViewModel : ViewModel() {

    var transactionDialogState by mutableStateOf(TransactionDialogState())
        private set

    var totalAmountBeforeComma by mutableStateOf(0)
        private set

    var totalAmountAfterComma by mutableStateOf(0)
        private set

    val transactionList = mutableStateListOf<TransactionUiItem>()

    private val parseTransactionValueInputUseCase = TransactionUseCase()
    private val calculateListSumUseCase = CalculateSumUseCase()

    fun onDepositClick(){
        transactionDialogState = transactionDialogState.copy(
            isOpen = true, type = TransactionType.Deposit
        )
    }

    fun onWithdrawClick(){
        transactionDialogState = transactionDialogState.copy(
            isOpen = true, type = TransactionType.Withdraw
        )
    }

    fun onDismissDialog(){
        transactionDialogState = TransactionDialogState()
    }

    fun onTransactionValueChange(newValue: String){
        val validationResult = parseTransactionValueInputUseCase(newValue)
        transactionDialogState = transactionDialogState.copy(currentValueInput = newValue)
        transactionDialogState = transactionDialogState.copy(isConfirmButtonEnabled = validationResult)
    }

    fun onTransactionConfirm() {
        val factor = if (transactionDialogState.type == TransactionType.Withdraw) -1 else 1
        val transactionFloatValue = transactionDialogState.currentValueInput.toFloatOrNull()?.times(factor) ?: return

        val timestamp = System.currentTimeMillis()
        val date = Date(timestamp)

        val transactionUiItem = TransactionUiItem(
            description = if(transactionDialogState.type == TransactionType.Withdraw) "Withdraw" else "Deposit",
            value = transactionFloatValue,
            date = date.toString(),
            color = if(transactionDialogState.type == TransactionType.Withdraw) redOrange else green
        )
        transactionList.add(transactionUiItem)
        setTotalAmount()
        onDismissDialog()
    }

    private fun setTotalAmount(){
        val totalAmount = calculateListSumUseCase(
            transactionList.map { it.value }
        )
        val valueMultipliedWith10Pow2 = (totalAmount * 100).roundToInt()
        totalAmountBeforeComma = valueMultipliedWith10Pow2 / 100
        totalAmountAfterComma = valueMultipliedWith10Pow2.absoluteValue % 100
    }

}