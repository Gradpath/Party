package com.gradpath.party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradpath.party.dao.PartyRepository;
import com.gradpath.party.entity.Party;
import com.gradpath.party.exception.PartyNotFoundException;

import java.util.List;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    public Party getPartyById(String id) {
    	return partyRepository.findById(id)
                .orElseThrow(() -> new PartyNotFoundException("Party not found with ID: " + id));
    }

    public Party createParty(Party party) {
        return partyRepository.save(party);
    }

    public Party updateParty(String id, Party updatedParty) {
        Party party = partyRepository.findById(id).orElse(null);
        if (party != null) {
            party.setPartyFirstName(updatedParty.getPartyFirstName());
            party.setPartyMiddleName(updatedParty.getPartyMiddleName());
            party.setPartyCreationDate(updatedParty.getPartyCreationDate());
            // Update other properties as needed
            return partyRepository.save(party);
        } else {
            throw new PartyNotFoundException("Party not found with ID: " + id);
        }
    }

    public boolean deleteParty(String id) {
        Party party = partyRepository.findById(id).orElse(null);
        if (party != null) {
            partyRepository.delete(party);
            return true;
        } else {
            return false;
        }
    }
}

