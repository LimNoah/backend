package com.kurlabo.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurlabo.backend.dto.mypage.DeleteWishListDto;
import com.kurlabo.backend.dto.mypage.InsertWishListDto;
import com.kurlabo.backend.dto.review.ReviewListDto;
import com.kurlabo.backend.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MypageControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void before(WebApplicationContext wac) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @DisplayName("GetWishList")
    @Test
    void getAllFavoriteList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mypage/mypage_wishlist")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("page", String.valueOf(2)))
                .andExpect(status().isOk());
//                .andExpect((jsonPath("$[0].product_id").value((long)5)))
//                .andExpect((jsonPath("$[1].product_id").value((long)9)))
//                .andExpect((jsonPath("$[2].product_id").value((long)13)))
//                .andExpect((jsonPath("$[3].product_id").value((long)139)))
//                .andExpect((jsonPath("$[4].product_id").value((long)111)));

    }

    @DisplayName("InsertWishList")
    @Test
    void insertWishlist() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/mypage/mypage_wishlist")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(new InsertWishListDto((long)5))))
//                .andExpect(status().isOk());//andExpect로 data 확인 필요
    }

    @DisplayName("DeleteWishList")
    @Test
    void deleteWishList() throws Exception {
//        List<Long> lists = new ArrayList<>(Arrays.asList((long)40));
        List<Long> lists = new ArrayList<>();
        String content = objectMapper.writeValueAsString(new DeleteWishListDto(lists));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/mypage/mypage_wishlist")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("page", String.valueOf(0))
                .content(content))
                .andExpect(status().isOk());//andExpect로 data 확인 필요
    }

    @DisplayName("OrderList")
    @Test
    void orderListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mypage/mypage_orderlist")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("page", String.valueOf(0)))
                .andExpect(status().isOk());
    }

    @DisplayName("OrderView")
    @Test
    void orderView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mypage/mypage_orderview?ordno=15002321")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.orderProduct[0].product_id").value((long)1))
//                .andExpect(jsonPath("$.orderProduct[0].name").value("[KF365] 감자 1kg"))
//                .andExpect(jsonPath("$.orderProduct[0].checkout_price").value(5000))
//                .andExpect(jsonPath("$.orderProduct[0].cnt").value(2))
//                .andExpect(jsonPath("$.delivery_condition").value("배송중"))
//                .andExpect(jsonPath("$.checkout_total_price").value(11000))
//                .andExpect(jsonPath("$.checkout_method").value("신용카드"))
//                .andExpect(jsonPath("$.orderer_name").value("임정우"))
//                .andExpect(jsonPath("$.sender_name").value("임정우"))
//                .andExpect(jsonPath("$.checkout_date").value("2020-03-15"))
//                .andExpect(jsonPath("$.reciever_name").value("최유선"))
//                .andExpect(jsonPath("$.reciever_phone").value("01032329191"))
//                .andExpect(jsonPath("$.reciever_address").value("서울시 관악구 관악동 과낙아파트"))
//                .andExpect(jsonPath("$.reciever_place").value("집 앞"))
//                .andExpect(jsonPath("$.reciever_visit_method").value("기타장소 계단 밑"))
//                .andExpect(jsonPath("$.arrived_alarm").value("오전 7시"));
    }

    @DisplayName("QnaTest")
    @Test
    void qnaTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mypage/mypage_qna"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inquiry_tag[0]").value("배송지연/불만"))
                .andExpect(jsonPath("$.inquiry_tag[5]").value("주문결제문의"))
                .andExpect(jsonPath("$.inquiry_tag[8]").value("교환문의"))
                .andExpect(jsonPath("$.order_id[0]").value("1945327660572"))
                .andExpect(jsonPath("$.order_id[1]").value("3484593475423"))
                .andExpect(jsonPath("$.email").value("noah@fastcampus.com"))
                .andExpect(jsonPath("$.phone").value("010-4321-5678"));
    }
}