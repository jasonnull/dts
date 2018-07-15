package io.github.jasonnull.dts.server.controller;

import io.github.jasonnull.dts.server.controller.interceptor.PermissionInterceptor;
import io.github.jasonnull.dts.server.core.conf.JobServerConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class JobInfoControllerTest extends AbstractSpringMvcTest {

    private Cookie cookie;

    @Before
    public void login() throws Exception {
        MvcResult ret = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", JobServerConfig.getAdminConfig().getLoginUsername())
                        .param("password", JobServerConfig.getAdminConfig().getLoginPassword())
        ).andReturn();
        cookie = ret.getResponse().getCookie(PermissionInterceptor.LOGIN_IDENTITY_KEY);
    }

    @Test
    public void testAdd() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("jobGroup", "1");

        MvcResult ret = mockMvc.perform(
                post("/jobinfo/pageList")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        //.content(paramsJson)
                        .params(parameters)
                        .cookie(cookie)
        ).andReturn();

        System.out.println(ret.getResponse().getContentAsString());
    }

}
