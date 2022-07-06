package com.nexai.project.model.entity;

import com.nexai.project.model.entity.type.CarClass;
import com.nexai.project.model.entity.type.EngineType;
import com.nexai.project.model.entity.type.GearboxType;

import java.io.Serializable;
import java.math.BigDecimal;

public class Car implements Identifiable, Serializable {

    private static final long serialVersionUID = -1686129624753722221L;

    private int id;
    private String brand;
    private String model;
    private GearboxType gearbox;
    private String manufacturedYear;
    private EngineType engineType;
    private BigDecimal pricePerDay;
    private CarClass carClass;
    private String imagePath;

    public Car() {
    }

    public Car(int id, String brand, String model, GearboxType gearbox, String manufacturedYear, EngineType engineType, BigDecimal pricePerDay, CarClass carClass, String imagePath) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.gearbox = gearbox;
        this.manufacturedYear = manufacturedYear;
        this.engineType = engineType;
        this.pricePerDay = pricePerDay;
        this.carClass = carClass;
        this.imagePath = imagePath;
    }

    public Car(String brand, String model, GearboxType gearbox, String manufacturedYear, EngineType engineType, BigDecimal pricePerDay, CarClass carClass, String imagePath) {
        this(-1, brand, model, gearbox, manufacturedYear, engineType, pricePerDay, carClass, imagePath);
    }


    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public GearboxType getGearbox() {
        return gearbox;
    }

    public String getManufacturedYear() {
        return manufacturedYear;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (gearbox != car.gearbox) return false;
        if (manufacturedYear != null ? !manufacturedYear.equals(car.manufacturedYear) : car.manufacturedYear != null)
            return false;
        if (engineType != car.engineType) return false;
        if (pricePerDay != null ? !pricePerDay.equals(car.pricePerDay) : car.pricePerDay != null) return false;
        if (carClass != car.carClass) return false;
        return imagePath != null ? imagePath.equals(car.imagePath) : car.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (gearbox != null ? gearbox.hashCode() : 0);
        result = 31 * result + (manufacturedYear != null ? manufacturedYear.hashCode() : 0);
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", gearbox=").append(gearbox);
        sb.append(", manufacturedYear='").append(manufacturedYear).append('\'');
        sb.append(", engineType=").append(engineType);
        sb.append(", pricePerDay=").append(pricePerDay);
        sb.append(", carClass=").append(carClass);
        sb.append(", imagePath='").append(imagePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}