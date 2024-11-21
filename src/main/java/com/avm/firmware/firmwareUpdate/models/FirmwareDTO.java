package com.avm.firmware.firmwareUpdate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirmwareDTO {
    private String hardwareId;
    private String firmwareName;
    private String version;
    private String downloadUrl;
}

