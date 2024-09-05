package tech.ecoelho.picpay.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import tech.ecoelho.picpay.backend.entity.WalletType;
import tech.ecoelho.picpay.backend.repository.WalletTypeRepository;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner { // Quando o projeto inicializar ele ja vai salvar esses dados no banco

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.ENUM.values())
                .forEach(walletType -> walletTypeRepository.save(walletType.get()));
    }

}
