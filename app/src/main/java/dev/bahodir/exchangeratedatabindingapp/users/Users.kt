package dev.bahodir.exchangeratedatabindingapp.users

import java.io.Serializable

data class Users(
    var cb_price: String = "",
    var code: String = "",
    var date: String = "",
    var nbu_buy_price: String = "",
    var nbu_cell_price: String = "",
    var title: String = "",
    var image: String = ""
) : Serializable