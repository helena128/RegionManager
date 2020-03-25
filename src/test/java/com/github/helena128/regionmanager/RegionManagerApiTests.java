package com.github.helena128.regionmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
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

import static com.github.helena128.regionmanager.util.EndpointHolder.REGIONS_BY_ID_ENDPOINT;
import static com.github.helena128.regionmanager.util.EndpointHolder.REGIONS_ENDPOINT;
import static com.github.helena128.regionmanager.util.RegionTestUtil.buildRegionEntity;
import static com.github.helena128.regionmanager.util.RegionTestUtil.buildRegionInput;
import static com.github.helena128.regionmanager.util.TestRegionPropertiesHolder.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegionManagerApplication.class)
@AutoConfigureMockMvc
public class RegionManagerApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegionsMybatisMapper mybatisMapper;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mybatisMapper.removeAllRegions();
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

    @Test
    public void shouldFindRegion() throws Exception {
        // create test data
        val regionEntity1 = buildRegionEntity(REGION_1_NAME, REGION_1_SHORTNAME);
        val regionEntity2 = buildRegionEntity(REGION_2_NAME, REGION_2_SHORTNAME);
        val regionEntityId1 = mybatisMapper.addRegionEntity(regionEntity1);
        mybatisMapper.addRegionEntity(regionEntity2);

        // send test request
        val mockMvcResult = this.mockMvc.perform(
                get(REGIONS_BY_ID_ENDPOINT, regionEntityId1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        val resultRegion = mapper.readValue(mockMvcResult.getResponse().getContentAsString(), Region.class);
        assertEquals(REGION_1_NAME, resultRegion.getName());
        assertEquals(REGION_1_SHORTNAME, resultRegion.getShortName());
        assertEquals(regionEntityId1.longValue(), resultRegion.getId().longValue());
    }

}
