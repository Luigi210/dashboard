package com.example.plugins.tutorial;

import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugin.web.ContextProvider;
import com.google.common.collect.Maps;

import java.util.Map;

@Scanned
public class HelloUserContextProvider implements ContextProvider {
    private final JiraAuthenticationContext authenticationContext;

    public HelloUserContextProvider(
            @ComponentImport final JiraAuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    @Override
    public void init(final Map<String, String> params) throws PluginParseException {
    }

    @Override
    public Map<String, Object> getContextMap(final Map<String, Object> context) {
        final Map<String, Object> newContext = Maps.newHashMap(context);
        newContext.put("username", authenticationContext.getLoggedInUser().getDisplayName());

        return newContext;
    }
}