package pe.isil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client implements Serializable {

	private int idPurchase;
	private double amount;
	private String purchaseDate;
	private int productId;
	private int idusuario;

}
