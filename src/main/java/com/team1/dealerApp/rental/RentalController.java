package com.team1.dealerApp.rental;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

  @PostMapping()
  public ResponseEntity<?> addRental(@RequestParam (name = "userId") UUID userId, @RequestBody CreateRentalDTO createRentalDTO){
    try{
        return ResponseEntity.ok(rentalService.addRental(userId, createRentalDTO));
    } catch (BadRequestException e) {
        log.error("Error in adding a new Rental {} : {}", createRentalDTO, e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
