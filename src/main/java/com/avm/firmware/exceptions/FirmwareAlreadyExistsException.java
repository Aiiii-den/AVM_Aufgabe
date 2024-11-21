package com.avm.firmware.exceptions;

public class FirmwareAlreadyExistsException extends RuntimeException{
    public FirmwareAlreadyExistsException(String message){
        super(message);
    }
}
