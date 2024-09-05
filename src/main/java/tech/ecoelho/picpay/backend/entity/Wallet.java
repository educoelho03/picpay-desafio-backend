package tech.ecoelho.picpay.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String cpfCnpj;
    private String email;
    private String password;
    private BigDecimal balance = BigDecimal.ZERO;
    private WalletType walletType;


    public boolean isTransferAllowerForWalletType(){
        return this.walletType.equals(WalletType.ENUM.USER.get());
    }


    public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.walletType = walletType;
    }

    public boolean isBalanceEqualOrGreatherThan(BigDecimal value) {
        return this.balance.doubleValue() >= value.doubleValue(); // comparação de dois valores
    }

    public void debit(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }
}
