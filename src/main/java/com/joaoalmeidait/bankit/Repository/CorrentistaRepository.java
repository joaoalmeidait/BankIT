package com.joaoalmeidait.bankit.Repository;

import com.joaoalmeidait.bankit.model.ContaCorrente;
import com.joaoalmeidait.bankit.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {

}
