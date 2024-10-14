package com.techelevator.dao;

import com.techelevator.model.Crust;
import com.techelevator.model.Pizza;
import com.techelevator.model.Topping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcPizzaDaoTests extends BaseDaoTests{

    private JdbcPizzaDao jdbcPizzaDao;

    private Topping testTopping;

    private Pizza testPizza;

    private Crust testCrust;

    @Before
    public void setup(){
        jdbcPizzaDao = new JdbcPizzaDao(new JdbcTemplate(dataSource));
    }

    @Test
    public void getCrustById_with_valid_id_returns_correct_crust(){
        Crust crust = jdbcPizzaDao.getCrustByCrustId(1);
        assertCrustMatch(jdbcPizzaDao.getCrustByCrustId(1), crust);
    }

    private void assertCrustMatch(Crust expected, Crust actual){
        Assert.assertEquals(expected.getId(),actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
    }
}
