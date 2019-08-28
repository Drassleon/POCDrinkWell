package dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseDTO {
    @Expose
    @SerializedName("status")
    var status: String? = null
    @Expose
    @SerializedName("code")
    var code: String? = null
    @Expose
    @SerializedName("message")
    var message: String? = null
    @Expose
    @SerializedName("detail")
    var detail: String? = null
    @Expose
    @SerializedName("object")
    var obj: Form? = null
}