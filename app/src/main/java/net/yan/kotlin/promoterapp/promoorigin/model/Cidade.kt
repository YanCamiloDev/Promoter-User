package net.yan.kotlin.promoterapp.promoorigin.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Cidade {
    var id: String = ""
        @Exclude
        get

    var local: String = ""

}