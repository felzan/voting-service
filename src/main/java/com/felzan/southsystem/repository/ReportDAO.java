package com.felzan.southsystem.repository;

import com.felzan.southsystem.dto.ReportDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReportDAO {

    EntityManagerFactory emf;

    public ReportDTO report(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            String sql = "SELECT t.id, COUNT(v.vote) as total, COUNT(CASE WHEN v.vote = 'NO' THEN 1 ELSE NULL END) AS NO, COUNT(CASE WHEN v.vote = 'YES' THEN 1 ELSE NULL END) AS YES FROM topic t LEFT JOIN vote v ON t.id = v.topic_id WHERE id = :id";
            Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);

            return reportDTOMapper(query.getResultList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    private ReportDTO reportDTOMapper(List<Object[]> resultList) {
        Object[] objects = resultList.get(0);
        Object topicId = objects[0];
        Object total = objects[1];
        Object no = objects[2];
        Object yes = objects[3];

        return new ReportDTO(topicId, total, no, yes);
    }
}
