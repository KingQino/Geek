package com.example.demo.http;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OkHttpUtilsTest {


    @Test
    void getCall() throws Exception {


        String response = OkHttpUtils.getCall("http://localhost:8801");
        System.out.println(response);
    }
}
