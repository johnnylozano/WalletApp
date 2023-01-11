package com.example.walletapp.presentation.state

import com.example.walletapp.presentation.types.TransactionType

data class TransactionDialogState(
    val isOpen:Boolean = false,
    val type: TransactionType = TransactionType.Deposit,
    val isConfirmButtonEnabled:Boolean = false,
    val currentValueInput:String = ""
)