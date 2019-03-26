package ru.karelin.tm.api.service;

import java.io.IOException;

public interface Service {
    void saveSerialize() throws IOException;
    void getSerialize() throws IOException, ClassNotFoundException;
}
