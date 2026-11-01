package com.royli.jobtracker.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.royli.jobtracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTest {

    @Mock
    private  JobApplicationRepository repository;

    @InjectMocks
    private  JobApplicationService service;

    @Test
    void shouldReturnFalseWhenDeletingNonExistingApplication(){

        Long id = 9999L;
        when(repository.existsById(id)).thenReturn(false);

        boolean result = service.deleteById(id);
        assertFalse(result);

        verify(repository, never()).deleteById(id);

    }

    @Test
    void shouldReturnTrueWhenDeletingExistingApplication(){

        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        boolean result = service.deleteById(id);
        assertTrue(result);

        verify(repository, times(1)).deleteById(id);
    }


}
