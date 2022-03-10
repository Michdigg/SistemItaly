package com.sistemitaly.Bean;

import java.util.Arrays;

public class GenericResponse {

    public String status;

    public Object[] error;

    public GenericResponsePayload payload;

    @Override
    public String toString() {
        return "BalanceResponse{" +
                "status='" + status + '\'' +
                ", error=" + Arrays.toString(error) +
                ", payload=" + payload +
                '}';
    }
}
