/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.auth.valve.internal;

import org.wso2.carbon.identity.auth.service.AuthenticationManager;
import org.wso2.carbon.identity.auth.service.factory.AuthenticationRequestBuilderFactory;
import org.wso2.carbon.user.core.service.RealmService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Holder.
 */
public class AuthenticationValveServiceHolder {

    private static AuthenticationValveServiceHolder authenticationValveServiceHolder = new
            AuthenticationValveServiceHolder();
    private List<AuthenticationRequestBuilderFactory> requestBuilderFactories = new ArrayList<>();
    private RealmService realmService;

    private List<AuthenticationManager> authenticationManagers = new ArrayList<>();

    private AuthenticationValveServiceHolder() {
    }

    public static AuthenticationValveServiceHolder getInstance() {
        return AuthenticationValveServiceHolder.authenticationValveServiceHolder;
    }

    public List<AuthenticationManager> getAuthenticationManagers() {
        return authenticationManagers;
    }

    public List<AuthenticationRequestBuilderFactory> getRequestBuilderFactories() {
        return requestBuilderFactories;
    }

    public RealmService getRealmService() {

        if (realmService == null) {
            throw new RuntimeException("RealmService is null");
        }

        return realmService;
    }

    public void setRealmService(RealmService realmService) {

        this.realmService = realmService;
    }
}
