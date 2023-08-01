package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import helpers.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginRestTests implements Helper, TestBase {

    String endpoint = "/v1/user/login/usernamepassword";


    @Test
    public void loginPositive(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("dara@mail.com")
                .password("Km12356#")
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
    public void loginNegativeWrongEmail(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("daramail.com")
                .password("Km12356#")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());

    }
}
