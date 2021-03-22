/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.identity.context.rewrite.internal;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.core.service.RealmService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.utils.CarbonUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component(
         name = "identity.context.rewrite.valve.component", 
         immediate = true)
public class ContextRewriteValveServiceComponent {

    private static final Log log = LogFactory.getLog(ContextRewriteValveServiceComponent.class);

    @Activate
    protected void activate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("ContextRewriteValveServiceComponent is activated.");
        }
        loadPageNotFoundErrorPage();
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("ContextRewriteValveServiceComponent is deactivated.");
        }
    }

    @Reference(
             name = "user.realmservice.default", 
             service = org.wso2.carbon.user.core.service.RealmService.class, 
             cardinality = ReferenceCardinality.MANDATORY, 
             policy = ReferencePolicy.DYNAMIC, 
             unbind = "unsetRealmService")
    protected void setRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("Setting the Realm Service.");
        }
        ContextRewriteValveServiceComponentHolder.getInstance().setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("Unsetting the Realm Service.");
        }
        ContextRewriteValveServiceComponentHolder.getInstance().setRealmService(null);
    }

    private void loadPageNotFoundErrorPage() {

        String errorPage = "Page Not Found";
        try {
            Path pageNotFoundHtmlResponse =
                    Paths.get(CarbonUtils.getCarbonHome(), "repository", "resources", "identity", "pages",
                            "page_not_found.html");
            if (!Files.exists(pageNotFoundHtmlResponse) ||
                    !Files.isRegularFile(pageNotFoundHtmlResponse)) {
                if (log.isDebugEnabled()) {
                    log.debug("pageNotFoundHtmlResponse is not present at: " + pageNotFoundHtmlResponse);
                }
            }
            File file = new File(pageNotFoundHtmlResponse.toString());
            errorPage = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.warn(
                    "File page_not_found.html not found. The default content will be used " +
                            "as the error page content.");
        }
        ContextRewriteValveServiceComponentHolder.getInstance().setPageNotFoundErrorPage(errorPage);
    }
}

