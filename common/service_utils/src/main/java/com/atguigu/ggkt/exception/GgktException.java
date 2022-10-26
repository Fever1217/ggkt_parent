package com.atguigu.ggkt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.service.ApiListing;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GgktException extends RuntimeException{
    private  Integer code;

    private String msg;
}
