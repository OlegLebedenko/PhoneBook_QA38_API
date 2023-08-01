package helpers;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZGFyYUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkxMDg0NzM1LCJpYXQiOjE2OTA0ODQ3MzV9.eBIHePRZq2xF2Bt-2rCqiAjyDeu1X0izk7EitJyjUgY";
    String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    String authH = "Authorization";
    int i = new Random().nextInt(1000) + 1000;
}
