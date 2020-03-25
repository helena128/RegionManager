package com.github.helena128.regionmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.model.Region;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.helena128.regionmanager.util.EndpointHolder.REGIONS_ENDPOINT;
import static com.github.helena128.regionmanager.util.RegionTestUtil.buildRegionInput;
import static com.github.helena128.regionmanager.util.TestRegionPropertiesHolder.REGION_1_NAME;
import static com.github.helena128.regionmanager.util.TestRegionPropertiesHolder.REGION_1_SHORTNAME;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegionManagerApplication.class)
@AutoConfigureMockMvc
public class RegionManagerApiTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    }

    @Test
    public void shouldCreateRegion() throws Exception {
        val regionInput = buildRegionInput(REGION_1_NAME, REGION_1_SHORTNAME);
        val mockMvcResult = this.mockMvc.perform(
                post(REGIONS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(regionInput))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        val createdRegion = mapper.readValue(mockMvcResult.getResponse().getContentAsString(), Region.class);
        assertEquals(REGION_1_NAME, createdRegion.getName());
        assertEquals(REGION_1_SHORTNAME, createdRegion.getShortName());
        assertNotNull(createdRegion.getId());
    }

}
