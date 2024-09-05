package tech.ecoelho.picpay.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_wallet_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    public enum ENUM {

        USER(1L, "USER"),
        MERCHANT(2L, "MERCHANT");

        ENUM(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        private Long id;
        private String description;

        public WalletType get(){
            return new WalletType(id, description);
        }
    }
}
