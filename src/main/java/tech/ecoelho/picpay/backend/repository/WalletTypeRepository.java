package tech.ecoelho.picpay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ecoelho.picpay.backend.entity.WalletType;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
