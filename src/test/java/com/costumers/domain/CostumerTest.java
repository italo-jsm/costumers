package com.costumers.domain;

import com.costumers.builder.CostumerBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class CostumerTest {

    @Test
    public void shouldHaveGettersAndSetters(){
        final Costumer costumer = new Costumer();
        costumer.setBirthDate(LocalDate.of(2020, 10, 1));
        costumer.setCpf("123.133.123-12");
        costumer.setName("NameTest");
        Assert.assertEquals(costumer.getBirthDate(), LocalDate.of(2020, 10, 1));
        Assert.assertEquals("123.133.123-12", costumer.getCpf());
        Assert.assertEquals("NameTest", costumer.getName());
    }

    @Test
    public void shouldHaveToStringMethod(){

    }

    @Test
    public void shouldHaveHashCodeMethod(){

    }

    @Test
    public void shouldHaveAge(){
        final Costumer costumer = CostumerBuilder.buildClient();
        Assert.assertNotEquals(0, costumer.getAge());
        Assert.assertEquals(LocalDate.now().compareTo(costumer.getBirthDate()), costumer.getAge());
    }
}
