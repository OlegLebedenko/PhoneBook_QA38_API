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

    @BeforeMethod
    public void precondition() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
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
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ") + 1);
    }


     @Test
    public void updateContactPositive() throws IOException {

        ContactDTO updatedContactDTO = ContactDTO.builder()
                .id(id)
                .name("Class38")
                .lastName("Automation")
                .email("telran_38" + i + "@mail.com")
                .phone("87654433" + i)
                .address("Akko")
                .description("Students")
                .build();

         RequestBody requestBody = RequestBody.create(gson.toJson(updatedContactDTO), JSON);

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
         System.out.println("New address is: " + updatedContactDTO.getAddress());
         System.out.println("New email is: " + updatedContactDTO.getEmail());





     }

}
