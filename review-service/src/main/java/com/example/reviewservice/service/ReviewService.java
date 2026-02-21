package com.example.reviewservice.service;

import com.example.reviewservice.dto.ReviewRequestDTO;
import com.example.reviewservice.dto.ReviewResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    ReviewResponseDTO createReview(ReviewRequestDTO requestDTO);

    Page<ReviewResponseDTO> getReviewsByBookId(Long bookId, Pageable pageable);

    Page<ReviewResponseDTO> getReviewsByUserId(Long userId, Pageable pageable);

    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO requestDTO);

    void deleteReview(Long id);

    Double getAverageRatingByBookId(Long bookId);

}
