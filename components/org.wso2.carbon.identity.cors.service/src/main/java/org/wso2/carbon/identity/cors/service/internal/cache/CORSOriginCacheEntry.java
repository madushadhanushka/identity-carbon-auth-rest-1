/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.identity.cors.service.internal.cache;

import org.wso2.carbon.identity.application.common.cache.CacheEntry;
import org.wso2.carbon.identity.cors.mgt.core.model.ValidatedOrigin;

/**
 * CORS origin container cache entry.
 */
public class CORSOriginCacheEntry extends CacheEntry {

    private static final long serialVersionUID = -6544537094095542728L;

    private final ValidatedOrigin[] validatedOrigins;

    public CORSOriginCacheEntry(ValidatedOrigin[] validatedOrigins) {

        this.validatedOrigins = validatedOrigins;
    }

    public ValidatedOrigin[] getValidatedOrigins() {

        return validatedOrigins;
    }
}
