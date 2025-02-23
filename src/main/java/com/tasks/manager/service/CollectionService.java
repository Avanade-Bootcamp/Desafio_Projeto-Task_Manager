package com.tasks.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.manager.domain.model.Collection;
import com.tasks.manager.domain.model.User;
import com.tasks.manager.domain.repository.CollectionRepository;
import com.tasks.manager.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {
    
    
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Collection> getAllCollections(Long userId) {
        return collectionRepository.findByUserId(userId);
    }

    public Optional<Collection> getCollectionById(Long id) {
        return collectionRepository.findById(id);
    }

    public Collection createCollection(String title, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Collection collection = new Collection();
        collection.setTitle(title);
        collection.setUser(userOptional.get());

        return collectionRepository.save(collection);
    }

    public void deleteCollection(Long id) {
        collectionRepository.deleteById(id);
    }

}