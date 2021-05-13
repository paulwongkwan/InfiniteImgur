package prism6.com.infiniteimgur.network

import io.reactivex.rxjava3.core.Observable
import prism6.com.infiniteimgur.model.Gallery
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface APIService {
    @GET("/3/gallery/{section}/{sort}/{page}")
    fun gallery(@HeaderMap header: Map<String, String>, @Path("section") section : String, @Path("sort") sort : String, @Path("page") page : Int) : Observable<Gallery>
}