package com.challenge.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.api.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
