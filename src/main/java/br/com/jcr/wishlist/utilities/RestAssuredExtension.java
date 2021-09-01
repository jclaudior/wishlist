package br.com.jcr.wishlist.utilities;

import br.com.jcr.wishlist.models.Product;
import br.com.jcr.wishlist.models.Wishlist;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.URISyntaxException;

public class RestAssuredExtension {
    public static RequestSpecification Request;

    public RestAssuredExtension(){
        //Arrange
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }

    public static ResponseOptions<Response> InsertWishList(String url, Wishlist wishlist){
        //Act
        try {

            Request.header("Content-Type", "application/json");
            Request.body(new Gson().toJson(wishlist));
            return Request.post(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> InsertProductInWishList(String url, Product product){
        //Act
        try {
            Request.header("Content-Type", "application/json");
            Request.body(new Gson().toJson(product));
            return Request.post(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> getWishlist(String url){
        //Act
        try {
            return Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> deleteWishlist(String url){
        //Act
        try {
            return Request.delete(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
