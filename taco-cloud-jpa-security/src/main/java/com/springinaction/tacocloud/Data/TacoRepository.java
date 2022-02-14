package com.springinaction.tacocloud.Data;

import com.springinaction.tacocloud.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
