package com.thc.realspr.repository;

import com.thc.realspr.domain.Tbpost;
import com.thc.realspr.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbpostRepository extends JpaRepository<Tbpost, String> {

    List<Tbpost> findAllByBoardname(String boardname);
}
