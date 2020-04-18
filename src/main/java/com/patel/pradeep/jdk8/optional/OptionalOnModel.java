package com.patel.pradeep.jdk8.optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class OptionalOnModel {
    public static void main(String[] args) {
        Insurance insurance = new Insurance();
        insurance.setName("LIC");
        Car car = new Car();
        car.setInsurance(Optional.of(insurance));
        Person person = new Person();
        person.setCar(Optional.of(car));

        Insurance insurance1 = new Insurance();
        insurance1.setName("PPF");
        Car car1 = new Car();
        car.setInsurance(Optional.of(insurance1));
        Person person1 = new Person();
        person1.setCar(Optional.of(car1));
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person1);

        Optional<Insurance> optInsurance = Optional.of(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
        System.out.println(name.orElse("NO-DATA"));

        Optional<Person> optPerson = Optional.of(person);
        System.out.println(getCarInsuranceName(optPerson));
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}

@Getter
@Setter
@ToString
class Person {
    private Optional<Car> car;
    public Optional<Car> getCar() { return car; }
}
@Getter
@Setter
@ToString
class Car {
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance() { return insurance; }
}
@Getter
@Setter
@ToString
class Insurance {
    private String name;
    public String getName() { return name; }
}