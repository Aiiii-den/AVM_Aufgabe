package com.avm.firmware.firmwareUpdate;

import com.avm.firmware.firmwareUpdate.models.FirmwareDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmwareRepository extends JpaRepository<FirmwareDBO, String> {
}
