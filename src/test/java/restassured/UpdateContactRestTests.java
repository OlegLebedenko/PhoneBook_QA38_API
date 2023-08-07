package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class UpdateContactRestTests implements Helper {

    String endpoint = "/v1/contacts";
    String id;
    ContactDTO contactDTO;

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;

         contactDTO = ContactDTO.builder()
                .name("Romeo")
                .lastName("Kaplan")
                .email("lover_" + i + "@mail.com")
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
        id = message.substring(message.lastIndexOf(" ") + 1);

    }

    @Test
    public void updateContactPositive(){

        contactDTO.setId(id);
        contactDTO.setName("Roman");
        contactDTO.setEmail("luzer_" + i + "@gmail.com");
        contactDTO.setAddress("Haifa");


        ContactResponseDTO responseDTO = given()
                .header(authH, TOKEN)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .put(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);

        String message = responseDTO.getMessage();
        System.out.println(message);
        System.out.println("New address is: " + contactDTO.getAddress());
        System.out.println("New email is: " + contactDTO.getEmail());
    }
}
