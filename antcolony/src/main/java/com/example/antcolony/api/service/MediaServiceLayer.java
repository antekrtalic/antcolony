package com.example.antcolony.api.service;

import com.example.antcolony.dto.MediaDTO;

import java.util.List;

public interface MediaServiceLayer {

    List<MediaDTO> findAll();
    MediaDTO findOneMediaById(Long id);
    MediaDTO addMedia(MediaDTO mediaDTO);
    MediaDTO updateMedia(Long id, MediaDTO mediaDTO);
    MediaDTO deleteMedia(Long id);
}
