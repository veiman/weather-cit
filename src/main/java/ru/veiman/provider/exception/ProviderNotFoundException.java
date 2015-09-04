package ru.veiman.provider.exception;

/**
 * Created by veiman on 25.08.15.
 */
public class ProviderNotFoundException extends Exception {

    public ProviderNotFoundException(String providerName) {
        super("Provider " + providerName + " not found. Check your config file.");
    }
}
