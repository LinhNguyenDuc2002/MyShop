package com.example.myshop.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.example.myshop.BaseTest;
import com.example.myshop.constant.CloudinaryConstant;
import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.CategoryPayload;
import com.example.myshop.service.CloudinaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class CategoryResourceTests extends BaseTest {
    @InjectMocks
    private CategoryResource categoryResource;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private CloudinaryService cloudinaryService;

    @Mock
    private Uploader uploader;

    @Override
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
//
//        when(cloudinary.uploader()).thenReturn(uploader);
    }

    @Override
    protected void cleanAfterEach() {

    }

//    @Test
//    void test_create() throws I18nException, IOException {
//        // dummy data
//        CategoryPayload categoryPayload = CategoryPayload.builder()
//                .name("Test")
//                .description("Test")
//                .image(multipartFile)
//                .build();
////        when(cloudinary.uploader().upload(any(byte[].class), any())).thenReturn(new HashMap<>());
////        doNothing().when(cloudinaryService).upload(any(MultipartFile.class), any(Map.class));
//
//        ResponseEntity<CategoryDTO> response = categoryResource.create(categoryPayload);
//
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        Assertions.assertNotNull(response.getBody());
//    }

    void mockMultipartFile() throws IOException {
        // Arrange
        String fileName = "test_image.jpg";
        String mimeType = "image/jpeg";
        byte[] content = new byte[]{1, 2, 3};

        // Thiết lập hành vi cho mock
        when(multipartFile.getOriginalFilename()).thenReturn(fileName);
        when(multipartFile.getContentType()).thenReturn(mimeType);
        when(multipartFile.getBytes()).thenReturn(content);
        when(multipartFile.isEmpty()).thenReturn(false);
    }
}
