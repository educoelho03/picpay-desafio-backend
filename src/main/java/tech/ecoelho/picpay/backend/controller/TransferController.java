package tech.ecoelho.picpay.backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.ecoelho.picpay.backend.dto.TransferDto;
import tech.ecoelho.picpay.backend.entity.Transfer;
import tech.ecoelho.picpay.backend.service.TransferService;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto){
        var response = transferService.transfer(dto);

        return ResponseEntity.ok(response);

    }
}
