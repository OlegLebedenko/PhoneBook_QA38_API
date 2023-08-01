package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactListDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import helpers.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteAllContactsRestTests implements Helper {

    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;

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
    }
        @Test
    public void deleteAllContactsPositive(){

            ContactResponseDTO responseDTO = given()
                    .header(authH, TOKEN)
                    .when()
                    .delete(endpoint + "/clear")
                    .then()
                    .assertThat().statusCode(200)
                    .extract()
                    .as(ContactResponseDTO.class);
            System.out.println(responseDTO.getMessage());

        }
}
