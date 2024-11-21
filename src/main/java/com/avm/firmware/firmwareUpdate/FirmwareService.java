package com.avm.firmware.firmwareUpdate;

import com.avm.firmware.exceptions.FirmwareAlreadyExistsException;
import com.avm.firmware.exceptions.FirmwareNotFoundException;
import com.avm.firmware.firmwareUpdate.models.FirmwareDBO;
import com.avm.firmware.firmwareUpdate.models.FirmwareDTO;
import com.avm.firmware.firmwareUpdate.models.FirmwareUpdateResponse;
import com.avm.firmware.firmwareUpdate.models.FritzBoxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmwareService {

    private final FirmwareRepository firmwareRepository;

    @Autowired
    public FirmwareService(FirmwareRepository firmwareRepository) {
        this.firmwareRepository = firmwareRepository;
    }

    public void addFirmware(FirmwareDTO firmwareDto) {
        if (this.firmwareRepository.findById(firmwareDto.getHardwareId()).isPresent()) {
            throw new FirmwareAlreadyExistsException("Firmware entry for hardware ID " +
                    firmwareDto.getHardwareId() + " already exists");
        }
        firmwareRepository.save(firmwareDtoToFirmwareDbo(firmwareDto));
    }

    public FirmwareUpdateResponse checkForUpdate(FritzBoxRequest updateRequest) {
        FirmwareDBO firmwareDbo = firmwareRepository.findById(updateRequest.getHardwareId())
                .orElseThrow(() -> new FirmwareNotFoundException("Firmware for hardware ID " +
                        updateRequest.getHardwareId() + " not found"));

        if (newVersionExists(updateRequest.getCurrentVersion(), firmwareDbo.getVersion())) {
            return FirmwareUpdateResponse.builder()
                    .updateRequired(true)
                    .downloadUrl(firmwareDbo.getDownloadUrl())
                    .build();
        }

        return FirmwareUpdateResponse.builder()
                .updateRequired(false)
                .build();
    }

    public void updateFirmware(FirmwareDTO firmwareDto) {
        FirmwareDBO firmwareDBO = this.firmwareRepository.findById(firmwareDto.getHardwareId())
                .orElseThrow(() -> new FirmwareNotFoundException("Firmware for hardware ID " +
                        firmwareDto.getHardwareId() + " not found"));

        firmwareDBO.setFirmwareName(firmwareDto.getFirmwareName());
        firmwareDBO.setVersion(firmwareDto.getVersion());
        firmwareDBO.setDownloadUrl(firmwareDto.getDownloadUrl());

        firmwareRepository.save(firmwareDBO);
    }


    public boolean newVersionExists(String currentVersion, String newVersion) {
        String[] currentParts = currentVersion.split("\\.");
        String[] newParts = newVersion.split("\\.");

        for (int i = 0; i < Math.min(currentParts.length, newParts.length); i++) {
            int currentPart = Integer.parseInt(currentParts[i]);
            int newPart = Integer.parseInt(newParts[i]);

            if (newPart > currentPart) {
                return true;
            } else if (newPart < currentPart) {
                return false;
            }
        }
        return false;
    }


    // for simplicity manual mapping instead of using MapStruct
    private FirmwareDBO firmwareDtoToFirmwareDbo(FirmwareDTO firmwareDTO) {
        return FirmwareDBO.builder()
                .firmwareName(firmwareDTO.getFirmwareName())
                .hardwareId(firmwareDTO.getHardwareId())
                .version(firmwareDTO.getVersion())
                .downloadUrl(firmwareDTO.getDownloadUrl())
                .build();
    }

    public FirmwareDTO getFirmware(String hardwareId) {
        FirmwareDBO firmwareDBO = this.firmwareRepository.findById(hardwareId)
                .orElseThrow(() -> new FirmwareNotFoundException("not found"));
        return FirmwareDTO.builder()
                .firmwareName(firmwareDBO.getFirmwareName())
                .version(firmwareDBO.getVersion())
                .hardwareId(firmwareDBO.getHardwareId())
                .downloadUrl(firmwareDBO.getDownloadUrl())
                .build();
    }
}
