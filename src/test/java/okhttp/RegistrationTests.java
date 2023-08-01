package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class RegistrationTests {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    int i = new Random().nextInt(1000) + 1000;

    @Test
    public void registrationPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("dama" + i + "@gmail.com")
                .password("Ky12956#")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
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
            System.out.println(errorDTO.getStatus() + " " + errorDTO.getError() + " " + errorDTO.getMessage());
            Assert.assertTrue(!response.isSuccessful());
        }
    }
}
