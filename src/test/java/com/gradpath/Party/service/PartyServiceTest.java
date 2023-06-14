package com.gradpath.Party.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gradpath.party.entity.Party;
import com.gradpath.party.exception.PartyNotFoundException;
import com.gradpath.party.dao.PartyRepository;
import com.gradpath.party.service.PartyService;

public class PartyServiceTest {
    
    @Mock
    private PartyRepository partyRepository;
    
    @InjectMocks
    private PartyService partyService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllParties() {
        // Mocking the behavior of the repository
        List<Party> partyList = new ArrayList<>();
        partyList.add(new Party("1", "John", "Doe", null, null, null, null));
        partyList.add(new Party("2", "Jane", "Smith", null, null, null, null));
        when(partyRepository.findAll()).thenReturn(partyList);
        
        // Calling the service method
        List<Party> result = partyService.getAllParties();
        
        // Verifying the result
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getPartyId());
        assertEquals("John", result.get(0).getPartyFirstName());
        assertEquals("Doe", result.get(0).getPartyMiddleName());
        assertEquals("2", result.get(1).getPartyId());
        assertEquals("Jane", result.get(1).getPartyFirstName());
        assertEquals("Smith", result.get(1).getPartyMiddleName());
        
        // Verifying that the repository method was called
        verify(partyRepository, times(1)).findAll();
    }
    
    @Test
    public void testGetPartyById() {
        // Mocking the behavior of the repository
        Party party = new Party("1", "John", "Doe", null, null, null, null);
        when(partyRepository.findById("1")).thenReturn(Optional.of(party));
        
        // Calling the service method
        Party result = partyService.getPartyById("1");
        
        // Verifying the result
        assertNotNull(result);
        assertEquals("1", result.getPartyId());
        assertEquals("John", result.getPartyFirstName());
        assertEquals("Doe", result.getPartyMiddleName());
        
        // Verifying that the repository method was called
        verify(partyRepository, times(1)).findById("1");
    }
    
    @Test
    public void testGetPartyById_NotFound() {
        // Mocking the behavior of the repository
        when(partyRepository.findById("1")).thenReturn(Optional.empty());
        
        // Calling the service method and asserting that it throws an exception
        assertThrows(PartyNotFoundException.class, () -> partyService.getPartyById("1"));
        
        // Verifying that the repository method was called
        verify(partyRepository, times(1)).findById("1");
    }
    
    @Test
    public void testCreateParty() {
        // Mocking the behavior of the repository
        Party party = new Party("1", "John", "Doe", null, null, null, null);
        when(partyRepository.save(party)).thenReturn(party);
        
        // Calling the service method
        Party result = partyService.createParty(party);
        
        // Verifying the result
        assertNotNull(result);
        assertEquals("1", result.getPartyId());
        assertEquals("John", result.getPartyFirstName());
        assertEquals("Doe", result.getPartyMiddleName());
        
        // Verifying that the repository method was called
        verify(partyRepository, times(1)).save(party);
    }
    
    @Test
    public void testUpdateParty() {
        // Mocking the behavior of the repository
        Party existingParty = new Party("1", "John", "Doe", null, null, null, null);
        when(partyRepository.findById("1")).thenReturn(Optional.of(existingParty));
        
        Party updatedParty = new Party("1", "Jane", "Smith", null, null, null, null);
        when(partyRepository.save(any())).thenReturn(updatedParty);
        
        // Calling the service method
        Party result = partyService.updateParty("1", updatedParty);
        
        // Verifying the result
        assertNotNull(result);
        assertEquals("1", result.getPartyId());
        assertEquals("Jane", result.getPartyFirstName());
        assertEquals("Smith", result.getPartyMiddleName());
        
        // Verifying that the repository methods were called
        verify(partyRepository, times(1)).findById("1");
        verify(partyRepository, times(1)).save(any());
    }
    
    @Test
    public void testUpdateParty_NotFound() {
        // Mocking the behavior of the repository
        when(partyRepository.findById("1")).thenReturn(Optional.empty());
        
        Party updatedParty = new Party("1", "Jane", "Smith", null, null, null, null);
        
        // Calling the service method and asserting that it throws an exception
        assertThrows(PartyNotFoundException.class, () -> partyService.updateParty("1", updatedParty));
        
        // Verifying that the repository method was called
        verify(partyRepository, times(1)).findById("1");
        verify(partyRepository, times(0)).save(updatedParty);
    }
    
    @Test
    public void testDeleteParty() {
        // Mocking the behavior of the repository
        Party existingParty = new Party("1", "John", "Doe", null, null, null, null);
        when(partyRepository.findById("1")).thenReturn(Optional.of(existingParty));
        
        // Calling the service method
        boolean result = partyService.deleteParty("1");
        
        // Verifying the result
        assertTrue(result);
        
        // Verifying that the repository methods were called
        verify(partyRepository, times(1)).findById("1");
        verify(partyRepository, times(1)).delete(existingParty);
    }
    
    @Test
    public void testDeleteParty_NotFound() {
        // Mocking the behavior of the repository
        when(partyRepository.findById("1")).thenReturn(Optional.empty());
        
        // Calling the service method
        boolean result = partyService.deleteParty("1");
        
        // Verifying the result
        assertFalse(result);
        
        // Verifying that the repository method was called
        verify(partyRepository, times(1)).findById("1");
        verify(partyRepository, times(0)).delete(any());
    }
}
