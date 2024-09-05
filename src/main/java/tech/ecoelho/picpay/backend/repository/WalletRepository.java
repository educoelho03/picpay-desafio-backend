package tech.ecoelho.picpay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ecoelho.picpay.backend.entity.Wallet;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);

}
