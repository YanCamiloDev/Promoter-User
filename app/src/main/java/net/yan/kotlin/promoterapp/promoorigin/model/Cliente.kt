package net.yan.kotlin.promoterapp.promoorigin.model

import com.google.firebase.database.Exclude


class Cliente {

    var id: String = ""
        @Exclude
        get

    var endereco: String = ""


}