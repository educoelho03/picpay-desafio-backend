package tech.ecoelho.picpay.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.ecoelho.picpay.backend.dto.TransferDto;
import tech.ecoelho.picpay.backend.entity.Transfer;
import tech.ecoelho.picpay.backend.entity.Wallet;
import tech.ecoelho.picpay.backend.exceptions.InsufficientBalanceException;
import tech.ecoelho.picpay.backend.exceptions.TransferNotAllowedForWalletTypeException;
import tech.ecoelho.picpay.backend.exceptions.TransferNotAuthorizedException;
import tech.ecoelho.picpay.backend.exceptions.WalletNotFoundException;
import tech.ecoelho.picpay.backend.repository.TransferRepository;
import tech.ecoelho.picpay.backend.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(NotificationService notificationService, AuthorizationService authorizationService, TransferRepository transferRepository
    , WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional // garante que tudo isso seja executado em uma transaçao, so vai jogar no banco se der tudo certo
    public Transfer transfer(TransferDto dto){
        var sender = walletRepository.findById(dto.payer())
                .orElseThrow(() -> new WalletNotFoundException(dto.payer()));

        var receiver = walletRepository.findById(dto.payee())
                .orElseThrow(() -> new WalletNotFoundException(dto.payee()));

        validTransfer(dto, sender);

        sender.debit(dto.value());
        receiver.credit(dto.value());

        var transfer = new Transfer(sender, receiver, dto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult)); // Envio da notificaçção de forma assincrona

        return transferResult;
    }


    public void validTransfer(TransferDto transferDto, Wallet sender){
        if(!sender.isTransferAllowerForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!sender.isBalanceEqualOrGreatherThan(transferDto.value())){
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDto)){
            throw new TransferNotAuthorizedException();
        }

    }
}
