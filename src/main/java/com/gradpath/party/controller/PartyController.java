package com.gradpath.party.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gradpath.party.entity.Party;
import com.gradpath.party.service.PartyService;

import java.util.List;

@RestController
@RequestMapping("/parties")
@CrossOrigin(origins = "http://localhost:4200")
public class PartyController {

    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    // Get all parties
    @GetMapping("/getAllParties")
    public ResponseEntity<List<Party>> getAllParties() {
        List<Party> parties = partyService.getAllParties();
        return new ResponseEntity<>(parties, HttpStatus.OK);
    }

    // Get a party by ID
    @GetMapping("/{id}")
    public ResponseEntity<Party> getPartyById(@PathVariable("id") String id) {
        Party party = partyService.getPartyById(id);
        if (party != null) {
            return new ResponseEntity<>(party, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new party
    @PostMapping("/createParty")
    public ResponseEntity<Party> createParty(@RequestBody Party party) {
        Party createdParty = partyService.createParty(party);
        return new ResponseEntity<>(createdParty, HttpStatus.CREATED);
    }

    // Update an existing party
    @PutMapping("/updateParty/{id}")
    public ResponseEntity<Party> updateParty(@PathVariable("id") String id, @RequestBody Party party) {
        Party updatedParty = partyService.updateParty(id, party);
        if (updatedParty != null) {
            return new ResponseEntity<>(updatedParty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a party
    @DeleteMapping("/deleteParty/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable("id") String id) {
        boolean deleted = partyService.deleteParty(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

