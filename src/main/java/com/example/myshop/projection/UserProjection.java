package com.example.myshop.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.beans.factory.annotation.Value;

/**
 * Projection class is READ-ONLY
 *
 * Entity is managed by Hibernate and contains many links to other entities.
 * So it's necessary to map to DTO to break the link
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface UserProjection {
    String getId();

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getDisplayName();

    String getEmail();

    String getUsername();

    String getPhoneNumber();

    @Value("#{target.image.secureUrl}")
    String getAvatarUrl();
}
