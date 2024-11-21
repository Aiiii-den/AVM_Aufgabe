package com.avm.firmware.firmwareUpdate.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirmwareDBO {
    @Id
    private String hardwareId;
    @Column(unique = true)
    private String firmwareName;
    private String version;
    private String downloadUrl;
}
