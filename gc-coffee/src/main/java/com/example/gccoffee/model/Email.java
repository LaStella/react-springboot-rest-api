package com.example.gccoffee.model;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private final String address;

    public Email(String address) {
        // 주소값이 없는 경우 예외처리
        Assert.notNull(address, "address should not be null");
        // 4-50자인지 확인한다.
        Assert.isTrue(address.length() >= 4 && address.length() <= 50,"address length must be between 4 and 50 characters.");
        Assert.isTrue(checkAddress(address), "Invalid email address");
        this.address = address;
    }

    private static boolean checkAddress(String address) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", address);
    }


    // 이메일주소가 같으면 두 객체가 같다고 하기 위한 equals와 hashCode는 직접 구현하는 것이 좋다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }
}
