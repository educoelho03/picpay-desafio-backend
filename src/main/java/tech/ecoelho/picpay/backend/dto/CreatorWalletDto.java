package tech.ecoelho.picpay.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.ecoelho.picpay.backend.entity.Wallet;
import tech.ecoelho.picpay.backend.entity.WalletType;

public record CreatorWalletDto(@NotBlank String fullName,
                               @NotBlank String cpfCnpj,
                               @NotBlank String email,
                               @NotBlank String passsword,
                               @NotNull WalletType.ENUM walletType) {

    public Wallet toWallet(){
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                passsword,
                walletType.get()
        );
    }
}
