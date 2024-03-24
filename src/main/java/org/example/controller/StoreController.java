package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.model.Store;
import org.example.repository.StoreRepository;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Controller
public class StoreController {

    StoreRepository storeRepository;

    public void createStore(Store store) {
        storeRepository.save(store);
    }

    public void deleteStore(Store store) {
        storeRepository.delete(store);
    }

    public void updateStore(Store store) {
        Optional<Store> existingStoreOptional = storeRepository.findById(store.getId());
        if (existingStoreOptional.isPresent()) {
            Store existingStore = existingStoreOptional.get();
            existingStore.setName(store.getName());
            existingStore.setMaxCustomers(store.getMaxCustomers());
            existingStore.setProfit(store.getProfit());

            storeRepository.save(existingStore);
        } else {
            throw new IllegalArgumentException("Store not found with ID: " + store.getId());
        }
    }

    public Optional<Store> findById(Long id) {
        return storeRepository.findById(id);
    }

    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    public List<Store> searchByName(String name) {
        return storeRepository.searchByName(name);
    }
}
