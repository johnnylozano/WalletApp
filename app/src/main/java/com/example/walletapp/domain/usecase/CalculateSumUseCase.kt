package com.example.walletapp.domain.usecase

class CalculateSumUseCase {

    operator fun invoke(list: List<Float>):Float {
        var result = 0f

        for(num in list){
            result += num
        }
        return result
    }

}