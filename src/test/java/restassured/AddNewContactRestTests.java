package restassured;

import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import helpers.TestBase;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddNewContactRestTests implements Helper, TestBase {

String endpoint = "/v1/contacts";

@Test(invocationCount = 5)
    public void addNewContactPositive(){

    ContactDTO contactDTO = ContactDTO.builder()
            .name("Ronald_" + i)
            .lastName("Reygan")
            .email("actor_" + i + "@gmail.com")
            .phone("87654321" + i)
            .address("Washington")
            .description("Crazy")
            .build();

    ContactResponseDTO responseDTO = given()
            .header(authH, TOKEN)
            .body(contactDTO)
            .contentType(ContentType.JSON)
            .when()
            .post(endpoint)
            .then()
            .assertThat().statusCode(200)
            .extract()
            .as(ContactResponseDTO.class);
    String message = responseDTO.getMessage();
    System.out.println(message);
    String id = message.substring(message.lastIndexOf(" ") + 1);
    System.out.println(id);
}
}
