package com.costumers.service;

import com.costumers.builder.CostumerBuilder;
import com.costumers.domain.Costumer;
import com.costumers.exception.CostumerNotFoundException;
import com.costumers.repository.CostumerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CostumerServiceTest {

    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    private CostumerService costumerService;

    @Test
    public void shouldReturnClientList(){
        Mockito.doReturn(CostumerBuilder.buildClientList()).when(costumerRepository).findAll();
        Assert.assertEquals( 4, costumerService.getAllClients().size());
    }

    @Test
    public void shouldFindOneClient(){
        Mockito.doReturn(Optional.of(CostumerBuilder.buildClient())).when(costumerRepository).findById(any());
        final Optional<Costumer> oneClient = costumerService.getOneCostumer(1L);
        Assert.assertTrue(oneClient.isPresent());
        Assert.assertEquals("name1", oneClient.get().getName());
    }

    @Test
    public void shouldUpdateOneClient(){
        final Costumer existingCostumer = CostumerBuilder.buildClient();
        existingCostumer.setId(1L);
        final Costumer updatedCostumer = CostumerBuilder.buildClient();
        updatedCostumer.setName("newName");
        Mockito.doReturn(updatedCostumer).when(costumerRepository).save(any());
        Mockito.doReturn(Optional.of(existingCostumer)).when(costumerRepository).findById(any());
        final Optional<Costumer> fullyUpdatedCostumer = costumerService.fullUpdateCostumer(existingCostumer);
        Optional<Costumer> partialUpdatedCostumer = costumerService.partialUpdateCostumer(1L, CostumerBuilder.buildChanges());
        Assert.assertTrue(fullyUpdatedCostumer.isPresent());
        Assert.assertEquals("newName", fullyUpdatedCostumer.get().getName());
        Assert.assertTrue(partialUpdatedCostumer.isPresent());
        Assert.assertEquals("newName", partialUpdatedCostumer.get().getName());
    }

    @Test
    public void shouldThrowExceptionOnNullId(){
        final Costumer noIdCostumer = new Costumer();
        Mockito.doReturn(Optional.of(noIdCostumer)).when(costumerRepository).findById(any());
        try{
            costumerService.fullUpdateCostumer(noIdCostumer);
            Assert.fail("Should not update costumer");
        }catch (Exception e){
            Assert.assertTrue(e instanceof CostumerNotFoundException);
        }
    }

    @Test
    public void shouldThrowExceptionOnNotFoundCostumer(){
        Mockito.doReturn(Optional.empty()).when(costumerRepository).findById(any());
        final Costumer notFoundCostumer = new Costumer();
        notFoundCostumer.setId(1L);
        try{
            costumerService.fullUpdateCostumer(notFoundCostumer);
            Assert.fail("Should not update costumer");
        }catch (Exception e){
            Assert.assertTrue(e instanceof CostumerNotFoundException);
        }
        try{
            costumerService.partialUpdateCostumer(1L, CostumerBuilder.buildChanges());
            Assert.fail("Should not update costumer");
        }catch (Exception e){
            Assert.assertTrue(e instanceof CostumerNotFoundException);
        }
    }

    @Test
    public void shouldCreateOneCostumer(){
        final Costumer toSave = CostumerBuilder.buildClient();
        final Costumer saved = CostumerBuilder.buildClientWithId();
        Mockito.doReturn(saved).when(costumerRepository).save(any());
        final Optional<Costumer> costumer = costumerService.saveNewCostumer(toSave);
        Assert.assertTrue(costumer.isPresent());
        Assert.assertNotNull(costumer.get().getId());
    }

    @Test
    public void shouldDeleteCostumer(){
        Mockito.doNothing().when(costumerRepository).delete(any());
        Mockito.doReturn(Optional.of(CostumerBuilder.buildClient())).when(costumerRepository).findById(any());
        try{
            costumerService.deleteCostumer(1L);
        }catch (Exception e){
            Assert.fail("Should not throw exception");
        }
    }

    @Test
    public void shouldFindCostumerByCpf(){
        Mockito.doReturn(Optional.of(CostumerBuilder.buildClientWithId())).when(costumerRepository).findByCpf(any());
        final Optional<Costumer> byCpf = costumerService.findByCpf("cpf");
        Assert.assertTrue(byCpf.isPresent());
        Assert.assertNotNull(byCpf.get().getId());
        Assert.assertEquals(CostumerBuilder.buildClient().getName(), byCpf.get().getName());
    }

    @Test
    public void shouldFindCostumerByName(){
        Mockito.doReturn(CostumerBuilder.buildClientList()).when(costumerRepository).findByName(any());
        final List<Costumer> byName = costumerService.findByName("cpf");
        Assert.assertTrue(byName.size() > 0);
        Assert.assertEquals(CostumerBuilder.buildClientList().get(0).getName(), byName.get(0).getName());
    }

    @Test
    public void shouldPatchCostumer(){
        Mockito.doReturn(Optional.of(CostumerBuilder.buildClientWithId())).when(costumerRepository).findById(any());
        Mockito.doReturn(CostumerBuilder.buildClientWithId()).when(costumerRepository).save(any());
        final Map<String, String> changes = new HashMap<>();
        changes.put("name", "Italo");
        final Optional<Costumer> costumer = costumerService.partialUpdateCostumer(1L, changes);
        Assert.assertTrue(costumer.isPresent());
    }

    @Test
    public void shouldNotDeleteCostumerOnCostumerNotFound(){
        Mockito.doReturn(Optional.empty()).when(costumerRepository).findById(any());
        try{
            costumerService.deleteCostumer(1L);
            Assert.fail("Should not delete costumer");
        }catch (Exception e){
            Assert.assertTrue(e instanceof CostumerNotFoundException);
        }
    }
}
