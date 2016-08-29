package id.co.ppu.realmapp.rest;

import java.util.List;

import id.co.ppu.realmapp.pojo.User;
import id.co.ppu.realmapp.rest.request.RequestLKP;
import id.co.ppu.realmapp.rest.request.RequestLogin;
import id.co.ppu.realmapp.rest.response.ResponseGetLKP;
import id.co.ppu.realmapp.rest.response.ResponseGetUsers;
import id.co.ppu.realmapp.rest.response.ResponseLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Eric on 19-Aug-16.
 */
public interface ApiInterface {
    @POST("fast/login")
    Call<ResponseLogin> login(@Body RequestLogin request);

    @POST("fast/users")
    Call<ResponseGetUsers> getUsers();

    @POST("fast/lkp")
    Call<ResponseGetLKP> getLKP(@Body RequestLKP request);

}
