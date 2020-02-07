package com.dib.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Error response model
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model that represents error response from REST server")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    @ApiModelProperty(value = "Unique error code", required = true, example = "BAD_REQUEST", position = 0)
    private ErrorCode errorCode;

    @ApiModelProperty(value = "Developer level message that explains what happened", required = true, example = "Null Pointer Exception", position = 1)
    private String developerMessage;

    @ApiModelProperty(value = "This is user friendly error message", required = true, example = "Invalid input parameters", position = 2)
    private String userMessage;

}
