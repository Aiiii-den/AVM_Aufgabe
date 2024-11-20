package com.avm.firmware.exceptions;

public class FirmwareNotFoundException extends RuntimeException{
    public FirmwareNotFoundException(String message){
        super(message);
    }
}
