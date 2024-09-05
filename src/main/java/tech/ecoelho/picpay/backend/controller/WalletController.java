package tech.ecoelho.picpay.backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ecoelho.picpay.backend.dto.CreatorWalletDto;
import tech.ecoelho.picpay.backend.entity.Wallet;
import tech.ecoelho.picpay.backend.service.WalletService;

@RestController
@RequestMapping
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreatorWalletDto dto){
        var wallet = walletService.createWallet(dto);
        return ResponseEntity.ok(wallet);
    }
}
