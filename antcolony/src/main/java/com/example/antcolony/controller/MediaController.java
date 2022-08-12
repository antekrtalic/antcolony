package com.example.antcolony.controller;

import com.example.antcolony.api.service.MediaServiceLayer;
import com.example.antcolony.dto.MediaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private ModelMapper modelMapper;
    private MediaServiceLayer mediaServiceLayer;

    @Autowired
    public MediaController(ModelMapper modelMapper, MediaServiceLayer mediaServiceLayer) {
        this.modelMapper = modelMapper;
        this.mediaServiceLayer = mediaServiceLayer;
    }

    @GetMapping("/")
    public List<MediaDTO> getAllMedia() {
        return mediaServiceLayer.findAll().stream()
                .map(media -> modelMapper.map(media, MediaDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MediaDTO getOneMedia(@PathVariable("id") Long id) {
        return mediaServiceLayer.findOneMediaById(id);
    }

    @PostMapping("/add")
    public MediaDTO addMedia(MediaDTO mediaDTO) {
        return mediaServiceLayer.addMedia(mediaDTO);
    }

    @PutMapping("/{id}")
    public MediaDTO updateMedia(@PathVariable("id") Long id, MediaDTO mediaDTO) {
        return mediaServiceLayer.updateMedia(id, mediaDTO);
    }

    @DeleteMapping("/delete/{id}")
    public MediaDTO deleteMedia(@PathVariable("id") Long id) {
        return mediaServiceLayer.deleteMedia(id);
    }

}
