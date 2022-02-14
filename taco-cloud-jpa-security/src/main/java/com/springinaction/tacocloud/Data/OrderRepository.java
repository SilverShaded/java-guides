package com.springinaction.tacocloud.Data;

import com.springinaction.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
