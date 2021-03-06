package com.github.helena128.regionmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
import io.swagger.model.OperationResultWithRegionList;
import io.swagger.model.Region;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.helena128.regionmanager.util.EndpointHolder.REGIONS_BY_ID_ENDPOINT;
import static com.github.helena128.regionmanager.util.EndpointHolder.REGIONS_ENDPOINT;
import static com.github.helena128.regionmanager.util.RegionTestUtil.buildRegionEntity;
import static com.github.helena128.regionmanager.util.RegionTestUtil.buildRegionInput;
import static com.github.helena128.regionmanager.util.TestRegionPropertiesHolder.*;
import static junit.framework.TestCase.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @DirtiesContext
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
    @DirtiesContext
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

    @Test
    @DirtiesContext
    public void shouldFindRegions() throws Exception {
        // create test data
        val regionEntity1 = buildRegionEntity(REGION_1_NAME, REGION_1_SHORTNAME);
        val regionEntity2 = buildRegionEntity(REGION_2_NAME, REGION_2_SHORTNAME);
        val regionEntityId1 = mybatisMapper.addRegionEntity(regionEntity1);
        val regionEntityId2 = mybatisMapper.addRegionEntity(regionEntity2);

        // send test request
        val mockMvcResult = this.mockMvc.perform(
                get(REGIONS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        val resultList = mapper.readValue(mockMvcResult.getResponse().getContentAsString(), OperationResultWithRegionList.class);
        assertEquals(2, resultList.size());
        assertTrue(resultList.stream().anyMatch(region -> regionEntityId1.equals(region.getId().longValue())));
        assertTrue(resultList.stream().anyMatch(region -> regionEntityId2.equals(region.getId().longValue())));
    }

    @Test
    @DirtiesContext
    public void shouldUpdateRegion() throws Exception {
        // create data
        val regionEntity1 = buildRegionEntity(REGION_1_NAME, REGION_1_SHORTNAME);
        val regionEntityId1 = mybatisMapper.addRegionEntity(regionEntity1);
        val regionUpdateInput = buildRegionInput(REGION_2_NAME, REGION_2_SHORTNAME);

        // send test request
        val mockMvcResult = this.mockMvc.perform(
                put(REGIONS_BY_ID_ENDPOINT, regionEntityId1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(regionUpdateInput))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        val resultRegion = mapper.readValue(mockMvcResult.getResponse().getContentAsString(), Region.class);
        assertEquals(REGION_2_NAME, resultRegion.getName());
        assertEquals(REGION_2_SHORTNAME, resultRegion.getShortName());
        assertEquals(regionEntityId1.longValue(), resultRegion.getId().longValue());
    }

    @Test
    @DirtiesContext
    public void shouldRemoveById() throws Exception {
        // create data
        val regionEntity1 = buildRegionEntity(REGION_1_NAME, REGION_1_SHORTNAME);
        val regionEntityId1 = mybatisMapper.addRegionEntity(regionEntity1);

        // send test request
        this.mockMvc.perform(
                delete(REGIONS_BY_ID_ENDPOINT, regionEntityId1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // check db
        assertNull(mybatisMapper.findRegionEntityById(regionEntityId1));
    }

    @Test
    public void shouldDeleteNonExistentRegion() throws Exception {
        this.mockMvc.perform(
                delete(REGIONS_BY_ID_ENDPOINT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
