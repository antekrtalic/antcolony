package com.example.antcolony.service;

import com.example.antcolony.api.service.MediaServiceLayer;
import com.example.antcolony.dto.MediaDTO;
import com.example.antcolony.models.Media;
import com.example.antcolony.repository.MediaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MediaServiceLayerImpl implements MediaServiceLayer {

    private ModelMapper modelMapper;

    private MediaRepository mediaRepository;

    @Autowired
    public MediaServiceLayerImpl(ModelMapper modelMapper, MediaRepository mediaRepository) {
        this.modelMapper = modelMapper;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public List<MediaDTO> findAll() {
        return mediaRepository.findAll().stream()
                .map(media -> modelMapper.map(media, MediaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MediaDTO findOneMediaById(Long id) {
        Optional<Media> result = mediaRepository.findById(id);

        Media media = null;

        if (result.isPresent()) {
            media = result.get();
        } else {
            throw new RuntimeException("Did not find any media with id " + id);
        }

        return modelMapper.map(media, MediaDTO.class);
    }

    @Override
    public MediaDTO addMedia(MediaDTO mediaDTO) {
        Media mediaRequest = modelMapper.map(mediaDTO, Media.class);

        Media media = mediaRepository.save(mediaRequest);

        MediaDTO mediaRespose = modelMapper.map(media, MediaDTO.class);

        return mediaRespose;
    }

    @Override
    public MediaDTO updateMedia(Long id, MediaDTO mediaDTO) {
        mediaDTO.setId(id);

        Media mediaRequest = modelMapper.map(mediaDTO, Media.class);

        mediaRepository.save(mediaRequest);

        MediaDTO mediaResponse = modelMapper.map(mediaRequest, MediaDTO.class);

        return mediaResponse;

    }

    @Override
    public MediaDTO deleteMedia(Long id) {

        Media media = mediaRepository.getReferenceById(id);

        mediaRepository.deleteById(id);

        MediaDTO mediaDTO = modelMapper.map(media, MediaDTO.class);

        return mediaDTO;
    }
}
