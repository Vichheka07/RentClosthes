import com.google.gson.annotations.SerializedName

data class ApiItem (
    val data: List<Datum>
)

data class Datum (
    val id: Long,
    val images: List<Image>,
    val title: String,
    val describe: String,
    val price: String,
    val orgprice: String,
    val day: String,
    val category: String,
    val codition: String,
    val size: String,
    val delivery: String,
    @SerializedName("user_id")
    val userID: Long
)

data class Image (
    val url: String
)
