package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.ServletConfig;
import org.scoula.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Servlet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MusicController.class, ServletConfig.class})
@Log4j2
class MusicControllerTest {
    @Autowired
    MusicService service;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void list() throws Exception {
        ModelMap model = mockMvc.perform(MockMvcRequestBuilders.get("/api/music/list"))
                .andReturn()
                .getModelAndView()
                .getModelMap();
        log.info(model);
    }

    @Test
    public void create() throws Exception {
        String viewName = mockMvc.perform(MockMvcRequestBuilders.post("/api/music/create"))
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(viewName);
    }

    @Test
    public void postCreate() throws Exception {
        String resultPage = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/music/create")
                        .param("title", "새로운 노래 제목")
                        .param("artist", "새로운 노래 가수")
                        .param("genre", "새로운 노래 장르"))
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(resultPage);
    }

    @Test
    public void get() throws Exception {
        ModelMap model = mockMvc.perform(MockMvcRequestBuilders.get("/api/music/get").param("id", "1"))
                .andReturn()
                .getModelAndView()
                .getModelMap();
        log.info(model);
    }

    @Test
    public void update() throws Exception {
        String resultPage = mockMvc.perform(
                MockMvcRequestBuilders.post("/music/update")
                        .param("id", "1")
                        .param("title", "수정된 새로운 노래 제목")
                        .param("artist", "수정된 노래 가수")
                        .param("genre", "수정된 노래 장르"))
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(resultPage);
    }

    @Test
    public void delete() throws Exception {
        String resultPage = mockMvc.perform(
                MockMvcRequestBuilders.post("/delete")
                        .param("id", "25"))
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(resultPage);
    }
}