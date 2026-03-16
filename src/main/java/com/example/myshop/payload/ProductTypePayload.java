package com.example.myshop.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductTypePayload {
    private String name;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Long quantity;

    @NotNull
    private MultipartFile image;

    private List<AttributeValuePayload> attributeValues;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttributeValuePayload {
        private String attributeId;

        private Long valueId;
    }
}
