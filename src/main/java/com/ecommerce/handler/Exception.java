package com.ecommerce.handler;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class Exception<E> {
    private final Date createDate;
    private final String hostName;
    private final String path;
    private final E message;
}
