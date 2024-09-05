package tech.ecoelho.picpay.backend.service;

import org.springframework.stereotype.Service;
import tech.ecoelho.picpay.backend.dto.CreatorWalletDto;
import tech.ecoelho.picpay.backend.entity.Wallet;
import tech.ecoelho.picpay.backend.exceptions.WalletDataAlreadyExistsException;
import tech.ecoelho.picpay.backend.repository.WalletRepository;

@Service
public class WalletService {

    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreatorWalletDto dto) {
        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if(walletDb.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
