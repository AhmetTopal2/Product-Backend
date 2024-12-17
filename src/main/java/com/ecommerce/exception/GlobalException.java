package com.ecommerce.exception;

import com.ecommerce.exception.ErrorMessage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GlobalException extends RuntimeException{

    public GlobalException(ErrorMessage errorMessage ){
        super(errorMessage.prepareErrorMessage());
    }

}
