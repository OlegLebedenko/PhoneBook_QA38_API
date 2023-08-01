package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContactsTests implements Helper {

    String endpoint = "/v1/contacts";


    @BeforeMethod
    public void precondition() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QAgroup38")
                .lastName("Automation")
                .email("telran_" + i + "@co.il")
                .phone("87654321" + i)
                .address("Rehovot")
                .description("Students")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + endpoint)
                .addHeader(authH, TOKEN)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO =gson.fromJson(response.body().string(), ContactResponseDTO.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);

    }

    @Test
    public void deleteAllContactsPositive() throws IOException {

        Request request = new Request.Builder()
                .url(BASE_URI + endpoint + "/clear")
                .addHeader(authH, TOKEN)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO =gson.fromJson(response.body().string(), ContactResponseDTO.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
    }
}
