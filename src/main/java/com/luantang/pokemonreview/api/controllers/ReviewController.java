package com.luantang.pokemonreview.api.controllers;

import com.luantang.pokemonreview.api.dto.ReviewDto;
import com.luantang.pokemonreview.api.models.Review;
import com.luantang.pokemonreview.api.services.ReviewService;
import com.luantang.pokemonreview.api.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public ReviewController(ReviewService reviewService, SequenceGeneratorService sequenceGeneratorService) {
        this.reviewService = reviewService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDto> createView(@PathVariable(value = "pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) {
        reviewDto.setId(sequenceGeneratorService.getSequenceNumber(Review.SEQUENCE_NAME));
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);
    }

     @GetMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId) {
        return new ResponseEntity<>(reviewService.getReviewByPokemonId(pokemonId), HttpStatus.OK);
    }

    @GetMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(pokemonId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/review/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId, @RequestBody ReviewDto reviewDto) {
        ReviewDto response = reviewService.updateReview(pokemonId, reviewId, reviewDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{pokemonId}/review/{reviewId}/delete")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId) {
        reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Review Deleted", HttpStatus.OK);
    }
}
