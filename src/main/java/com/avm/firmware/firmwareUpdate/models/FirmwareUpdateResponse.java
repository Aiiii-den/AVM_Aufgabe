package com.avm.firmware.firmwareUpdate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirmwareUpdateResponse {
    private boolean updateRequired;
    private String downloadUrl;
}
