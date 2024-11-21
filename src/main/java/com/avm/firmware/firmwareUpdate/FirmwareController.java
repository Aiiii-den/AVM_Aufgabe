package com.avm.firmware.firmwareUpdate;

import com.avm.firmware.firmwareUpdate.models.FirmwareDTO;
import com.avm.firmware.firmwareUpdate.models.FirmwareUpdateResponse;
import com.avm.firmware.firmwareUpdate.models.FritzBoxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/firmware")
public class FirmwareController {
    private final FirmwareService firmwareService;

    @Autowired
    public FirmwareController(FirmwareService firmwareService) {
        this.firmwareService = firmwareService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addFirmware(@RequestBody FirmwareDTO firmwareDto) {
        this.firmwareService.addFirmware(firmwareDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateFirmware(@RequestBody FirmwareDTO firmwareDto) {
        this.firmwareService.updateFirmware(firmwareDto);
        return ResponseEntity.ok().build();
    }

    //For personal testing purposes only
    @GetMapping("/{hardwareId}")
    public ResponseEntity<FirmwareDTO> getFirmware(@PathVariable String hardwareId) {
        return ResponseEntity.ok(this.firmwareService.getFirmware(hardwareId));
    }

    @PostMapping("/check-update")
    public ResponseEntity<FirmwareUpdateResponse> checkForUpdate(@RequestBody FritzBoxRequest request) {
        return ResponseEntity.ok(this.firmwareService.checkForUpdate(request));
    }
}
