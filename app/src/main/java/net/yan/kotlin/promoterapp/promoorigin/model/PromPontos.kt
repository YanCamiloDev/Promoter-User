package net.yan.kotlin.promoterapp.promoorigin.model


import com.google.firebase.database.Exclude


class PromPontos {

    var id: String = ""
        @Exclude
        get

    var fk_id_promoter: String = ""

    var fk_id_pontos: String = ""
    var fk_id_cidade: String = ""
    var data: String? = null
    var foto: String? = null
}