package com.expeditors.trackservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PricingProviderTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PRICING_URL = "http://localhost:10002/pricing";

    @Test
    public void testGetAll() throws Exception {
        var actions = mockMvc.perform(get(PRICING_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        var expect = actions.andExpect(status().isOk());
        var result = expect.andReturn();

        var jsonResult = result.getResponse().getContentAsString();

        System.out.println(STR."jsonResult: \{jsonResult}");
    }
}
