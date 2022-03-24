package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member2 member2 = new Member2();
            member2.setName("member1");
            member2.setTeam(team);

            em.persist(member2);

            em.flush();
            em.clear();

            Member2 findMember = em.find(Member2.class, member2.getId());
            List<Member2> member2s = findMember.getTeam().getMember2s();

            for (Member2 m : member2s) {
                System.out.println("m = " + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
