package com.royli.jobtracker.service;
import com.royli.jobtracker.entity.JobApplication;
import java.util.List;
import java.util.Optional;
import com.royli.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;
import com.royli.jobtracker.dto.CreateJobApplicationRequest;
import com.royli.jobtracker.dto.UpdateJobApplicationRequest;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> findAll() {
        return repository.findAll();
    }

    public JobApplication save(JobApplication application){
        return repository.save(application);
    }

    public Optional<JobApplication> findById(long id){
        return repository.findById(id);
    }

    public JobApplication create(CreateJobApplicationRequest request){

        JobApplication application = new JobApplication();

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setLocation(request.getLocation());
        application.setAppliedDate(request.getAppliedDate());
        application.setStatus(request.getStatus());
        application.setFollowUpDate(request.getFollowUpDate());
        application.setNotes(request.getNotes());

        return repository.save(application);

    }

    public Optional<JobApplication> update(
            Long id,
            UpdateJobApplicationRequest request){
        return repository.findById(id)
                .map(application -> {
                    application.setCompanyName(request.getCompanyName());
                    application.setJobTitle(request.getJobTitle());
                    application.setLocation(request.getLocation());
                    application.setAppliedDate(request.getAppliedDate());
                    application.setStatus(request.getStatus());
                    application.setFollowUpDate(request.getFollowUpDate());
                    application.setNotes(request.getNotes());

                    return repository.save(application);
                });
    }

    public boolean deleteById(Long id){
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
