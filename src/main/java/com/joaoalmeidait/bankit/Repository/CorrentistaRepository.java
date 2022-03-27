package com.joaoalmeidait.bankit.Repository;

import com.joaoalmeidait.bankit.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {

    @Query(
            value = "SELECT * FROM correntista cr where cr.cpf = :cpf",
            nativeQuery = true)
    Correntista findByCpf(String cpf);

}
