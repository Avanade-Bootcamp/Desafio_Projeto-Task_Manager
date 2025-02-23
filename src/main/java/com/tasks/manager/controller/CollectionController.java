package com.tasks.manager.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.manager.controller.dto.CollectionResponseDTO;
import com.tasks.manager.domain.model.Collection;
import com.tasks.manager.domain.model.User;
import com.tasks.manager.service.CollectionService;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<CollectionResponseDTO> getAllCollections() {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Collection> collections = collectionService.getAllCollections(userId);
        return collections.stream()
            .map(collection -> new CollectionResponseDTO(collection.getId(), collection.getTitle(), collection.getUser().getId()))
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable Long id) {
        return collectionService.getCollectionById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CollectionResponseDTO> createCollection(@RequestBody Map<String, String> request) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String title = request.get("title");
        Collection collection = collectionService.createCollection(title, userId);
        CollectionResponseDTO responseDTO = new CollectionResponseDTO(collection.getId(), collection.getTitle(), collection.getUser().getId());
        return ResponseEntity.ok(responseDTO);
    }
}
