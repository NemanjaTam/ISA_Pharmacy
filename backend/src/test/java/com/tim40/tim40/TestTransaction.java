package com.tim40.tim40;




import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.MedicationRating;
import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.PrescriptionRegime;
import com.tim40.tim40.model.enums.TypeOfMedication;
import com.tim40.tim40.repository.MedicationRepository;
import com.tim40.tim40.service.PharmacyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


@SpringBootTest
public class TestTransaction {
    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private MedicationRepository medicationRepository;

    @Before
    public void setUp() throws Exception{
        Medication meds  = new Medication();
        meds.setId(Long.valueOf(1));
        meds.setVersion(0L);
        meds.setName("Lekic");
        meds.setDescription("nnnn");
        meds.setCode("123");
       this.medicationRepository.save(meds);
    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public  void test() throws Throwable{
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> future1 = executorService.submit(new Runnable() {
            @Override
            public void run() {
            	
            MedicationQuantityDTO dto = new MedicationQuantityDTO();
            dto.setId(Long.valueOf(1));
            dto.setName("NOVI LEK 1");
            
            pharmacyService.editMedication(dto, Long.valueOf(1));
 
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){}
                
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                MedicationQuantityDTO dto = new MedicationQuantityDTO();
                dto.setId(Long.valueOf(1));
                dto.setName("NOVI LEK 2");
                
                pharmacyService.editMedication(dto, Long.valueOf(1));
            }
        });

        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
