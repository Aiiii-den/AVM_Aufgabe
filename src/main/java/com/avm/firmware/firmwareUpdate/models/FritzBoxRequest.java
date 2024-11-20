package com.avm.firmware.firmwareUpdate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FritzBoxRequest {
    private String hardwareId;
    private String currentVersion;
}
