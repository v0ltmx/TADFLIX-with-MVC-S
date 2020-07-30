package tads.aula;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Filme {
    @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Size(min = 5,  max = 50 , message = "O tamanho não corresponde aos requisitos")
    @NotBlank
    String titulo;
    @NotBlank
    String descricao;
    @NotBlank
    String categoria;
    Integer anoLancamento;
}