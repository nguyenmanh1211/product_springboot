package com.sapo.restfulapispring.model.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Valid
public class ProductRequest {
    private String productCode;
    @NotNull(message = "Thiếu  Tên!!!")
    private String productName;
    private String productDescription;
    private String picturePath;
    private Long productAmount;
    private Long amountSold;
    private BigDecimal prices;
    private Long categoryId;
    private Long storageId;

}
