package prism6.com.infiniteimgur.network

import prism6.com.infiniteimgur.model.Gallery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface APIService {
    @GET("/3/gallery/{section}/{sort}/{page}")
    suspend fun gallery(@HeaderMap header: Map<String, String>, @Path("section") section : String, @Path("sort") sort : String, @Path("page") page : Int) : Response<Gallery>
}