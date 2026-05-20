package com.divyanshCode.BlogApplication.Exception;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException{


///this is a constructor -
    public ResourceNotFound(String message)
    {
        super(message);
    }
}
