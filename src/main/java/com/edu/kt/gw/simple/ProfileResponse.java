package com.edu.kt.gw.simple;

import java.util.Set;

record ProfileResponse(String username, Set<String> roles) {
}