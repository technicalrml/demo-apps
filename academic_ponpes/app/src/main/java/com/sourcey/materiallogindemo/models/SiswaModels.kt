package com.sourcey.materiallogindemo.models

import java.util.*


class SiswaModels (var id: Int = getAutoId(), var card_code: String = "", var nis: String = "", var nama: String = ""){
    companion object {
        fun getAutoId(): Int{
            val random = Random()
            return random.nextInt(100);
        }
    }
}