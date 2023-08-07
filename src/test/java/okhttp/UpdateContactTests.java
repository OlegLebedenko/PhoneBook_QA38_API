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

public class UpdateContactTests implements Helper {

    String endpoint = "/v1/contacts";
    String id;
    ContactDTO contactDTO;

    @BeforeMethod
    public void precondition() throws IOException {

         contactDTO = ContactDTO.builder()
                .name("Test38")
                .lastName("Automation")
                .email("telran_" + i + "@mail.ru")
                .phone("87654433" + i)
                .address("Holon")
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
        id = message.substring(message.lastIndexOf(" ") + 1);
    }


     @Test
    public void updateContactPositive() throws IOException {

       contactDTO.setId(id);
       contactDTO.setAddress("London");
       contactDTO.setEmail("temza_" + i + "@gmail.com");

         RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

         Request request = new Request.Builder()
                 .url(BASE_URI + endpoint)
                 .addHeader(authH, TOKEN)
                 .put(requestBody)
                 .build();

         Response response = client.newCall(request).execute();

         ContactResponseDTO contactResponseDTO =gson.fromJson(response.body().string(), ContactResponseDTO.class);

         Assert.assertTrue(response.isSuccessful());

         String message = contactResponseDTO.getMessage();
         System.out.println(message);
         System.out.println("New address is: " + contactDTO.getAddress());
         System.out.println("New email is: " + contactDTO.getEmail());





     }

}
