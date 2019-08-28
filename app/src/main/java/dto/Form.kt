package dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Form:Serializable{
    @Expose
    @SerializedName("identifier")
    var identifier: String? = null
    @Expose
    @SerializedName("compromisedWater")
    var compromisedWater: Double = 0.0
    @Expose
    @SerializedName("realWater")
    var realWater: Double = 0.0
    @Expose
    @SerializedName("host")
    var host: String? = null
}

