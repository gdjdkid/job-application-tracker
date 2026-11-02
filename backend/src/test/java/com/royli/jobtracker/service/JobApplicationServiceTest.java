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
import com.royli.jobtracker.entity.JobApplication;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.royli.jobtracker.dto.CreateJobApplicationRequest;
import org.mockito.ArgumentCaptor;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertSame;

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

    @Test
    void shouldReturnApplicationWhenIdExists(){
        Long id = 1L;
        JobApplication application = new JobApplication();
        application.setCompanyName("Siemens");
        application.setJobTitle("Java Backend Developer");
        when(repository.findById(id))
                .thenReturn(Optional.of(application));

        Optional<JobApplication> result = service.findById(id);

        assertTrue(result.isPresent());
        assertEquals("Siemens", result.get().getCompanyName());
        assertEquals("Java Backend Developer", result.get().getJobTitle());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist(){
        Long id = 9999L;
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        Optional<JobApplication> result = service.findById(id);

        assertTrue(result.isEmpty());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldCreateApplicationWithCorrectFields() {
        CreateJobApplicationRequest request = new CreateJobApplicationRequest();
        request.setCompanyName("Siemens");
        request.setJobTitle("Java Backend Developer");
        request.setLocation("Frankfurt");
        request.setAppliedDate(LocalDate.of(2026, 11, 2));
        request.setFollowUpDate(LocalDate.of(2026, 11, 16));
        request.setNotes("Applied through the company website");
        when(repository.save(any(JobApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        JobApplication result = service.create(request);

        ArgumentCaptor<JobApplication> captor =
                ArgumentCaptor.forClass(JobApplication.class);
        verify(repository, times(1)).save(captor.capture());
        JobApplication savedApplication = captor.getValue();
        assertEquals("Java Backend Developer", savedApplication.getJobTitle());
        assertEquals("Frankfurt", savedApplication.getLocation());
        assertEquals(LocalDate.of(2026, 11, 2),
                savedApplication.getAppliedDate());
        assertEquals(LocalDate.of(2026, 11, 16),
                savedApplication.getFollowUpDate());
        assertEquals("Applied through the company website",
                savedApplication.getNotes());
        assertSame(savedApplication, result);
    }


}
