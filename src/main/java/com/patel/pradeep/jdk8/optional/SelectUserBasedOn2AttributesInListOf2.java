package com.patel.pradeep.jdk8.optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SelectUserBasedOn2AttributesInListOf2 {
    public static void main(String[] args) {
        User user = new User();
        user.setOrgNumber("US00000");
        user.setLocked(true);
        user.setCreatedDate(LocalDate.of(2019, Month.APRIL, 1));
        user.setUpdatedDate(LocalDate.of(2019, Month.MAY, 1));

        Entitlement entitlement = new Entitlement();
        entitlement.setCreatedDate(LocalDate.of(2019, Month.APRIL, 1));
        entitlement.setUpdatedDate(LocalDate.of(2019, Month.MAY, 1));
        entitlement.setName("L2 Supplier");
        user.getEntitlements().add(entitlement);

        User user1 = new User();
        user1.setOrgNumber("US00004");
        user1.setLocked(false);
        user1.setCreatedDate(LocalDate.of(2020, Month.APRIL, 1));
        user1.setUpdatedDate(LocalDate.of(2020, Month.MAY, 1));

        Entitlement entitlement1 = new Entitlement();
        entitlement1.setCreatedDate(LocalDate.of(2020, Month.APRIL, 1));
        entitlement1.setUpdatedDate(LocalDate.of(2020, Month.MAY, 1));
        entitlement1.setName("Spender Ops Admin");
        user1.getEntitlements().add(entitlement1);

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        System.out.println(users);

        Optional<User> finalUser = Optional.empty();
        if(getActiveUsersCount(users) > 1){
            finalUser = getModelOfficeUser(users);
        }else if(getActiveUsersCount(users) == 1){
            finalUser = getActiveUser(users);
        }else{
            finalUser = getModelOfficeUser(users);
        }
        System.out.println(finalUser);
    }

    public static long getActiveUsersCount(List<User> users) {
        return users.stream()
                .filter(x -> !x.isLocked())
                .count();
    }

    public static Optional<User> getActiveUser(List<User> users) {
        return users.stream()
                .filter(x -> !x.isLocked())
                .findFirst();
    }

    public static Optional<User> getModelOfficeUser(List<User> users) {
        return users.stream()
                .filter(x -> x.getOrgNumber().equals("US00000"))
                .findFirst();
    }

}

@Getter
@Setter
@ToString
class User {
    private boolean isLocked;
    private String orgNumber;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private List<Entitlement> entitlements = new ArrayList<>();
}

@Getter
@Setter
@ToString
class Entitlement {
    private String name;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}