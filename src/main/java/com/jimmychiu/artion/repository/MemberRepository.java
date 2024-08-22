package com.jimmychiu.artion.repository;

import com.jimmychiu.artion.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUsername(String username);
}
