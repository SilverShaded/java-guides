package com.springinaction.tacocloud.conf;

import com.springinaction.tacocloud.model.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcSimple  {

    private JdbcTemplate jdbcTemplate;

    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query(
                "select id,name,type from Ingredient where id=?",
                this::mapRowToIngredient,id
        );
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }

}
