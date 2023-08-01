package okhttp;

import dto.AuthRequestDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationUsingInterfaceTests implements Helper {

    @Test
    public void registrationPositive() throws IOException {

        String endpoint = "/v1/user/registration/usernamepassword";

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("mika" + i + "@mail.com")
                .password("Mk12956#77")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println("Response code is : " + response.code());
        Assert.assertTrue(response.isSuccessful());

    }

    @Test
    public void registrationNegativeWrongEmail() throws IOException {

        String endpoint = "/v1/user/registration/usernamepassword";

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("mika" + i + "mail.com")
                .password("Mk12956#77")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println("Response code is : " + response.code());
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getError() + " " + errorDTO.getMessage());
        Assert.assertTrue(!response.isSuccessful());

    }

}