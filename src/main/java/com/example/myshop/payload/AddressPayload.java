package com.example.myshop.payload;

import com.example.myshop.entity.City;
import com.example.myshop.entity.District;
import com.example.myshop.entity.Ward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AddressPayload {
    private String detail;

    private City city;

    private District district;

    private Ward ward;
}
