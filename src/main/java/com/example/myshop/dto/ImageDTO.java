package com.example.myshop.dto;

import com.example.myshop.constant.ImageStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ImageDTO {
    private String id;

    private String publicId;

    private String assetId;

    private String format;

    private String secureUrl;

    private String resourceType;

    private ImageStatus status;
}
