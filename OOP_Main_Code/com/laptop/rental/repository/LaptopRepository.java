package com.laptop.rental.repository;

import com.laptop.rental.model.Laptop;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LaptopRepository {
    private static final String FILE_NAME = "laptops.txt";

    public void save(Laptop laptop) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.printf("ID: %d, Brand: %s, Model: %s, Specs: %s, Rate: %.2f, Condition: %s%n", 
                    laptop.getId(), laptop.getBrand(), laptop.getModel(), laptop.getSpecifications(),
                    laptop.getHourlyRate(), laptop.getCondition());
        }
    }

    public List<Laptop> findAll() throws IOException {
        List<Laptop> laptops = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return laptops;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        String idPart = parts[0].trim();
                        int id = Integer.parseInt(idPart.substring(idPart.indexOf(":") + 1).trim());
                        
                        String brandPart = parts[1].trim();
                        String brand = brandPart.substring(brandPart.indexOf(":") + 1).trim();
                        
                        String modelPart = parts[2].trim();
                        String model = modelPart.substring(modelPart.indexOf(":") + 1).trim();
                        
                        String specsPart = parts[3].trim();
                        String specs = specsPart.substring(specsPart.indexOf(":") + 1).trim();
                        
                        String ratePart = parts[parts.length - 2].trim();
                        double rate = Double.parseDouble(ratePart.substring(ratePart.indexOf(":") + 1).trim());
                        
                        String conditionPart = parts[parts.length - 1].trim();
                        String condition = conditionPart.substring(conditionPart.indexOf(":") + 1).trim();
                        
                        boolean available = true;
                        
                        laptops.add(new Laptop(id, brand, model, specs, available, rate, condition));
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }
        }
        return laptops;
    }

    public Laptop findById(int id) throws IOException {
        for (Laptop laptop : findAll()) {
            if (laptop.getId() == id) {
                return laptop;
            }
        }
        return null;
    }

    public List<Laptop> findAvailable() throws IOException {
        List<Laptop> availableLaptops = new ArrayList<>();
        for (Laptop laptop : findAll()) {
            if (laptop.isAvailable()) {
                availableLaptops.add(laptop);
            }
        }
        return availableLaptops;
    }

    public List<Laptop> findByBrand(String brand) throws IOException {
        List<Laptop> brandLaptops = new ArrayList<>();
        for (Laptop laptop : findAll()) {
            if (laptop.getBrand().equalsIgnoreCase(brand)) {
                brandLaptops.add(laptop);
            }
        }
        return brandLaptops;
    }

    public void update(Laptop laptop) throws IOException {
        List<Laptop> laptops = findAll();
        for (int i = 0; i < laptops.size(); i++) {
            if (laptops.get(i).getId() == laptop.getId()) {
                laptops.set(i, laptop);
                break;
            }
        }
        saveAll(laptops);
    }

    public void delete(int id) throws IOException {
        List<Laptop> laptops = findAll();
        laptops.removeIf(laptop -> laptop.getId() == id);
        saveAll(laptops);
    }

    private void saveAll(List<Laptop> laptops) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            for (Laptop laptop : laptops) {
                writer.printf("ID: %d, Brand: %s, Model: %s, Specs: %s, Rate: %.2f, Condition: %s%n", 
                        laptop.getId(), laptop.getBrand(), laptop.getModel(), laptop.getSpecifications(),
                        laptop.getHourlyRate(), laptop.getCondition());
            }
        }
    }
} 