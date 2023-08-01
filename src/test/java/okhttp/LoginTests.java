package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("dara@mail.com")
                .password("Km12356#")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()) {
           AuthResponseDTO responseDTO = gson.fromJson(response.body().string(), AuthResponseDTO.class);
            System.out.println(responseDTO.getToken());
            System.out.println("Response code is : " + response.code());
            Assert.assertTrue(response.isSuccessful());
        }else{
            System.out.println("Response code is : " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
            Assert.assertTrue(!response.isSuccessful());
        }
    }
}

//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZGFyYUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkxMDg0NzM1LCJpYXQiOjE2OTA0ODQ3MzV9.eBIHePRZq2xF2Bt-2rCqiAjyDeu1X0izk7EitJyjUgY
