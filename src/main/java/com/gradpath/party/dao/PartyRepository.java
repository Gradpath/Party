package com.gradpath.party.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gradpath.party.entity.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, String> {
}
