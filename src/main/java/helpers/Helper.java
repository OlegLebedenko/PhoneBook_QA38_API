package helpers;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZGFyYUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkxODQyNTEzLCJpYXQiOjE2OTEyNDI1MTN9.gWgsU5SCmKQ8hyZ6Vdnis5DDKsX-Hz-fENbc0TcDBuA";
    String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    String authH = "Authorization";
    int i = new Random().nextInt(1000) + 1000;
}
