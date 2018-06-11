package com.wpruszak.kotlin

import java.security.Provider
import java.security.Security

class Providers {
    fun getProviders(): List<Provider> {
        return Security.getProviders().asList()
    }
}
