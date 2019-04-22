package ru.karelin.tmserver.util;



import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class SignatureUtil {

        @Contract("null, _, _ -> null; !null, null, _ -> null; !null, !null, null -> null")
        @Nullable
        public static String sign(
                @Nullable final String value,
                @Nullable final String salt,
                @Nullable final Integer cycle
        ) {
            if (value == null || salt == null || cycle == null) return null;
            @Nullable String result = value;
            MD5Generator md5Generator = new MD5Generator();
            for (int i = 0; i < cycle; i++) {
                result = md5Generator.generate(salt + result + salt);
            }
            return result;
        }

}
