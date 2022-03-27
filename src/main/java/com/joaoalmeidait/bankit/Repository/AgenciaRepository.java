package com.joaoalmeidait.bankit.Repository;

import com.joaoalmeidait.bankit.model.Agencia;
import com.joaoalmeidait.bankit.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

}
