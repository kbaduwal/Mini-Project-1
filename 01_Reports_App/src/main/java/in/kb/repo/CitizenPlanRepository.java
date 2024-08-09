package in.kb.repo;

import in.kb.entity.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {

    @Query("SELECT DISTINCT(planName) FROM CitizenPlan")
    public List<String> getPlanNames();

    @Query("SELECT DISTINCT(planStatus) FROM CitizenPlan")
    public List<String> getPlanStatus();

}
