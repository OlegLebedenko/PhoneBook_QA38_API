package restassured;

import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import helpers.TestBase;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationRestTests implements Helper, TestBase {

    String endpoint = "/v1/user/registration/usernamepassword";

    @Test
    public void registrationPositive(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("dana" + i + "@gmail.com")
                .password("Ng12956#")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());

    }

    @Test
    public void registrationNegativeWrongEmail(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("danagmail.com")
                .password("Ng12956#")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());

    }


    @Test
    public void registrationNegativeDuplicateUser(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("dara@mail.com")
                .password("Km12356#")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(409)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());

    }

}
