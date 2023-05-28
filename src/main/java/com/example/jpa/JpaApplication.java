package com.example.jpa;


import com.example.jpa.jpql.Member;
import com.example.jpa.jpql.MemberType;
import com.example.jpa.jpql.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {


    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
//            Team team = new Team();
//            team.setName("teamA");
//            entityManager.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            entityManager.persist(member1);

            Member member2= new Member();
            member2.setUsername("관리자2");
            entityManager.persist(member2);


            entityManager.flush();
            entityManager.clear();

            // 7.페치조인
            // ##############  페치조인  ##############

            String query ="select m from Member m join fetch m.team";
            List<Member> result = entityManager.createQuery(query, Member.class)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
            }


// JPQL 함수 .

                    //            String query = "select function('group_concat' , m.username) from Member m";
                    //
                    //            List<String> result = entityManager.createQuery(query, String.class)
                    //                    .getResultList();
                    //            for (String s : result) {
                    //                System.out.println("s = " + s);
                    //            }

                    //            String query = "select cconcat('a' , 'b')  from Member m";

                    //            String query = "select substring(m.username , 2,3)  from Member m";

                                //locate란 특정 문자열이 포함되어 있는지 찾아주는 함수
                    //            String query = "select locate('de' , 'abcdegf')  from Member m";
                    //
                    //            List<Integer> result = entityManager.createQuery(query, Integer.class)
                    //                    .getResultList();
                    //            for (Integer s : result) {
                    //                System.out.println("s = " + s);
//            }




//6.2 nullif
//            String query = "select nullif(m.username, '관리자') from Member m";
//            List<String> result = entityManager.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }


// 6.1 coalesce
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            List<String> result = entityManager.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            // 6. ############ case when  ########################################
//            String query =
//                            " case   when m.age <= 10 then '학생요금' " +
//                            "       when m.age >= 60 then '경로요금' " +
//                            "       else '일반요금' " +
//                            "end " +
//                            "from Member m";
//            List<String> result = entityManager.createQuery(query, String.class)
//                    .getResultList();
//            for(String s : result) {
//                System.out.println("s = " + s);
//            }





//6.jpql 타입표현
// 6. ############ JPQL 타입 표현 ########################################
//            String query = "select m.username, 'HELLO', true from Member m " +
//                            "where m.type = :userType";
//            List<Object[]> result = entityManager.createQuery(query)
//                    .setParameter("userType", MemberType.ADMIN)
//                    .getResultList();
//
//            for(Object[] objects : result) {
//                System.out.println("objects[0] = " + objects[0]);
//                System.out.println("objects[1] = " + objects[1]);
//                System.out.println("objects[2] = " + objects[2]);
//            }
            // 6. ############ JPQL 타입 표현 ########################################






 //5.서브쿼리
            // 5. ############ 서브쿼리 ########################################
//            String query = "select (select avg(m1.age) from Member m1) as avgAge from Member m join m.team t on m.username = t.name";
//            List<Member> result =  entityManager.createQuery(query, Member.class)
//                        .getResultList();





//4. 조인
            // 4. ############ 조인 ########################################
//                String query = "select m from Member m left outer join m.team t";
//               List<Member> result =  entityManager.createQuery(query, Member.class)
//                        .getResultList();
            // 4 ###############  조인 ########################################



// 3. 페이징
            // 3.############ 페이징 ########################################
//                List<Member> result = entityManager.createQuery("select m from Member m order by m.age desc", Member.class)
//                        .setFirstResult(1)
//                        .setMaxResults(10)
//                        .getResultList();
//
//                System.out.println("result.size = " + result.size());
//                for (Member member1 : result) {
//                    System.out.println("member1 = " + member1);
//                }
            // ############ 페이징 ########################################



// 2.파라미터 바인딩
            // 2. ############ 파라미터 바인딩 ########################################
            //            Member result = entityManager.createQuery("select m from Member m where m.username = :username", Member.class)
            //                    .setParameter("username", "member1")
            //                    .getSingleResult();
            //            System.out.println("singleResult = " + result.getUsername());
            //   ############ 파라미터 바인딩 ########################################



// 1.리스트 단일
            // 1.  ############ 리스트 , 단일 ########################################
            //			List<Member> resultList = query1.getResultList(); // 결과가 하나 이상일 때, 리스트 반환
            //			Member result = query1.getSingleResult(); // 결과가 정확히 하나일 때, 단일 객체 반환
            // ############ 리스트 , 단일 ########################################


            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();

    }
}
