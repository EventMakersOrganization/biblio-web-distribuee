package com.example.reviewservice.service;

import com.example.reviewservice.dto.ReviewRequestDTO;
import com.example.reviewservice.dto.ReviewResponseDTO;
import com.example.reviewservice.entity.Review;
import com.example.reviewservice.exception.ResourceNotFoundException;
import com.example.reviewservice.exception.ReviewAlreadyExistsException;
import com.example.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO requestDTO) {
        Optional<Review> existing = reviewRepository.findByBookIdAndUserId(requestDTO.getBookId(), requestDTO.getUserId());
        if (existing.isPresent()) {
            throw new ReviewAlreadyExistsException("Review already exists for user " + requestDTO.getUserId() + " and book " + requestDTO.getBookId());
        }
        Review review = mapToEntity(requestDTO);
        Review saved = reviewRepository.save(review);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewResponseDTO> getReviewsByBookId(Long bookId, Pageable pageable) {
        return reviewRepository.findByBookId(bookId, pageable).map(this::mapToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewResponseDTO> getReviewsByUserId(Long userId, Pageable pageable) {
        return reviewRepository.findByUserId(userId, pageable).map(this::mapToDTO);
    }

    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO requestDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
        review.setRating(requestDTO.getRating());
        review.setComment(requestDTO.getComment());
        Review updated = reviewRepository.save(review);
        return mapToDTO(updated);
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id " + id);
        }
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageRatingByBookId(Long bookId) {
        return reviewRepository.getAverageRatingByBookId(bookId);
    }

    private Review mapToEntity(ReviewRequestDTO dto) {
        Review review = new Review();
        review.setUserId(dto.getUserId());
        review.setBookId(dto.getBookId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return review;
    }

    private ReviewResponseDTO mapToDTO(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setUserId(review.getUserId());
        dto.setBookId(review.getBookId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setUpdatedAt(review.getUpdatedAt());
        return dto;
    }

}
