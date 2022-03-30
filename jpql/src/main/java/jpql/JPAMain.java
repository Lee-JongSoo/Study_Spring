package jpql;

import javax.persistence.*;
import java.util.List;

public class JPAMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA= new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB= new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

/*            em.flush();
            em.clear();*/

            /**
             * 올바른 join 방법
             List<Team> result = em.createQuery("select t from Member m join m.team t", Team.class)
             .getResultList();
             */

            /**
             * 임베디드 타입 프로젝션
             em.createQuery("select o.address from Order o ", Order.class)
             .getResultList();
             */

            /**
             * 스칼라 타입 프로젝션
             em.createQuery("select distinct m.username, m.age from Member m ")
             .getResultList();
             */

            /**
             * Query 타입으로 조희
             List resultList = em.createQuery("select distinct m.username, m.age from Member m ")
             .getResultList();
             */

            /**
             * Object[] 타입으로 조희
             List resultList = em.createQuery("select distinct m.username, m.age from Member m ")
             .getResultList();

             List<Object[]> resultList = em.createQuery("select distinct m.username, m.age from Member m ")
             .getResultList();

             Object o = resultList.get(0);
             Object[] result = (Object[]) o;
             System.out.println("result = " + result[0]);
             System.out.println("result = " + result[1]);
             */

            /**
             * new 명령어로 조회
             List<MemberDTO> result = em.createQuery("select distinct new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
             .getResultList();

             MemberDTO memberDTO = result.get(0);
             System.out.println("memberDTO = " + memberDTO.getUsername());
             System.out.println("memberDTO = " + memberDTO.getAge());
             */

            /**
             * Paging
             List<Member> result = em.createQuery("select m from Member m order by m.age desc ", Member.class)
             .setFirstResult(1)
             .setMaxResults(10)
             .getResultList();

             System.out.println("result.size = " + result.size());
             for (Member member1 : result) {
             System.out.println("member1 = " + member1);
             }
             */

            /**
             * 내부 조인
             String query = "select m from Member m inner join m.team t";
             List<Member> result = em.createQuery(query, Member.class)
             .getResultList();
             */

            /**
             * 외부 조인
             String query = "select m from Member m left join m.team t";
             List<Member> result = em.createQuery(query, Member.class)
             .getResultList();
             */

            /**
             * 세타 조인
             String query = "select m from Member m, Team t where m.username = t.name";
             List<Member> result = em.createQuery(query, Member.class)
             .getResultList();
             */

            /**
             * 조인 대상 필터
             String query = "select m from Member m left join m.team t on t.name = 'temaA'";
             List<Member> result = em.createQuery(query, Member.class)
             .getResultList();

             System.out.println("result = " + result.size());
             */

            /**
             * 연관관계 없는 엔티티 외부 조인
             String query = "select m from Member m left join Team t on m.username = t.name";
             List<Member> result = em.createQuery(query, Member.class)
             .getResultList();

             System.out.println("result = " + result.size());
             */

            /**
             * 서브 쿼리 한계
             String query = "select (select avg(m1.age) From Member m1) as avgAge from Member m left join Team t on m.username = t.name";
             List<Member> result = em.createQuery(query, Member.class)
             .getResultList();

             System.out.println("result = " + result.size());

             from 절에는 select 불가능*****
             */

            /**
             * JPQL 타입 표현
             * 문자, 숫자, boolean
             String query = "select m.username, 'HELLO', true from Member m";
             List<Object[]> result = em.createQuery(query)
             .getResultList();

             for (Object[] objects : result) {
             System.out.println("objects[0] = " + objects[0]);
             System.out.println("objects[0] = " + objects[1]);
             System.out.println("objects[0] = " + objects[2]);
             }

             * enum
             String query = "select m.username, 'HELLO', true from Member m " +
             "where m.type = :userType";

             List<Object[]> result = em.createQuery(query)
             .setParameter("userType", MemberType.ADMIN)
             .getResultList();

             for (Object[] objects : result) {
             System.out.println("objects[0] = " + objects[0]);
             System.out.println("objects[0] = " + objects[1]);
             System.out.println("objects[0] = " + objects[2]);
             }
             */

            /**
             * 기본 CASE 식
             String query =
             "select " +
             "case when m.age <= 10 then '학생요금' " +
             "     when m.age >= 60 then '경로요금' " +
             "     else '일반요금' " +
             "end " +
             "from Member m";
             List<String> result = em.createQuery(query, String.class)
             .getResultList();

             for (String s : result) {
             System.out.println("s = " + s);
             }
             */

            /**
             * COALESCE 하나씩 조호해서 null이 아니면 반환
             String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
             List<String> result = em.createQuery(query, String.class)
             .getResultList();

             for (String s : result) {
             System.out.println("s = " + s);
             }
             */

            /**
             * ULLIF 두 값이 같으면 null 반환, 다르면 첫번째 값 반환
             String query = "select nullif(m.username, '관리자') as username from Member m";
             List<String> result = em.createQuery(query, String.class)
             .getResultList();

             for (String s : result) {
             System.out.println("s = " + s);
             }

             */

            /**
             * JPQL 기본 함수
             String query = "select concat('a', 'b') from Member m";
             String query = "select substring(m.username, 2, 3) from Member m";
             String query = "select locate('de', 'abcdefg') from Member m"; ***Integer type use***
             String query = "select size(t.members) from Team t";
             */

            /**
             * 경로 표현식 - 상태 필드
             String query = "select m.username from Member m";
             */

            /**
             * 경로 표현식 - 단일 값 연관 경로 -> 묵시적 내부 조인 발생, 탐색O = 안 좋은 방법 쓰지 말기를 권장
             String query = "select m.team from Member m";
             */

            /**
             * 경로 표현식 - 컬렉션 값 연관 경로 -> 묵시적 내부 조인 발생, 탐색O = 안 좋은 방법 쓰지 말기를 권장
             String query = "select t.members from Team t";
             */

            /**
             * fetch join
             String query = "select m from Member m join fetch m.team";

             List<Member> result = em.createQuery(query, Member.class)
             .getResultList();

             for (Member member : result) {
             System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
             }
             */

            /**
             * Collection fetch join -> 일대다 관계일 경우 뻥튀기 됨
             String query = "select t from Team t join fetch t.members";

             List<Team> result = em.createQuery(query, Team.class)
                .getResultList();

             for (Team team : result) {
             System.out.println("team = " + team.getName() + "|members= " + team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("-> member = " + member);
                }
             }

             ==================해결===================

             String query = "select distinct t from Team t join fetch t.members";

             List<Team> result = em.createQuery(query, Team.class)
             .getResultList();

             for (Team team : result) {
             System.out.println("team = " + team.getName() + "|members= " + team.getMembers().size());
             for (Member member : team.getMembers()) {
             System.out.println("-> member = " + member);
             }
             }
             */

            /**
             * NamedQuery
             List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
             .setParameter("username", "회원1")
             .getResultList();

             for (Member member : resultList) {
             System.out.println("member = " + member);
             }

             */

            /**
             * 벌크 연산
             //flush 자동 호출 -> commit, query, flush
             int resultCount = em.createQuery("update Member m set m.age = 20")
             .executeUpdate();

             System.out.println("resultCount = " + resultCount);

             ========벌크 연산 수행 후 영속성 컨텍스트 초기화=========
             int resultCount = em.createQuery("update Member m set m.age = 20")
             .executeUpdate();

             em.clear();

             Member findMember = em.find(Member.class, member1.getId());

             System.out.println("findMember = " + findMember.getAge());
             */

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            em.clear();

            Member findMember = em.find(Member.class, member1.getId());

            System.out.println("findMember = " + findMember.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
