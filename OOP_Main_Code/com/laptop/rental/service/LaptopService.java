package com.laptop.rental.service;

import com.laptop.rental.model.Laptop;
import com.laptop.rental.repository.LaptopRepository;
import java.io.IOException;
import java.util.List;

public class LaptopService {
    private LaptopRepository laptopRepository = new LaptopRepository();

    public boolean addLaptop(int id, String brand, String model, String specifications, double hourlyRate, String condition) throws IOException {
        if (laptopRepository.findById(id) != null) {
            return false;
        }

        Laptop laptop = new Laptop(id, brand, model, specifications, true, hourlyRate, condition);
        laptopRepository.save(laptop);
        return true;
    }

    public Laptop getLaptopById(int id) throws IOException {
        return laptopRepository.findById(id);
    }

    public List<Laptop> getAllLaptops() throws IOException {
        return laptopRepository.findAll();
    }

    public List<Laptop> getAvailableLaptops() throws IOException {
        return laptopRepository.findAvailable();
    }

    public List<Laptop> getLaptopsByBrand(String brand) throws IOException {
        return laptopRepository.findByBrand(brand);
    }

    public boolean updateLaptop(int id, String brand, String model, String specifications, double hourlyRate, String condition) throws IOException {
        Laptop laptop = laptopRepository.findById(id);
        if (laptop == null) {
            return false;
        }

        laptop.setBrand(brand);
        laptop.setModel(model);
        laptop.setSpecifications(specifications);
        laptop.setHourlyRate(hourlyRate);
        laptop.setCondition(condition);
        
        laptopRepository.update(laptop);
        return true;
    }

    public boolean deleteLaptop(int id) throws IOException {
        Laptop laptop = laptopRepository.findById(id);
        if (laptop == null) {
            return false;
        }

        laptopRepository.delete(id);
        return true;
    }

    public boolean setLaptopAvailability(int id, boolean available) throws IOException {
        Laptop laptop = laptopRepository.findById(id);
        if (laptop == null) {
            return false;
        }

        laptop.setAvailable(available);
        laptopRepository.update(laptop);
        return true;
    }

    public boolean updateLaptopCondition(int id, String condition) throws IOException {
        Laptop laptop = laptopRepository.findById(id);
        if (laptop == null) {
            return false;
        }

        laptop.setCondition(condition);
        laptopRepository.update(laptop);
        return true;
    }

    public boolean updateLaptopRate(int id, double hourlyRate) throws IOException {
        Laptop laptop = laptopRepository.findById(id);
        if (laptop == null) {
            return false;
        }

        laptop.setHourlyRate(hourlyRate);
        laptopRepository.update(laptop);
        return true;
    }

    public int getAvailableLaptopCount() throws IOException {
        return laptopRepository.findAvailable().size();
    }

    public int getTotalLaptopCount() throws IOException {
        return laptopRepository.findAll().size();
    }
} 