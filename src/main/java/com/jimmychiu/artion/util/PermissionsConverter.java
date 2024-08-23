package com.jimmychiu.artion.util;

import com.jimmychiu.artion.enumType.Permission;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionsConverter {
    public static Set<Permission> stringToPermissionSet(String permissions) {
        return Arrays.stream(permissions.split(","))
                .map(Permission::valueOf)
                .collect(Collectors.toSet());
    }
}
