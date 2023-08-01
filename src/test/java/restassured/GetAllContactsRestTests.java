package restassured;

import com.jayway.restassured.RestAssured;
import dto.ContactDTO;
import dto.ContactListDTO;
import helpers.Helper;
import helpers.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsRestTests implements Helper, TestBase {

    String endpoint = "/v1/contacts";


    @Test
    public void getAllContactsPositive(){

        ContactListDTO listDTO = given()
                .header(authH, TOKEN)
                .when()
                .get(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactListDTO.class);

        for(ContactDTO contactDTO : listDTO.getContacts()) {
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getName());
            System.out.println(contactDTO.getEmail());
            System.out.println("==========================================");
        }
    }
}
