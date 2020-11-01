package com.example.ticketapps.profile.edit



class EditResponse (val success:String?, val message: String?, val data: Edit) {
    data class Edit (val city : String?,
                       val address : String?,
                       val image : String?)
}