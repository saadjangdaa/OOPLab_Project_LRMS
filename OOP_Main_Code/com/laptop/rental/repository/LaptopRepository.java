package com.laptop.rental.repository;

import com.laptop.rental.model.Laptop;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LaptopRepository {
    private static final String FILE_NAME = "laptops.txt";

    public void save(Laptop laptop) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.printf("ID: %d Brand: %s Model: %s Specs: %s Available: %s Rate: %.2f Condition: %s%n", 
                    laptop.getId(), laptop.getBrand(), laptop.getModel(), laptop.getSpecifications(),
                    laptop.isAvailable(), laptop.getHourlyRate(), laptop.getCondition());
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
                String[] parts = line.split(" ");
                if (parts.length >= 14) {
                    int id = Integer.parseInt(parts[1]);
                    String brand = parts[3];
                    String model = parts[5];
                    String specs = parts[7];
                    boolean available = Boolean.parseBoolean(parts[9]);
                    double rate = Double.parseDouble(parts[11]);
                    String condition = parts[13];
                    laptops.add(new Laptop(id, brand, model, specs, available, rate, condition));
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
                writer.printf("ID: %d Brand: %s Model: %s Specs: %s Available: %s Rate: %.2f Condition: %s%n", 
                        laptop.getId(), laptop.getBrand(), laptop.getModel(), laptop.getSpecifications(),
                        laptop.isAvailable(), laptop.getHourlyRate(), laptop.getCondition());
            }
        }
    }
} 