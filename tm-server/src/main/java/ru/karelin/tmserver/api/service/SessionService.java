package ru.karelin.tmserver.api.service;

import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.exception.WrongSessionException;

public interface SessionService {
    @Nullable
    Session getNewSession(String login, String password);

    void removeOldSessions(int minutes);

    void removeSession(@Nullable String sessionId) throws WrongSessionException;

    boolean isSessionExists(@Nullable SessionDto session) throws WrongSessionException;

    String serverInfo();
}
