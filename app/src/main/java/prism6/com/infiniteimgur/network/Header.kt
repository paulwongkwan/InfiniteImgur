package prism6.com.infiniteimgur.network

class Header {
    companion object {
        val clientID : String = "2f10b6d4d2a5a61"
        val clientSecret : String = "cae119f520d4a70a642288bf012ee5ed55b97cbf"

        fun header() : Map<String, String> {
            return mapOf("Authorization" to "Client-ID " + clientID)
        }
    }
}