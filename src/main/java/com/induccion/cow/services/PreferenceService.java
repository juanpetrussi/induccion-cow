package com.induccion.cow.services;

import com.induccion.cow.models.PreferenceDto;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

public class PreferenceService {

    private static final PreferenceService INSTANCE = new PreferenceService();
    private PreferenceService() {
        if (INSTANCE != null) {
            throw new IllegalStateException("PreferenceService singleton already created");
        }
    }
    public static PreferenceService getInstance() {
        return  INSTANCE;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone PreferenceService class");
    }

    public Preference createPreferencePunto1(PreferenceDto preferenceDto) throws MPException {
        Preference preference =
                new Preference().setPayer(preferenceDto.getPayer()).appendItem(preferenceDto.getItem()).save();

        return preference;
    }
}
